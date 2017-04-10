/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import org.scijava.Context;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */
public class UiMain {

    @Parameter
    private static ContactService contactService;
    
    
    public UiMain(Context context){
        context.inject(this);
        Contact contact = new Contact();
        contactService.addContact(contact);
        
    }
}
