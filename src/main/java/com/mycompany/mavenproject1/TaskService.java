/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
public interface TaskService extends SciJavaService {
 
    public void addTask(String taskContent); 

    public List<Task> getTaskList();

    public void removeTask (Task task);

    public void removeSelectedTasks (List <Task> toRemove);
  
}

