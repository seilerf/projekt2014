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
 * Interface für den Musikdienst, mit allen nötigen Funktionsaufrufen zur
 * Kommunikation mit LoadBalancer und Webserver.
 * @author fseiler/ M.Ullmann
 */
public interface IMusikd extends Remote{
    
    public String test() throws RemoteException;
    public void addMp3(String mp3_title, String mp3_Artist) throws RemoteException;
    public void deleteMp3(int mp3_id) throws RemoteException;
    public String[] getMp3(int mp3_id) throws RemoteException;
    public String[] getMp3ByArtist (int mp3ArtistId) throws RemoteException;
    public List<String[]> getAllMp3() throws RemoteException;
    public List<String[]> getAllArtist() throws RemoteException; 
    public void mp3Download(String mp3_title, int mp3_id) throws RemoteException;
    public void upLoad(String part) throws RemoteException;
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) throws RemoteException;
    public void addCommentForArt(String comTitle, String comDesc, int refArt) throws RemoteException;
    public void deleteComment(int com_Id) throws RemoteException;
    public List<String[]> getAllComment() throws RemoteException;
    public List<String[]> getAllCommentForArt(int refArt) throws RemoteException;
    public List<String[]> getAllCommentForTitle(int refMp3) throws RemoteException;
    
}
