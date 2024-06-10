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
import javafx.scene.text.Text;
import monitor.denguedefender.models.User;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 * Classe responsável por montar a cena de login.
 * @author victo
 */
public class Login extends View {
    private User insertedUser;
    
    /**
    * Construtor da classe Reports
    * @param sceneManager   gerenciador de cenas
    * @param sessionManager gerenciador de sessão
    **/
    public Login(SceneManager sceneManager, SessionManager sessionManager) {
        super(sceneManager, sessionManager);
    }
    
    /**
    * Método responsável por criar o elemento de notícia e retornar seu
    * instanciamento
    */
    private Pane buildInfo() {
        Pane newsBox = new Pane();
        newsBox.setPrefSize(650, 100);
        newsBox.relocate(50, 50);
        
        Text newsTitle = new Text(0, 0, "Alerta para o aumento de casos de dengue");
        newsTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: 700;");
        
        Text newsDate = new Text(0, 20, "maio 01, 2024");
        newsDate.setStyle("-fx-font-size: 12px; -fx-text-fill: #eeeeee;");
        
        Text newsText = new Text(0, 60, " No nosso App você pode ter acesso a "
            + "diversas informacões de como se prevenir\ndo mosquito da dengue, "
            + "além de poder visualizar áreas que possuem alertas\npara maiores "
            + "incidencias do caso."
        );
        newsText.setStyle("-fx-font-size: 14px;");
        
        newsBox.getChildren().addAll(newsTitle, newsDate, newsText);
        
        return newsBox;
    }
    
    /**
    * Método que é acionado somente quando a View é adicionada ao contexto do:
    * @see monitor.denguedefender.utils.SceneManager
    * 
    * Nessa classe o método build é responsável por criar os elementos da cena
    */
    @Override
    public void build() {
        int infoWidth = 675;
        int loginWidth = 425;
        
        Pane infoPane = new Pane();
        infoPane.setPrefSize(infoWidth, this.sceneManager.getHeight());
        infoPane.relocate(this.sceneManager.getWidth() - infoWidth, 0);
        
        Rectangle infoBackground = new Rectangle(infoWidth, this.sceneManager.getHeight(), Color.WHITE);
        infoBackground.setEffect(new DropShadow(10, Color.GRAY));
        
        infoPane.getChildren().addAll(infoBackground);
        
        Pane loginPane = new Pane();
        loginPane.setPrefSize(loginWidth, 300);
        loginPane.relocate(50, this.sceneManager.getHeight() / 2 - 150);
        
        Rectangle loginBox = new Rectangle(loginWidth, 300, Color.WHITE);
        loginBox.setArcHeight(10.0d);
        loginBox.setArcWidth(10.0d);
        loginBox.setEffect(new DropShadow(10, Color.GRAY));
        
        Text t1 = new Text(25, 40, "Insira seus dados para iniciar a sessão:");
        t1.getStyleClass().add("bold-poppins");
        
        Text loginFieldName = new Text(25, 70, "CPF");
        loginFieldName.setStyle("-fx-font-size: 14px");
        
        TextField loginInput = new TextField();
        loginInput.setPrefSize(loginWidth - 50, 40);
        loginInput.relocate(25, 80);
        loginInput.setStyle("-fx-border-radius: 5;");
        loginInput.setPromptText("Insira seu CPF");
        
        Text loginInfo = new Text(25, 130, "Insira seu CPF para se cadastrar ou acessar nosso sistema");
        loginInfo.setStyle("-fx-font-size: 11px;");
        
        Button continueBtn = new Button("Continuar");
        continueBtn.setPrefSize(loginWidth - 50, 40);
        continueBtn.relocate(25, 150);
        continueBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #1351B4; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        continueBtn.setOnAction(e -> this.handleContinue(e, loginInput, loginFieldName, loginInfo));
        
        Button visitorAcessBtn = new Button("Acessar como Visitante");
        visitorAcessBtn.setPrefSize(loginWidth - 50, 40);
        visitorAcessBtn.relocate(25, 200);
        visitorAcessBtn.setStyle(
            "-fx-border-radius: 5; -fx-background-color: #53AF32; -fx-font-size: 14px; -fx-font-weight: 700; -fx-text-fill: white; -fx-cursor: hand;"
        );
        visitorAcessBtn.setOnAction(e -> this.handleVisitorAccess(e));
        
        loginPane.getChildren().addAll(
            loginBox, t1, loginFieldName, loginInput, loginInfo, continueBtn, visitorAcessBtn
        );
        
        infoPane.getChildren().add(this.buildInfo());
        
        this.canvas.getStylesheets().add("styles.css");
        this.canvas.getChildren().addAll(infoPane, loginPane);
    }
    
    /**
    * Método que é acionado toda vez em que o botão de Continuar for pressionado,
    * quando executado verifica no banco o usuário e realiza a autenticação.
    * 
    * @param e              evento de ação do botão
    * @param loginInput     campo de texto
    * @param loginFieldName elemento de texto(nome do campo)
    * @param loginInfo      elemento de texto(informações adicionais)
    */
    private void handleContinue(ActionEvent e, TextField loginInput, Text loginFieldName, Text loginInfo) {
        if (insertedUser == null) {
            System.out.println("Clicked " + loginInput.getText());
        
            User user = User.getByDocument(loginInput.getText());

            if (user != null) {
                insertedUser = user;

                loginInput.setText("");
                loginInput.setPromptText("Insira sua senha");

                loginFieldName.setText("SENHA");
                loginInfo.setText("Insira sua senha para acessar nosso sistema");
            } else {

            }
        } else {
            if (insertedUser.getPassword().equals(loginInput.getText())) {
                SessionManager.session.setUser(insertedUser);
                sceneManager.show("map");
            }
        }
    }
    
    /**
    * Método que é acionado toda vez em que o botão de Acessar como visitante
    * for pressionado
    * 
    * @param e evento de ação do botão
    */
    private void handleVisitorAccess(ActionEvent e) {
        this.sessionManager.setVisitorSession(true);
        this.sceneManager.show("map");
    }
}
