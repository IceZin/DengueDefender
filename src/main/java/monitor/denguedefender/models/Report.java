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
 * Classe responsável por criar um modelo de denúncia para integrar com o banco
 * de dados.
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
    
    /**
    * Método responsável por criar uma instancia de denúncia a partir do resultado
    * de busca no banco de dados.
    * 
    * @param rs resultado da busca no banco de dados
    */
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
    
    /**
    * Método responsável por salvar a instancia atual da denúncia.
    */
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
    
    /**
    * Método responsável por retornar todas as denúncias a partir de uma cidade.
    * 
    * @param searchCity nome da cidade
    */
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
    
    /**
    * Método responsável por retornar todas as denúncias.
    */
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
    
    /**
    * Método responsável por retornar todas as cidades distintas das denúncias.
    */
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
    
    /**
    * Método responsável por retornar todos os bairros disintos de uma cidade.
    * 
    * @param searchCity nome da cidade
    */
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
    
    /**
    * Método responsável por definir o id de uma denúncia.
    * 
    * @param id id da denúncia
    */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
    * Método responsável por retornar o id de uma denúncia.
    */
    public int getId() {
        return this.id;
    }
    
    /**
    * Método responsável por definir o tipo de uma denúncia.
    * 
    * @param type tipo da denúncia
    */
    public void setType(int type) {
        this.type = type;
    }
    
    /**
    * Método responsável por retornar o tipo de uma denúncia.
    */
    public int getType() {
        return this.type;
    }
    
    /**
    * Método responsável por definir a cidade de uma denúncia.
    * 
    * @param city cidade da denúncia
    */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
    * Método responsável por retornar a cidade de uma denúncia.
    */
    public String getCity() {
        return this.city;
    }
    
    /**
    * Método responsável por definir o bairro de uma denúncia.
    * 
    * @param neighborhood bairro da denúncia
    */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    /**
    * Método responsável por retornar o bairro de uma denúncia.
    */
    public String getNeighborhood() {
        return this.neighborhood;
    }
    
    /**
    * Método responsável por definir o endereço de uma denúncia.
    * 
    * @param address endereço da denúncia
    */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
    * Método responsável por retornar o endereço de uma denúncia.
    */
    public String getAddress() {
        return this.address;
    }
    
    /**
    * Método responsável por definir a data de uma denúncia.
    * 
    * @param date data da denúncia
    */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
    * Método responsável por retornar a data de uma denúncia.
    */
    public Date getDate() {
        return this.date;
    }
    
    /**
    * Método responsável por definir a latitude de uma denúncia.
    * 
    * @param lat latitude da denúncia
    */
    public void setLatitude(double lat) {
        this.lat = lat;
    }
    
    /**
    * Método responsável por retornar a latitude de uma denúncia.
    */
    public double getLatitude() {
        return this.lat;
    }
    
    /**
    * Método responsável por definir a longitude de uma denúncia.
    * 
    * @param lng longitude da denúncia
    */
    public void setLongitude(double lng) {
        this.lng = lng;
    }
    
    /**
    * Método responsável por retornar a longitude de uma denúncia.
    */
    public double getLongitude() {
        return this.lng;
    }
    
    /**
    * Método responsável por definir o id do usuário de uma denúncia.
    * 
    * @param userId id do usuário
    */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
    * Método responsável por retornar o id do usuário de uma denúncia.
    */
    public int getUserId() {
        return this.userId;
    }
}
