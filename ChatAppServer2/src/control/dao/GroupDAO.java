/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Group;

/**
 *
 * @author Anh Nguyen
 */
public class GroupDAO extends IDAO<Group> {

    public GroupDAO(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int createGroup(String groupname, String username) {
        int resInsert = insert(new Group(groupname));
        if (resInsert > 0) {
            int resJoin = joinGroup(groupname, username);
            if (resJoin > 0) {
                return 1;
            }
        }
        return 0;
    }

    public int joinGroup(String groupname, String username) {
        try {
            String query = "insert into `group_user`(groupname, username) values (?, ?)";
            this.preStatement = this.conn.prepareStatement(query);
            this.preStatement.setString(1, groupname);
            this.preStatement.setString(2, username);
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(GroupDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int isInGroup(String groupname, String username) {
        try {
            String query = "select * from `group_user` where `groupname` = ? and `username` = ?";
            this.preStatement = this.conn.prepareStatement(query);
            this.preStatement.setString(1, groupname);
            this.preStatement.setString(2, username);
            rs = this.preStatement.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public ArrayList<Group> selectAll() {
        String query = "select * from `group`";
        ArrayList<Group> listGroups = new ArrayList<>();
        try {
            this.preStatement = this.conn.prepareStatement(query);
            rs = this.preStatement.executeQuery();
            while (rs.next()) {
                Group g = new Group(rs.getString("groupname"));
                listGroups.add(g);
            }
            return listGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Group object) {
        try {
            String query = "insert into `group`(groupname) values (?)";
            this.preStatement = this.conn.prepareStatement(query);
            this.preStatement.setString(1, object.getGroupname());
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(GroupDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int update(Group object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Group object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
