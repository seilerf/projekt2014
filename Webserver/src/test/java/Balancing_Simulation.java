/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fh.ostfalia.projekt2014.rmi.Mp3DaoRmi;
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
   
          Mp3DaoRmi balancetest = new Mp3DaoRmi(); 

    public Balancing_Simulation() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void balancetest()
            
    {
        
        for(int i=0; i<50; i++)
        {           System.out.println("Testdurchlauf"+ i);
                  System.out.println(balancetest.getTest());

        }
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
