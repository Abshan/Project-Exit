/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DatabaseConnection {
    
    public Connection getConnection() {
        String username = "root";
        String password = "root";

        String jdbcUrl = "jdbc:mysql://35.240.248.82:3306/ProjectExit_DB";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            return con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Connection to the internet has been terminated!");
            return null;
        }
    }
    
}
