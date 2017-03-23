/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;
import org.scijava.Context;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */


public class DirectoryFile extends File implements ItemFile {
    
    boolean selected;
    

    @Parameter
    private FileService fileService;
            
    public DirectoryFile(String pathname, Context context) {
        super(pathname);
        context.inject(this);
    }

    @Override
    public void open() {
        
        fileService.updateData(this.getPath());
    }

    
    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
