/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import events.FieldUpdateEvent;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import monitor.denguedefender.utils.Filter;
import monitor.denguedefender.utils.FilterRule;

/**
 *
 * @author victo
 */
public class FilterBox extends Pane {
    private EventHandler onCityUpdate;
    private ArrayList<String> cities = new ArrayList<>();
    private ArrayList<String> neighborhoods = new ArrayList<>();
    
    private final DropDown cityDp;
    private final DropDown neighborhoodDp;
    
    public FilterBox(int width, int height) {
        super();
        
        this.setPrefSize(width, height);
        this.relocate(25, 25);
        
        Rectangle background = new Rectangle(width, height, Color.WHITE);
        background.setArcHeight(20.0d);
        background.setArcWidth(20.0d);
        background.setEffect(new DropShadow(10, Color.GRAY));
        
        this.cityDp = new DropDown("city", 250, 30, "CIDADE");
        this.cityDp.relocate(25, 30);
        
        this.cityDp.setOnUpdate(e -> this.triggerOnUpdate(e));
        
        this.neighborhoodDp = new DropDown("neighborhood", 250, 30, "BAIRRO");
        this.neighborhoodDp.relocate(300, 30);
        
        this.neighborhoodDp.setOnUpdate(e -> this.triggerOnUpdate(e));
        
        //DropDown dp = new DropDown(items, "date", 250, 40, "DATA");
        
        this.getChildren().addAll(background, cityDp, neighborhoodDp);
    }
    
    public void setOnUpdate(EventHandler<FieldUpdateEvent> e) {
        this.onCityUpdate = e;
    }
    
    private void triggerOnUpdate(FieldUpdateEvent e) {
        if (this.onCityUpdate != null) {
            this.onCityUpdate.handle(e);
        }
    }
    
    public void setCities(ArrayList<String> items) {
        this.cities = (ArrayList<String>) items.clone();
        
        this.cityDp.setItems(this.cities);
    }
    
    public void setNeighborhoods(ArrayList<String> items) {
        this.neighborhoods = (ArrayList<String>) items.clone();
        this.neighborhoods.add(0, "Todos");
        
        this.neighborhoodDp.setItems(this.neighborhoods);
    }
}
