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
public interface FileService extends SciJavaService  {
    
    public File getFile();
    public void setFile(File file);

    public void addFile(String source);
    
    
}
