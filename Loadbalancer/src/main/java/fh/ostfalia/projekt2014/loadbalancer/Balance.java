/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.rmi.IMusikd;
import fh.ostfalia.projekt2014.rmi.Musikd;
import java.net.MalformedURLException;
import static java.rmi.Naming.lookup;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anton
 */


/**
 * 
 * Methode um Anfragen 1-2 Servern zuzuweisen.
 * Die Anzahl und Adressen der jeweiligen Server wird dann über eine Webschnittstelle festlegbar sein.
 * Je nach dem wie viele Server vorhanden sind, wird dann automatisch die Balancing Methode ausgwählt.
 * Zusätzlich kann man noch zwischen 2 Balancing Methoden tauschen. 
 */
public class Balance {

    private Musikd musik ;
    
    private Timer timer = new Timer();
    
    private boolean balancemethod = false;
    
    private int serveranzahl=2;

    

    public int getServeranzahl() {
        return serveranzahl;
    }

    public void setServeranzahl(int serveranzahl) {
        this.serveranzahl = serveranzahl;
    }
    private int ghf=0;
    private BalanceTimer balancetimer;
    public Balance() {
    
    }
   
     public IMusikd balancieren(){
         
         if(balancemethod==false)
         {
         
            if(serveranzahl==2)
             {
        
        if (ghf == 1)
                    {
           
               System.out.println("Methodenaufruf getTest von server 2");
               this.ghf=0;
               return musik.getiServer2();
               
           
                     }
        else{
           
               System.out.println("Methodenaufruf getTest von server 1");
               this.ghf+=1;
               return musik.getiServer1();
           

        }
        
    }
            else{
             
               System.out.println("Methodenaufruf getTest von server 1");
               this.ghf+=1;
               return musik.getiServer1();
            }
         
         }
         else
         {
             if(balancetimer.getSwitchcount()==0)
             {
                 return musik.getiServer1();
             }
             else
             {
                 return musik.getiServer2();
             }
         }
    }

     
  public void startTimer(){
      

 timer.schedule(new BalanceTimer(), 0, 5000);
      
  }
  
  
  public void stopTimer(){
      
      timer.cancel();
  }
  
  public void balanceMethod(boolean balancemethod){
      
      if(balancemethod=false){
          
          this.balancemethod=balancemethod;
          stopTimer();
      }
      else{
          this.balancemethod=balancemethod;
          startTimer();
      }
  }
  
  
  public boolean getBalancemethod() {
        return balancemethod;
    }

    public void setBalancemethod(boolean balancemethod) {
        this.balancemethod = balancemethod;
    }
  
}
