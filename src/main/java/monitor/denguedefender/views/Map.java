/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import components.Menu;
import components.TextBox;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import maps.GoogleMaps;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 *
 * @author victo
 */
public class Map extends View {

    public Map(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
    
    @Override
    public void build() {
        GoogleMaps maps = new GoogleMaps(sceneManager.getWidth(), sceneManager.getHeight());
        Menu menu = new Menu(sceneManager);
        
        Pane bottomInfo = new Pane();
        bottomInfo.setPrefSize(sceneManager.getWidth() - 175, 80);
        bottomInfo.relocate(25, sceneManager.getHeight() - 80);
        
        Rectangle bottomInfoBg = new Rectangle(sceneManager.getWidth() - 175, 50, Color.WHITE);
        bottomInfoBg.setArcHeight(20.0d);
        bottomInfoBg.setArcWidth(20.0d);
        bottomInfoBg.setEffect(new DropShadow(10, Color.GRAY));
        
        Text cases = new Text("Casos:");
        cases.relocate(20, 16);
        cases.setStyle("-fx-font-size: 14px; -fx-font-weight: 600;");
        
        TextBox casesValue = new TextBox("148.294", 100, 30, 20);
        casesValue.relocate(80, 10);
        
        Text reports = new Text("Denúncias registradas:");
        reports.relocate(200, 16);
        reports.setStyle("-fx-font-size: 14px; -fx-font-weight: 600;");
        
        TextBox reportsAmount = new TextBox("1.294", 100, 30, 20);
        reportsAmount.relocate(370, 10);
        
        Text city = new Text("Cidade:");
        city.relocate(490, 16);
        city.setStyle("-fx-font-size: 14px; -fx-font-weight: 600;");
        
        TextBox cityValue = new TextBox("São Bernardo do Campo", 200, 30, 20);
        cityValue.relocate(560, 10);
        
        bottomInfo.getChildren().addAll(bottomInfoBg, cases, casesValue, reports, reportsAmount, city, cityValue);
        
        Pane colorsInfo = new Pane();
        colorsInfo.setPrefSize(240, 80);
        colorsInfo.relocate(25, sceneManager.getHeight() - 185);
        
        Rectangle colorsInfoBg = new Rectangle(240, 80, Color.WHITE);
        colorsInfoBg.setArcHeight(20.0d);
        colorsInfoBg.setArcWidth(20.0d);
        colorsInfoBg.setEffect(new DropShadow(10, Color.GRAY));
        
        Text greenInfo = new Text("Alerta para pequena incidencia");
        greenInfo.relocate(30, 9);
        greenInfo.setStyle("-fx-font-size: 12px; -fx-font-weight: 600;");
        
        Circle greenCircle = new Circle();
        greenCircle.setRadius(7.0f);
        greenCircle.relocate(10, 10);
        greenCircle.setFill(Color.valueOf("#66CC00"));
        
        Text orangeInfo = new Text("Alerta para média incidencia");
        orangeInfo.relocate(30, 31);
        orangeInfo.setStyle("-fx-font-size: 12px; -fx-font-weight: 600;");
        
        Circle orangeCircle = new Circle();
        orangeCircle.setRadius(7.0f);
        orangeCircle.relocate(10, 33);
        orangeCircle.setFill(Color.valueOf("#FF7A00"));
        
        Text redInfo = new Text("Alerta para grande incidencia");
        redInfo.relocate(30, 54);
        redInfo.setStyle("-fx-font-size: 12px; -fx-font-weight: 600;");
        
        Circle redCircle = new Circle();
        redCircle.setRadius(7.0f);
        redCircle.relocate(10, 56);
        redCircle.setFill(Color.valueOf("#C90000"));
        
        colorsInfo.getChildren().addAll(
            colorsInfoBg, greenInfo, orangeInfo, redInfo, greenCircle, orangeCircle, redCircle
        );
        
        Button addPointBtn = new Button("Adicionar ponto");
        addPointBtn.setPrefSize(350, 40);
        addPointBtn.relocate(25, 200);
        addPointBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #53AF32; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        addPointBtn.setOnAction(e -> maps.addPoint(-23.649299, -46.599699, 1000));
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(maps, menu, bottomInfo, colorsInfo, addPointBtn);
    }
}
