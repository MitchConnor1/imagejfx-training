/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import javafx.scene.image.Image;

/**
 *
 * @author julien
 */
public class Contact {

    private int id;
    private String name;  
    private String mail;
    private String telephone;
    private Image image;

    
    public Contact(){
        
    }
    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
}
