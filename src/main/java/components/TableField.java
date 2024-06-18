/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Classe responsável por criar um elemento de campo de tabela.
 * @author victo
 */
public class TableField extends Pane {
    private boolean subfieldSetted = false;
    private final int width;
    private final int height;
    private final Text field;
    
    /**
    * Construtor da classe TableField responsável por montar os elementos.
    * 
    * @param width  largura do campo de tabela
    * @param height altura do campo de tabela
    * @param text   texto a ser exibido
    * @param center parametro para identificar se o texto deve ser centralizado
    **/
    public TableField(int width, int height, String text, boolean center) {
        super();
        
        this.setPrefSize(width, height);
        
        this.width = width;
        this.height = height;
        
        this.field = new Text(0, height * 0.5 + 4, text);
        this.field.setWrappingWidth(width);
        this.field.setStyle("-fx-font-size: 14px; -fx-font-weight: 500;");
        
        if (center) {
            this.field.setTextAlignment(TextAlignment.CENTER);
        }
        
        this.getChildren().addAll(this.field);
    }
    
    public void setSubField(String text, String iconPath) {
        if (!this.subfieldSetted) {
            this.subfieldSetted = true;
            
            this.field.relocate(0, height * 0.5 - 13);
            
            Image image = new Image(iconPath);
            ImageView icon = new ImageView(image);

            icon.setFitHeight(14);
            icon.setFitWidth(14);
            icon.relocate(0, this.height * 0.5 + 2);
            
            ColorAdjust darkenImage = new ColorAdjust();
            darkenImage.setBrightness(-0.2);
            
            icon.setEffect(darkenImage);
            
            Text subfield = new Text(14, this.height * 0.5 + 14, text);
            subfield.setWrappingWidth(this.width - 14);
            subfield.setStyle("-fx-fill: #B1B1B1; -fx-font-size: 14px; -fx-font-weight: 500;");
            
            this.getChildren().addAll(icon, subfield);
        }
    }
    
    public boolean containsSubField() {
        return this.subfieldSetted;
    }
}
