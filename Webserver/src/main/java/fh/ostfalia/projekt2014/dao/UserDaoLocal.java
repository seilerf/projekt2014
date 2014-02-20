/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anton
 */

public interface UserDaoLocal extends Remote {

    void addUser(User user)throws RemoteException;

    void editUser(User user)throws RemoteException;

    void deleteUser(int userId)throws RemoteException;

    User getUser(int userId)throws RemoteException;

    List<User> getAllUsers()throws RemoteException;
    
    void testRMI() throws RemoteException;
    
}
