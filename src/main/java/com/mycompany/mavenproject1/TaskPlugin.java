/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.scijava.Context;
import org.scijava.plugin.SciJavaPlugin;

/**
 *
 * @author julien
 */

public interface TaskPlugin extends SciJavaPlugin {
    
    
    public void processTask(Task task, Context context);
    
    public void processTask (List <Task> t, Context context);
    
    
}
