/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maps;

import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
    
    public void addPoint(double lat, double lng, int value) {
        String script = String.format(
            "addArea({lat: %s, lng: %s}, %d)",
            String.valueOf(lat).replace(",", "."),
            String.valueOf(lng).replace(",", "."),
            value
        );
        
        System.out.println("Running script: ");
        System.out.println(script);
        
        this.webEngine.executeScript(script);
    }
}
