/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Classe responsável por criar um elemento de caixa de texto.
 * @author victo
 */
public class TextBox extends StackPane {
    /**
    * Construtor da classe TextBox responsável por montar os elementos.
    * 
    * @param text   texto a ser exibido
    * @param width  largura da caixa de texto
    * @param height altura da caixa de texto
    * @param radius raio da borda da caixa de texto
    **/
    public TextBox(String text, int width, int height, int radius) {
        super();
        
        this.setPrefSize(width, height);
        
        Rectangle bottomInfoBg = new Rectangle(width, height, Color.valueOf("#1351B4"));
        bottomInfoBg.setArcHeight(radius * 1.0d);
        bottomInfoBg.setArcWidth(radius * 1.0d);
        
        Text placeholderText = new Text(0, 0, text);
        placeholderText.setStyle("-fx-fill: white; -fx-font-size: 14px; -fx-font-weight: 700;");
        
        this.getChildren().addAll(bottomInfoBg, placeholderText);
    }
}
