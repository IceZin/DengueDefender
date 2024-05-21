/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitor.denguedefender.utils;

import components.ReportItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author victo
 */
public class Filter {
    private final Map<String, FilterRule> rules = new HashMap<>();
    
    public void addRule(String ruleKey, FilterRule rule) {
        this.rules.put(ruleKey, rule);
    }
    
    public boolean removeRule(String ruleKey) {
        if (this.rules.containsKey(ruleKey)) {
            this.rules.remove(ruleKey);
            return true;
        }
        
        return false;
    }
    
    public ArrayList<ReportItem> apply(ArrayList<ReportItem> items) {
        ArrayList<ReportItem> filteredItems = new ArrayList<>();
        
        for (int i = 0; i < items.size(); i++) {
            boolean pushItem = true;
            ReportItem item = items.get(i);
            
            for (Map.Entry<String, FilterRule> ruleRef : this.rules.entrySet()) {
                FilterRule rule = ruleRef.getValue();
                
                if (!rule.verify(item)) {
                    pushItem = false;
                    break;
                }
            }
            
            if (pushItem) {
                filteredItems.add(item);
            }
        }
        
        return filteredItems;
    }
}
