/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import java.io.File;
import javafx.scene.control.Alert;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */



@Plugin(type = SciJavaService.class, priority = 1)
public class DefaultTaskService extends AbstractService implements TaskService {

    private final File currentFile = new File ("/home/julien/Pictures");
    private MD5Task task;
    
    @Parameter
    EventService eventService;
    
    @Override
    public void launchMD5(){
        
        if (currentFile == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a folder");
            alert.showAndWait();
        }

        else{

            File[] files = currentFile.listFiles();

            task = new MD5Task(files);
            eventService.publish(new TaskProcessedEvent(task));

            Thread thread = new Thread(task);
            
            thread.setDaemon(true);
            thread.start();
            
        }
    }
    
    @Override
    public void stopMD5(){
        
        task.cancel();
        
    }
}
