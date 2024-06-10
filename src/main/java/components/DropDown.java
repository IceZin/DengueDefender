/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import events.FieldUpdateEvent;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Classe responsável por criar um elemento de drop down.
 * @author victo
 */
public class DropDown extends Pane {
    private ArrayList<String> items;
    private final ArrayList<Button> itemsNode = new ArrayList<>();
    private boolean active = false;
    private final int width;
    private final int height;
    
    private final ScrollPane itemsBox = new ScrollPane();
    private final ImageView icon;
    private final Button selector;
    private final Pane content = new Pane();
    
    private final String id;
    private EventHandler<FieldUpdateEvent> onUpdate;
    
    /**
    * Construtor da classe Menu responsável por montar os elementos.
    * 
    * @param id              identificador único do drop down
    * @param selectorWidth   largura do seletor
    * @param selectorHeight  altura do seletor
    * @param placeholderText texto de placeholder
    **/
    public DropDown(String id, int selectorWidth, int selectorHeight, String placeholderText) {
        super();
        
        this.id = id;
        this.width = selectorWidth;
        this.height = selectorHeight;
        
        Text placeholder = new Text(0, -8, placeholderText);
        placeholder.setStyle("-fx-fill: #B1B1B1; -fx-font-size: 14px; -fx-font-weight: 700;");
        
        Image image = new Image("up-arrow.png");
        this.icon = new ImageView(image);
        
        this.icon.setFitHeight(16);
        this.icon.setFitWidth(16);
        this.icon.relocate(selectorWidth - 30, selectorHeight / 2 - 8);
        this.icon.setRotate(180);
        
        Rectangle selectorBg = new Rectangle(selectorWidth, selectorHeight, Color.valueOf("#eeeeee"));
        selectorBg.setArcHeight(10.0d);
        selectorBg.setArcWidth(10.0d);
        
        this.selector = new Button();
        this.selector.setPrefSize(selectorWidth - 30, selectorHeight);
        this.selector.setStyle(
            "-fx-font-size: 14px; -fx-background-radius: 10; -fx-background-color: #eeeeee; -fx-cursor: hand;"
        );
        this.selector.setOnAction(e -> this.toggle());
        
        this.itemsBox.setPrefSize(selectorWidth, 400);
        this.itemsBox.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.itemsBox.setVbarPolicy(ScrollBarPolicy.NEVER);
        
        this.itemsBox.setContent(this.content);
        this.itemsBox.relocate(0, -2000);
        
        this.setPrefSize(this.width, this.height);
        
        this.getChildren().addAll(placeholder, selectorBg, selector, this.icon, this.itemsBox);
    }
    
    /**
    * Método reponsável por alternar o estado de exibição do drop down.
    **/
    private void toggle() {
        this.active = !this.active;
        
        this.setPrefSize(this.width, this.height);
        
        if (active) {
            this.itemsBox.relocate(0, this.height + 10);
        } else {
            this.itemsBox.relocate(0, -2000);
        }
        
        this.icon.setRotate(this.icon.getRotate() + 180);
    }
    
    /**
    * Método acionado sempre que um item for selecionado.
    * 
    * @param index indice do item selecionado
    **/
    private void select(int index) {
        if (this.onUpdate != null) {
            FieldUpdateEvent e = new FieldUpdateEvent(EventType.ROOT);
            e.setField(this.id);
            e.setValue(this.items.get(index));
            
            this.onUpdate.handle(e);
        }
        
        this.selector.setText(this.items.get(index));
    }
    
    /**
    * Método responsável por definir a ação de atualização do drop down
    * 
    * @param e instanciamento de um handler de evento
    **/
    public void setOnUpdate(EventHandler<FieldUpdateEvent> e) {
        this.onUpdate = e;
    }
    
    /**
    * Método responsável por definir os itens do drop down
    * 
    * @param items lista de itens
    **/
    public void setItems(ArrayList<String> items) {
        this.items = (ArrayList<String>) items.clone();
        
        this.itemsNode.clear();
        this.content.getChildren().clear();
        this.content.setPrefSize(this.width, items.size() * this.height);
        
        for (int i = 0; i < this.items.size(); i++) {
            Button item = new Button(this.items.get(i));
            item.setPrefSize(this.width, this.height);
            item.relocate(0, this.height * i);
            item.setStyle(
                "-fx-border-radius: 5; -fx-background-color: #eeeeee; -fx-font-size: 12px; -fx-font-weight: 500; -fx-cursor: hand;"
            );
            
            final int index = i;
            item.setOnAction(e -> this.select(index));
            
            this.content.getChildren().add(item);
        }
        
        this.select(0);
    }
}
