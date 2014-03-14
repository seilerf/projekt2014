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
    private Timer timer ;
    private boolean balancemethod = false;
    private int serveranzahl=2;
   
    private int ghf=0;
    private BalanceTimer balancetimer;
    
    public Balance() {
        BalanceTimer balancetimer = new BalanceTimer();
        this.balancetimer=balancetimer;
    }
    
    public int getServeranzahl() {
        return serveranzahl;
    }

    public void setServeranzahl(int serveranzahl) {
        this.serveranzahl = serveranzahl;
    }
   
    public IMusikd balancieren(Musikd musik){
        if(balancemethod==false){
            //System.out.println("Methodenaufruf2 getTest von server 2");
            if(serveranzahl==2){
                //System.out.println("Methodenaufruf3 getTest von server 2");
                if (ghf == 1){
                    System.out.println("Loadbalancer: Zähler bei 1, leite an Server 2 weiter...");
                    this.ghf=0;
                    System.out.println("\t" + musik.getiServer2().toString());
                    
                    return musik.getiServer2();
                }
                else{
                   System.out.println("Loadbalancer: Zähler bei 0, leite an Server 1 weiter...");
                   this.ghf=1;
                   System.out.println("\t" + musik.getiServer1().toString());
                   
                   return musik.getiServer1();
                }
            }
            else{
               System.out.println("Loadbalancer: Leite an Server 1 weiter...");

               return musik.getiServer1();
            }
        }
        else {  
            if(balancetimer.getSwitchcount()==0){
                System.out.println("Methodenaufruf interval getTest von server 1");
                return musik.getiServer1();
             }
             else{
                System.out.println("Methodenaufruf interval getTest von server 2");
                return musik.getiServer2();
             }
         }
    }
     
    public void startTimer(){
        this.timer = new Timer();
        System.out.println("Dem neuen timer das dingens zugewisen");
        this.balancetimer= new BalanceTimer();
        this.timer.schedule(balancetimer, 0, 5000);
    }
  
    public void stopTimer(){
        this.timer.cancel();
        //timer.purge();
    }
  
    public void balanceMethod( ){
        if(balancemethod==false){
            stopTimer();
        }
        else{
            startTimer();
        }
    }
  
    public boolean getBalancemethod() {
        return balancemethod;
    }

    public void setBalancemethod(boolean balancemethod) {
        this.balancemethod = balancemethod;
        System.out.println(balancemethod);
    }
}
