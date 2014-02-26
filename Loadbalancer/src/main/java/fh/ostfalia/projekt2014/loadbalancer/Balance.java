/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.rmi.IMusikd;
import java.net.MalformedURLException;
import static java.rmi.Naming.lookup;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anton
 */


/**
 * 
 * Methode um Anfragen 1-3 Servern zuzuweisen.
 * Die Anzahl und Adressen der jeweiligen Server wird dann über eine Webschnittstelle festlegbar sein.
 * Je nach dem wie viele Server vorhanden sind, wird dann automatisch die Balancing Methode ausgwählt.
 */
public class Balance {

    
     private int ghf = 0;
    private int anzserv=1;
    private String server1="localhost";
    private String server2;
    
    public Balance() {
    
    }
   
    
    

    public int getAnzserv() {
        return anzserv;
    }

    public void setAnzserv(int anzserv) {
        this.anzserv = anzserv;
    }

    public String getServer1() {
        return server1;
    }

    public void setServer1(String server1) {
        this.server1 = server1;
    }

    public String getServer2() {
        return server2;
    }

    public void setServer2(String server2) {
        this.server2 = server2;
    }

    public String getServer3() {
        return server3;
    }

    public void setServer3(String server3) {
        this.server3 = server3;
    }
    private String server3;
    
    
    
    
    
    
    public void Balances(){
        
    
        if (anzserv==1)
        {
            BalanceWith1();
        }
     if (anzserv==2 ){
         BalanceWith2();
         
     }
     
     else{
         BalanceWith3();
         
     }
        
  
    
            }
    
    
    
    public  String BalanceWith1(){
        
        
        
            return server1;
        
        
    }

    public void BalanceWith2(){
        
          if (ghf == 1)
    {
        try {
            IMusikd intf = (IMusikd) lookup(server1);
        } catch (NotBoundException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }
        ghf=0;
    }
    else{
        try {
            IMusikd intf = (IMusikd) lookup(server2);
        } catch (NotBoundException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }
        ghf+=1;
    }
        
    }
    
    public void BalanceWith3(){
        
    }
    
    
    
    

    
}
