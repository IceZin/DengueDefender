/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import components.FilterBox;
import components.Menu;
import components.ReportItem;
import components.Table;
import java.util.ArrayList;
import monitor.denguedefender.models.Report;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 *
 * @author victo
 */
public class Reports extends View {
    private Table table;
    private FilterBox filters;

    public Reports(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
    
    @Override
    public void build() {
        Menu menu = new Menu(sceneManager);
        
        filters = new FilterBox(sceneManager.getWidth() - 175, 70);
        
        this.table = new Table(sceneManager.getWidth() - 175, sceneManager.getHeight() - 150, 60);
        this.table.relocate(25, 125);
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(this.table, filters, menu);
    }
    
    @Override
    public void load() {
        table.clearItems();
        
        for (Report report : Report.selectAll()) {
            ReportItem item = new ReportItem(
                report.getType(),
                report.getCity(),
                report.getNeighborhood(),
                report.getDate()
            );
            
            table.addItem(item);
        }
        
        ArrayList<String> cities = Report.getDistinctCities();
        ArrayList<String> neighborhoods = Report.getDistinctNeighborhoods(cities.get(0));
        
        filters.setCities(cities);
        filters.setNeighborhoods(neighborhoods);
        
        table.show();
        
        filters.setOnUpdate(e -> {
            if (e.getField().equals("city")) {
                table.applyCityFilter(e.getValue());
                
                ArrayList<String> neighborhoodsSearch = Report.getDistinctNeighborhoods(e.getValue());
                filters.setNeighborhoods(neighborhoodsSearch);
            } else if (e.getField().equals("neighborhood")) {
                table.applyNeighborhoodFilter(e.getValue());
            }
        });
    }
}
