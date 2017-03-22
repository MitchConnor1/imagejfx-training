/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

/**
 *
 * @author julien
 */
public interface ItemFile{
    
    public void open();
    
    public String getName();

    public boolean isSelected();
    
    public void setSelected(boolean selected);
    
}
