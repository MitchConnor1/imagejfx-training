/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.util.List;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
public interface FileService extends SciJavaService{
    
    public List<Files> getFilesList();
    
}
