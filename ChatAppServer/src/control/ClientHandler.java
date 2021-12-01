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
import model.User;

/**
 *
 * @author Anh Nguyen
 */
public class ClientHandler extends Thread {

    private String username = null;
    private HashSet<String> setGroupnames = new HashSet<>();

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSocket;

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

    //flow get obj -> logic doe -> send obj()
    //flow handleClientSocket() while -> handleFunction();
    private void handleClientSocket() {
        try {
            this.ois = new ObjectInputStream(this.clientSocket.getInputStream());
            this.oos = new ObjectOutputStream(this.clientSocket.getOutputStream());

            Object obj;
            while ((obj = ois.readObject()) != null) {
                ObjectWrapper req = (ObjectWrapper) obj;
                String action = req.getCommand();

                System.out.println("from: " + this.clientSocket + ": " + req.toString());

                switch (action) {
                    case Action.END:
                        handleLogout();
                        break;
                    case Action.LOGIN:
                        handleLogin(req);
                        break;
                    case Action.SEND_MESSAGE:
                        handleSendText(req);
                        break;
                    case Action.CREATE_GROUP:
                        handleCreateGroup(req);
                        break;
                    case Action.REFRESH_GROUP:
                        handleRefreshGroup(req);
                        break;
                    case Action.SEND_ALL:
                        handleSendAll(req);
                        break;
//                    case Action.REGISTER:
//                        handleResgister(req);
//                        break;
                    default:
                        ObjectWrapper res = new ObjectWrapper();
                        res.sendTextToSingle(Action.SEND_MESSAGE, "server", "you", "error");
                        this.oos.writeObject(res);
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RoutesHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// processs and send obj to client
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

        } else if (req.getGroupname() != null) {
            String groupname = req.getGroupname();
            for (var client : listClients) {
                if (client.isInRoom(groupname) && !client.getUsername().equalsIgnoreCase(req.getUsernameFrom())) {
                    System.out.println("163 - from: " + this.clientSocket + ": " + req.toString());
                    client.send(req);
                }
            }
        }
    }

    // flow:
    private void handleCreateGroup(ObjectWrapper req) throws IOException {
        String groupname = req.getGroupname();
        String usernameFrom = req.getUsernameFrom();
        ObjectWrapper res = new ObjectWrapper();

        try {
            int result = this.socketHandler.getGroupDAO().createGroup(groupname, usernameFrom);

            if (result > 0) {
                if (!this.setGroupnames.contains(groupname)) {
                    this.setGroupnames.add(groupname);
                    res.sendFindRoom(Action.JOIN_GROUP, "server", groupname);
                    send(res);
                }
            } else {
                System.out.println("180: create error");
                res.sendFindRoom(Action.JOIN_GROUP, "server", null);
            }
        } catch (Exception e) {
            System.out.println("cannot create");
            e.printStackTrace();
        }
    }

    private void handleRefreshGroup(ObjectWrapper req) throws IOException {
        String groupname = req.getGroupname();
        ObjectWrapper res = new ObjectWrapper();

        if (!this.setGroupnames.contains(groupname)) {
            this.setGroupnames.add(groupname);
            res.sendRefreshGroup("server", groupname);
            send(res);
        }

//        int result = this.socketHandler.getGroupDAO().isInGroup(groupname, usernameFrom);
//        if (result > 0) {
//        if (!this.setGroupnames.contains(groupname)) {
//            this.setGroupnames.add(groupname);
//            res.sendRefreshGroup("server", groupname);
//            send(res);
//        }
//        } else {
//            res.sendRefreshGroup("server", null);
//            send(res);
//        }
    }

    private void handleSendAll(ObjectWrapper req) throws IOException {
        ArrayList<ClientHandler> listClients = this.socketHandler.getListClients();

        for (var client : listClients) {
            if (!client.getUsername().equals(req.getUsernameFrom())) {
                client.send(req);
            }
        }
    }

    private void handleResgister(ObjectWrapper req) throws IOException {
        String username = req.getUsername();
        String password = req.getPassword();
        ObjectWrapper res = new ObjectWrapper();

        boolean checkUniqueUsername = this.socketHandler.getUserDAO().checkUniqueUsername(username);

        if (checkUniqueUsername == false) {
            User user = new User(username, password);
            int insertUser = this.socketHandler.getUserDAO().insert(user);
            if (insertUser > 0) {
                res.Register(username, password, "200");
                send(res);
            } else {
                System.out.println("245: cannot insert user");
                res.Register(username, password, "401");
                send(res);
            }
        } else {
            System.out.println("252: trung username");
            res.Register(username, username, "402");
            send(res);
        }
    }

    //internal function
    public String getUsername() {
        return this.username;
    }

    public ArrayList<ClientHandler> getListClients() {
        return this.socketHandler.getListClients();
    }

    public boolean isInRoom(String groupname) {
        return this.setGroupnames.contains(groupname);
    }

    private void send(ObjectWrapper request) throws IOException {
        if (this.username != null) {
            this.oos.writeObject(request);
        } else {
            System.out.println(request);
            System.out.println("286: send() loi");
        }
    }
}
