/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import java.io.File;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
public interface PropertyExerciseService extends SciJavaService  {
    
    public File getSourceFile();

    public void setSourceFile(File sourceFile) ;
      
    public File getTargetFile(); 
        
    public void setTargetFile(File targetFile);

    public void addFile(String source, FileType fileType);
    
    public void exchangeFiles();
    
}
