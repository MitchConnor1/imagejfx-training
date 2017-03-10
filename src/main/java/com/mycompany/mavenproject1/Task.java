/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 *
 * @author julien
 */

public class Task{
    
    
    private String text;
    private BooleanProperty selected = new SimpleBooleanProperty();

    public Task(String text, Boolean selected) {
        this.text = text;
        this.selected.setValue (selected);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isSelected() {
        return selected.getValue();
    }

    public void setSelected(Boolean selected) {
        this.selected.setValue(selected);
    }
    
    public BooleanProperty selectedProperty(){
        return selected;
    }
}
