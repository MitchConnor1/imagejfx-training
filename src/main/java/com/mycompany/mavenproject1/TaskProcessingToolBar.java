/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import javafx.scene.control.Button;
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
    
    @Parameter 
    PluginService pluginService;

    public TaskProcessingToolBar(Context context) {
        
//        The context is injected by the toolbox itself, it will have to wait for the MainApp to inject it to the root
        context.inject(this);
        pluginService
		.getPluginsOfType(TaskPlugin.class)
		.forEach(this::addPlugin);
        
    }
    
    
    public void addPlugin(PluginInfo <TaskPlugin> pluginInfo){
        
        Button button = new Button (pluginInfo.getLabel());
        
        TaskPlugin plugin;
        
        try {
            plugin = pluginInfo.createInstance();
        } catch (InstantiableException ex) {
            System.out.println(ex.getMessage());
        }
        
        button.setOnAction(actionEvent -> applyPlugin (plugin));
        
        this.getItems().add(button);
        
        
    }
    
    public void applyPlugin(TaskPlugin plugin){
        
        
        
    }
    
    
}
