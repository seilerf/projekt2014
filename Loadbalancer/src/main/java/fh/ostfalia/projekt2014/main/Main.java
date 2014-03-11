/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.main;

import fh.ostfalia.projekt2014.rmi.Musikd;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fseiler
 */
public class Main {
    
    public static void main (String[] args){
        try {
            
        
            Musikd musikd = new Musikd();
           // musikd.getTest();
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}