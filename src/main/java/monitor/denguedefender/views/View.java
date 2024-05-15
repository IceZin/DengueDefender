/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 *
 * @author victo
 */
public class View {
    protected Pane canvas = new Pane();
    protected SceneManager sceneManager = new SceneManager();
    protected SessionManager sessionManager = new SessionManager();
    
    public View(SceneManager sceneManager, SessionManager sessionManager) {
        this.sceneManager = sceneManager;
        this.sessionManager = sessionManager;
    }

    public void build() {
        Circle circle = new Circle(50,Color.BLUE);
        circle.relocate(20, 20);
        
        Rectangle rectangle = new Rectangle(100,100,Color.RED);
        rectangle.relocate(70,70);
        
        this.canvas.getChildren().addAll(circle, rectangle);
    }
    
    public Pane getCanvas() {
        return this.canvas;
    }
}
