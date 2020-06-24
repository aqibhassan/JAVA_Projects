/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsfinalproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author AQIB HASSAN
 */
public class RandomFileGenerator {
    RandomFileGenerator(int num) throws IOException
    {
        int n ;
      
Random rand = new Random();
        FileWriter fw=new FileWriter("output.txt"); 
        System.out.println("creation of file no:"+num);
        // read character wise from string and write  
        // into FileWriter  
//         n=num;
        for (int i = 0; i < num; i++) 
        {
            n= rand.nextInt(100000);
           
//            n=i+1;
            fw.write(""+n);
            fw.write(",");
            
        }
           
        fw.close();
    }
    
}
