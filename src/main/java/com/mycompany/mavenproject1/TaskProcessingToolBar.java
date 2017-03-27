/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import org.scijava.Context;
import org.scijava.InstantiableException;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.PluginInfo;
import org.scijava.plugin.PluginService;

/**
 *
 * @author julien
 */
public class TaskProcessingToolBar extends ToolBar {
   /**
    @Parameter 
    PluginService pluginService;

    @Parameter
    Context context;
    
    ListView <Task> listView;
    public TaskProcessingToolBar(Context context, ListView <Task> listView) {
        
//        The context is injected by the toolbox itself, it will have to wait for the MainApp to inject it to the root
        context.inject(this);
        this.listView = listView;
        pluginService
		.getPluginsOfType(TaskPlugin.class)
		.forEach(this::addPlugin);
        
    }
    
    
    public void addPlugin(PluginInfo <TaskPlugin> pluginInfo){
        
        try {
            Button button = new Button (pluginInfo.getLabel());
            
            TaskPlugin plugin;
            
            plugin = pluginInfo.createInstance();
            
            
            button.setOnAction(actionEvent -> plugin.processTask(listView.getItems(), context));
            
            this.getItems().add(button);
        } catch (InstantiableException ex) {
            Logger.getLogger(TaskProcessingToolBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
        */
}
