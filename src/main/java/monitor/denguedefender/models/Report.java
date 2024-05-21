/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitor.denguedefender.utils.PostgresConnector;
/**
 *
 * @author victo
 */
public class Report {
    private int id;
    private int type;
    private String city;
    private String neighborhood;
    private String address;
    private Date date;
    private int userId;
    
    private Report loadFromResultSet(ResultSet rs) throws SQLException {
        Report report = new Report();
        
        report.setId(rs.getInt(1));
        report.setType(rs.getInt(2));
        report.setCity(rs.getString(3));
        report.setNeighborhood(rs.getString(4));
        report.setAddress(rs.getString(5));
        report.setDate(rs.getDate(6));
        report.setUserId(rs.getInt(7));
        
        return report;
    }
    
    public ArrayList<Report> selectByCity(String searchCity) {
        ArrayList<Report> reports = new ArrayList<>();
        
        try {
            Connection conn = PostgresConnector.postgres.getConnection();
            
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM reports WHERE city = ?;"
            );
            st.setString(1, searchCity);
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                reports.add(this.loadFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reports;
    }
    
    public static ArrayList<String> getDistinctCities() {
        ArrayList<String> cities = new ArrayList<>();
        
        try {
            Connection conn = PostgresConnector.postgres.getConnection();
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT DISTINCT city FROM reports;");
            
            while (rs.next()) {
                cities.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cities;
    }
    
    public static ArrayList<String> getDistinctNeighborhoods(String searchCity) {
        ArrayList<String> neighborhoods = new ArrayList<>();
        
        try {
            Connection conn = PostgresConnector.postgres.getConnection();
            
            PreparedStatement st = conn.prepareStatement(
                "SELECT DISTINCT neighborhood FROM reports WHERE city = ?;"
            );
            st.setString(1, searchCity);
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                neighborhoods.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return neighborhoods;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    public String getNeighborhood() {
        return this.neighborhood;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return this.userId;
    }
}
