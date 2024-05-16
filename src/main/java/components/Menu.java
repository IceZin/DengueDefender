/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import monitor.denguedefender.utils.SceneManager;

/**
 *
 * @author victo
 */
public class Menu extends Pane {
    public Menu(SceneManager sceneManager) {
        super();
        
        this.setPrefSize(100, 550);
        this.relocate(800, 25);
        
        Rectangle background = new Rectangle(100, 550, Color.WHITE);
        background.setArcHeight(20.0d);
        background.setArcWidth(20.0d);
        background.setEffect(new DropShadow(10, Color.GRAY));
        
        MenuButton mapView = new MenuButton("Mapa de incidencias", "map.png", 15, 15);
        MenuButton calendarView = new MenuButton("Calendário de eventos", "calendar.png", 15, 100);
        MenuButton reportsView = new MenuButton("Alertas", "report.png", 15, 185);
        
        mapView.setOnAction(e -> sceneManager.show("map"));
        calendarView.setOnAction(e -> sceneManager.show("calendar"));
        reportsView.setOnAction(e -> sceneManager.show("reports"));
        
        this.getChildren().addAll(background, mapView, calendarView, reportsView);
    }
}
