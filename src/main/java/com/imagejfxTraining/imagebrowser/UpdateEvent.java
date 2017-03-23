/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.util.List;
import org.scijava.event.SciJavaEvent;

/**
 *
 * @author julien
 */
public class UpdateEvent extends SciJavaEvent {

    public final List<ItemFile> files;
    
    public UpdateEvent(List<ItemFile> files) {

        this.files = files;

    }
    
    public List<ItemFile> getFiles(){
        
        return files;
    }


    
    
}
