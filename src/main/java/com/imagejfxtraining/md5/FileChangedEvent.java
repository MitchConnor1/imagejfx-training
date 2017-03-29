/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxtraining.md5;

import java.io.File;
import org.scijava.event.SciJavaEvent;

/**
 *
 * @author julien
 */
public class FileChangedEvent extends SciJavaEvent {
    
    private final File file_;

    
    public FileChangedEvent(File file){
        
        file_ = file;
    }
    
    public File getFile(){
        
        return file_;
    }
    
    public String getFileName(){
        
        return file_.getName();
    }
    
}
