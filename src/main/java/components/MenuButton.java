/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author victo
 */
public class MenuButton extends Pane {
    private final StackPane placeholder = new StackPane();
    private final Button button = new Button();
    
    public MenuButton(String name, String imagePath, int x, int y) {
        super();
        
        this.relocate(x, y);
        
        Image image = new Image(imagePath);
        ImageView icon = new ImageView(image);
        
        icon.setFitHeight(35);
        icon.setFitWidth(35);
        
        this.button.setPrefSize(70, 70);
        this.button.setGraphic(icon);
        this.button.setStyle(
            "-fx-background-radius: 10; -fx-background-color: #1351B4; -fx-cursor: hand;"
        );
        
        Rectangle placeholderBackground = new Rectangle(200, 30, Color.valueOf("#1351B4"));
        placeholderBackground.setArcHeight(10.0d);
        placeholderBackground.setArcWidth(10.0d);
        
        Text placeholderText = new Text(0, 0, name);
        placeholderText.setStyle("-fx-fill: white; -fx-font-size: 14px; -fx-font-weight: 700;");
        
        this.placeholder.relocate(-220, 20);
        this.placeholder.getChildren().addAll(placeholderBackground, placeholderText);
        
        this.placeholder.setVisible(false);
        
        this.getChildren().addAll(button, placeholder);
        
        this.setOnMouseEntered(e -> this.handleMouseEnter());
        this.setOnMouseExited(e -> this.handleMouseExit());
    }
    
    private void handleMouseEnter() {
        this.placeholder.setVisible(true);
    }
    
    private void handleMouseExit() {
        this.placeholder.setVisible(false);
    }
    
    public void setOnAction(EventHandler e) {
        this.button.setOnAction(e);
    }
}
