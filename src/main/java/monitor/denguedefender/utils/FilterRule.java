/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import components.ReportItem;

/**
 *
 * @author victo
 */
public class FilterRule {

    private String field;
    private String comparisionValue;

    private String getFieldValue(ReportItem instance) {
        switch (field) {
            case "city":
                return instance.getCity();
            case "neighborhood":
                return instance.getNeighborhood();
        }
        
        return "";
    }

    public void setField(String value) {
        this.field = value;
    }

    public void setComparisionValue(String value) {
        this.comparisionValue = value;
    }

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
