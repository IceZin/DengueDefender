/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import monitor.denguedefender.utils.SceneManager;
import monitor.denguedefender.utils.SessionManager;

/**
 * Classe genérica responsável por criar o corpo de uma view(tela), ao criar
 * uma nova tela é preciso que essa classe View seja estendida.
 * @author victo
 */
public class View {
    protected Pane canvas = new Pane();
    protected SceneManager sceneManager = new SceneManager();
    protected SessionManager sessionManager = new SessionManager();
    
    /**
    * Construtor da classe View
    * @param sceneManager   gerenciador de cenas
    * @param sessionManager gerenciador de sessão
    **/
    public View(SceneManager sceneManager, SessionManager sessionManager) {
        this.sceneManager = sceneManager;
        this.sessionManager = sessionManager;
    }

   /**
    * Método que é acionado somente quando a View é adicionada ao contexto do
    * @see monitor.denguedefender.utils.SceneManager
    */
    public void build() {
        Circle circle = new Circle(50,Color.BLUE);
        circle.relocate(20, 20);
        
        Rectangle rectangle = new Rectangle(100,100,Color.RED);
        rectangle.relocate(70,70);
        
        this.canvas.getChildren().addAll(circle, rectangle);
    }
    
    /**
    * Método que é acionado toda vez que o usuário é redirecionado para a cena
    **/
    public void load() {
        
    }
    
    /**
    * Método responsável por retornar o instanciamento do corpo da cena
    **/
    public Pane getCanvas() {
        return this.canvas;
    }
}
