/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.scijava.Context;
import org.scijava.plugin.Plugin;

/**
 *
 * @author julien
 */



@Plugin(type=TaskPlugin.class, label = "Set all task done")
public class SelectAllTasks implements TaskPlugin {

    @Override
    public void processTask(Task task, Context context) {
        task.setSelected(true);
    }

    @Override
    public void processTask(List<Task> t, Context context) {
        t.forEach((task) -> task.setSelected(true)); //To change body of generated methods, choose Tools | Templates.
    }
    
}
