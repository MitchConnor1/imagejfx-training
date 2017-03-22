/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.File;

/**
 *
 * @author julien
 */
public class Directory extends File implements ItemFile {
    
    boolean selected;

    public Directory(String pathname) {
        super(pathname);
    }

    @Override
    public void open() {
        System.out.println("DDD");
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
