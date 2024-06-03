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
import monitor.denguedefender.utils.SessionManager;
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
    private double lat;
    private double lng;
    private int userId;
    
    private static Report loadFromResultSet(ResultSet rs) throws SQLException {
        Report report = new Report();
        
        report.setId(rs.getInt(1));
        report.setType(rs.getInt(2));
        report.setCity(rs.getString(3));
        report.setNeighborhood(rs.getString(4));
        report.setAddress(rs.getString(5));
        report.setLatitude(rs.getDouble(7));
        report.setLongitude(rs.getDouble(8));
        report.setUserId(rs.getInt(9));
        report.setDate(rs.getDate(10));
        
        return report;
    }
    
    public void save() {
        System.out.println("Saving report");
        
        System.out.println(SessionManager.session.getUser().getId());
        System.out.println(this.id);
        
        if (this.id == 0 && SessionManager.session.getUser() != null) {
            try {
                Connection conn = PostgresConnector.postgres.getConnection();
                
                this.date = new Date();

                PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO reports (type, city, neighborhood, latitude, longitude, user_id, date) values (?, ?, ?, ?, ?, ?, ?);"
                );
                st.setInt(1, this.type);
                st.setString(2, this.city);
                st.setString(3, this.neighborhood);
                st.setDouble(4, this.lat);
                st.setDouble(5, this.lng);
                st.setInt(6, SessionManager.session.getUser().getId());
                st.setDate(7, new java.sql.Date(this.date.getTime()));

                st.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                reports.add(Report.loadFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reports;
    }
    
    public static ArrayList<Report> selectAll() {
        ArrayList<Report> reports = new ArrayList<>();
        
        try {
            Connection conn = PostgresConnector.postgres.getConnection();
            
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM reports;"
            );
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                reports.add(Report.loadFromResultSet(rs));
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
                String city = rs.getString(1);
                cities.add(city);
                System.out.println(city);
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
    
    public void setLatitude(double lat) {
        this.lat = lat;
    }
    
    public double getLatitude() {
        return this.lat;
    }
    
    public void setLongitude(double lng) {
        this.lng = lng;
    }
    
    public double getLongitude() {
        return this.lng;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return this.userId;
    }
}
