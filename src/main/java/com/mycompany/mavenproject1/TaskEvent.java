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
public class TaskEvent extends SciJavaEvent {

    public final Task task;

    public TaskEvent(Task task) {
        this.task = task;
    }

    public TaskEvent() {
        this.task = null;
    }

    public Task getTask() {
        return task;
    }
    
        
}