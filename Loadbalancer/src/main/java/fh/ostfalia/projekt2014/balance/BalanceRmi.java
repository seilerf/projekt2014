/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.balance;

import fh.ostfalia.projekt2014.loadbalancer.Balance;
import fh.ostfalia.projekt2014.rmi.IMusikd;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Anton
 */
public class BalanceRmi extends UnicastRemoteObject implements IBalance{

 private Balance balance;
 //private IBalance balancermi;
     
    public BalanceRmi() throws RemoteException   {
        //this.registerForRmi();
        
    
    }
    
 public void registerForRmi(){
       IBalance balancermi = this;
        try {
            LocateRegistry.createRegistry(1099);
        }
        catch (RemoteException ex){
            System.err.println("Musikdienst: Port 1099 bereits belegt.");
        } 
        try {    
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind("Balance", balancermi);
            System.out.println("Stub an \"/Musikd_1\" gebunden.");
        } 
        catch (RemoteException ex) {
            System.err.println("Musikdienst: RemoteException aufgetreten!");
            
        } catch (AlreadyBoundException ex) {
            try {
                Registry registry = LocateRegistry.getRegistry(1099);
                registry.bind("rmi://localhost/Balance2", balancermi);
                System.out.println("Stub an \"/Musikd_2\" gebunden.");
            } catch (RemoteException ex1) {
                Logger.getLogger(BalanceRmi.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (AlreadyBoundException ex1) {
                Logger.getLogger(BalanceRmi.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        System.out.println("Musikdienst: RMI-Dienste registriert!");        
    }

       
   
     public void setAnzserv(int anzserv){
         balance.setServeranzahl(anzserv);
     }
   
    public int getAnzserv(){
       return  balance.getServeranzahl();
     }

    
    public boolean getBalancemethod() {
        return balance.getBalancemethod();
    }
   
    public void setBalancemethod(boolean balancemethod) {
        balance.setBalancemethod(balancemethod);
    }
    
}


