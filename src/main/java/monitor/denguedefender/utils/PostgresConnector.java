/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import java.util.Properties;
import java.sql.*;

/**
 * Classe responsável por se conectar ao bancos de dados.
 * @author victo
 */
public class PostgresConnector {
    public static PostgresConnector postgres = new PostgresConnector();
    private Connection conn;
    
    /**
    * Método responsável por se conectar ao banco.
    * @author victo
    */
    public void connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:15432/denguedefender";
        
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "false");
        
        this.conn = DriverManager.getConnection(url, props);
    }
    
    /**
    * Método responsável por retornar a conexão atual com o banco
    * @author victo
    */
    public Connection getConnection() {
        return this.conn;
    }
}
