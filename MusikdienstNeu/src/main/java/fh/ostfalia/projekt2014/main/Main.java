/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.main;

import fh.ostfalia.projekt2014.dao.Mp3Dao;
import fh.ostfalia.projekt2014.rmi.Mp3DaoRmi;
import java.rmi.RemoteException;

/**
 *
 * @author fseiler
 */
public class Main {
    
    public static void main (String[] args){
        Mp3Dao mp3Dao = new Mp3Dao();
        
        try {
            Mp3DaoRmi mp3DaoRmi = new Mp3DaoRmi(mp3Dao);
            mp3DaoRmi.registerRMI();
        } catch (RemoteException ex) {
            System.err.println("RemoteException!");
        }
    }
    
}
