/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


/**
 * Bei dieser Musikd Klasse handelt es sich um die Webserver seitige Implementation des
 * Musikdienstes. 
 * @author fseiler/M.Ullmann
 */
public class Musikd implements IMusikd, Serializable {
    
    //Interface des Musikdienstes
    private IMusikd intfMusikd = null;
    //Übergabe Paramter, für den Mp3-Pfad
    private String part;
    //Referenz String für den Kommentar-Titel
    private String comTitle;
    //Referenz String für die Kommentar-Beschreibung
    private String comDesc;
    //Referenz auf die Mp3-Id für einen neuen Kommentar
    private int comRefMp3;
    //Referenz auf die Künstler-Id für einen neuen Kommentar
    private int comRefArt;
    //Referenz auf die Kommentar-Id für das Löschen eines Kommentares
    private int comRefDel;

    /**
     * Konstruktor mit super-Aufruf, sowie dem lookupRmi für das Musikdienst-Interface
     */
    public Musikd(){
        super();
        this.lookupRMI();
    }
    
    /**
     * Die lookupRMI Methode holt sich das vom Musikdienst registrierte Interface.
     * Dabei ist im wesentlichen zu beachten, dass der Port sowie der lookup-Name
     * die gleichen sind wie bei der Registrierung.
     */
    private void lookupRMI(){
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            System.out.println("Registry erkannt");
            this.intfMusikd = (IMusikd) registry.lookup("Musikd");
            //this.intfMusikd = (IMusikd) registry.lookup("rmi://localhost/Musikd_1");
            System.out.println("Lookup für das MusikInterface ausgeführt");

        } catch (RemoteException ex) {
            System.err.println("RemotExeption beim RMI-Lookup aufgetreten (Klasse: "+ getClass().getName() +")");
        } catch (NotBoundException ex) {
            System.err.println("NotBoundException aufgetreten. Objekt nicht gefunden.");
        } 
    }

    /**
     * Testfunktion für die Applikation keine Bedeutung.
     * @return
     */
    @Override
    public String test() {
        return intfMusikd.test();
    }
    
    /**
     * War nur zu Testzwecken implementiert, findet ebenfalls in der Applikation keine Anwendung.
     * @param mp3_title
     * @param mp3_Artist
     * @param mp3_name 
     */
    @Override
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Aufruf der deleteMp3 Methode über das Musikdienst-Interface.
     * @param mp3_id    -> Referenz auf die Mp3-Id
     */
    @Override
    public void deleteMp3(int mp3_id) {
        intfMusikd.deleteMp3(mp3_id);
    }

    /**
     * Aufruf der getMp3 Methode über das Musikdienst-Interface.
     * @param mp3_id    -> Referenz auf die Mp3-Id 
     * @return 
     */
    @Override
    public String[] getMp3(int mp3_id) {
        String[] mp3 = (String[]) intfMusikd.getMp3(mp3_id);
        return mp3;
    }

    /**
     * War nur zu Testzwecken implementiert, findet ebenfalls in der Applikation keine Anwendung.
     * @param mp3ArtistId
     * @return 
     */
    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * Aufruf der getAllMp3 Methode über das Musikdienst-Interface.
     * @return  -> Liste mit String-Arrays von Mp3-Dateien
     */
    @Override
    public List<String[]> getAllMp3() {
        return intfMusikd.getAllMp3();
    }
    
    /**
     * Aufruf der getAllArtist Methode über das Musikdienst-Interface.
     * @return  -> Liste mit String-Arrays von Künstlern
     */
    @Override
    public List<String[]> getAllArtist() {
        return intfMusikd.getAllArtist();
    }
    
    /**
     * Setter für die Serveradress1 über das Musikdienst-Interface.
     * @param serveradress1 
     */
    @Override
    public void setServeradress1(String serveradress1)  {
      intfMusikd.setServeradress1(serveradress1);
    }
    
    /**
     * Setter für die Serveradress2 über das Musikdienst-Interface.
     * @param serveradress2 
     */
    @Override
    public void setServeradress2(String serveradress2) {
        intfMusikd.setServeradress2(serveradress2);
    }
    
    /**
     * Getter für die Serveradress1 über das Musikdienst-Interface.
     * @return 
     */
    @Override
    public String getServeradress1()  {
      return intfMusikd.getServeradress1();
    }
    
    /**
     * Getter für die Serveradress2 über das Musikdienst-Interface.
     * @return 
     */
    @Override
    public String getServeradress2() {
       return intfMusikd.getServeradress2();
    }

    /**
     * Getter für die Serveranzahl über das Musikdienst-Interface.
     * @return 
     */
    @Override
    public int getAnzserv() {
    return intfMusikd.getAnzserv();
    }
    
    /**
     * Setter für die Serveranzahl über das Musikdienst-Interface.
     * @param anzserv   -> Referenz auf die Serveranzahl
     */
    @Override
     public void setAnzserv(int anzserv)  {
        intfMusikd.setAnzserv(anzserv);
    }
    
     /**
      * Getter für die Balancemethod über das Musikdienst-Interface.
      * @return 
      */
    @Override
    public boolean getBalancemethod() {
     return intfMusikd.getBalancemethod();
    }
    
    /**
     * Setter für die Balancemethod über das Musikdienst-Interface.
     * @param balancemethod 
     */
    @Override
    public void setBalancemethod(boolean balancemethod)  {
        intfMusikd.setBalancemethod(balancemethod);
    }
    
    /**
     * BalanceMethod Aufruf über das Musikdienst-Interface.
     */
    @Override
    public void balanceMethod()  {
        intfMusikd.balanceMethod();
    }
    
    /**
     * Setter für das Interval über das Musikdienst-Interface.
     * @param interval 
     */
    @Override
    public void setInterval(int interval)  {
        intfMusikd.setInterval(interval);
    }
    
    /**
     * Getter für das Interval über das Musikdienst-Interface.
     * @return 
     */
    @Override
    public int getInterval()  {  
        return intfMusikd.getInterval();
    }

    /**
     * Getter für das File über das Musikdienst-Interface.
     * @param mp3_id    -> Referenz auf die Mp3-Id
     * @return          -> Liefert den entsprechenden Bytestrom zurück
     */
    @Override
    public byte[] getFile(int mp3_id) {
        byte[] file = intfMusikd.getFile(mp3_id);
        return file;
    }
    
    /**
     * Upload Aufruf über das Musikdienst-Interface.
     */
    public void upLoad() {
     intfMusikd.upLoad(part);
    }
    
    /**
     * Setter für den Referenz String des Mp3-Pfades.
     * @param part  -> Referenz String für den Mp3-Pfad 
     */
    public void setPart(String part) {
        this.part = part;
    }
    
    /**
     * Getter für den Referenz String des Mp3-Pfades.
     * @return  -> Gibt den Referenz-Pfad zurück
     */
    public String getPart() {
        return this.part;
    }
    
    /**
     * Setter für den Kommentar-Titel.
     * @param comTitle  -> Kommentar-Titel 
     */
    public void setComTitle(String comTitle) {
        this.comTitle = comTitle;
    }
    
    /**
     * Getter für den Kommentar-Titel.
     * @return  -> Kommentar-Titel
     */
    public String getComTitle() {
        return this.comTitle;
    }
    
    /**
     * Setter für die Kommentar-Beschreibung.
     * @param comDesc   -> Kommentar-Beschreibung
     */
    public void setComDesc(String comDesc) {
        this.comDesc = comDesc;
    }
    
    /**
     * Getter für die Kommentar-Beschreibung.
     * @return  -> Kommentar-Beschreibung
     */
    public String getComDesc() {
        return this.comDesc;
    }
    
    /**
     * Getter für die Referenz der Kommentar Mp3-Id.
     * @return  -> Kommentar Mp3-Id
     */
    public int getComRefMp3() {
        return this.comRefMp3;
    }
    
    /**
     * Setter für die Referenz der Kommentar Mp3-Id.
     * @param refMp3    -> Kommentar Mp3-Id
     */
    public void setComRefMp3(int refMp3) {
        this.comRefMp3 = refMp3;
    }
    
     /**
     * Getter für die Referenz der Kommentar Künstler-Id.
     * @return  -> Kommentar Künstler-Id
     */
    public int getComRefArt() {
        return this.comRefArt;
    }
    
    /**
     * Setter für die Referenz der Kommentar Künstler-Id.
     * @param refArt    -> Kommentar Künstler-Id 
     */
    public void setComRefArt(int refArt) {
        this.comRefArt = refArt;
    }
    
    /**
     * Getter für die Referenz-Id des zu löschenden Kommentares.
     * @return -> Referenz der Kommentar-Id
     */
    public int getComRefDel() {
        return this.comRefDel;
    }
    
    /**
     * Setter für die Referenz-Id des zu löschenden Kommentares.
     * @param refDel -> Referenz der Kommentar-Id
     */
    public void setComRefDel(int refDel) {
        this.comRefDel = refDel;
    }
    
    /**
     * 
     * @param part 
     */
    @Override
    public void upLoad(String part) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
     * Aufruf der mp3Download Methode über das Musikdienst-Interface.
     * @param mp3_title -> Referenz auf den Mp3-Titel
     * @param mp3_id    -> Referenz auf die Mp3-Id
     */
    @Override
    public void mp3Download(String mp3_title, int mp3_id) {
        intfMusikd.mp3Download(mp3_title, mp3_id);
    }

    /**
     * Aufruf der addCommentForMp3 Methode über das Musikdienst-Interface.
     */
    @Override
    public void addCommentForMp3() {
         intfMusikd.addCommentForMp3(comTitle, comDesc, this.comRefMp3);
    }
    
    /**
     * Aufruf der addCommentForArt Methode über das Musikdienst-Interface.
     */
    @Override
    public void addCommentForArt() {
         intfMusikd.addCommentForArt(comTitle, comDesc, this.comRefArt);
    }
    
    /**
     * Aufruf der deleteComment Methode über das Musikdienst-Interface.
     */
    @Override
    public void deleteComment() {
        intfMusikd.deleteComment(this.comRefDel);
    }
    
    /**
     * Aufruf der deleteComment Methode über das Musikdienst-Interface.
     * @param com_Id    -> Referenz auf die Kommentar-Id
     */
    @Override
    public void deleteComment(int com_Id) {
        intfMusikd.deleteComment(com_Id);
    }

    /**
     * Aufruf der getAllComment Methode über das Musikdienst-Interface.
     * @return  -> Liste von String Arrays, mit allen Kommentaren
     */
    @Override
    public List<String[]> getAllComment() {
       return intfMusikd.getAllComment();
    }

    /**
     * Aufruf der getAllCommentForArt Methode über das Musikdienst-Interface.
     * @param refArt    -> Referenz auf die Künstler-Id
     * @return          -> Falls größer Null wird eine Liste von String-Arrays, mit entsprechenden Kommentaren zurückgegeben
     */
    @Override
    public List<String[]> getAllCommentForArt(int refArt) {
        if(refArt > 0) {
        this.comRefArt = refArt;      
        return intfMusikd.getAllCommentForArt(refArt);
        } else {
            return null;
        }
    }

    /**
     * Aufruf der getAllCommentForTitle Methode über das Musikdienst-Interface.
     * @param refMp3    -> Referenz auf die Mp3-Id
     * @return          -> Falls größer Null wird eine Liste von String-Arrays, mit entsprechenden Kommentaren zurückgegeben
     */
    @Override
    public List<String[]> getAllCommentForTitle(int refMp3) {
        if(refMp3 > 0) {
        this.comRefMp3 = refMp3;
        return intfMusikd.getAllCommentForTitle(refMp3);
        } else {
            return null;
        }
    }

    /**
     * Aufruf der addCommentForMp3 Methode über das Musikdienst-Interface.
     * @param comTitle  -> Referenz auf den Kommentar-Titel
     * @param comDesc   -> Referenz auf die Kommentar-Beschreibung
     * @param refMp3    -> Referenz auf die Mp3-Id
     */
    @Override
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) {
        intfMusikd.addCommentForMp3(comTitle, comDesc, refMp3);
    }
    
    /**
     * Aufruf der addCommentForArt Methode über das Musikdienst-Interface.
     * @param comTitle   -> Referenz auf den Kommentar-Titel
     * @param comDesc    -> Referenz auf die Kommentar-Beschreibung
     * @param refArt     -> Referenz auf die Künstler-Id
     */
    @Override
    public void addCommentForArt(String comTitle, String comDesc, int refArt) {
        intfMusikd.addCommentForArt(comTitle, comDesc, refArt);   
    }
 
}
