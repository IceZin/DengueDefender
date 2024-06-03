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
 *
 * @author victo
 */
public class GoogleMaps extends Pane {
    private final WebEngine webEngine;
    
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
    
    public void addPoint(double lat, double lng) {
        String script = String.format(
            "addReport(%s, %s)",
            String.valueOf(lat).replace(",", "."),
            String.valueOf(lng).replace(",", ".")
        );
        
        this.webEngine.executeScript(script);
    }
    
    public void clearAreas() {
        String script = "clearAreas()";
        
        this.webEngine.executeScript(script);
    }
    
    public void showAreas() {
        String script = "showAreas()";
        
        this.webEngine.executeScript(script);
    }
    
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
