/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.dao.Mp3Dao;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author fseiler
 */
public class Mp3DaoRmi extends UnicastRemoteObject implements IMp3{
    
    private Mp3Dao mp3Dao;
    
    public Mp3DaoRmi(Mp3Dao mp3Dao) throws RemoteException{
        this.mp3Dao = mp3Dao;
    }
    
    public void registerRMI(){
        try {
            System.out.println("Server: Dienst für RMI registrieren...");

            IMp3 intfMusikdienst = this;
            
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/AccessToMp3", intfMusikdienst);
            
            System.out.println("Server: Remote-Dienst registriert!");
            
        } catch (RemoteException ex) {
            System.err.println("Musikserver: RemoteException aufgetreten");
        } catch (MalformedURLException ex) {
            System.err.println("Musikserver: MalformedURLException aufgetreten");
        }
    }

    @Override
    public String getTest() throws RemoteException {
        return "Antwort von Server";
    }

    @Override
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMp3(int mp3_id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getMp3(int mp3_id) throws RemoteException {
        String title = mp3Dao.getMp3(mp3_id).getMp3_title();
        String artist = mp3Dao.getMp3(mp3_id).getMp3Artist().getArtistName();
        String mp3Id = String.valueOf(mp3Dao.getMp3(mp3_id).getMp3_id());
        String[] mp3 = {title, artist, mp3Id};

        return mp3;
    }

    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getAllMp3() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
