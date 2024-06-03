/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author victo
 */
public class ReportItem extends Pane {
    private final String[] reports = {
        "Possível foco de dengue",
        "Água parada",
        "Pessoas doentes na região",
        "Local abandonado com passível contribuição de proliferação",
        "Falta de saneamento básico",
        "Acúmulo de lixo"
    };
    
    private final int reportType;
    private final String city;
    private final String neighborhood;
    private final Date date;
    
    public ReportItem(int reportType, String city, String neighborhood, Date date) {
        super();
        
        this.reportType = reportType;
        this.city = city;
        this.neighborhood = neighborhood;
        this.date = date;
    }
    
    public void build(int width, int height) {
        Rectangle separator = new Rectangle(width, 2, Color.valueOf("#E9E9E9"));
        separator.relocate(0, 0);
        
        TableField reportWithCity = new TableField((int) (width * 0.55), height, this.reports[this.reportType], false);
        reportWithCity.setSubField(this.city, "location.png");
        
        TableField neighborhoodField = new TableField((int) (width * 0.31), height, this.neighborhood, true);
        neighborhoodField.relocate(width * 0.55, 0);
        
        String dateFormatted = new SimpleDateFormat("dd/MM/yyyy").format(this.date);
        TableField dateField = new TableField((int) (width * 0.14), height, dateFormatted, true);
        dateField.relocate(width * 0.86, 0);
        
        this.getChildren().addAll(separator, reportWithCity, neighborhoodField, dateField);
    }
    
    public String getCity() {
        return this.city;
    }
    
    public String getNeighborhood() {
        return this.neighborhood;
    }
}
