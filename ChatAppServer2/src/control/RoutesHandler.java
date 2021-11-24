/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ServerView;

/**
 *
 * @author Anh Nguyen
 */
public class RoutesHandler implements ActionListener {

    private ServerView serverView;
    private int serverPort;

    public RoutesHandler() {
    }

    public RoutesHandler(ServerView serverView) {
        this.serverView = serverView;
        this.serverView.addButtonListener(this);
        this.serverView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start":
                try {
                this.serverPort = Integer.parseInt(this.serverView.getPortText());
                SocketHandler server = new SocketHandler(this.serverPort, this.serverView);
                server.start();

            } catch (NumberFormatException ex) {
                this.serverView.alert("Port must be a number.");
            }
            break;

            case "End":
                System.exit(0);
                break;
            default:
        }
    }
}
