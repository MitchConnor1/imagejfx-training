package com.mycompany.mavenproject1;


import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.Observable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import org.scijava.Context;
import org.scijava.InstantiableException;
import org.scijava.SciJava;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.PluginInfo;
import org.scijava.plugin.PluginService;

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
    
    @FXML
    private Button removeButton;
    
    @FXML
    private ToolBar toolBar;
    
// The declaration of the Service
    @Parameter
    private TaskService task;
    
    
    @Parameter 
    private TaskPlugin taskPlugin;
    
    @Parameter
    private PluginService pluginService;
  
    @Parameter
    private Context context;
    
    
    public FXMLController() throws IOException, InstantiableException{
        /**
         * Main entry point into SciJava.
         * This class enables working with SciJava services.
         */
        


        FXMLLoader loader = new FXMLLoader ();
        
        
        /**        Injects the application context into the given object
         * If the given object has any non-final Context fields annotated with @Parameter, sets the value of those fields to this context.
         * If the given object has any non-final Service fields annotated with @Parameter, sets the value of those fields to the corresponding service available from this context.
         * Calls EventService.subscribe(Object) with the object to register any @EventHandler annotated methods as event subscribers. 
         */
        SciJava scijava = new SciJava();
        
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        scijava.context().inject(this);
        
      
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
    private void selectAll() throws InstantiableException{
        
        listView.getItems().forEach( (t) -> t.setSelected(true));
        PluginInfo <TaskPlugin> plugin = pluginService.getPlugin (SelectAllTasks.class, TaskPlugin.class);
        TaskPlugin pl = plugin.createInstance();
        pl.processTask(listView.getItems(), context);
    }
    
    @FXML
    private void removeSelection() throws InstantiableException{
        PluginInfo <TaskPlugin> plugin = pluginService.getPlugin(RemoveTask.class, TaskPlugin.class);
        TaskPlugin pl = plugin.createInstance();
        pl.processTask(listView.getItems(), context);
    }    
    
    @EventHandler 
    public void onTaskDeletedEvent (TaskDeletedEvent event){
        Platform.runLater( () ->
            listView.getItems().remove(event.getTask())
        );
    }    
    
}