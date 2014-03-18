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
 * @author M.Ullmann
 */
public interface ICommentd extends Remote {
    
    public String getTest() throws RemoteException;
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) throws RemoteException;  
    public void addCommentForArt(String comTitle, String comDesc, int refArt) throws RemoteException; 
    public void deleteComment(int com_Id) throws RemoteException;
    public String[] getComment(int comment_ID) throws RemoteException;
    public List<String[]> getAllComment() throws RemoteException;
    public List<String[]> getAllCommentForTitle(int refMp3) throws RemoteException;
    public List<String[]> getAllCommentForArt(int refArt) throws RemoteException;
    
}
