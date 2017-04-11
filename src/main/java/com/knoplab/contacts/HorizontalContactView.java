/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author julien
 */
public class HorizontalContactView extends HBox {
    private ImageView image;
    private Label name;
    private Label email;
    private Label telephone;
    
    public HorizontalContactView(){
        super();
    }
    
    public HorizontalContactView(Image image, String name, String email, String telephone) {
        super();
        this.setPadding(new Insets (20, 20, 20, 20));
        this.setMaxSize(10, 10);
        this.image.setFitHeight(100);
        this.image.setFitWidth(100);
        this.name.setText(name);
        
        this.email.setText(email);
        this.telephone.setText(telephone);
        this.image.setImage(image);
        this.getChildren().addAll(this.image, this.name, this.email, this.telephone);

    }
    
    public HorizontalContactView(Contact contact){
        
        super();
        this.setPadding(new Insets (20, 20, 20, 20));
        this.setMaxSize(10, 10);
        this.image.setFitHeight(100);
        this.image.setFitWidth(100);
        this.name.setText(contact.getName());
        this.email.setText(contact.getMail());
        this.telephone.setText(contact.getTelephone());
        this.image.setImage(contact.getImage());
        this.getChildren().addAll(this.image, this.name, this.email, this.telephone);
                
    }
}
