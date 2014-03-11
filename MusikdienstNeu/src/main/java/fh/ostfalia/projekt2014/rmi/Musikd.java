/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.model.Mp3;
import fh.ostfalia.projekt2014.model.Mp3Artist;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author fseiler
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{

    @PersistenceContext
    private final EntityManager em;
    private final EntityTransaction et;
            
    public Musikd() throws RemoteException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3");
        em = emf.createEntityManager();
        et = em.getTransaction();
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
    public String test() throws RemoteException {
        return "Musikdienst1 online!";
    }

    @Override
    public void addMp3(String mp3_title, String mp3_Artist) throws RemoteException {
        Mp3 mp3 = new Mp3();
        et.begin();
        List<Mp3Artist> queryArtist = em.createNamedQuery("Mp3Artist.findByName").setParameter("name", mp3_Artist).getResultList();
        et.commit();
        
        if (queryArtist.size() >= 1)
            mp3.setArtist(queryArtist.get(0));
        else{
            Mp3Artist mp3Artist = new Mp3Artist();
            mp3Artist.setArtistName(mp3_Artist);
            et.begin();
            em.persist(mp3Artist);
            et.commit();
        }

        mp3.setMp3Title(mp3_title);
        mp3.setMp3File(null);
        
        et.begin();
        em.persist(mp3);
        et.commit();
    }

    @Override
    public void deleteMp3(int mp3_id) throws RemoteException {
        et.begin();
        em.remove(getMp3(mp3_id));
        et.commit();
    }

    @Override
    public String[] getMp3(int mp3_id) throws RemoteException {
        et.begin();
        Mp3 mp3 = em.find(Mp3.class, mp3_id);
        et.commit();
        
        String[] mp3String = {mp3.getMp3Id().toString(), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
    }

    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) throws RemoteException {
        et.begin();
        Mp3 mp3 = em.find(Mp3.class, mp3ArtistId);
        et.commit();
        
        String[] mp3String = {mp3.getMp3Id().toString(), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
    }

    @Override
    public List<String[]> getAllMp3() throws RemoteException {
        et.begin();
        List<Mp3> mp3List = em.createNamedQuery("Mp3.findAll").getResultList();
        et.commit();
        
        List<String[]> mp3Strings = new LinkedList<>();
        int i;
        for (i=0; i<mp3List.size(); i++){
            Mp3 mp3 = mp3List.get(i);
            String[] mp3String = {mp3.getMp3Id().toString(), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
            boolean add = mp3Strings.add(mp3String);
        }
        
        return mp3Strings;
    }
}
