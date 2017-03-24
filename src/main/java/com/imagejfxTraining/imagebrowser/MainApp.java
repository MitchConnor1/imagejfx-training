/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxTraining.imagebrowser;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scijava.SciJava;

/**
 *
 * @author julien
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        SciJava scijava = new SciJava();
        
        
//        The first folder in which the user arrives when launching the application
        String firstFolder = "/home";
        Parent root = new FXMLController(scijava.context(), firstFolder);
        
        Scene scene = new Scene(root, 1280, 960);
        primaryStage.setTitle("Image Browser");
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
