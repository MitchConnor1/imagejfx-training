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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.scijava.Context;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */


public class SubSceneController extends HBox {
    
    @FXML
    private Button button;
    
    @FXML
    private Label label;
    
    @FXML
    TextField textField;
    
    @Parameter
    private PropertyExerciseService fileService;
    
    private final ObjectProperty <File> fileProperty = new SimpleObjectProperty();
    
    private final ObservableValue <String> fileButtonText = Bindings.createStringBinding (this::getFileButtonText, fileProperty);
    
    private final ObservableValue <String> textFieldText = Bindings.createStringBinding (this::getTextFieldText, fileProperty);
    
    private final BooleanProperty disabledProperty = new SimpleBooleanProperty();
    
    public final FileType fileType;
    
    private static final String CHOOSE = "choose";
    private static final String CHANGE = "change";
    
    
    public SubSceneController(FileType fileType, Context context) throws IOException{
        
        
        this.fileType = fileType;
        context.inject(this);
        FXMLLoader loader = new FXMLLoader ();
        
        loader.setLocation(getClass().getResource("/fxml/SubScene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        label.setText(fileType.getTypeName());
        
        init();
    }
    
    private void init(){
                
        button.textProperty().bind(fileButtonText);
        textField.textProperty().bind(textFieldText);
        button.disableProperty().bind(disabledProperty);
        if (! label.getText().equals("source"))
            this.setDisabledProperty(true);
        
    }
    
    private String getFileButtonText(){
        if (fileProperty.getValue() == null)
            return CHOOSE;
        else
            return CHANGE;
    }
    
    private String getTextFieldText(){
        if (fileProperty.getValue() == null)
            return null;
        return fileProperty.getValue().getName();
    }
    
    
    public ObjectProperty<File> FileProperty(){
        return fileProperty;
    }
    
    @FXML
    public void onButtonClicked(){
        
        fileService.addFile(label.getText(), this.fileType);
        
    }
    
    
    @EventHandler
    public void onFileAdded(FileAddedEvent event){
        if (event.getSource().equals(label.getText())) 
            Platform.runLater( () -> {
                fileProperty.setValue(event.getFile());
        });
        
        
    }
    
    public void setDisabledProperty(boolean bool){
        disabledProperty.set(bool);
        
    }
    
    public BooleanProperty getDisabledProperty(){
        return disabledProperty;
    }
    
}
