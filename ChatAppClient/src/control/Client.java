package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Action;
import model.ObjectWrapper;
import view.Chat;
import view.Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anh Nguyen
 */
public class Client extends WindowAdapter implements ActionListener {

    private String host;
    private int port;
    private ArrayList<String> listOnlineUsers;
    private ArrayList<String> listGroups;
    private String username;
    private Login loginView;
    private Chat chatView;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Thread thread;

    public Client(String host, int port, Login loginView) {
        this.host = host;
        this.port = port;
        this.loginView = loginView;
        this.chatView = new Chat();
        this.listOnlineUsers = new ArrayList<>();
        this.listGroups = new ArrayList<>();

        this.loginView.addListener(this);
        this.chatView.addButtonListener(this);
        this.chatView.addCloseListener(this);
        this.loginView.setVisible(true);

        if (!connect()) {
            this.loginView.show("connect error");
        }
    }

// routes
    //flow -> click -> send to server -> receive from server -> update ui components
    //flow -> actionPerformed -> handleFuctions -> loop() to get Obj -> call update ui Function();
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Login":
                String username = this.loginView.getUsername();
                String password = this.loginView.getPassword();

                if (login(username, password)) {
                    System.out.println("login success");
                } else {
                    this.loginView.show("username/ password is wrong");
                }
                break;

            case "Send":
                String to = this.chatView.getLbSendTo();
                String message = this.chatView.getTxtAreaSend();

                if (!to.equals("...") && listOnlineUsers.contains(to) && message.length() > 0) {
                    handleSendTextToSingle(to, message);
                }
                break;
            case "Create group":
                String groupname = this.chatView.getGroupname();
                if (groupname.length() > 0) {
                    handleCreateGroup(groupname);
                } else {
                    this.chatView.show("Cannot create group");
                }
                break;
            case "Refresh":
                String groupnameR = this.chatView.getGroupname();
                if (groupnameR.length() > 0) {
                    handleRefreshRoom(groupnameR);
                } else {
                    this.chatView.show("cannot refresh");
                }
                break;

            case "Send Group":
                String sendTo = this.chatView.getLbSendToGroup();
                String messageGroup = this.chatView.getTxtAreaGroup();

                if (!sendTo.equals("...") && this.listGroups.contains(sendTo) && messageGroup.length() > 0) {
                    handleSendTextToGroup(sendTo, messageGroup);
                }
                break;

            case "Send All":
                String messageAll = this.chatView.getTxtAreaAll();
                if (messageAll.length() > 0) {
                    handleSendAll(messageAll);
                }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        ObjectWrapper req = new ObjectWrapper();
        req.sendEnd(Action.END, this.username);
        send(req);
        this.thread.stop();
        System.exit(0);
    }

    private boolean connect() {
        try {
            this.socket = new Socket(this.host, this.port);
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean login(String username, String password) {
        ObjectWrapper req = new ObjectWrapper();
        req.sendLogin(Action.LOGIN, username, password);
        send(req);

        try {
            ObjectWrapper res = (ObjectWrapper) this.ois.readObject();
            System.out.println(res.toString());
            if (res.getText().equals("1")) {
                this.loginView.setVisible(false);
                this.chatView.setTitle(username);
                this.chatView.setVisible(true);
                this.username = username;
                listening();
                return true;
            } else {
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void listening() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                loop();
            }
        };
        this.thread = thread;
        thread.start();
    }

// gửi object đến server
    private void handleUpdateStatus(ObjectWrapper req) {
        if (req.getStatus() == 1) {
            if (!this.listOnlineUsers.contains(1)) {
                this.listOnlineUsers.add(req.getUsernameFrom());
                this.chatView.updateStatus(req.getUsernameFrom(), 1);
            }
        } else if (req.getStatus() == 0) {
            if (this.listOnlineUsers.contains(req.getUsernameFrom())) {
                this.listOnlineUsers.remove(req.getUsernameFrom());
                this.chatView.updateStatus(req.getUsernameFrom(), 0);
            }
        }
    }

    private void handleSendTextToSingle(String usernameTo, String sendText) {
        ObjectWrapper req = new ObjectWrapper();
        req.sendTextToSingle(Action.SEND_MESSAGE, this.username, usernameTo, sendText);
        send(req);
        System.out.println("202: " + req.toString());
        this.chatView.updateSendMessage(usernameTo, sendText);
        this.chatView.clearTxtAreaSend();
    }

    private void handleCreateGroup(String groupname) {
        ObjectWrapper req = new ObjectWrapper();
        req.sendCreateGroup(username, groupname);
        send(req);
    }

    private void handleRefreshRoom(String groupname) {
        ObjectWrapper req = new ObjectWrapper();
        req.sendRefreshGroup(this.username, groupname);
        send(req);
    }

    private void handleSendTextToGroup(String groupName, String message) {
        ObjectWrapper req = new ObjectWrapper();
        req.sendTextToGroup(this.username, groupName, message);
        send(req);
        this.chatView.updateSendGroupMessage(groupName, message);
        this.chatView.clearTxtAreaGroup();
    }

    private void handleSendAll(String message) {
        ObjectWrapper req = new ObjectWrapper();
        req.sendMessageToAll(message, this.username);
        send(req);
        this.chatView.updateSendAllMessage(message);
        this.chatView.clearTxtAreaAll();
    }

    private void send(ObjectWrapper req) {
        try {
            this.oos.writeObject(req);

        } catch (Exception e) {
            System.out.println("cannot send request");
            e.printStackTrace();
        }
    }

    //receive Object from server
    private void loop() {
        try {
            while (true) {
                ObjectWrapper req = (ObjectWrapper) ois.readObject();
                if (req != null) {
                    System.out.println(req.toString());
                    String action = req.getCommand();

                    switch (action) {
                        case Action.UPDATE_STATUS:
                            if (req.getUsernameFrom() != null) {
                                handleUpdateStatus(req);
                            }
                            break;

                        case Action.SEND_MESSAGE:
                            if (req.getUsernameTo() != null) {
                                this.chatView.updateReceiveMessage(req.getUsernameFrom(), req.getText());
                            } else if (req.getGroupname() != null) {
                                this.chatView.updateReceiveGroupMessage(req.getUsernameFrom(), req.getGroupname(), req.getText());
                            }
                            break;
                        case Action.CREATE_GROUP:
                            if (req.getGroupname() != null) {
                                String groupname = req.getGroupname();
                                if (!this.listGroups.contains(groupname)) {
                                    this.listGroups.add(groupname);
                                    this.chatView.updateGroupStatus(groupname);
                                } else {
                                    this.chatView.show("some error");
                                }
                            }
                        case Action.JOIN_GROUP:
                            if (req.getGroupname() != null) {
                                String groupname = req.getGroupname();
                                if (!this.listGroups.contains(groupname)) {
                                    this.listGroups.add(groupname);
                                    this.chatView.updateGroupStatus(groupname);
                                } else {
                                    this.chatView.show("join group error");
                                }
                            } else {
                                this.chatView.show("join group error, group is not exist");
                            }
                        case Action.REFRESH_GROUP:
                            if (req.getGroupname() != null) {
                                String groupname = req.getGroupname();
                                if (!this.listGroups.contains(groupname)) {
                                    this.listGroups.add(groupname);
                                    this.chatView.updateGroupStatus(groupname);
                                } else {
                                    this.chatView.show("you has already in this group");
                                }
                            } else {
                                this.chatView.show("you need join group first");
                            }
                            break;
                        case Action.SEND_ALL:
                            if (req.getUsernameFrom() != null) {
                                this.chatView.updateReceiveAllMessage(req.getUsernameFrom(), req.getText());
                            }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                this.socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
