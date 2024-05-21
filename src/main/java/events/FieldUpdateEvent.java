/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author victo
 */
public class FieldUpdateEvent extends Event {
    private String field;
    private String value;

    public FieldUpdateEvent(EventType<? extends Event> et) {
        super(et);
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getField() {
        return this.field;
    }
    
    public void setField(String field) {
        this.field = field;
    }
}
