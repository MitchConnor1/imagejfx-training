/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import java.io.File;
import javafx.stage.FileChooser;
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



@Plugin(type = SciJavaService.class, priority =  1)
public class DefaultPropertyExerciseService extends AbstractService implements PropertyExerciseService {

    @Parameter
    private EventService eventService;
    
    @Parameter 
    private TaskService taskService;
    
    private File sourceFile;
    private File targetFile;
    private File currentFile;
    
    @Override
    public File getSourceFile() {
        return sourceFile;
    }

    @Override
    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    @Override
    public File getTargetFile() {
        return targetFile;
    }

    @Override
    public void setTargetFile(File targetFile) {
        this.targetFile = targetFile;
    }
    
    
    @Override
    public void addFile(String id, FileType fileType) {
        
        FileChooser fileChooser = new FileChooser();
        currentFile = fileChooser.showOpenDialog(new Stage());
        if (fileType.equals(FileType.SOURCE))
            sourceFile = currentFile;
        else if (fileType.equals(FileType.TARGET))
            targetFile = currentFile;
        
        if (currentFile != null)
            eventService.publish(new FileAddedEvent(currentFile, id, fileType));
    }
    
    @Override
    public void exchangeFiles(){
        
        eventService.publish (new FileExchangeEvent(sourceFile, targetFile));
        File tmp;
        tmp = sourceFile;
        sourceFile = targetFile;
        targetFile = tmp;
        
    }
    
    
}
