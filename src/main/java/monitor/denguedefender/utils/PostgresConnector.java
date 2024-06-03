/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import java.util.Properties;
import java.sql.*;

/**
 *
 * @author victo
 */
public class PostgresConnector {
    public static PostgresConnector postgres = new PostgresConnector();
    private Connection conn;
    
    public void connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/denguedefender";
        
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        
        this.conn = DriverManager.getConnection(url, props);
    }
    
    public Connection getConnection() {
        return this.conn;
    }
}
