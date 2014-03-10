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
 * @author fseiler
 */
public class Mp3DaoRmi implements IMusikd, Serializable {
    
    private IMusikd intfMusikd = null;
    
    private String serveradress;
    
    public Mp3DaoRmi(){
        super();
        this.lookupRMI();
    }

    private void lookupRMI(){
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            System.out.println("Registry erkannt");
            this.intfMusikd = (IMusikd) registry.lookup("Musikd");
            System.out.println("Lookup für das MusikInterface ausgeführt");

        } catch (RemoteException ex) {
            System.err.println("RemotExeption beim RMI-Lookup aufgetreten (Klasse: "+ getClass().getName() +")");
        } catch (NotBoundException ex) {
            System.err.println("NotBoundException aufgetreten. Objekt nicht gefunden.");
        } 
    }
    
    @Override
    public String getTest() {
       // System.out.println("MP3: " + this.getMp3(4)[1]);
        return intfMusikd.getTest();
        
    }

    @Override
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMp3(int mp3_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getMp3(int mp3_id) {
        //this.lookupRMI();
        String[] mp3 = (String[]) intfMusikd.getMp3(mp3_id);
        System.out.println("Client --> Titel: " + mp3[4]);
        return (String[]) intfMusikd.getMp3(mp3_id);
    }

    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getAllMp3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
   
    
}
