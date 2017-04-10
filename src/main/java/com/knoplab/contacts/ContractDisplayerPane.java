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
public class ContractDisplayerPane extends TilePane implements View  {
    
    public ContractDisplayerPane() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

    }
    
    
    @Override
    public void refresh() {
        
    }
    
}
