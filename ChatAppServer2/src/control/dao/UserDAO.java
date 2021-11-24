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
import model.User;

/**
 *
 * @author Anh Nguyen
 */
public class UserDAO extends IDAO<User> {

    public UserDAO(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean checkLogin(String username, String password) {
        try {
            String query = "select * from `user` where `username` = ? and `password` = ?";
            this.preStatement = this.conn.prepareStatement(query);
            this.preStatement.setString(1, username);
            this.preStatement.setString(2, password);
            ResultSet results = this.preStatement.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public User[] selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(User obj) {
        try {
            String sql = "insert into user(username, password) values (?,?)";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, obj.getUsername());
            this.preStatement.setString(2, obj.getPassword());
            int rowCount = this.preStatement.executeUpdate();
            return rowCount;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int update(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
