/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import components.ReportItem;

/**
 * Classe responsável por realizar a lógica de filtragem para a tabela de
 * denúncias.
 * 
 * @author victo
 */
public class FilterRule {

    private String field;
    private String comparisionValue;

    /**
    * Método responsável por retornar o valor de um campo
    * 
    * @param instance instanciamento do campo
    */
    private String getFieldValue(ReportItem instance) {
        switch (field) {
            case "city":
                return instance.getCity();
            case "neighborhood":
                return instance.getNeighborhood();
        }
        
        return "";
    }

    /**
    * Método responsável por definir o nome do campo que está sendo filtrado
    * 
    * @param value nome do campo
    */
    public void setField(String value) {
        this.field = value;
    }

    /**
    * Método responsável por definir o valor a ser comparado
    * 
    * @param value valor de comparação
    */
    public void setComparisionValue(String value) {
        this.comparisionValue = value;
    }

    /**
    * Método responsável por verificar se o campo entra na regra definida nessa
    * classe.
    * 
    * @param value instanciamento do campo
    */
    public boolean verify(ReportItem value) {
        if (this.comparisionValue != null) {
            if (this.comparisionValue.equals("Todos")) {
                return true;
            }
            
            return this.getFieldValue(value).equals(this.comparisionValue);
        } else {
            return true;
        }
    }
}
