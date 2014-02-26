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
@Local
public interface UserDaoLocal  {

    void addUser(User user) ;

    void editUser(User user) ;

    void deleteUser(int userId) ;

    User getUser(int userId) ;

    List<User> getAllUsers() ;
    
    
    
}
