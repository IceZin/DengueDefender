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
 * Classe responsável gerenciar as cenas que forem adicionadas.
 * @author victo
 */
public class SceneManager {
    private final Map<String, View> scenes = new HashMap<>();
    private Scene scene;
    
    private int width = 0;
    private int height = 0;
    
    /**
    * Método responsável por adicionar uma cena ao gerenciador.
    * 
    * @param sceneKey chave uníca para identifcar a cena
    * @param scene    instanciamento da cena
    */
    public void add(String sceneKey, View scene) {
        this.scenes.put(sceneKey, scene);
    }
    
    /**
    * Método responsável exibir uma cena.
    * 
    * @param sceneKey chave uníca para identifcar a cena
    */
    public void show(String sceneKey) {
        View showScene = this.scenes.get(sceneKey);
        this.scene.setRoot(showScene.getCanvas());
        showScene.load();
    }
    
    /**
    * Método responsável definir a cena principal onde será renderizada as
    * outras cenas que forem adicionadas.
    * 
    * @param scene  instanciamento da cena principal
    * @param width  largura da cena principal
    * @param height altura da cena principal
    */
    public void setMainScene(Scene scene, int width, int height) {
        this.scene = scene;
        this.width = width;
        this.height = height;
    }
    
    /**
    * Método responsável por retornar a largura da cena principal
    */
    public int getWidth() {
        return this.width;
    }
    
    /**
    * Método responsável por retornar a altura da cena principal
    */
    public int getHeight() {
        return this.height;
    }
}
