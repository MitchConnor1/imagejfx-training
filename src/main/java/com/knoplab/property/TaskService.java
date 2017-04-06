/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import javafx.scene.control.ProgressBar;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
public interface TaskService extends SciJavaService {
        
    public void launchMD5();
    
    public void stopMD5();
    
}
