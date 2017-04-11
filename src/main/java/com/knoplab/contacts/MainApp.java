
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knoplab.contacts;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scijava.SciJava;
import org.scijava.plugin.Parameter;

/**
 *
 * @author julien
 */
public class MainApp extends Application {
    
    
    public void start(Stage primaryStage) throws IOException {
        SciJava scijava = new SciJava();
        
        Parent root = new FXMLController(scijava.context());
        Scene scene = new Scene (root);

        primaryStage.setTitle("Contact App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        
        
}