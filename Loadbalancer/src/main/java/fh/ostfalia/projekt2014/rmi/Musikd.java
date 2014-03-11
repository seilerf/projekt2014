

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.loadbalancer.Balance;
import fh.ostfalia.projekt2014.loadbalancer.BalanceTimer;
import fh.ostfalia.projekt2014.rmi.IMusikd;
import static java.lang.String.valueOf;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fseiler
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{

    private IMusikd iMusikd;
    
    private Balance balance;
    
      private IMusikd iServer1;
    private IMusikd iServer2;
    private IMusikd iServer333;
    
    private String serveradress1= "localhost";
    private String serveradress2= "localhost";
    
    private int ghf=0;
    
    
    

    public IMusikd getiServer1() {
        return iServer1;
    }

    public void setiServer1(IMusikd iServer1) {
        this.iServer1 = iServer1;
    }

    public IMusikd getiServer2() {
        return iServer2;
    }

    public void setiServer2(IMusikd iServer2) {
        this.iServer2 = iServer2;
    }

    
  
    
    
    public Musikd(int g) throws RemoteException{
        
    }
    
    public Musikd() throws RemoteException{
        Balance balance = new Balance();
        this.balance= balance;
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

    
    
   
    public String test()   {
        
            
            System.out.println("gettest loadbalancer aufgerufen");
        try {
            this.iServer333=balance.balancieren(Musikd.this);
            return iServer333.test();
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
           
    }
        
    
 public void addMp3(String mp3_title, String mp3_Artist) {
        try {
            balance.balancieren(Musikd.this).addMp3(mp3_title, mp3_Artist);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteMp3(int mp3_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getMp3(int mp3_id) throws RemoteException {
       
            System.out.println("Methodenaufruf getmp3 von server 2");
           return balance.balancieren(Musikd.this).getMp3(mp3_id);
       
    }
    
    public String[] getMp3ByArtist(int mp3ArtistId) {
        try {
           return balance.balancieren(Musikd.this).getMp3ByArtist(mp3ArtistId);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String[]> getAllMp3() {
        try {
             balance.balancieren(Musikd.this).getAllMp3();
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
   public void balanceMethod() {
        balance.balanceMethod();
    }
    
}
