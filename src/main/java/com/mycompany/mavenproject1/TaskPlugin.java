/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.scijava.plugin.SciJavaPlugin;

/**
 *
 * @author julien
 */

public interface TaskPlugin extends SciJavaPlugin {
    
    
    public List<Task> processTask(Task task);
    
    
}