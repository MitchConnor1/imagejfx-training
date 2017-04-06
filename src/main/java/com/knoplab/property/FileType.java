/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

/**
 *
 * @author julien
 */
public enum FileType {

    SOURCE ("source"), 
    TARGET ("target");
    
    private final String typeName;
    
    FileType(String typeName){
        this.typeName = typeName;
        
        
    }
    
    public String getTypeName(){
        return typeName;
    }
    
    
}
