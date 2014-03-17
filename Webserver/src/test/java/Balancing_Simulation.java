import fh.ostfalia.projekt2014.rmi.Musikd;

import org.junit.Test;


/**
 * Diese Tests beweisen die RMI Kommunikation vom Webserver bis hin zum Musikdienst und beweisen unter anderem auch
 * die beiden Loadbalancing Methoden.
 * @author Anton
 */
public class Balancing_Simulation {
   
          Musikd balancetest = new Musikd(); 
         // BalanceRmi balancetest2= new BalanceRmi();

    public Balancing_Simulation() {
    }
    
   /**
 *
 * Diese Methode ruft die test() Methode 50 mal über rmi auf, über den
 * Loadbalancer bis hin zum entsprechenden Musikdienst. Dabei entstehen die entsprechenden Ausgaben, 
 * welche beweisen, dass das Loadbalancing funktioniert. Wenn es funktioniert muss Musikdienst 1 online in der Ausgabe erscheinen. 
 * In der Loadbalancing Ausgabe wird die funktionalität der Round Robin Balancing Methode bewiesen.
 */
   @Test
    public void Round_Robin_Test()
            
    {
        
        for(int i=0; i<50; i++)
        {           System.out.println("Testdurchlauf"+ i);
                  System.out.println(balancetest.test());

        }
    }  
     /**
     *  Tut das gleiche wie der obere Test, aber diesmal mit dem Interval Balancing. Hierfür wird zunächst die Balance Methode auf true= interval gesetzt 
     *  und der Timer gestartet
     *  Hier wird die Methode 7 mal aufgerufen aber immer mit einer 3 sek. Verzögerung
     *  Bei einem voreingestelltem 5 sek.Intervall ist dann zu beobachten das manchmal 2 mal hintereinander der gleiche Musikdienst zurückgegeben wird.
     * @throws InterruptedException
     */
    @Test
    public void Interval_Test() throws InterruptedException
            
    {
        balancetest.setBalancemethod(true);
        balancetest.balanceMethod();
        
        for(int i=0; i<7; i++)
            
        {   
            Thread.sleep(3000);
            System.out.println("Testdurchlauf interval"+ i);
                
                  System.out.println(balancetest.test());

        }
        
        balancetest.setBalancemethod(false);
        balancetest.balanceMethod();
    }
    
}
