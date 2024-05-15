/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import monitor.denguedefender.views.View;

/**
 *
 * @author victo
 */
public class SceneManager {
    private final Map<String, View> scenes = new HashMap<>();
    private Scene scene;
    
    public void add(String sceneKey, View scene) {
        this.scenes.put(sceneKey, scene);
    }
    
    public void show(String sceneKey) {
        this.scene.setRoot(this.scenes.get(sceneKey).getCanvas());
    }
    
    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
