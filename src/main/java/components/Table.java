/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import monitor.denguedefender.utils.Filter;
import monitor.denguedefender.utils.FilterRule;

/**
 * Classe responsável por criar um elemento de tabela.
 * @author victo
 */
public class Table extends Pane {
    private final ArrayList<ReportItem> items = new ArrayList<>();
    
    private final int width;
    private final int itemSize;
    
    Pane content = new Pane();
    
    private final Filter filter = new Filter();
    
    private final FilterRule cityFilterRule = new FilterRule();
    private final FilterRule neighborhoodFilterRule = new FilterRule();
    
    /**
    * Construtor da classe TableField responsável por montar os elementos.
    * 
    * @param width    largura da tabela
    * @param height   altura da tabela
    * @param itemSize tamanho dos itens da tabela
    **/
    public Table(int width, int height, int itemSize) {
        super();
        
        this.width = width;
        this.itemSize = itemSize;
        
        this.cityFilterRule.setField("city");
        this.neighborhoodFilterRule.setField("neighborhood");
        
        this.filter.addRule("city", cityFilterRule);
        this.filter.addRule("neighborhood", neighborhoodFilterRule);
        
        Rectangle background = new Rectangle(width, height, Color.valueOf("#FFFFFF"));
        background.setArcHeight(20.0d);
        background.setArcWidth(20.0d);
        background.setEffect(new DropShadow(10, Color.GRAY));
        
        Pane header = new Pane();
        header.setPrefSize(width, itemSize);
        
        Text reportAndCity = new Text(0, itemSize * 0.5 + 7, "DENUNCIA/CIDADE");
        reportAndCity.setWrappingWidth(width * 0.55);
        reportAndCity.setTextAlignment(TextAlignment.CENTER);
        reportAndCity.setStyle("-fx-fill: #B1B1B1; -fx-font-weight: 700; -fx-font-size: 14px;");
        
        Rectangle firstSeparator = this.createSeparator();
        firstSeparator.relocate(width * 0.55, 5);
        
        Text neighborhood = new Text(width * 0.55, itemSize * 0.5 + 7, "BAIRRO");
        neighborhood.setWrappingWidth(width * 0.3);
        neighborhood.setTextAlignment(TextAlignment.CENTER);
        neighborhood.setStyle("-fx-fill: #B1B1B1; -fx-font-weight: 700; -fx-font-size: 14px;");
        
        Rectangle secondSeparator = this.createSeparator();
        secondSeparator.relocate(width * 0.85, 5);
        
        Text date = new Text(width * 0.85, itemSize * 0.5 + 7, "DATA");
        date.setWrappingWidth(width * 0.15);
        date.setTextAlignment(TextAlignment.CENTER);
        date.setStyle("-fx-fill: #B1B1B1; -fx-font-weight: 700; -fx-font-size: 14px;");
        
        header.getChildren().addAll(reportAndCity, firstSeparator, neighborhood, secondSeparator, date);
        
        this.content.setPrefSize(width, itemSize);
        this.content.setStyle("-fx-background-color: transparent;");
        
        ScrollPane tableScrollBox = new ScrollPane();
        tableScrollBox.setPrefSize(width, height - itemSize);
        tableScrollBox.relocate(0, itemSize);
        tableScrollBox.setContent(this.content);
        tableScrollBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tableScrollBox.getStyleClass().add("transparentTable");
        
        this.getChildren().addAll(background, header, tableScrollBox);
    }
    
    /**
    * Método responsável por criar um separador de item.
    **/
    private Rectangle createSeparator() {
        Rectangle separator = new Rectangle(2, this.itemSize - 10, Color.valueOf("#E9E9E9"));
        separator.setArcHeight(1.0d);
        separator.setArcWidth(1.0d);
        
        return separator;
    }
    
    /**
    * Método responsável por adicionar um item a tabela.
    * 
    * @param item instanciamento de um item de denúncia
    **/
    public void addItem(ReportItem item) {
        this.items.add(item);
        
        item.build(this.width - 20, this.itemSize);
    }
    
    /**
    * Método responsável por aplicar o filtro de cidade.
    * 
    * @param city nome da cidade
    **/
    public void applyCityFilter(String city) {
        this.cityFilterRule.setComparisionValue(city);
        
        System.out.printf("Setting city filter to %s\n", city);
        
        this.show();
    }
    
    /**
    * Método responsável por aplicar o filtro de bairro.
    * 
    * @param neighborhood nome do bairro
    **/
    public void applyNeighborhoodFilter(String neighborhood) {
        this.neighborhoodFilterRule.setComparisionValue(neighborhood);
        
        System.out.printf("Setting neighborhood filter to %s\n", neighborhood);
        
        this.show();
    }
    
    /**
    * Método responsável por exibir os itens filtrados na tabela.
    **/
    public final void show() {
        this.content.getChildren().clear();
        
        ArrayList<ReportItem> filteredItems = this.filter.apply(this.items);
                
        for (int i = 0; i < filteredItems.size(); i++) {
            ReportItem item = filteredItems.get(i);

            item.setPrefSize(this.width - 20, this.itemSize);
            item.relocate(10, i * this.itemSize);
            
            this.content.getChildren().add(item);
        }
        
        this.content.setPrefSize(this.width, filteredItems.size() * this.itemSize);
    }
    
    /**
    * Método responsável por limpar os itens da tabela.
    **/
    public void clearItems() {
        this.items.clear();
    }
}
