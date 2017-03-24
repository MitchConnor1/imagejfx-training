/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author julien
 */

@Plugin(type = FilePlugin.class, label = "Previous File")
public class PreviousFile implements FilePlugin{

    
    @Parameter
    private FileService fileService;
    
    @Override
    public void processFile(String fileName) {
        
        File previousFile = fileService.getParent();
        fileService.open(previousFile.getName());
    }
    
}
