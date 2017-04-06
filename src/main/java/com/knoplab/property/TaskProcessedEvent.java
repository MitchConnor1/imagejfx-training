/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import javafx.concurrent.Task;
import org.scijava.event.SciJavaEvent;

/**
 *
 * @author julien
 */
public class TaskProcessedEvent extends SciJavaEvent {

    private final Task task_;

    public TaskProcessedEvent(Task task){
        task_ = task;
    }
    
    public Task getTask(){
        return task_;
    }
    
}
