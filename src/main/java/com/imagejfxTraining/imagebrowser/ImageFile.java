/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author julien
 */
public class ImageFile extends File implements ItemFile {

    boolean selected;
    
    public ImageFile(String pathname) {
        super(pathname);
    }

    @Override
    public void open() {
        try {    
            Runtime.getRuntime().exec("eog "+ this.getPath());
        } catch (IOException ex) {
            ex.getMessage();
        }
       
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
