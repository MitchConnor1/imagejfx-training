/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;


import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
public interface ContactService extends SciJavaService {
    
    public void addContact (Contact contact);
    public void deleteContact (Contact contact);
    public void editContact(Contact contact, Callable c);
    
}
