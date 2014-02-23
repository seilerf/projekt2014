/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.balance;


import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Anton
 */
public class BalanceRmi implements IBalance, Serializable {
    
    
       private IBalance iBalance;
    
    public BalanceRmi(){
        super();
        this.lookupRMI();
    }
    
    public BalanceRmi(IBalance iBalance){
        this.iBalance = iBalance;
        this.lookupRMI();
    }

    private void lookupRMI(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1100);
            System.out.println("Registry erkannt");
            //Remote obj = (Remote) Naming.lookup("Mp3DaoRmi");
            this.iBalance = (IBalance) registry.lookup("Balance");
            System.out.println("Lookup für das Balancing ausgeführt");

        } catch (RemoteException ex) {
            System.err.println("RemotExeption beim RMI-Lookup aufgetreten (Klasse: "+ getClass().getName() +")");
        } catch (NotBoundException ex) {
            System.err.println("NotBoundException aufgetreten. Objekt nicht gefunden.");;
        } 
    }
    
  

    @Override
    public String getAdress() {
return iBalance.getAdress();
    }
    
}
