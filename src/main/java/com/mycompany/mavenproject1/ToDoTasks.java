/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
@Plugin(type = SciJavaService.class, priority = 10)
public class ToDoTasks extends AbstractService implements TaskService {

    @Parameter
    EventService eventService;

    List <Task> listOfTasks = new ArrayList <>();
    
    
    @Override
    public void addTask(String taskContent) {
        Task toCarry = new Task (taskContent, false);
       
        listOfTasks.add (toCarry);
        System.out.println("HEYYYYYYYYYYYYY");
        eventService.publish (new TaskAddedEvent(toCarry));

    }

    @Override
    public List<Task> getTaskList() {
        return (listOfTasks);
    }

    @Override
    public void removeTask(Task task) {
        listOfTasks.remove (task);
    }
    
}
