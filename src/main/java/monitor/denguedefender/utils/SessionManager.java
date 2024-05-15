/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import monitor.denguedefender.models.User;

/**
 *
 * @author victo
 */
public class SessionManager {
    private User user;
    private boolean visitorSession;
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setVisitorSession(boolean state) {
        this.visitorSession = state;
    }
    
    public boolean getVisitorSession() {
        return this.visitorSession;
    }
}
