/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Classe responsável por gerenciar um evento de campo atualizado.
 * @author victo
 */
public class FieldUpdateEvent extends Event {
    private String field;
    private String value;

    /**
    * Construtor da classe FieldUpdateEvent.
    * 
    * @param et instanciamento de um evento
    **/
    public FieldUpdateEvent(EventType<? extends Event> et) {
        super(et);
    }

    /**
    * Método responsável por retornar o valor do evento.
    **/
    public String getValue() {
        return this.value;
    }
    
    /**
    * Método responsável por definir o valor do evento.
    * 
    * @param value instanciamento de um evento
    **/
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
    * Método responsável por retornar o nome do campo do evento.
    **/
    public String getField() {
        return this.field;
    }
    
    /**
    * Método responsável por definir o nome do campo do evento.
    * 
    * @param field nome do campo do evento
    **/
    public void setField(String field) {
        this.field = field;
    }
}
