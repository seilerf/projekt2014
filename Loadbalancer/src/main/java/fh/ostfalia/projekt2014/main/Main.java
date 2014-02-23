/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.main;

import fh.ostfalia.projekt2014.balance.BalanceRmi;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anton
 */
public class Main {
    
    
    public static void main (String[] args){
        //Mp3Dao mp3Dao = new Mp3Dao();
        BalanceRmi balanceRmi;
        try {
            balanceRmi = new BalanceRmi(null);
            balanceRmi.registerRMI();
            System.out.println("Registiriert");
            System.out.println(balanceRmi.getAdress());
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }
}