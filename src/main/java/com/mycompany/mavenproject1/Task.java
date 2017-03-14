/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;

/**
 *
 * @author julien
 */
public interface Task {
    
    public String getText();
    
    public void setText(String text);

    public Boolean isSelected();
    
    public void setSelected(Boolean selected);
    
    public BooleanProperty selectedProperty();
    
}
