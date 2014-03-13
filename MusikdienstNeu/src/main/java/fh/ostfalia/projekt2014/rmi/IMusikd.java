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
 *
 * @author fseiler
 */
public interface IMusikd extends Remote{
    
    public String test() throws RemoteException;
    public void addMp3(String mp3_title, String mp3_Artist) throws RemoteException;
    public void deleteMp3(int mp3_id) throws RemoteException;
    public String[] getMp3(int mp3_id) throws RemoteException;
    public String[] getMp3ByArtist (int mp3ArtistId) throws RemoteException;
    public List<String[]> getAllMp3() throws RemoteException;
    public byte[] getFile(int mp3_id) throws RemoteException;
    
}
