/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.TilePane;

/**
 *
 * @author julien
 */
public class ContactDisplayerPane extends TilePane implements View  {
    
    public ContactDisplayerPane() throws IOException{

        super();
    }
    
    
    @Override
    public void refresh() {
        
    }
    
}
