/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmibsp;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.*;
/**
 *
 * @author KingDCB
 */
public class DienstServerImpl extends UnicastRemoteObject implements DienstServer {
    HashMap<String, Dienst> dienstListe;
    
    public DienstServerImpl() throws RemoteException {
        diensteEinrichten();
    }
    
    private void diensteEinrichten(){
        dienstListe = new HashMap<String, Dienst>();
        System.out.println("diensteEinrichten");
    }
    
    public Object[] getDienstListe(){
        System.out.println("in Remote");
        return dienstListe.keySet().toArray();
    }
    
    public Dienst getDienst(Object dienstSchlüssel) throws RemoteException{
        Dienst derDienst = dienstListe.get(dienstSchlüssel);
        return derDienst;
    }
    
    public void testrmi(){
        try{
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/DienstServer", new DienstServerImpl());
            System.out.println("Remote-Dienst läuft!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}
