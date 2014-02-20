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
public class Mp3DaoRmi implements IMp3, Serializable {
    
    private IMp3 intfMusikdienst;
    
    public Mp3DaoRmi(){
        super();
        this.lookupRMI();
    }
    
    public Mp3DaoRmi(IMp3 intfMusikdienst){
        this.intfMusikdienst = intfMusikdienst;
        this.lookupRMI();
    }

    private void lookupRMI(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            System.out.println("Registry erkannt");
            //Remote obj = (Remote) Naming.lookup("Mp3DaoRmi");
            this.intfMusikdienst = (IMp3) registry.lookup("AccessToMp3");
            System.out.println("Lookup für das MusikInterface ausgeführt");

        } catch (RemoteException ex) {
            System.err.println("RemotExeption beim RMI-Lookup aufgetreten (Klasse: "+ getClass().getName() +")");
        } catch (NotBoundException ex) {
            System.err.println("NotBoundException aufgetreten. Objekt nicht gefunden.");;
        } 
    }
    
    @Override
    public String getTest() {
        System.out.println("Client: " + intfMusikdienst.getTest());
        return intfMusikdienst.getTest();
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
        return intfMusikdienst.getMp3(mp3_id);
    }

    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getAllMp3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
