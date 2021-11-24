/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anh Nguyen
 */
public class MySQLConn {

    public static Connection getMySQLConnection() {
        try {
            String hostName = "localhost";

            String dbName = "chat-java";
            String userName = "root";
            String password = "root";
            return getMySQLConnection(hostName, dbName, userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
            String userName, String password) throws SQLException,
            ClassNotFoundException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?zeroDateTimeBehavior=convertToNull";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
