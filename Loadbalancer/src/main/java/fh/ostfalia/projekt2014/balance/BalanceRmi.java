/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.balance;

import fh.ostfalia.projekt2014.loadbalancer.Balance;
import fh.ostfalia.projekt2014.rmi.IMusikd;
import java.net.MalformedURLException;
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
 private IBalance iBalance;
    
    public BalanceRmi() throws RemoteException   {
        this.registerForRmi();
        
    
    }
    
   private void registerForRmi(){
        try {        
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            System.err.println("Loadbalancer: Port 1099 bereits belegt.");
        } try {
            System.out.println("Loadbalancer: Balance Dientse für RMI registrieren...");

            this.iBalance = this;
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Balance", iBalance);

            System.out.println("Loadbalancer: Balance RMI-Dienste registriert!");
        } catch (RemoteException ex) {
            Logger.getLogger(BalanceRmi.class.getName()).log(Level.SEVERE, null, ex);
        }
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


