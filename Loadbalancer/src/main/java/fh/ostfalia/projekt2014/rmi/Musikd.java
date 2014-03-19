

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.loadbalancer.Balance;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse holt sich die Registry der beiden Musikdienste und Registriert daraus eine neue 
 * mit den zusätzlichen Loadbalancer Methoden, welche der Webserver dann abruft.
 * @author Anton / M.Ullmann
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{

    private IMusikd iMusikd;
    private Balance balance;
    private IMusikd iServer1;
    private IMusikd iServer2;
    
    private String serveradress1= "localhost";
    private String serveradress2= "localhost";
    
    /**
     * Instanziiert eine neue Balance Klasse 
     * ruft die Registrierung auf, welche die Methoden des Loadbalancers registriert
     * ruft die look up Methode auf, welche sich die beiden Registry Einträge der Musikdienste holt
     * @throws RemoteException
     */
    public Musikd() throws RemoteException{
        this.balance =  new Balance();
        this.registerForRmi();
        this.lookupRmi();
    }
    /**
     *  Getter für iServer1
     * @return Interface1 vom Musikdienst1
     */
    public IMusikd getiServer1() {
        return iServer1;
    }

    /**
     * Setter für iServer1
     * @param iServer1
     */
    public void setiServer1(IMusikd iServer1) {
        this.iServer1 = iServer1;
    }

    /**
     * Getter für iServer2
     * @return Interface2 vom Musikdienst2
     */
    public IMusikd getiServer2() {
        return iServer2;
    }

    /**
     * Setter für iServer2
     * @param iServer2 
     */
    public void setiServer2(IMusikd iServer2) {
        this.iServer2 = iServer2;
    }

    /**
     * Getter für serveradress1
     * @return 
     */
    public String getServeradress1() {
        return serveradress1;
    }

    /**
     * Setter für serveradress1
     * @param serveradress1 
     */
    public void setServeradress1(String serveradress1) {
        this.serveradress1 = serveradress1;
        System.out.println("Loadbalancer: server1 adresse "+serveradress1);
    }

    /**
     * Getter für serveradress2
     * @return 
     */
    public String getServeradress2() {
        return serveradress2;
    }

    /**
     * Setter für serveradress2
     * @param serveradress2 
     */
    public void setServeradress2(String serveradress2) {
        this.serveradress2 = serveradress2;
        System.out.println("Loadbalancer: server2 adresse "+serveradress2);
    }
     
    /**
     * Holt sich die Registry unter Port 1099 und schaut nach den Musikdienst einträgen
     * unter berücksichtigung der jeweiligen Serveradresse des Musikdienstes
     * bindet den Eintrag unter iServer interface
     * Geschieht für beide Musikdienste, falls sie etwas Eingetragen haben 
     */
    private void lookupRmi(){
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            this.iServer1 = (IMusikd) registry.lookup("rmi://"+serveradress1+"/Musikd_1");
            System.out.println("Loadbalancer: Interface unter Musikd_1 gefunden!");
        } catch(RemoteException ex){
            System.err.println("Loadbalancer: RemoteException beim RMI-Lookup aufgetreten!");
        } catch (NotBoundException ex) {
            System.err.println("Loadbalancer: Beim RMI-Lookup wurde kein Objekt unter \"/Musikd_1\" gefunden!");
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }  
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            this.iServer2 = (IMusikd) registry.lookup("rmi://"+serveradress2+"/Musikd_2");
            System.out.println("Loadbalancer: Interface unter Musikd_2 gefunden!");
        } catch(RemoteException ex){
            this.iServer2 = null;
            System.err.println("Loadbalancer: RemoteException beim RMI-Lookup aufgetreten!");
        } catch (NotBoundException ex) {
            this.iServer2 = null;
            System.err.println("Loadbalancer: Beim RMI-Lookup wurde kein Objekt unter \"/Musikd_2\" gefunden!");
        }
    }
    
     /**
     *Bindet das IMusikd Interface unter Port 1099 mit dem Namen Musikd
     * Das Interface beinhaltet die Methoden des Musikdienstes, sowie auch die 
     * notwendigen Loadbalancer Methoden für den Webserver
     * 
     */
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
     
    /**
     * ruft balancieren der Balance Klasse auf
     * @return die getTest Methode des entsprechenden Musikdienstes
     */
    public String test()   {
        System.out.println("Im Loadbalancer angekommen!!");
        try {
            System.out.println("Im LoadBalancer gewesen!!");
           return iServer1.test();
             
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * ruft balancieren der Balance Klasse auf
     * addMp3 Methode des entsprechenden Musikdienstes
     * @param mp3_title
     * @param mp3_Artist
     */
    public void addMp3(String mp3_title, String mp3_Artist) {
        try {
            balance.balancieren(Musikd.this).addMp3(mp3_title, mp3_Artist);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ruft balancieren der Balance Klasse auf
     * @param mp3_id
     *  die deleteMp3 Methode des entsprechenden Musikdienstes
     */
    public void deleteMp3(int mp3_id) {
        try {
            balance.balancieren(Musikd.this).deleteMp3(mp3_id);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ruft balancieren der Balance Klasse auf
     * @param mp3_id
     *  die getMp3 Methode des entsprechenden Musikdienstes
     * @return 
     * @throws java.rmi.RemoteException
     */
    public String[] getMp3(int mp3_id) throws RemoteException {
        System.out.println("Methodenaufruf getmp3 von server 2");
        return balance.balancieren(Musikd.this).getMp3(mp3_id);
    }
    
     /**
     * ruft balancieren der Balance Klasse auf
     * @param mp3ArtistId
     * die getMp3ByArtist Methode des entsprechenden Musikdienstes
     * @return 
     */
    public String[] getMp3ByArtist(int mp3ArtistId) {
        try {
           return balance.balancieren(Musikd.this).getMp3ByArtist(mp3ArtistId);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return null;
    }

    /**
     * ruft balancieren der Balance Klasse auf
     * @return die getAllMp3 Methode des entsprechenden Musikdienstes
     */
    public List<String[]> getAllMp3() {
        try {
             return balance.balancieren(Musikd.this).getAllMp3();
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
      /**
       * Ruft die getAllArtist Methode über das Musikdienst Interface auf.
       * Zusätzlich wird der Aufruf noch über die Klasse Balance balanciert.
       * @return    -> Liste von String-Arrays, welche alle Künstler enthalten
       */
      public List<String[]> getAllArtist() {
        try {
             return balance.balancieren(Musikd.this).getAllArtist();
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
   /**
     *
     * @param anzserv
     */
    public void setAnzserv(int anzserv){
         balance.setServeranzahl(anzserv);
     }
   
    /**
     *
     * @return serveranzahl
     */
    public int getAnzserv(){
       return  balance.getServeranzahl();
     }

    /**
     *
     * @return BalanceMethode (true, false)
     */
    public boolean getBalancemethod() {
        return balance.getBalancemethod();
    }
   
    /**
     *
     * @param balancemethod
     */
    public void setBalancemethod(boolean balancemethod) {
        balance.setBalancemethod(balancemethod);
    }

    /**
     *ruft BalanceMethod von Klasse Balance auf
     * 
     */
    public void balanceMethod() {
        balance.balanceMethod();
    }

    /**
     *ruft setInterval von Klasse Balance auf
     * @param interval
     */
    public void setInterval(int interval){
        balance.setInterval(interval);
    }
    
    /**
     *ruft getInterval von Klasse Balance auf
     * @return interval
     */
    public int getInterval(){
       return balance.getInterval();
    }
    
    /**
     * Findet in der Applikation keine Verwendung mehr, diente Testzwecken.
     * @param mp3_id    -> Referenz auf eine Mp3-Id
     * @return
     * @throws RemoteException 
     */
    public byte[] getFile(int mp3_id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Die Upload Funktion ruft die, über das Musikdienst Interface definierte, Upload Methode mit dem String Paramater Part auf. 
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @param part -> Referenz-String auf den in der GUI ausgewählten Mp3-Pfad
     */
    public void upLoad(String part) {
      try {    
          balance.balancieren(Musikd.this).upLoad(part);      
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Die mp3Download Funktion ruft die, über das Musikdienst Interface definierte, mp3Download Methode mit den mit übergebenen Parametern auf.
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @param mp3_title -> Referenz auf den Mp3-Titel
     * @param mp3_id    -> Referenz auf die Mp3-Id
     */
    public void mp3Download(String mp3_title, int mp3_id) {
         try {    
          balance.balancieren(Musikd.this).mp3Download(mp3_title, mp3_id);      
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Die addCommentForMp3 Funktion ruft, die über Musikdienst Interface definierte, addCommentForMp3 Methode mit den mit übergebenen Parametern auf.
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @param comTitle  -> Referenz auf den Kommentar-Titel
     * @param comDesc   -> Referenz auf die Kommentar-Beschreibung
     * @param refMp3    -> Referenz auf die Mp3-Id
     * @throws RemoteException 
     */
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) throws RemoteException {
        try {    
          balance.balancieren(Musikd.this).addCommentForMp3(comTitle, comDesc, refMp3);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Die addCommentForArt Funktion ruft, die über Musikdienst Interface definierte, addCommentForArt Methode mit den mit übergebenen Parametern auf.
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @param comTitle  -> Referenz auf den Kommentar-Titel
     * @param comDesc   -> Referenz auf die Kommentar-Beschreibung
     * @param refArt    -> Referenz auf die Künstler-Id
     * @throws RemoteException 
     */
    public void addCommentForArt(String comTitle, String comDesc, int refArt) throws RemoteException {
        try {    
          balance.balancieren(Musikd.this).addCommentForArt(comTitle, comDesc, refArt);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Die deleteComment Funktion ruft, die über das Musikdienst Interface definierte, deleteComment Methode mit dem übergebenen Parameter auf.
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @param com_Id    -> Referenz auf die Kommentar-Id
     * @throws RemoteException 
     */
    public void deleteComment(int com_Id) throws RemoteException {
       try {    
          balance.balancieren(Musikd.this).deleteComment(com_Id);
        } catch (RemoteException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Die getAllComment Funktion ruft, die über das Musikdienst Interface definierte, getAllComment Methode auf.
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @return  ->Liste von String-Arrays
     * @throws RemoteException 
     */
    public List<String[]> getAllComment() throws RemoteException {
        
        return balance.balancieren(Musikd.this).getAllComment();
    }

    /**
     * Die getAllCommentForArt Funktion ruft, die über das Musikdienst Interface definierte, getAllCommentForArt Methode mit dem übergebenen Parameter auf.
     * Zusätzlich wird die aufgerufene Funktion gebalanct.
     * @param refArt    -> Referenz auf die Künstler-Id
     * @return
     * @throws RemoteException 
     */
    public List<String[]> getAllCommentForArt(int refArt) throws RemoteException {
   
        return balance.balancieren(Musikd.this).getAllCommentForArt(refArt);
    }

    public List<String[]> getAllCommentForTitle(int refMp3) throws RemoteException {
        
        return balance.balancieren(Musikd.this).getAllCommentForTitle(refMp3);
    }
        
}
