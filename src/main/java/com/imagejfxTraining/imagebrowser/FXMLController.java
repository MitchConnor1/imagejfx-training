/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.awt.TextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import javafx.scene.layout.BorderPane;
import org.scijava.plugin.Parameter;


/**
 *
 * @author julien
 */
public class FXMLController extends BorderPane {
    
    @FXML
    private TextField textField;

    @FXML
    private ListView listView;
    
    @Parameter
    private FileService fileService;
    
    public FXMLController() throws IOException{
        
    FXMLLoader loader = new FXMLLoader ();
    
    loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();

    }
    
    
    
        
}
