/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author julien
 */
public class TaskAddedEvent extends TaskEvent {

    public TaskAddedEvent(Task task){
        
        super(task);
        
    }
    
}
