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
 *
 * @author victo
 */
public class User {
    private int id;
    private String document;
    private String password;
    private String role;
    
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
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setDocument(String document) {
        this.document = document;
    }
    
    public String getDocument() {
        return this.document;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return this.role;
    }
}
