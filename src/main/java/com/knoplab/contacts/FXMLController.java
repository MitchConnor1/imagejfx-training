/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import java.io.IOException;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.scijava.Context;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */
public class FXMLController extends BorderPane{

    
    @FXML
    private ListView <Contact> listView;
    
    
    @Parameter
    private static ContactService contactService;
    
    
    public FXMLController(Context context) throws IOException{
        context.inject(this);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        Contact contact = new Contact();
        Image image = new Image("/fxml/failure.jpg");
        
        listView.setCellFactory(this::createContactCell);
        listView.setOrientation(Orientation.VERTICAL);
        listView.getItems().add(new Contact("Didier", "dd@gmail.com", "foqp", image));
        listView.getItems().add(new Contact("Didier", "dd@gmail.com", "foqp", image));
        listView.getItems().add(new Contact("Didier", "dd@gmail.com", "foqp", image));
        listView.getItems().add(new Contact("Didier", "dd@gmail.com", "foqp", image));
        
    }
    
    private ListCell <Contact> createContactCell(ListView <Contact> listView){
        
        return new ContactListCell();
    }
    
    private static class ContactListCell extends ListCell <Contact> {
        
        private HBox hbox;
        private VBox vbox;
        
        private ContactListCell (){
            super();
            hbox = new HBox();
            vbox = new VBox();
            this.itemProperty().addListener(this::onItemChanged);
        }
        
        private void onItemChanged(Observable obs, Contact oldValue, Contact newValue){
 
            if (newValue == null)
                this.setGraphic(null);
            
            
            else{
            this.setGraphic (new VerticalContactView (newValue) );
            }
        }
        
    }
        
}
