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
 * Classe responsável por criar um elemento de menu.
 * @author victo
 */
public class Menu extends Pane {
    /**
    * Construtor da classe Menu responsável por montar os elementos.
    * 
    * @param sceneManager instanciamento do gerenciador de cenas
    **/
    public Menu(SceneManager sceneManager) {
        super();
        
        this.setPrefSize(100, sceneManager.getHeight() - 50);
        this.relocate(sceneManager.getWidth() - 125, 25);
        
        Rectangle background = new Rectangle(100, sceneManager.getHeight() - 50, Color.WHITE);
        background.setArcHeight(20.0d);
        background.setArcWidth(20.0d);
        background.setEffect(new DropShadow(10, Color.GRAY));
        
        MenuButton dashboardView = new MenuButton("Página inicial", "dashboard.png", 15, 15, 1.0);
        MenuButton mapView = new MenuButton("Mapa de incidencias", "map.png", 15, 100, 1.0);
        MenuButton reportsView = new MenuButton("Denúncias", "report.png", 15, 185, 1.0);
        
        dashboardView.setOnAction(e -> sceneManager.show("home"));
        mapView.setOnAction(e -> sceneManager.show("map"));
        reportsView.setOnAction(e -> sceneManager.show("reports"));
        
        this.getChildren().addAll(background, dashboardView, mapView, reportsView);
    }
}
