package com.mycompany.mavenproject1;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.Observable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */
public class FXMLController extends BorderPane {
    
    // this declares a component that is on the FXML document
    @FXML
    private TextField newTask;
    
    @FXML
    private ListView <Task> listView;
    
    // The declaration of the Service
    @Parameter
    private TaskService task;
    
    
    
    @Parameter 
    private TaskPlugin taskPlugin;
    
    
    public FXMLController() throws IOException{
        
        FXMLLoader loader = new FXMLLoader ();
        
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    
        //This will set a new cell factory to be used in the ListView.
        //It forces the old ListCell to be thrown away, and new ListCell's created with the new cell factory.
        
        listView.setCellFactory(this::createCell);

    }
    
    
    
//    Corresponds to a Callback<ListView<Task>, ListCell<Task>>
    private ListCell <Task> createCell (ListView <Task> tasks){
        return new TaskListCell();
    }
    
    
    private static class TaskListCell extends ListCell <Task> {
        
        CheckBox checkbox;
        
        private TaskListCell(){
            
            super();
            
            checkbox = new CheckBox();
            
            this.itemProperty().addListener(this::notifyCell);
        }
        
        
        private void notifyCell (Observable obs, Task oldValue, Task newValue){
            
            if (oldValue != null)
                oldValue.selectedProperty().unbindBidirectional(checkbox.selectedProperty());
            if (newValue == null)
                this.setGraphic(null);
            
            
            else{
                this.setGraphic(checkbox);
                checkbox.setSelected(newValue.isSelected());
                //Create a bidirectional binding between two properties.
                newValue.selectedProperty().bindBidirectional(checkbox.selectedProperty());
                checkbox.setText (newValue.getText());
            }
            
        }
        
    }
    
    @FXML
    private void close(){
        
        Platform.exit();
    }
    
    @FXML
    private void addCell (){
        if (newTask.getText() != null){
            task.addTask(newTask.getText());
            newTask.setText(null);
            
        }
        else{
            Alert alert = new Alert (AlertType.ERROR, "Please enter a task to do.");
            alert.showAndWait();
        }
    }
    
    @EventHandler
    public void onTaskAddedEvent (TaskAddedEvent event){
        Platform.runLater( () ->
                listView.getItems().add(event.getTask())
                
        );
    }
    
    @FXML
    private void selectAll(){
        listView.getItems().forEach( (t) -> t.setSelected(true));
    }
    
    @FXML
    private void removeSelection(){
        List <Task> toRemove = listView.getItems().stream()
                .filter((t) -> t.isSelected())
                .collect(Collectors.toList());
        
        
        toRemove.forEach( (t) -> task.removeTask(t));


    }    
    
    @EventHandler 
    public void onTaskDeletedEvent (TaskDeletedEvent event){
        Platform.runLater( () ->
            listView.getItems().remove(event.getTask())
        );
    }    
    
        
}