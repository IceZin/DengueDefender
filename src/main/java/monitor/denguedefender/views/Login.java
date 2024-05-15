/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 *
 * @author victo
 */
public class Login extends View {
    public Login(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
   
    @Override
    public void build() {
        Pane infoPane = new Pane();
        infoPane.setPrefSize(500, 550);
        infoPane.relocate(25, 25);
        
        Rectangle infoBackground = new Rectangle(450, 550, Color.WHITE);
        infoBackground.setArcHeight(10.0d);
        infoBackground.setArcWidth(10.0d);
        infoBackground.setEffect(new DropShadow(10, Color.GRAY));
        
        infoPane.getChildren().addAll(infoBackground);
        
        Pane loginPane = new Pane();
        loginPane.setPrefSize(400, 550);
        loginPane.relocate(500, 25);
        
        Rectangle loginBox = new Rectangle(400, 550, Color.WHITE);
        loginBox.setArcHeight(10.0d);
        loginBox.setArcWidth(10.0d);
        loginBox.setEffect(new DropShadow(10, Color.GRAY));
        
        Text t1 = new Text(25, 40, "Insira seus dados para iniciar a sessão:");
        t1.getStyleClass().add("bold-poppins");
        
        Text t2 = new Text(25, 70, "CPF");
        t2.setStyle("-fx-font-size: 14px");
        
        TextField cpfInput = new TextField();
        cpfInput.setPrefSize(350, 40);
        cpfInput.relocate(25, 80);
        cpfInput.setStyle("-fx-border-radius: 5;");
        cpfInput.setPromptText("Insira seu CPF");
        
        Text cpfInfo = new Text(25, 130, "Insira seu CPF para se cadastrar ou acessar nosso sistema");
        cpfInfo.setStyle("-fx-font-size: 10px;");
        
        Button continueBtn = new Button("Continuar");
        continueBtn.setPrefSize(350, 40);
        continueBtn.relocate(25, 150);
        continueBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #1351B4; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        continueBtn.setOnAction(e -> this.handleContinue(e, cpfInput));
        
        Button visitorAcessBtn = new Button("Acessar como Visitante");
        visitorAcessBtn.setPrefSize(350, 40);
        visitorAcessBtn.relocate(25, 200);
        visitorAcessBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #53AF32; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        visitorAcessBtn.setOnAction(e -> this.handleVisitorAccess(e));
        
        loginPane.getChildren().addAll(
            loginBox, t1, t2, cpfInput, cpfInfo, continueBtn, visitorAcessBtn
        );
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(infoPane, loginPane);
    }
    
    private void handleContinue(ActionEvent e, TextField cpfInput) {
        System.out.println("Clicked " + cpfInput.getText());
    }
    
    private void handleVisitorAccess(ActionEvent e) {
        this.sessionManager.setVisitorSession(true);
        this.sceneManager.show("home");
    }
}
