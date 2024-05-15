package monitor.denguedefender;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;
import monitor.denguedefender.views.Home;
import monitor.denguedefender.views.Login;
import monitor.denguedefender.views.View;


/**
 * JavaFX App
 */
public class App extends Application {
    SceneManager sceneManager = new SceneManager();
    SessionManager sessionManager = new SessionManager();

    @Override
    public void start(Stage stage) {
        View start = new View(this.sceneManager, this.sessionManager);
        start.build();

        var scene = new Scene(start.getCanvas(), 925, 600);
        
        this.sceneManager.setScene(scene);
        
        stage.setScene(scene);
        stage.show();
        
        View login = new Login(this.sceneManager, this.sessionManager);
        login.build();
        
        View home = new Home(this.sceneManager, this.sessionManager);
        home.build();
        
        this.sceneManager.add("login", login);
        this.sceneManager.add("home", home);
        
        this.sceneManager.show("login");
    }

    public static void main(String[] args) {
        launch();
    }

}