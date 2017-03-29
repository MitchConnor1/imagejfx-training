/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.imagejfxtraining.md5;


import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import org.scijava.Context;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */
public class FXMLController extends GridPane {

    @FXML
    private Button selectFileButton, startButton;
    
    @FXML 
    private ProgressBar progressBar;
    
    @FXML
    private Label label;
    
    
    @Parameter
    private FileService fileService;
    
    public FXMLController(Context context) throws IOException {
        
        context.inject(this);
             
        FXMLLoader loader = new FXMLLoader ();
        
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        }
    
    @FXML 
    private void selectFile(){

        fileService.openFileSelection();
    }
    
    @EventHandler
    private void onFileChanged(FileChangedEvent e){
        
        String fileName = e.getFileName();
        Platform.runLater( () -> 
                selectFileButton.setText(fileName));
        
    }

    @FXML
    private void launchMD5Algorithm(){
        if (startButton.getText().equals("Cancel"))
            fileService.stopMD5();
        else fileService.launchMD5();

        
    }
    
    
    @EventHandler
    private void onTasklaunched(TaskProcessedEvent event){
        Platform.runLater( 
                () -> { progressBar.setVisible(true);
        label.setVisible(true);
        selectFileButton.setDisable(true);});

        Task task = event.getTask();
        Platform.runLater( () -> {
                startButton.setText("Cancel");
                progressBar.progressProperty().bind(task.progressProperty());
                task.stateProperty().addListener(this::refresh);
                label.textProperty().bind(task.messageProperty());
        });
        

    }
    
    private void refresh(ObservableValue obs, Object oldValue, Object newValue){
        if (obs.getValue().equals(Worker.State.SUCCEEDED) || (obs.getValue().equals (Worker.State.CANCELLED))){
            
            progressBar.setVisible(false);
            label.setVisible(false);
            startButton.setText("Start");
            selectFileButton.setDisable(false);
        }
        
    }
    
}

