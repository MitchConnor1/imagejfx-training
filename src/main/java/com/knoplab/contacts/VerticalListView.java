/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import java.util.List;
import javafx.beans.Observable;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author julien
 */
public class VerticalListView extends ListView implements View {

    
    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    public VerticalListView (List <Contact> contacts){
        this.setOrientation(Orientation.HORIZONTAL); 
        this.getItems().addAll(contacts);
        
    }

    
  private class ContactListCell extends ListCell <Contact> {
        
        private ContactListCell (){
            super();
            this.itemProperty().addListener(this::onItemChanged);
        }
        
        private void onItemChanged(Observable obs, Contact oldValue, Contact newValue){
 
            if (newValue == null)
                this.setGraphic(null);
            
            else{
                this.setGraphic(new VerticalContactView(newValue));
                
            }
        }
        
    }
    
}
    
