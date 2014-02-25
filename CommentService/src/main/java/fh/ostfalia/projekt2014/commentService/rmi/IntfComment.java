/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentService.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author AdminMax
 */
public interface IntfComment extends Remote {
    
    public String getTest() throws RemoteException;
    public void addComment(String comment_Title, String comment_Description) throws RemoteException;
    public void deleteComment(int comment_ID) throws RemoteException;
    public String[] getComment(int comment_ID) throws RemoteException;
    public List<String[]> getAllComment() throws RemoteException;
    
}
