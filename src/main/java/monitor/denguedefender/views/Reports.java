/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import components.FilterBox;
import components.Menu;
import components.MenuButton;
import components.ReportItem;
import components.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 *
 * @author victo
 */
public class Reports extends View {
    private final int selectedCity = 0;
    private Table table;

    public Reports(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
    
    @Override
    public void build() {
        Menu menu = new Menu(sceneManager);
        
        ArrayList<String> items = new ArrayList<>();
        
        items.add("São Bernardo do Campo");
        items.add("São Paulo");
        
        for (int i = 0; i < 20; i++) {
            items.add(String.format("Item %d", i));
        }
        
        FilterBox filters = new FilterBox(sceneManager.getWidth() - 175, 70);
        
        this.table = new Table(sceneManager.getWidth() - 175, sceneManager.getHeight() - 150, 60);
        this.table.relocate(25, 125);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, 5, 1);
        Date date = calendar.getTime();
        
        ArrayList<String> cities = new ArrayList<>();
        cities.add("São Bernardo do Campo");
        cities.add("São Paulo");
        
        ArrayList<String> neighborhoodSBC = new ArrayList<>();
        neighborhoodSBC.add("Paulicéia");
        neighborhoodSBC.add("Rudge Ramos");
        neighborhoodSBC.add("Baeta Neves");
        neighborhoodSBC.add("Ferrazópolis");
        neighborhoodSBC.add("Planalto");
        
        ArrayList<String> neighborhoodSP = new ArrayList<>();
        neighborhoodSP.add("Moema");
        neighborhoodSP.add("Saúde");
        neighborhoodSP.add("Jabaquara");
        neighborhoodSP.add("Pinheiros");
        neighborhoodSP.add("Brooklin");
        
        filters.setCities(cities);
        filters.setNeighborhoods(neighborhoodSBC);
        
        for (int i = 0; i < 100; i++) {
            ReportItem item = new ReportItem(
                (int) Math.round(Math.random() * 5),
                cities.get(0),
                neighborhoodSBC.get((int) Math.round(Math.random() * 4)),
                date
            );
            table.addItem(item);
        }
        
        for (int i = 0; i < 100; i++) {
            ReportItem item = new ReportItem(
                (int) Math.round(Math.random() * 5),
                cities.get(1),
                neighborhoodSP.get((int) Math.round(Math.random() * 4)),
                date
            );
            table.addItem(item);
        }
        
        table.show();
        
        filters.setOnUpdate(e -> {
            if (e.getField().equals("city")) {
                table.applyCityFilter(e.getValue());

                if (e.getValue() == "São Bernardo do Campo") {
                    filters.setNeighborhoods(neighborhoodSBC);
                } else {
                    filters.setNeighborhoods(neighborhoodSP);
                }
            } else if (e.getField().equals("neighborhood")) {
                if (selectedCity == 0) {
                    table.applyNeighborhoodFilter(e.getValue());
                } else {
                    table.applyNeighborhoodFilter(e.getValue());
                }
            }
        });
        
        MenuButton reportsView = new MenuButton("Denunciar", "report.png", 15, 270, 1.0);
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(this.table, filters, menu);
    }
}
