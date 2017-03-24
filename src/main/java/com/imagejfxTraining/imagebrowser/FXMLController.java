/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;


import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import org.scijava.Context;
import org.scijava.InstantiableException;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.PluginInfo;
import org.scijava.plugin.PluginService;


/**
 *
 * @author julien
 */
public class FXMLController extends BorderPane {
    
    @FXML
    private TextField textField;

    @FXML
    private ListView <String> listView;
    
    @FXML
    private ToolBar toolBar;
    
    @Parameter
    private FileService fileService;
    
    @Parameter 
    private PluginService pluginService;
    
    @Parameter
    private Context context;
    
    public FXMLController(Context context, String firstFolder) throws IOException{
        
        context.inject(this);
             
        FXMLLoader loader = new FXMLLoader ();
        
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fileService.updateData(firstFolder);
        
        
        pluginService
                .getPluginsOfType(FilePlugin.class)
                .forEach(this::addPlugin);
        
    }
    
    
    public void addPlugin(PluginInfo<FilePlugin> pluginInfo) {
            try {
                
                Button button = new Button(pluginInfo.getLabel());
                
                FilePlugin plugin = pluginInfo.createInstance();
                
                context.inject(plugin);
                
                button.setOnAction(actionEvent->applyPlugin(plugin));
                
                toolBar.getItems().add(button);
                
            }
            catch (InstantiableException ex) {
                ex.getMessage();
            }
        }
    
    public void applyPlugin(FilePlugin plugin) {
            
            String input = listView.getSelectionModel().getSelectedItem();
            plugin.processFile(input);
            
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
         
    }

    @FXML
    private void onKeyReleased(KeyEvent e){
        fileService.filter(textField.getText());
    }    
    
    
}