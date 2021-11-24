/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ObjectWrapper;
import model.Action;

/**
 *
 * @author Anh Nguyen
 */
public class ClientHandler extends Thread {

    private String username = null;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSocket;
    private HashSet<String> setRoomnames = new HashSet<>();
    private SocketHandler socketHandler;

    public ClientHandler() {
    }

    public ClientHandler(SocketHandler socketHandler, Socket clientSocket) {
        this.socketHandler = socketHandler;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        handleClientSocket();
    }

    private void handleClientSocket() {
        try {
            this.ois = new ObjectInputStream(this.clientSocket.getInputStream());
            this.oos = new ObjectOutputStream(this.clientSocket.getOutputStream());

            Object obj;
            while ((obj = ois.readObject()) != null) {
                ObjectWrapper req = (ObjectWrapper) obj;
                String command = req.getCommand();

                System.out.println("from: " + this.clientSocket + ": " + req.toString());
                if (command.equalsIgnoreCase(Action.END)) {
                    handleLogout();
                    break;
                } else if (command.equalsIgnoreCase(Action.LOGIN)) {
                    handleLogin(req);
                } else if (command.equalsIgnoreCase(Action.SEND_MESSAGE)) {
                    handleSendText(req);
                } else {
                    ObjectWrapper res = new ObjectWrapper();
                    res.sendTextToSingle(Action.SEND_MESSAGE, "server", "you", "error");
                    this.oos.writeObject(res);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RoutesHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleLogin(ObjectWrapper req) throws IOException {
        String username = req.getUsername();
        String password = req.getPassword();
        ObjectWrapper res = new ObjectWrapper();

        if (this.socketHandler.getUserDAO().checkLogin(username, password)) {
            res = new ObjectWrapper();
            res.sendTextToSingle(Action.SEND_MESSAGE, "server", "you", "1");

            this.oos.writeObject(res);

            this.username = username;
            System.out.println(username + " login success");

            ArrayList<ClientHandler> listClients = this.socketHandler.getListClients();

            // get other client online
            for (var client : listClients) {
                if (!this.username.equals(client.getUsername())) {
                    res = new ObjectWrapper();
                    res.sendUpdateStatus(Action.UPDATE_STATUS, 1, client.getUsername());
                    this.send(res);
                }
            }

            // send other online users current user's status
            for (var client : listClients) {
                if (!this.username.equals(client.getUsername())) {
                    res = new ObjectWrapper();
                    res.sendUpdateStatus(Action.UPDATE_STATUS, 1, this.username);
                    client.send(res);
                }
            }
        } else {
            res = new ObjectWrapper();
            res.sendTextToSingle(Action.SEND_MESSAGE, "server", "you", "0");
            this.oos.writeObject(res);
        }
    }

    private void handleLogout() throws IOException {
        this.socketHandler.removeClient(this);
        ArrayList<ClientHandler> listClients = this.socketHandler.getListClients();
        ObjectWrapper res;

        for (var client : listClients) {
            if (!this.username.equals(client.getUsername())) {
                res = new ObjectWrapper();
                res.sendUpdateStatus(Action.UPDATE_STATUS, 0, this.username);
                client.send(res);
            }
        }
        this.clientSocket.close();
    }

    private void handleSendText(ObjectWrapper req) throws IOException {
        ArrayList<ClientHandler> listClients = this.socketHandler.getListClients();
        if (req.getUsernameTo() != null) {
            String usernameTo = req.getUsernameTo();
            for (var client : listClients) {
                if (usernameTo.equalsIgnoreCase(client.getUsername())) {
                    System.out.println("from: " + this.clientSocket + ": " + req.toString());
                    client.send(req);
                }
            }
        } else if (req.getRoomname() != null) {
            String roomname = req.getRoomname();
            for (var client : listClients) {
                if (client.isMemberOfRoom(roomname) && !client.getUsername().equalsIgnoreCase(req.getUsernameFrom())) {
                    System.out.println("from: " + this.clientSocket + ": " + req.toString());
                    client.send(req);
                    System.out.println("153: " + req.toString());
                }
            }
        }
    }

    //niternal function
    public String getUsername() {
        return this.username;
    }

    private void send(ObjectWrapper request) throws IOException {
        if (this.username != null) {
            this.oos.writeObject(request);
        }
    }

    public ArrayList<ClientHandler> getListClients() {
        return this.socketHandler.getListClients();
    }

    public boolean isMemberOfRoom(String roomname) {
        return this.setRoomnames.contains(roomname);
    }
}
