/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitor.denguedefender.utils.PostgresConnector;

/**
 * Classe responsável por criar um modelo de usuário para integrar com o banco
 * de dados.
 * 
 * @author victo
 */
public class User {
    private int id;
    private String document;
    private String password;
    private String role;
    
    /**
    * Método responsável por retornar um usuário a partir do documento.
    * 
    * @param document documento do usuário
    */
    public static User getByDocument(String document) {
        try {
            Connection conn = PostgresConnector.postgres.getConnection();
            
            PreparedStatement st = conn.prepareStatement(
                "SELECT id, password, role FROM users WHERE document = ?;"
            );
            st.setString(1, document);
            
            ResultSet rs = st.executeQuery();
            
            rs.next();
            User user = new User();
            user.setId(rs.getInt(1));
            user.setDocument(document);
            user.setPassword(rs.getString(2));
            user.setRole(rs.getString(3));
            
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
    * Método responsável por definir o id do usuário.
    * 
    * @param id id do usuário
    */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
    * Método responsável por retornar o id do usuário.
    */
    public int getId() {
        return this.id;
    }
    
    /**
    * Método responsável por definir o documento do usuário.
    * 
    * @param document documento do usuário
    */
    public void setDocument(String document) {
        this.document = document;
    }
    
    /**
    * Método responsável por retornar o documento do usuário.
    */
    public String getDocument() {
        return this.document;
    }
    
    /**
    * Método responsável por definir a senha do usuário.
    * 
    * @param password senha do usuário
    */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
    * Método responsável por retornar a senha do usuário.
    */
    public String getPassword() {
        return this.password;
    }
    
    /**
    * Método responsável por definir o cargo do usuário.
    * 
    * @param role cargo do usuário
    */
    public void setRole(String role) {
        this.role = role;
    }
    
    /**
    * Método responsável por retornar o cargo do usuário.
    */
    public String getRole() {
        return this.role;
    }
}
