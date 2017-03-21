/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;
import java.util.List;
import org.scijava.SciJava;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;

/**
 *
 * @author julien
 */
@Plugin(type=SciJava.class, priority=10)
public class DefaultFileService extends AbstractService implements FileService {
    
    
    private List <Files> filesList;
    
    @Override
    public List<Files> getFilesList() {
        return filesList;
    }
    
    public void updateData(String dirName){
        
        filesList.clear();
        File dir = new File(dirName);
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            System.out.println("");
        }
    }
    
}
