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

/**
 * Classe responsável por criar um elemento de caixa de filtro.
 * @author victo
 */
public class FilterBox extends Pane {
    private EventHandler onCityUpdate;
    private ArrayList<String> cities = new ArrayList<>();
    private ArrayList<String> neighborhoods = new ArrayList<>();
    
    private final DropDown cityDp;
    private final DropDown neighborhoodDp;
    
    /**
    * Construtor da classe Menu responsável por montar os elementos.
    * 
    * @param width  largura da caixa de filtro
    * @param height altura da caixa de filtro
    **/
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
    
    /**
    * Método responsável por definir a ação atualização do campo de filtro.
    * 
    * @param e instanciamento de um handler de evento de atualização de campo
    **/
    public void setOnUpdate(EventHandler<FieldUpdateEvent> e) {
        this.onCityUpdate = e;
    }
    
    /**
    * Método responsável por acionar o evento de atualização de cidade.
    * 
    * @param e evento de atualização de campo
    **/
    private void triggerOnUpdate(FieldUpdateEvent e) {
        if (this.onCityUpdate != null) {
            this.onCityUpdate.handle(e);
        }
    }
    
    /**
    * Método responsável por definir as cidades.
    * 
    * @param items lista de cidades
    **/
    public void setCities(ArrayList<String> items) {
        this.cities = (ArrayList<String>) items.clone();
        
        this.cityDp.setItems(this.cities);
    }
    
    /**
    * Método responsável por definir os bairros.
    * 
    * @param items lista de bairros
    **/
    public void setNeighborhoods(ArrayList<String> items) {
        this.neighborhoods = (ArrayList<String>) items.clone();
        this.neighborhoods.add(0, "Todos");
        
        this.neighborhoodDp.setItems(this.neighborhoods);
    }
}
