/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import monitor.denguedefender.models.User;

/**
 * Classe responsável gerenciar a sessão do usuário.
 * @author victo
 */
public class SessionManager {
    public static SessionManager session = new SessionManager();
    
    private User user;
    private boolean visitorSession;
    
    /**
    * Método responsável por definir o usuário.
    * @param user usuário
    */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
    * Método responsável por retornar o usuário.
    */
    public User getUser() {
        return this.user;
    }
    
    /**
    * Método responsável por definir se a sessão é de um visitante.
    * @param state booleano para identificar sessão de visitante
    */
    public void setVisitorSession(boolean state) {
        this.visitorSession = state;
    }
    
    /**
    * Método responsável por retornar se a sessão é de um visitante.
    */
    public boolean getVisitorSession() {
        return this.visitorSession;
    }
}
