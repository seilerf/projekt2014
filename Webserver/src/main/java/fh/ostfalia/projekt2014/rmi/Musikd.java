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
 *
 * @author fseiler/M.Ullmann
 */
public class Musikd implements IMusikd, Serializable {
    
    private IMusikd intfMusikd = null;
    private String part;
    private String comTitle;
    private String comDesc;
    private int comRefMp3;
    private int comRefArt;
    private int comRefDel;

    
    
    public Musikd(){
        super();
        this.lookupRMI();
    }

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
     *
     * @return
     */
    @Override
    public String test() {
        return intfMusikd.test();
    }

    @Override
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMp3(int mp3_id) {
        intfMusikd.deleteMp3(mp3_id);
    }

    @Override
    public String[] getMp3(int mp3_id) {
        String[] mp3 = (String[]) intfMusikd.getMp3(mp3_id);
        return mp3;
    }

    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getAllMp3() {
        return intfMusikd.getAllMp3();
    }
    
    @Override
    public List<String[]> getAllArtist() {
        return intfMusikd.getAllArtist();
    }
    
    @Override
    public void setServeradress1(String serveradress1)  {
      intfMusikd.setServeradress1(serveradress1);
    }
    
    @Override
    public void setServeradress2(String serveradress2) {
        intfMusikd.setServeradress2(serveradress2);
    }
    
 
    
       @Override
    public String getServeradress1()  {
      return intfMusikd.getServeradress1();
    }
    
    @Override
    public String getServeradress2() {
       return intfMusikd.getServeradress2();
    }
    
   

    @Override
    public int getAnzserv() {
    return intfMusikd.getAnzserv();
    }
    
    @Override
     public void setAnzserv(int anzserv)  {
        intfMusikd.setAnzserv(anzserv);
    }
     
    @Override
    public boolean getBalancemethod() {
     return intfMusikd.getBalancemethod();
    }
    
    @Override
    public void setBalancemethod(boolean balancemethod)  {
        intfMusikd.setBalancemethod(balancemethod);
    }
    @Override
    public void balanceMethod()  {
        intfMusikd.balanceMethod();
    }
    
     @Override
    public void setInterval(int interval)  {
        intfMusikd.setInterval(interval);
    }
    @Override
    public int getInterval()  {
        
        return intfMusikd.getInterval();
    }

    @Override
    public byte[] getFile(int mp3_id) {
        byte[] file = intfMusikd.getFile(mp3_id);
        return file;
    }
    
    public void upLoad() {
     intfMusikd.upLoad(part);
    }
    
    public void setPart(String part) {
        this.part = part;
    }
    
    public String getPart() {
        return this.part;
    }
    
    public void setComTitle(String comTitle) {
        this.comTitle = comTitle;
    }
    
    public String getComTitle() {
        return this.comTitle;
    }
    
    public void setComDesc(String comDesc) {
        this.comDesc = comDesc;
    }
    
    public String getComDesc() {
        return this.comDesc;
    }
    
    public int getComRefMp3() {
        return this.comRefMp3;
    }
    
    public void setComRefMp3(int refMp3) {
        this.comRefMp3 = refMp3;
    }
    
    public int getComRefArt() {
        return this.comRefArt;
    }
    
    public void setComRefArt(int refArt) {
        this.comRefArt = refArt;
    }
    
    public int getComRefDel() {
        return this.comRefDel;
    }
    
    public void setComRefDel(int refDel) {
        this.comRefDel = refDel;
    }
    
    @Override
    public void upLoad(String part) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void mp3Download(String mp3_title, int mp3_id) {
        intfMusikd.mp3Download(mp3_title, mp3_id);
    }

    @Override
    public void addCommentForMp3() {
  
         intfMusikd.addCommentForMp3(comTitle, comDesc, this.comRefMp3);
    }
    
    @Override
    public void addCommentForArt() {
       
         intfMusikd.addCommentForArt(comTitle, comDesc, this.comRefArt);
    }
    
    @Override
    public void deleteComment() {
        
        intfMusikd.deleteComment(this.comRefDel);
    }
    
    @Override
    public void deleteComment(int com_Id) {
        System.out.println("Werde ich im Webserver aufgerufen?!");
        System.out.println("Welchen Wert hat die hier übergebene com_id: "+com_Id);
        intfMusikd.deleteComment(com_Id);
    }

    @Override
    public List<String[]> getAllComment() {
       return intfMusikd.getAllComment();
    }

    @Override
    public List<String[]> getAllCommentForArt(int refArt) {
        if(refArt > 0) {
        System.out.println("Wert von refArt vorher: "+refArt);
        System.out.println("Hier beim Aufruf angekommen");
        System.out.println("Welchen Wert hat comRefArt: "+this.comRefArt);
        this.comRefArt = refArt;
        System.out.println("Welchen Wert hat refArt: "+refArt);       
        return intfMusikd.getAllCommentForArt(refArt);
        } else {
            return null;
        }
    }

    @Override
    public List<String[]> getAllCommentForTitle(int refMp3) {
        if(refMp3 > 0) {
        System.out.println("Wert von refMp3 vorher: "+refMp3);
        System.out.println("Hier beim Aufruf angekommen");
        System.out.println("Welchen Wert hat comRefMp3 vorher: "+this.comRefMp3);
        this.comRefMp3 = refMp3;
        System.out.println("Welchen Wert hat refMp3: "+refMp3);
        return intfMusikd.getAllCommentForTitle(refMp3);
        } else {
            return null;
        }
    }

    @Override
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) {
        intfMusikd.addCommentForMp3(comTitle, comDesc, refMp3);
    }
    
    @Override
    public void addCommentForArt(String comTitle, String comDesc, int refArt) {
        intfMusikd.addCommentForArt(comTitle, comDesc, refArt);
        
    }
 
}
