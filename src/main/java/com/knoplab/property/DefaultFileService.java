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
public class DefaultFileService extends AbstractService implements FileService {

    @Parameter
    private EventService eventService;
    
    private File currentFile;
    
    @Override
    public File getFile() {
        return currentFile;
    }

    @Override
    public void setFile(File file) {
        this.currentFile = file;
    }

    @Override
    public void addFile(String id) {
        
        FileChooser fileChooser = new FileChooser();
        currentFile = fileChooser.showOpenDialog(new Stage());
        if (currentFile != null)
            eventService.publish(new FileAddedEvent(currentFile, id));
    }
    
}
