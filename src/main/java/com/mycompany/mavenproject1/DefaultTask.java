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

public class DefaultTask implements Task{
    
    
    private String text;
    private final BooleanProperty selected = new SimpleBooleanProperty();

    public DefaultTask(String text, Boolean selected) {
        this.text = text;
        this.selected.setValue (selected);
    }
    
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Boolean isSelected() {
        return selected.getValue();
    }

    @Override
    public void setSelected(Boolean selected) {
        this.selected.setValue(selected);
    }
    
    @Override
    public BooleanProperty selectedProperty(){
        return selected;
    }
}
