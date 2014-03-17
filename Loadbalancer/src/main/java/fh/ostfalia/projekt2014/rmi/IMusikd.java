/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Die Interfaces welche Registriert werden.
 * @author Anton
 */
public interface IMusikd extends Remote{

    public String test() throws RemoteException;
    public void addMp3(String mp3_title, String mp3_Artist) throws RemoteException;
    public void deleteMp3(int mp3_id) throws RemoteException;
    public String[] getMp3(int mp3_id) throws RemoteException;
    public String[] getMp3ByArtist (int mp3ArtistId) throws RemoteException;
    public List<String[]> getAllMp3() throws RemoteException;
    public List<String[]> getAllArtist() throws RemoteException;
    public byte[] getFile(int mp3_id) throws RemoteException;
    
    public void setServeradress1(String serveradress1) throws RemoteException;
    public void setServeradress2(String serveradress2) throws RemoteException;
    public String getServeradress1() throws RemoteException;
    public String getServeradress2() throws RemoteException;
    public void upLoad(String part) throws RemoteException;
    public void mp3Download(String mp3_title, int mp3_id) throws RemoteException;
    
    public void setAnzserv(int anzserv) throws RemoteException;
    public int getAnzserv() throws RemoteException;
    public boolean getBalancemethod() throws RemoteException;
    public void setBalancemethod(boolean balancemethod) throws RemoteException;
    public void balanceMethod() throws RemoteException;
    public int getInterval() throws RemoteException;
    public void setInterval(int interval) throws RemoteException;
    
    public void addCommentForMp3(String comTitle, String comDesc,int refMp3) throws RemoteException;
    public void addCommentForArt(String comTitle, String comDesc,int refArt) throws RemoteException;
    public void deleteComment(int com_Id) throws RemoteException;
    public void getAllComment() throws RemoteException;
    public void getAllCommentForArt(int refArt) throws RemoteException;
    public void getAllCommentForTitle(int refMp3) throws RemoteException;
    
}
