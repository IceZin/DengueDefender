/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import components.DropDown;
import components.Menu;
import components.MenuButton;
import components.TextBox;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import maps.GoogleMaps;
import monitor.denguedefender.models.Report;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 * Classe responsável por montar a cena do mapa interativo.
 * @author victo
 */
public class Map extends View {
    private final String[] reportTypes = {
        "Possível foco de dengue",
        "Água parada",
        "Pessoas doentes na região",
        "Local abandonado com passível contribuição de proliferação",
        "Falta de saneamento básico",
        "Acúmulo de lixo"
    };
    private Report currentReport;
    private GoogleMaps maps;

    /**
    * Construtor da classe Reports
    * @param sceneManager   gerenciador de cenas
    * @param sessionManager gerenciador de sessão
    **/
    public Map(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
    
    /**
    * Método que é acionado somente quando a View é adicionada ao contexto do:
    * @see monitor.denguedefender.utils.SceneManager
    * 
    * Nessa classe o método build é responsável por criar os elementos da cena
    */
    @Override
    public void build() {
        this.maps = new GoogleMaps(sceneManager.getWidth(), sceneManager.getHeight());
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
        
        Pane modalView = new Pane();
        modalView.setPrefSize(sceneManager.getWidth(), sceneManager.getHeight());
        
        Rectangle modalBg = new Rectangle(sceneManager.getWidth(), sceneManager.getHeight(), Color.BLACK);
        modalBg.setOpacity(0.4);
        
        Pane reportModal = new Pane();
        reportModal.relocate(sceneManager.getWidth() * 0.5 - 300, sceneManager.getHeight()* 0.5 - 75);
        reportModal.setPrefSize(600, 150);
        
        Rectangle reportModalBg = new Rectangle(600, 150, Color.WHITE);
        reportModalBg.setArcHeight(20.0d);
        reportModalBg.setArcWidth(20.0d);
        reportModalBg.setEffect(new DropShadow(10, Color.GRAY));
        
        DropDown reportType = new DropDown("reportType", 550, 30, "TIPO DENUNCIA");
        reportType.relocate(25, 40);
        reportType.setItems(new ArrayList<>(Arrays.asList(this.reportTypes)));
        reportType.setOnUpdate(e -> {
            this.currentReport.setType(Arrays.asList(this.reportTypes).indexOf(e.getValue()));
        });
        
        Button createBtn = new Button("Denunciar");
        createBtn.setPrefSize(250, 40);
        createBtn.relocate(25, 90);
        createBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #1351B4; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        createBtn.setOnAction(e -> {
            this.currentReport.save();
            modalView.setVisible(false);
            this.load();
        });
        
        Button cancelBtn = new Button("Cancelar");
        cancelBtn.setPrefSize(250, 40);
        cancelBtn.relocate(325, 90);
        cancelBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #1351B4; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        cancelBtn.setOnAction(e -> {
            this.currentReport = null;
            modalView.setVisible(false);
        });
        
        reportModal.getChildren().addAll(reportModalBg, createBtn, cancelBtn, reportType);
        
        modalView.getChildren().addAll(modalBg, reportModal);
        
        MenuButton registerReport = new MenuButton(
            "Denunciar",
            "report.png",
            sceneManager.getWidth() - 225,
            sceneManager.getHeight() - 175,
            1.0
        );
        
        modalView.setVisible(false);
        
        registerReport.setOnAction(e -> {
            currentReport = maps.getPos();
            
            if (currentReport != null) {
                modalView.setVisible(true);
            }
        });
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(maps, menu, bottomInfo, colorsInfo, registerReport, modalView);
    }
    
    /**
    * Método que é acionado toda vez que o usuário é redirecionado para a cena.
    * 
    * Nessa classe o método load é responsável por buscar as denúncias no banco
    * de dados e enviá-las ao mapa.
    */
    @Override
    public void load() {
        this.maps.clearAreas();
        
        for (Report report : Report.selectAll()) {
            this.maps.addPoint(report.getLatitude(), report.getLongitude());
        }
        
        this.maps.showAreas();
    }
}
