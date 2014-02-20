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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Mp3 mp3 = new Mp3();
        mp3.setMp3Artist(null);
        mp3.setMp3_file(null);
        mp3.setMp3_title("Testtitel");
        
        em.close();
        emf.close();
       
        //Mp3DaoRmi mp3DaoRmi = new Mp3DaoRmi(mp3Dao);
        //mp3DaoRmi.registerRMI();
    }
    
}
