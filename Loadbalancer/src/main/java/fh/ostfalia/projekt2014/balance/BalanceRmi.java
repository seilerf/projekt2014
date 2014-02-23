/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.balance;

import fh.ostfalia.projekt2014.loadbalancer.Balance;
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
    
    public BalanceRmi(Balance balance) throws RemoteException{
        this.balance = balance;
    }
    
    public void registerRMI() {
        try {
            System.out.println("Server: Dienst für RMI registrieren...");

            IBalance intbalance = this;
            
            
            LocateRegistry.createRegistry(1100);  
        Registry registry = LocateRegistry.getRegistry(1100);  
        registry.rebind("Balance", intbalance);
            
           // LocateRegistry.createRegistry(1099);
           // Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            //registry.rebind("rmi://localhost/Balance", intbalance); 
           // Naming.rebind("rmi://localhost/Balance", intbalance);
            
            System.out.println("LoadBalancer: Remote-Dienst registriert!");
            
        } catch (RemoteException ex) {
            System.err.println("LoadBalancer: RemoteException aufgetreten");
            ex.printStackTrace();
        }
        
        
        
    }

    @Override
    public String getAdress() throws RemoteException {
        
        
        Balance balance = new Balance();
        String adress = balance.BalanceWith1();
        
        System.out.println(adress);
        
        return adress;
    }

}


