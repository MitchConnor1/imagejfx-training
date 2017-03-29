/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxtraining.md5;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
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
public class DefaultFileService extends AbstractService implements FileService {

    private File currentFile = null;
    private MD5Task task;
    
    @Parameter
    EventService eventService;
    
    @Override
    public void openFileSelection() {
        Stage stage = new Stage();

        DirectoryChooser fileChooser = new DirectoryChooser();
        File file = fileChooser.showDialog(stage);
        if (file != null)
            currentFile = file;
        if (currentFile != null)
            eventService.publish(new FileChangedEvent(currentFile));
        
    }
    
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
