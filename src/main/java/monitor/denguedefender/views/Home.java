/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import components.Menu;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 *
 * @author victo
 */
public class Home extends View {
    
    public Home(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
    
    @Override
    public void build() {
        Menu menu = new Menu(sceneManager);
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(menu);
    }
}
