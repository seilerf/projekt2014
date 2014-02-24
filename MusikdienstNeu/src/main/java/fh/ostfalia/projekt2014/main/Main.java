/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.main;

import fh.ostfalia.projekt2014.dao.Mp3Dao;
import fh.ostfalia.projekt2014.model.Mp3;
import fh.ostfalia.projekt2014.rmi.Mp3DaoRmi;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fseiler
 */
public class Main {
    
    public static void main (String[] args){
        //Mp3Dao mp3Dao = new Mp3Dao();
        Mp3DaoRmi mp3DaoRmi;
        try {
            mp3DaoRmi = new Mp3DaoRmi(null);
            mp3DaoRmi.registerRMI();
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Mp3 mp3 = em.find(Mp3.class, 1);
        System.out.println("MP3: " + mp3.getMp3Title());
        
        em.close();
        emf.close();
        
    }
    
}
