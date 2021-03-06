/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.dao.GroupDAO;
import control.dao.UserDAO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import view.ServerView;

/**
 *
 * @author Anh Nguyen
 */
public class SocketHandler extends Thread {

    private int serverPort;
    private ArrayList<ClientHandler> listClients = new ArrayList<>();

    private UserDAO userDAO;
    private GroupDAO groupDAO;

    private ServerView serverView;

    public SocketHandler(int serverPort, ServerView serverView) {
        this.serverPort = serverPort;
        this.serverView = serverView;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public GroupDAO getGroupDAO() {
        return groupDAO;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            this.userDAO = new UserDAO(MySQLConn.getMySQLConnection());
            this.groupDAO = new GroupDAO(MySQLConn.getMySQLConnection());
            System.out.println("database connected");
            serverView.disableStartButton();
            System.out.println("server running");
            while (true) {
                System.out.println("waiting client");
                Socket clientSocket = serverSocket.accept();
                System.out.println("accept client: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(this, clientSocket);
                listClients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            serverView.alert("cannot sart server");
        }
    }

    public ArrayList<ClientHandler> getListClients() {
        return this.listClients;
    }

    public void removeClient(ClientHandler client) {
        this.listClients.remove(client);
    }
}
