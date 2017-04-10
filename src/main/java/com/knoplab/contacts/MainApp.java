
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import org.scijava.SciJava;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */
public class MainApp {
    
    
    public static void main(String[] args) {
        SciJava scijava = new SciJava();
        
        UiMain uiMain = new UiMain(scijava.context());
        
    }
    
    
}
