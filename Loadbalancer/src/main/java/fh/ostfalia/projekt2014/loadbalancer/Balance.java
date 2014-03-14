

package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.rmi.IMusikd;
import fh.ostfalia.projekt2014.rmi.Musikd;

import java.util.Timer;

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

    
    private Timer timer ;
    
    private boolean balancemethod = false;
    
    private int serveranzahl=2;
   
   
    private int switchcount=0;
    private BalanceTimer balancetimer;
    private int interval=5;

   
    
    
    public Balance() {
        BalanceTimer balancetimer = new BalanceTimer();
        this.balancetimer=balancetimer;
    
    }

    /**
     *
     * @return serveranzahl
     */
    public int getServeranzahl() {
        return serveranzahl;
    }

    /**
     *
     * @param serveranzahl
     */
    public void setServeranzahl(int serveranzahl) {
        this.serveranzahl = serveranzahl;
    }

    /**
     *Hier wird nach vielen Bedingungen gebalanced und somit der Musikdienst ermittelt welcher die Anfrage bekommen soll
     * Zuerst wird nach der BalancierMethode unterschieden
     * false bedeutet Round Robin
     * dann nach der Serveranzahl ob 1 oder 2...wenn nur 1 dann bekommt man auch nur den ersten Musikdienst
     * 
     * dann folgt die Prüfung welche Musikdienst dran ist je nach switchcount
     * 
     * FÜR WEITER ERKLÄRUNG SIEHE SEITENKOMMENTARE
     * 
     * @param musik um das richtige Interface des Musikd zurückzugeben.
     * @return das entsprechende Interface des Musikdienstes, je nach dem wer die Anfrage bekommen soll
     */
    public IMusikd balancieren(Musikd musik){
        
        if(serveranzahl==2)
             {
        
                if(balancemethod==false)
                     {
         
                       if (switchcount == 1)                   //Counter welcher Musikdienst dran ist bei Round Robin...bei 1 --> Musikdienst 2
                          {
           
                             this.switchcount=0;              // wird dann wieder 0 gesetzt, damit das nächste mal der andere Musikdienst dran kommt
               
                             return musik.getiServer2();             
                           }
                       
                        else{                           // wenn Counter 0 ist dann return Musikdienst 1
                               this.switchcount=1;
                               System.out.println(musik.getiServer1().toString());
                               return musik.getiServer1();  
                            }
        
                     }
                else                  // wenn BalanceMethode auf Interval gestellt ist
                {  
                    if(balancetimer.getSwitchcount()==0)  // hier wird der Switchcounter von BalanceTimer nachgeschaut um zu prüfen 
                                                           //welches Musikdienst Interface zurückgegeben werden soll
                    {System.out.println("Methodenaufruf interval getTest von server 1");
                        return musik.getiServer1();
                    }
                    else
                    {System.out.println("Methodenaufruf interval getTest von server 2");
                        return musik.getiServer2();
                    }
                }
        
            }
           
        else{               //wenn es nur einen Musikdienst 1 gibt
             
               System.out.println("Methodenaufruf getTest von server 1");
               
               return musik.getiServer1();
            }
    }

    /**
     *Intitialisiert neuen Timer und neuen BalanceTimer
     * ruft dann alle paar sek. den BalanceTimer auf, welcher dann die Musikdienste switcht
     * je nach dem wie das Interval vom Administrator gesetzt wurde 
     * standardmäßig alle 5 sek.
     */
    public void startTimer(){
       this.timer = new Timer();
       this.balancetimer= new BalanceTimer();
       
       this.timer.schedule(balancetimer, 0, interval*1000);
      
  }

    /**
     * Beendet den aktuellen Timer
     * passiert wenn auf Round Robin wieder umgestellt wird
     */
    public void stopTimer(){
      
      this.timer.cancel();
      //timer.purge();
  }

    /**
     *Prüft welche BalanceMethode eingestellt ist und stoppt oder startet dementsprechend den Timer
     */
    public void balanceMethod( ){
      
      if(balancemethod==false){
          
          
          stopTimer();
      }
      else{
         
          startTimer();
      }
  }

    /**
     *
     * @return balancemethod
     * balancemethod= welche Balancier Methode zurzeit eingestellt ist
     */
    public boolean getBalancemethod() {
        return balancemethod;
    }

    /**
     *
     * @param balancemethod
     */
    public void setBalancemethod(boolean balancemethod) {
        this.balancemethod = balancemethod;
        System.out.println(balancemethod);
    }

    /**
     *
     * @return interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     *
     * @param interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }
   
       
}
