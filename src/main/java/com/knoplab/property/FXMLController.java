/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.property;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.scijava.Context;
import org.scijava.plugin.Parameter;


/**
 *
 * @author julien
 */

public class FXMLController extends GridPane {
    
    @Parameter
    private Context context;
    
    private static final String SOURCE = "source";
    private static final String TARGET = "target";
    
    public FXMLController(Context context) throws IOException {
        
        context.inject(this);
        FXMLLoader loader = new FXMLLoader ();
        
        loader.setLocation(getClass().getResource("/fxml/Scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        viewInit();
                
     }
    
    private void viewInit() throws IOException{
        
        HBox source = new SubSceneController(SOURCE, context);
        HBox target = new SubSceneController(TARGET, context);
        
        GridPane.setConstraints(source, 0, 0);
        GridPane.setConstraints(target, 0, 2);
        
        this.getChildren().addAll(source, target);
        
    }
    
}
