/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
@Plugin(type=SciJavaService.class, priority=10)
public class DefaultFileService extends AbstractService implements FileService {
    
    private File previousFile;
    private final List <ItemFile> filesList = new ArrayList<>();
    private final List <ItemFile> displayedFiles = new ArrayList<>();
    
    @Parameter
    private EventService eventService;
    
    
    @Override
    public List<ItemFile> getFilesList() {
        File test;
        return filesList;
    }
    
    @Override 
    public File getParent(){
        return previousFile;
    }
    
    @Override
    public void updateData(String dirName){
        filesList.clear();
        displayedFiles.clear();

        
        File dir = new File(dirName);
        File parent = new File(dir.getParent());
        
        previousFile = parent;
        
        ItemFile previous = new DirectoryFile(parent.getPath(), eventService.context());
       
        
        File[] listOfFiles = dir.listFiles();
        
        for (File file : listOfFiles) {
            ItemFile item = null;
            if (file.isDirectory())
                item = new DirectoryFile(file.getPath(), eventService.context());
            else if (typeOfFile(file).equals("image"))
                item = new ImageFile (file.getPath());
            if (item != null)
                filesList.add(item);
        }
        
        filesList.forEach((t) -> displayedFiles.add(t));
        filesList.add(previous);
        eventService.publish(new UpdateEvent(displayedFiles));
    }
    
    @Override
    public void open(String fileName) {

        List <ItemFile> toOpen = filesList.stream()
                .filter((file) -> (file.getName().equals(fileName))).collect(Collectors.toList());
        toOpen.forEach(ItemFile::open);
        
    }

    @Override
    public void delete(String fileName) {
        List <ItemFile> toRemove = filesList.stream().
                filter((f) -> f.getName().equals(fileName))
                .collect(Collectors.toList());
        
        displayedFiles.removeAll(toRemove);
        
        eventService.publish(new UpdateEvent (displayedFiles));
        
    }
    
    private String typeOfFile(File file){
        String fileType = new String("");
     
        try {
            fileType = Files.probeContentType(file.toPath());
        } catch (IOException ex) {
            ex.getMessage();
        }
     
        return (fileType.split("/")[0]);
    
    }

    @Override
    public void filter(String str){
        displayedFiles.clear();
        filesList.forEach((t) -> displayedFiles.add(t));
        List<ItemFile> toRemove = filesList.stream().filter((file) -> (! file.getName().startsWith(str))).collect(Collectors.toList());
        displayedFiles.removeAll(toRemove);
        
        eventService.publish(new UpdateEvent(displayedFiles));
        
    }
    
}