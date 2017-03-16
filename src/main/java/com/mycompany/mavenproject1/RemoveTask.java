/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author julien
 */

@Plugin(type=TaskPlugin.class, label = "Set all task done")
public class RemoveTask implements TaskPlugin{

    @Parameter
    TaskService task;
        
    @Override
    public void processTask(Task t) {
        task.removeTask(t);
    }
    
}
