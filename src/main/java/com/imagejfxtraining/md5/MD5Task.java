/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imagejfxtraining.md5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import org.apache.commons.codec.binary.Hex;
/**
 *
 * @author julien
 */
public class MD5Task extends Task <Void>{

    File[] toTreat;
    
    
    public MD5Task(File[] files){
        super();
        toTreat = files;
        
    }
    
    @Override
    protected Void call() throws Exception {
        int length;
        length = toTreat.length;
        int cpt = 0;
        byte[] digest = null;
        
        File output = new File("output.txt");
        output.createNewFile();
        Path outFile = Paths.get(output.getName());
        List <String> init = Arrays.asList("MD5 List: ");
        Files.write(outFile, init);
        
        for(File file : toTreat){
            
            if (isCancelled()){
                this.updateMessage("Cancelled");
                return null;
            }
            this.updateMessage(file.getName());
            digest = computeMD5(file);
            this.writeInFile(file, outFile, digest);
            cpt++;
            this.updateProgress(cpt, length);

            try{
                Thread.sleep(1000);
            }

            catch(InterruptedException interrupted){
                this.updateMessage("Cancelled");
                return null;
            }
            
        }
        return null;
    }
    
    private byte[] computeMD5(File file){
        
        byte[] toReturn = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            try (InputStream is = Files.newInputStream(Paths.get(file.getPath()));
                        DigestInputStream dis = new DigestInputStream(is, md))
            {
                /* Read decorated stream (dis) to EOF as normal... */

            } catch (IOException ex) {
                Logger.getLogger(DefaultFileService.class.getName()).log(Level.SEVERE, null, ex);
            }
            toReturn = md.digest();
            
        }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DefaultFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return toReturn;
    }
    
    
    private void writeInFile(File currentFile, Path outFile, byte[] data) {

        String fromByte = new String (Hex.encodeHex(data));
        String toConcatenate = currentFile.getName() + ": " + fromByte;
        List<String> toWrite = Arrays.asList(toConcatenate);
        try
        {
            Files.write(outFile, toWrite, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(MD5Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}



