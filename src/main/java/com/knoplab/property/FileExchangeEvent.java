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
public class FileExchangeEvent extends SciJavaEvent {
    
    private final File sourceFile;
    private final File targetFile;
    
    
    public FileExchangeEvent(File source, File target){
        this.sourceFile = source;
        this.targetFile = target;
        
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public File getTargetFile() {
        return targetFile;
    }
    
    
    
}
