/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import java.io.File;
import org.scijava.event.SciJavaEvent;

/**
 *
 * @author julien
 */
public class FileAddedEvent extends SciJavaEvent {
    
    private final File file;
    private final String source;
    
    public FileAddedEvent (File file, String id){
        this.file = file;
        this.source = id;
    }
    
    public File getFile(){
        return file;
    }
    
    public String getSource(){
        return source;
    }
    
}
