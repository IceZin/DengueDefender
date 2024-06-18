///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package monitor.denguedefender.views;
//
//import components.Menu;
//import monitor.denguedefender.utils.SceneManager;
//import monitor.denguedefender.utils.SessionManager;
//
///**
// *
// * @author victo
// */
//public class Home extends View {
//    
//    public Home(SceneManager sceneManager, SessionManager sessionManager) {
//        super(sceneManager, sessionManager);
//    }
//    
//    @Override
//    public void build() {
//        Menu menu = new Menu(sceneManager);
//        
//        this.canvas.getStylesheets().add("styles.css");
//        this.canvas.getChildren().addAll(menu);
//    }
//}
//package monitor.denguedefender.views;
//import components.Menu;
//import monitor.denguedefender.utils.SceneManager;
//import monitor.denguedefender.utils.SessionManager;
//import static javafx.application.Application.launch;
//import javafx.geometry.Insets;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//
//public class Home extends View {
//
//     public Home(SceneManager sceneManager, SessionManager sessionManager) {
//       super(sceneManager, sessionManager);  
//     }
//    @Override
//    public void build() {
//        Menu menu = new Menu(sceneManager);
//        // Labels for statistics
//        Label labelTotalCases = new Label("20.365 pessoas");
//        Label labelLastMonthCases = new Label("2.185 pessoas");
//
//        // Progress bars for cities
//        ProgressBar progressBarSP = new ProgressBar(0.8);
//        ProgressBar progressBarSA = new ProgressBar(0.6);
//        ProgressBar progressBarCampinas = new ProgressBar(0.4);
//        ProgressBar progressBarSBC = new ProgressBar(0.7);
//        ProgressBar progressBarSantos = new ProgressBar(0.5);
//
//        // Labels for cities
//        Label labelSP = new Label("São Paulo");
//        Label labelSA = new Label("Santo André");
//        Label labelCampinas = new Label("Campinas");
//        Label labelSBC = new Label("São Bernardo do Campo");
//        Label labelSantos = new Label("Santos");
//
//        // Layout for statistics
//        VBox statisticsLayout = new VBox(10, labelTotalCases, labelLastMonthCases);
//        
//        // Layout for progress bars
//        VBox progressBarsLayout = new VBox(5);
//        progressBarsLayout.getChildren().addAll(labelSP, progressBarSP, labelSA, progressBarSA,
//                labelCampinas, progressBarCampinas, labelSBC, progressBarSBC, labelSantos, progressBarSantos);
//
//        // Main layout
//        HBox mainLayout = new HBox(100, statisticsLayout, progressBarsLayout);
//        mainLayout.setPadding(new Insets(100));
//
//        this.canvas.getStylesheets().add("styles.css");
//        this.canvas.getChildren().addAll(mainLayout, menu);
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
package monitor.denguedefender.views;

import components.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

public class Home extends View {

    public Home(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }

    private Pane buildStatisticsBox(String labelText, String valueText) {
        VBox statisticsBox = new VBox();
        statisticsBox.setAlignment(Pos.CENTER);
        statisticsBox.setSpacing(10);
        statisticsBox.setPadding(new Insets(20));
        statisticsBox.setPrefSize(300, 150);
        statisticsBox.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        Label valueLabel = new Label(valueText);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        valueLabel.setStyle("-fx-text-fill: black;");

        Label descriptionLabel = new Label(labelText);
        descriptionLabel.setFont(Font.font("Arial", 14));
        descriptionLabel.setStyle("-fx-text-fill: #53AF32;");

        statisticsBox.getChildren().addAll(valueLabel, descriptionLabel);
        return statisticsBox;
    }

    private VBox buildProgressBox() {
        VBox progressBox = new VBox();
        progressBox.setSpacing(50);
        progressBox.setPadding(new Insets(30));

        String[] cities = {"São Paulo", "Campinas", "São Bernardo do Campo", "Santo André", "São Paulo"};
        double[] progressValues = {0.8, 0.6, 0.5, 0.4, 0.8};
        int[] caseCounts = {13483, 6926, 5824, 4135, 13483};

        for (int i = 0; i < cities.length; i++) {
            HBox cityRow = new HBox();
            cityRow.setAlignment(Pos.CENTER_LEFT);
            cityRow.setSpacing(10);

            Label cityLabel = new Label(cities[i]);
            cityLabel.setPrefWidth(200);
            cityLabel.setFont(Font.font("Arial", 14));

            ProgressBar progressBar = new ProgressBar(progressValues[i]);
            progressBar.setPrefWidth(540);
            progressBar.setPrefHeight(30);
            progressBar.setStyle("-fx-accent: #53AF32");

            Label casesLabel = new Label(String.valueOf(caseCounts[i]));
            casesLabel.setPrefWidth(80);
            casesLabel.setFont(Font.font("Arial", 16));

            cityRow.getChildren().addAll(cityLabel, progressBar, casesLabel);
            progressBox.getChildren().add(cityRow);
        }

        return progressBox;
    }

    @Override
    public void build() {
         Menu menu = new Menu(sceneManager);
        // Layout for the main content
        VBox mainContent = new VBox();
        mainContent.setSpacing(40);
        mainContent.setPadding(new Insets(40));
        mainContent.setAlignment(Pos.CENTER);

        Pane statisticsSection = new Pane();
        statisticsSection.relocate(50, 50);
        Pane total =  buildStatisticsBox("Casos totais registrados", "20.365 pessoas");
        Pane total1 =  buildStatisticsBox("Casos registrados no ultimo mes", "2.185 pessoas");
        Pane total2 =  buildStatisticsBox("Denúncias registradas", "1.174 denúncias");
        total2.relocate(650, 0);
        total1.relocate(325, 0);

        statisticsSection.getChildren().addAll(
           total, total1, total2
        );
        
        

        VBox progressSection = new VBox();
        progressSection.setSpacing(30);
        progressSection.setAlignment(Pos.CENTER);

        Label progressHeader = new Label("Casos registrados por cidade");
        progressHeader.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        progressHeader.setStyle("-fx-background-color: #53AF32; -fx-text-fill: white; -fx-padding: 10;");
        progressHeader.setMaxWidth(Double.MAX_VALUE);
        progressHeader.setAlignment(Pos.CENTER);

        progressSection.getChildren().addAll(
            buildProgressBox(),
            progressHeader
        );

        mainContent.getChildren().addAll(statisticsSection, progressSection);

        // Layout for the entire screen
        HBox layout = new HBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));

        HBox.setHgrow(mainContent, Priority.ALWAYS);

        layout.getChildren().addAll(mainContent);

        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(layout, menu);
    }

}