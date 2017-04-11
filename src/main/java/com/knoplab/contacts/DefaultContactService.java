/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author julien
 */
@Plugin (type=SciJavaService.class, priority=5)
public class DefaultContactService extends AbstractService implements ContactService {

    private final List <Contact> listContact = new ArrayList<>();
    
    public void init() throws IOException{
        
    }
    @Override
    public void addContact(Contact contact) {
        listContact.add(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        listContact.remove(contact);
        
    }

    @Override
    public void editContact(Contact contact, Callable c) {
        
    }    
    
}
