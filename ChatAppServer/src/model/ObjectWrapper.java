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

    private String action;
    private String usernameFrom;
    private String usernameTo;
    private String text;
    private String groupname;
    private String username;
    private String password;
    private int status;

    public ObjectWrapper() {
    }
    
    public void sendMessageToAll(String message, String usernameFrom) {
        this.usernameFrom = usernameFrom;
        this.action = Action.SEND_ALL;
        this.text = message;
    }

    public void sendRefreshGroup(String usernameFrom, String groupname) {
        this.action = Action.REFRESH_GROUP;
        this.usernameFrom = usernameFrom;
        this.groupname = groupname;
    }

    public void sendTextToGroup(String usernameFrom, String groupname, String message) {
        this.action = Action.SEND_MESSAGE;
        this.usernameFrom = usernameFrom;
        this.groupname = groupname;
        this.text = message;
    }

    public void sendTextToSingle(String command, String usernameFrom, String usernameTo, String text) {
        this.action = command;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.text = text;
    }

    public void sendTextToRoom(String command, String usernameFrom, String roomname, String text) {
        this.action = command;
        this.usernameFrom = usernameFrom;
        this.groupname = roomname;
        this.text = text;
    }

    public void sendLogin(String command, String username, String password) {
        this.action = command;
        this.username = username;
        this.password = password;
    }

    public void sendEnd(String command, String usernameFrom) {
        this.action = command;
        this.usernameFrom = usernameFrom;
    }

    public void sendUpdateStatus(String command, int status, String usernameFrom) {
        // status: 0 is off, 1 is on
        // usernameFrom: user who is on o r off
        this.action = command;
        this.status = status;
        this.usernameFrom = usernameFrom;
    }

    public void sendJoin(String command, String usernameFrom, String roomname) {
        this.action = command;
        this.usernameFrom = usernameFrom;
        this.groupname = roomname;
    }

    public void sendJoinRoom(String command, String usernameFrom, String roomname) {
        this.action = command;
        this.usernameFrom = usernameFrom;
        this.groupname = roomname;
    }

    public void sendFindRoom(String command, String usernameFrom, String roomname) {
        this.action = command;
        this.usernameFrom = usernameFrom;
        this.groupname = roomname;
    }

    public void sendCreateGroup(String usernameFrom, String roomname) {
        this.action = Action.CREATE_GROUP;
        this.usernameFrom = usernameFrom;
        this.groupname = roomname;
    }

    public String getCommand() {
        return action;
    }

    public void setCommand(String command) {
        this.action = command;
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

    public String getGroupname() {
        return groupname;
    }

    public void setRoomname(String roomname) {
        this.groupname = roomname;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Request{" + "command=" + action + ", usernameFrom=" + usernameFrom + ", usernameTo=" + usernameTo + ", text=" + text + ", roomname=" + groupname + ", username=" + username + ", password=" + password + ", status=" + status + '}';
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
