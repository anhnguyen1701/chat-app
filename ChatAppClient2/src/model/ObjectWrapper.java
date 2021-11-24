/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Anh Nguyen
 */
public class ObjectWrapper implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private String command;
    private String usernameFrom;
    private String usernameTo;
    private String text;
    private String roomname;
    private String username;
    private String password;
    private int status;

    public ObjectWrapper() {
    }

    public void sendTextToSingle(String command, String usernameFrom, String usernameTo, String text) {
        this.command = command;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.text = text;
    }

    public void sendTextToRoom(String command, String usernameFrom, String roomname, String text) {
        this.command = command;
        this.usernameFrom = usernameFrom;
        this.roomname = roomname;
        this.text = text;
    }

    public void sendLogin(String command, String username, String password) {
        this.command = command;
        this.username = username;
        this.password = password;
    }

    public void sendEnd(String command, String usernameFrom) {
        this.command = command;
        this.usernameFrom = usernameFrom;
    }

    public void sendUpdateStatus(String command, int status, String usernameFrom) {
        // status: 0 is off, 1 is on
        // usernameFrom: user who is on or off
        this.command = command;
        this.status = status;
        this.usernameFrom = usernameFrom;
    }

    public void sendJoin(String command, String usernameFrom, String roomname) {
        this.command = command;
        this.usernameFrom = usernameFrom;
        this.roomname = roomname;
    }

    public void sendJoinRoom(String command, String usernameFrom, String roomname) {
        this.command = command;
        this.usernameFrom = usernameFrom;
        this.roomname = roomname;
    }

    public void sendFindRoom(String command, String usernameFrom, String roomname) {
        this.command = command;
        this.usernameFrom = usernameFrom;
        this.roomname = roomname;
    }

    public void sendCreateRoom(String command, String usernameFrom, String roomname) {
        this.command = command;
        this.usernameFrom = usernameFrom;
        this.roomname = roomname;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Request{" + "command=" + command + ", usernameFrom=" + usernameFrom + ", usernameTo=" + usernameTo + ", text=" + text + ", roomname=" + roomname + ", username=" + username + ", password=" + password + ", status=" + status + '}';
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
