

import fh.ostfalia.projekt2014.balance.BalanceRmi;
import fh.ostfalia.projekt2014.rmi.Musikd;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anton
 */
public class Balancing_Simulation {
   
          Musikd balancetest = new Musikd(); 
         // BalanceRmi balancetest2= new BalanceRmi();

    public Balancing_Simulation() {
    }
    
   /**
 *
 * Diese Methode ruft die getTest() Methode 49 mal über rmi auf, über den
 * Loadbalancer bis hin zum entsprechenden Musikdienst. Dabei entstehen die entsprechenden Ausgaben, 
 * welche beweisen, dass das Loadbalancing funktioniert. Wenn es funktioniert müssen abwechselnd "Musikdienst online 1"
 * // "Musikdienst online 2" in der Ausgabe erscheinen.
 */
    
    @Test
    public void balancetest1()
            
    {
        
        for(int i=0; i<50; i++)
        {           System.out.println("Testdurchlauf"+ i);
                  System.out.println(balancetest.test());

        }
    }
     
   /** @Test
    public void balancetest2()
            
    {
        balancetest.setBalancemethod(true);
        
        for(int i=0; i<50; i++)
        {           System.out.println("Testdurchlauf"+ i);
                
                  System.out.println(balancetest.test());

        }
    }
    **/
}
