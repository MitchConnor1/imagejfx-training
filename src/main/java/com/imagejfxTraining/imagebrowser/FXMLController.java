/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import org.scijava.Context;
import org.scijava.SciJava;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;


/**
 *
 * @author julien
 */
public class FXMLController extends BorderPane {
    
    @FXML
    private TextField textField;

    @FXML
    private ListView <String> listView;
    
    @Parameter
    private FileService fileService;
    
    public FXMLController(Context context) throws IOException{
        
        context.inject(this);
        FXMLLoader loader = new FXMLLoader ();
    
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fileService.updateData("/home/julien/");
    }
    
    
   
    @EventHandler
    private void onDataUpdated(UpdateEvent event){
        Platform.runLater( () -> {
                listView.getItems().clear();
                event.getFiles().forEach((f) -> listView.getItems().add(f.getName()));});
    }
    
    
    
    @FXML
    private void openFile(MouseEvent mouseEvent){
         if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                String name = listView.getSelectionModel().getSelectedItem();
                fileService.open(name);
            }
        }
         
         else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)){
             String name = listView.getSelectionModel().getSelectedItem();
             fileService.delete(name);
         }

    }

    
    @FXML 
    private void onKeyReleased(KeyEvent e){
        fileService.filter(textField.getText());
        System.out.println(textField.getText());
    }
}