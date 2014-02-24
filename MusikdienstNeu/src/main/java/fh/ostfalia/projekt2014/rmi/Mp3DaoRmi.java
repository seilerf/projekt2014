/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.dao.Mp3Dao;
import fh.ostfalia.projekt2014.model.Mp3;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Mp3 mp3 = em.find(Mp3.class, 1);
        System.out.println("MP3: " + mp3.getMp3Title());
        System.out.println("Künstler: " + mp3.getArtist().getArtistName());
        
        em.close();
        emf.close();
        
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mp3 mp3 = em.find(Mp3.class, mp3_id);
        em.close();
        emf.close();
        
        String[] mp3String = {String.valueOf(mp3.getMp3Id()), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
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
