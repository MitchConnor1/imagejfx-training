/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import org.scijava.event.SciJavaEvent;

/**
 *
 * @author julien
 */
public class TaskAddedEvent extends SciJavaEvent {

    public final Task taskAdded;

    public TaskAddedEvent(Task taskAdded) {
        this.taskAdded = taskAdded;
    }

    public TaskAddedEvent() {
        this.taskAdded = null;
    }

    public Task getTaskAdded() {
        return taskAdded;
    }
    
        
}
