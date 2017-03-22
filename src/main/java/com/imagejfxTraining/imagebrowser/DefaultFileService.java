/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
@Plugin(type=SciJavaService.class, priority=10)
public class DefaultFileService extends AbstractService implements FileService {
    
    
    private final List <ItemFile> filesList = new ArrayList<>();
    
    @Override
    public List<ItemFile> getFilesList() {
        return filesList;
    }
    
    @Override
    public void updateData(String dirName){
        
        filesList.clear();
        File dir = new File(dirName);
        File[] listOfFiles = dir.listFiles();
        for (File file : listOfFiles) {
            ItemFile item = null;
            if (file.isDirectory())
                    item = new Directory(file.getPath());
            else if (isImage(file))
                    item = new ImageFile (file.getPath());
            if (item != null)
                filesList.add(item);
        }
    }
    
    
    
    public static boolean isImage(File file) {
        try {
            ImageIO.read(file);
            return true;
        } catch (IOException e) {
            return false;
        }
        
    }    

    @Override
    public void open(String fileName) {
        filesList.stream()
                .filter((file) -> (file.getName().equals(fileName)))
                .forEachOrdered((file) -> {
                    file.open();
        });
    
    }

    @Override
    public void delete(String fileName) {
        List <ItemFile> toRemove = filesList.stream().
                filter((f) -> f.getName().equals(fileName))
                .collect(Collectors.toList());
        filesList.removeAll(toRemove);
    }
}