/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Diese Klasse dient für Zugriffe auf die User Datenbank bzw. zum User anlegen
 * 
 * 
 * @author Anton
 */


public class UserDao   {

    @PersistenceContext
    private EntityManager em;
    @Resource
    UserTransaction ut;

    
    public UserDao(){
        super();
    }

    /**
     * Legt den übergebenen User in der UserDatenbank an
     * @param user
     */
    public void addUser(User user) {
        try {

            ut.begin();
            em.persist(user);

            ut.commit();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sucht unter einer userId nach einem User Eintrag und liefert ihn als Ergebnis zurück
     * @param userId
     * @return
     */
    public User getUser(int userid) {
        return em.find(User.class, userid);
    }

    /**
     *
     * @return alle User
     */
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.getAll").getResultList();
    }

    

    

   


    
    
}
