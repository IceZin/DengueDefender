/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author victo
 */
public class Menu extends Pane {
    public Menu() {
        super();
        
        this.setPrefSize(100, 550);
        this.relocate(800, 25);
        
        Rectangle background = new Rectangle(100, 550, Color.WHITE);
        background.setArcHeight(20.0d);
        background.setArcWidth(20.0d);
        background.setEffect(new DropShadow(10, Color.GRAY));
        
        Image map = new Image("map.png");
        ImageView mapIcon = new ImageView(map);
        
        mapIcon.setFitHeight(35);
        mapIcon.setFitWidth(35);
        
        Button mapView = new Button();
        mapView.setPrefSize(70, 70);
        mapView.relocate(15, 15);
        mapView.setGraphic(mapIcon);
        mapView.setStyle(
            "-fx-background-radius: 10; -fx-background-color: #1351B4; -fx-cursor: hand;"
        );
        
        Image calendar = new Image("calendar.png");
        ImageView calendarIcon = new ImageView(calendar);
        
        calendarIcon.setFitHeight(35);
        calendarIcon.setFitWidth(35);
        
        Button calendarView = new Button();
        calendarView.setPrefSize(70, 70);
        calendarView.relocate(15, 100);
        calendarView.setGraphic(calendarIcon);
        calendarView.setStyle(
            "-fx-background-radius: 10; -fx-background-color: #1351B4; -fx-cursor: hand;"
        );
        
        this.getChildren().addAll(background, mapView, calendarView);
    }
}
