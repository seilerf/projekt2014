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
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author fseiler
 */
public class Musikd implements IMusikd, Serializable {
    
    private IMusikd intfMusikd = null;
    
    //private String serveradress;
    
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
}
