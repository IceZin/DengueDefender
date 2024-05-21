package monitor.denguedefender;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import monitor.denguedefender.utils.PostgresConnector;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;
import monitor.denguedefender.views.EventsCalendar;
import monitor.denguedefender.views.Home;
import monitor.denguedefender.views.Login;
import monitor.denguedefender.views.Map;
import monitor.denguedefender.views.Reports;
import monitor.denguedefender.views.View;


/**
 * JavaFX App
 */
public class App extends Application {
    SceneManager sceneManager = new SceneManager();
    SessionManager sessionManager = new SessionManager();

    @Override
    public void start(Stage stage) throws Exception {
        PostgresConnector.postgres.connect();
        
        View start = new View(this.sceneManager, this.sessionManager);
        start.build();

        var scene = new Scene(start.getCanvas(), 1200, 800);
        
        this.sceneManager.setScene(scene, 1200, 800);
        
        stage.setScene(scene);
        stage.show();
        
        View login = new Login(this.sceneManager, this.sessionManager);
        login.build();
        
        View home = new Home(this.sceneManager, this.sessionManager);
        home.build();
        
        View map = new Map(this.sceneManager, this.sessionManager);
        map.build();
        
        View calendar = new EventsCalendar(this.sceneManager, this.sessionManager);
        calendar.build();
        
        View reports = new Reports(this.sceneManager, this.sessionManager);
        reports.build();
        
        this.sceneManager.add("login", login);
        this.sceneManager.add("home", home);
        this.sceneManager.add("map", map);
        this.sceneManager.add("calendar", calendar);
        this.sceneManager.add("reports", reports);
        
        this.sceneManager.show("login");
    }

    public static void main(String[] args) {
        launch();
    }

}