/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;

/**
 *
 * @author Anh Nguyen
 */
public class RoomDAO extends IDAO<Room> {

    public RoomDAO(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkInRoom(String roomname, String username) {
        try {
            String sql = "select * from `room_user` where `roomname` = ? and `username` = ?";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, roomname);
            this.preStatement.setString(2, username);
            ResultSet results = this.preStatement.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public boolean joinRoom(String roomname, String username) {
        try {
            String sql = "insert into `room_user` (`roomname`, `username`) values (?, ?)";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, roomname);
            this.preStatement.setString(2, username);
            int rowCount = this.preStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean createRoom(String roomname, String username) {
        int resultInsert = insert(new Room(roomname));
        if (resultInsert > 0) {
            joinRoom(roomname, username);
            if (resultInsert > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Room[] selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Room selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Room room) {
        try {
            String sql = "insert into `room` (`roomname`) values (?)";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, room.getRoomname());
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int update(Room object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Room object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
