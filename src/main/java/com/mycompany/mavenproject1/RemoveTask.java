/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import org.scijava.Context;





/**
 *
 * @author julien
 */

@Plugin(type=TaskPlugin.class, label = "Remove selected tasks")
public class RemoveTask implements TaskPlugin{
    
    @Parameter
    TaskService task;
    
    @Override
    public void processTask(Task t, Context context) {
        context.inject (this);
        task.removeTask(t);
    }
    
    public void processTask (List <Task> t, Context context){
        context.inject(this);
        task.removeSelectedTasks(t);
        
    }
    
}
