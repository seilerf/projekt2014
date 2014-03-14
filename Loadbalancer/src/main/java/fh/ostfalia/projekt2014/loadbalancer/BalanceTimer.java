
package fh.ostfalia.projekt2014.loadbalancer;

import java.util.TimerTask;

/**
 * Der BalanceTimer wird von der IntervalMethode alle 5 Sekunden aufgerufen und schaltet dann mit run() 
 * zwischen den Musikdiensten hin und her beim Aufruf
 * @author Anton
 */
public class BalanceTimer extends TimerTask{

   //Switchcount ist der Zähler der zwischen Musikdienst1 und Musikdienst2 schaltet
   int switchcount = 0;
   
   
public BalanceTimer() {
        super();
    }

    /**
     * 
     * @return switchcount
     */
    public int getSwitchcount() {
        return switchcount;
    }

   
   
    
    
    /**
     * Beim Aufruf wird geschaut ob der Count auf 1 oder 0 steht und dann
     * von 1 auf 0 gesetzt...vice versa
     * 
     * @param switchcount
     */
    public void run() {
       if (switchcount == 1)
           
    {
                                    System.out.println("Interval für Server2");
               this.switchcount=0;
     }
       
    else{
                                    System.out.println("Interval für Server1");
               this.switchcount=1;
              
        }
    }
    
    
    
    
    
    
 }


