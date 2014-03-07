/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import static java.lang.String.valueOf;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fseiler
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{

    private IMusikd iMusikd;

  
    private String serveradress1= "localhost";
    private String serveradress2= "localhost";
    private int serveranzahl=2;

    public int getServeranzahl() {
        return serveranzahl;
    }

    public void setServeranzahl(int serveranzahl) {
        this.serveranzahl = serveranzahl;
    }

    private IMusikd iServer1;
    private IMusikd iServer2;
    private int ghf=0;
    
    public Musikd() throws RemoteException{
        this.registerForRmi();
        this.lookupRmi();
    }
    
    
      public String getServeradress1() {
        return serveradress1;
    }

    public void setServeradress1(String serveradress1) {
        this.serveradress1 = serveradress1;
        System.out.println("Loadbalancer: server1 adresse "+serveradress1);

    }

    public String getServeradress2() {
        return serveradress2;
    }

    public void setServeradress2(String serveradress2) {
        this.serveradress2 = serveradress2;
        System.out.println("Loadbalancer: server2 adresse "+serveradress2);

    }
    
    private void registerForRmi(){
        try {        
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            System.err.println("Loadbalancer: Port 1099 bereits belegt.");
        } try {
            System.out.println("Loadbalancer: Dientse für RMI registrieren...");

            this.iMusikd = this;
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Musikd", iMusikd);

            System.out.println("Loadbalancer: RMI-Dienste registriert!");
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lookupRmi(){
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            this.iServer1 = (IMusikd) registry.lookup("rmi://"+serveradress1+"/Musikd_1");
        } catch(RemoteException ex){
            System.err.println("Loadbalancer: RemoteException beim RMI-Lookup aufgetreten!");
        } catch (NotBoundException ex) {
            System.err.println("Loadbalancer: Beim RMI-Lookup wurde kein Objekt unter \"/Musikd_1\" gefunden!");
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            this.iServer2 = (IMusikd) registry.lookup("rmi://"+serveradress2+"/Musikd_2");
        } catch(RemoteException ex){
            this.iServer2 = null;
            System.err.println("Loadbalancer: RemoteException beim RMI-Lookup aufgetreten!");
        } catch (NotBoundException ex) {
            this.iServer2 = null;
            System.err.println("Loadbalancer: Beim RMI-Lookup wurde kein Objekt unter \"/Musikd_2\" gefunden!");
        }
    }

    public String getTest() {
      
        if(serveranzahl==2)
        {
        
        if (ghf == 1)
    {
           try {
               System.out.println("Methodenaufruf getTest von server 2");
               this.ghf=0;
               return this.iServer2.getTest();
               
           } catch (RemoteException ex) {
               Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    else{
           try {
               System.out.println("Methodenaufruf getTest von server 1");
               this.ghf+=1;
               return this.iServer1.getTest();
           } catch (RemoteException ex) {
               Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
           }

        }
        
    }
        else
             try {
               System.out.println("Methodenaufruf getTest von server 1");
               this.ghf+=1;
               return this.iServer1.getTest();
           } catch (RemoteException ex) {
               Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
           }
                return null;

    }
        
    

    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteMp3(int mp3_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getMp3(int mp3_id) {
       if (ghf == 1)
    {
           try {
               return this.iServer2.getMp3(mp3_id);
           } catch (RemoteException ex) {
               Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
           }
           System.out.println("Methodenaufruf getmp3 von server 2");
        this.ghf=0;
    }
    else{
           try {
               return iServer1.getMp3(mp3_id);
           } catch (RemoteException ex) {
               Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
           }
                 System.out.println("Methodenaufruf getmp3 von server 1");

        this.ghf+=1;
    }
        return null;
        
    }
    
    public String[] getMp3ByArtist(int mp3ArtistId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String[]> getAllMp3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
