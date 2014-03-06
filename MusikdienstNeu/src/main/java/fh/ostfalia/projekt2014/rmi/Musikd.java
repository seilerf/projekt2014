/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.dao.Mp3Dao;
import fh.ostfalia.projekt2014.model.Mp3;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fseiler
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{
    
    private EntityManagerFactory emf;
    private EntityManager em;
        
    public Musikd() throws RemoteException{
        this.emf = Persistence.createEntityManagerFactory("Mp3");
        this.em = emf.createEntityManager();
    }
    
    public void registerForRmi(){
        System.out.println("Musikdienst: Dienste für RMI registrieren...");
        IMusikd intfMusikdienst = this;
        
        try {
            LocateRegistry.createRegistry(1099);
        } 
        catch (RemoteException ex){
            System.err.println("Musikdienst: Port 1099 bereits belegt.");
        } 
        try {    
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind("rmi://localhost/Musikd_1", intfMusikdienst);
            System.out.println("Stub an \"/Musikd_1\" gebunden.");
        } 
        catch (RemoteException ex) {
            System.err.println("Musikdienst: RemoteException aufgetreten!");
            
        } catch (AlreadyBoundException ex) {
            try {
                Registry registry = LocateRegistry.getRegistry(1099);
                registry.bind("rmi://localhost/Musikd_2", intfMusikdienst);
                System.out.println("Stub an \"/Musikd_2\" gebunden.");
            } catch (RemoteException ex1) {
                Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (AlreadyBoundException ex1) {
                Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        System.out.println("Musikdienst: RMI-Dienste registriert!");
    }

    @Override
    public String getTest() throws RemoteException {
        System.out.println("md aufgerufen!!");
        return "Musikdienst online!";
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
        Mp3Dao mp3Dao = new Mp3Dao();
        Mp3 mp3 = mp3Dao.getMp3(mp3_id);
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
