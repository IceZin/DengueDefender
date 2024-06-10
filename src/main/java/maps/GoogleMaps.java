/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import monitor.denguedefender.models.Report;

/**
 * Classe responsável por integrar uma página web que exibe um mapa do google
 * maps.
 * 
 * @author victo
 */
public class GoogleMaps extends Pane {
    private final WebEngine webEngine;
    
    /**
    * Construtor da classe GoogleMaps
    * 
    * @param width  largura do mapa
    * @param height altura do mapa
    **/
    public GoogleMaps(int width, int height) {
        super();
        
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color: #ff0000");
        
        WebView webView = new WebView();
        this.webEngine = webView.getEngine();
        this.webEngine.load(getClass().getResource("/googlemaps.html").toString());
        
        webView.setPrefSize(width, height);
        
        this.getChildren().add(webView);
    }
    
    /**
    * Método responsável por adicionar um ponto de denúncia no mapa.
    * 
    * @param lat latitude da denúncia
    * @param lng longitude da denúncia
    */
    public void addPoint(double lat, double lng) {
        String script = String.format(
            "addReport(%s, %s)",
            String.valueOf(lat).replace(",", "."),
            String.valueOf(lng).replace(",", ".")
        );
        
        this.webEngine.executeScript(script);
    }
    
    /**
    * Método responsável por remover todas a áreas de denúncias.
    */
    public void clearAreas() {
        String script = "clearAreas()";
        
        this.webEngine.executeScript(script);
    }
    
    /**
    * Método responsável por renderizar todas a áreas de denúncias.
    */
    public void showAreas() {
        String script = "showAreas()";
        
        this.webEngine.executeScript(script);
    }
    
    /**
    * Método responsável por retornar a instancia de uma denúncia com a posição
    * definida a partir de uma seleção no mapa.
    */
    public Report getPos() {
        String result = (String) this.webEngine.executeScript("getPosInfo()");
        
        System.out.println(result);
        
        if (!result.equals("undefined")) {
            String[] resultSplit = result.split(", ");
            
            double lat = Double.parseDouble(resultSplit[0]);
            double lng = Double.parseDouble(resultSplit[1]);
            String city = resultSplit[2];
            String neighborhood = resultSplit[3];
            
            Report report = new Report();
            report.setCity(city);
            report.setNeighborhood(neighborhood);
            report.setLatitude(lat);
            report.setLongitude(lng);
            
            return report;
        }
        
        return null;
    }
}
