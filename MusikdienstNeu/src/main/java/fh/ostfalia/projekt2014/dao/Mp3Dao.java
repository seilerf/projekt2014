/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.Mp3;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author fseiler
 */
public class Mp3Dao implements IMp3Dao{
    
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction ut;
    private byte[] fileContent;
    private Mp3ArtistDao mp3ArtistDao;

    public Mp3Dao() {
    }
    
    public void addMp3List(ArrayList<Mp3> mp3List) {
        try {
            ut.begin();

            for (int i = 0; i <= mp3List.size(); i++) {
                Mp3 tempMp3 = mp3List.get(i);
                em.persist(tempMp3);
            }

            ut.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (RollbackException ex) {
            Logger.getLogger(Mp3Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(Mp3Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(Mp3Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Mp3Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(Mp3Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(Mp3Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteMp3(int mp3_id) {
        em.remove(getMp3(mp3_id));
    }

    @Override
    public Mp3 getMp3(int mp3_id) {
        return em.find(Mp3.class, mp3_id);
    }

    @Override
    public Mp3 getMp3ByArtist(int mp3ArtistId) {
        return em.find(Mp3.class, mp3ArtistId);
    }

    public int getMp3ArtistIdByMp3Id(int mp3_id) {
        Mp3 mp3=  em.find(Mp3.class, mp3_id);
        
        return mp3.getArtistId();
    }

    public String getMp3_title(int mp3_id) {
        return em.find(Mp3.class, mp3_id).getMp3_title();
    }

    public byte[] getMp3_file(int mp3_id) {
        return em.find(Mp3.class, mp3_id).getMp3_file();
    }

    @Override
    public List<Mp3> getAllMp3() {
        return em.createNamedQuery("Mp3.getAll").getResultList();
    }

    // Methoden zum Extrahieren der Id aus der URI, um Mp3 zu identifizieren 
    private String passedParameter;

    public String getPassedParameter() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.passedParameter = (String) facesContext.getExternalContext().
                getRequestParameterMap().get("id");
        return this.passedParameter;
    }

    public void setPassedParameter(String passedParameter) {
        this.passedParameter = passedParameter;
    }

    @Override
    public void addMp3(Mp3 mp3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
