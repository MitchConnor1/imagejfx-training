/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    
    @Parameter
    private Context context;
    
    @Parameter
    private TaskService taskService;
    
    @FXML
    private Label label;
    
    @FXML
    private Button exchangeButton;
    
    @FXML 
    private Button startButton;
    
    @FXML
    private ProgressBar progressBar;
    
    @FXML 
    private Button resetButton;
    
    @Parameter
    private PropertyExerciseService fileService;
    
    private SubSceneController source;
    private SubSceneController target;
    
    private final BooleanProperty sourceDisabledProperty = new SimpleBooleanProperty();
    private final BooleanProperty targetDisabledProperty = new SimpleBooleanProperty();
    private final ObjectProperty <File> sourceFile = new SimpleObjectProperty();
    private final ObjectProperty <File> targetFile = new SimpleObjectProperty();
    private final ObservableValue <Boolean> exchangeButtonDisabled = 
            Bindings.createBooleanBinding(this::getAvailibility, sourceFile, targetFile);
    private final ObservableValue <String> labelText = 
            Bindings.createStringBinding(this::getLabel, sourceFile, targetFile);
    
    private final ObjectProperty <Worker.State> taskRunningProperty = new SimpleObjectProperty();
    private final ObservableValue <String> startButtonLabel =
            Bindings.createStringBinding(this::getStartButtonText, taskRunningProperty);
    
    
    
    private static final String START = "start";
    private static final String CANCEL = "cancel";
        
    
    public FXMLController(Context context) throws IOException {
        
        context.inject(this);
        FXMLLoader loader = new FXMLLoader ();
        
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        viewInit();
        init();        
     }
    
    private void viewInit() throws IOException{
        
        source = new SubSceneController(FileType.SOURCE, context);
        target = new SubSceneController(FileType.TARGET, context);
        
        exchangeButton.setText("Exchange");
                
        GridPane.setConstraints(source, 0, 0);
        GridPane.setConstraints(target, 0, 2);
        
        this.getChildren().addAll(source, target);
        
    }
    

    
    private void init(){
        
        source.getDisabledProperty().bind(sourceDisabledProperty);
        target.getDisabledProperty().bind(sourceDisabledProperty.not());
        source.FileProperty().bindBidirectional(sourceFile);
        target.FileProperty().bindBidirectional(targetFile);
        exchangeButton.disableProperty().bind(exchangeButtonDisabled);
        startButton.disableProperty().bind(exchangeButtonDisabled);
        label.textProperty().bind(labelText);
        startButton.textProperty().bind(startButtonLabel);
        
    }
    
    
    private String getStartButtonText(){
        
        if (null == taskRunningProperty.getValue())
            return START;
        else switch (taskRunningProperty.getValue()) {
            case RUNNING:
                return CANCEL;
            case SUCCEEDED:
                progressBar.progressProperty().unbind();
                progressBar.setProgress(0.0);
                break;
            default:
                break;
        }
        return START;
            
    }
    private Boolean getAvailibility(){
        
        if (sourceFile.getValue() == null || targetFile.getValue() == null )
            return Boolean.TRUE;
        return Boolean.FALSE;
        
    }
    
    private String getLabel (){
        
       if (sourceFile.getValue() == null)
           return "Please enter a " + FileType.SOURCE.getTypeName() +" file.";
       else if (targetFile.getValue() == null)
           return "Please enter a " + FileType.TARGET.getTypeName() +" file.";
       else
           return "Comparing file " + sourceFile.getValue().getName() + " and file "
                   + targetFile.getValue().getName() + ".";
    }
    
    
    @EventHandler
    public void onFileAdded(FileAddedEvent event){
        if (event.getFileType().equals(FileType.SOURCE)){
            Platform.runLater( () -> {
            sourceDisabledProperty.set(true);
            });
        }
        else if (event.getFileType().equals(FileType.TARGET)){
            Platform.runLater( () -> {
                target.getDisabledProperty().bind(targetDisabledProperty);
                targetDisabledProperty.set(true);
            });
        }
        
    }
    
    @FXML
    private void onResetButton(){
        sourceFile.setValue(null);
        targetFile.setValue(null);
        sourceDisabledProperty.set(false);
        target.getDisabledProperty().bind(sourceDisabledProperty.not());
    }
    
    @FXML
    private void onStartButton(){
        if (startButton.getText().equals(CANCEL))
            taskService.stopMD5();
        taskService.launchMD5();
    }
    
    @EventHandler
    private void onTasklaunched(TaskProcessedEvent event){
        Task task = event.getTask();
        Platform.runLater( () -> {
            progressBar.progressProperty().bind(task.progressProperty());
            taskRunningProperty.bind(task.stateProperty());
        });
        
    }
    @FXML
    public void onExchangeButton(){
        fileService.exchangeFiles();
        
    }
    @EventHandler
    public void onFileExchange(FileExchangeEvent event){
        
        File fromSource = event.getTargetFile();
        File fromTarget = event.getSourceFile();
        
        Platform.runLater(() -> {
        sourceFile.setValue(fromSource);
        targetFile.setValue(fromTarget);
        });
        
    }
    
}
