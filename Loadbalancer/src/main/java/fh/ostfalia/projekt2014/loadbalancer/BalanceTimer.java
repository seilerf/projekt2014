/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import java.util.TimerTask;

/**
 *
 * @author Anton
 */
public class BalanceTimer extends TimerTask{

    
   int switchcount = 0;
   
   
public BalanceTimer() {
        super();
    }

    public int getSwitchcount() {
        return switchcount;
    }

    public void setSwitchcount(int switchcount) {
        this.switchcount = switchcount;
    }

   
    
    
    
    public void run() {
       if (switchcount == 1)
           
    {
               System.out.println("Interval für Server2");
               this.switchcount=0;
     }
       
    else{
               System.out.println("Interval für Server1");
               this.switchcount+=1;
              
        }
    }
    
    
    
    
    
    
 }


