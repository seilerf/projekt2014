/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.Id3Tag;
import fh.ostfalia.projekt2014.model.Mp3;
import fh.ostfalia.projekt2014.model.Mp3Artist;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
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
public class Mp3ArtistDao implements IMp3ArtistDao{
    @PersistenceContext
    private EntityManager em;
    @Resource
    UserTransaction ut;
    private Id3Tag id3;
    private Part part;

    @Override
    public void persistMp3Artist(Mp3Artist mp3Artist) {
        try {
            System.out.println("Beginne Persistierung der MP3-Artists...");
            ut.begin();
            System.out.println(mp3Artist.getArtistName());
            System.out.println(mp3Artist.getArtistId());
            em.persist(mp3Artist);
            ut.commit();

        } catch (RollbackException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(Mp3ArtistDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void upload() throws IOException {
        id3 = new Id3Tag();
        File file = new File("C:\\Users\\fseiler\\Documents\\GitHub\\projekt2014\\Upload\\" + part.getSubmittedFileName());

        Mp3Artist mp3Artist;

        Mp3 mp3 = new Mp3();
        mp3 = id3.readMp3File(file);
        System.out.println(mp3.getMp3Title());

        mp3Artist = mp3.getArtist();

        mp3Artist.addMp3(mp3);

        this.persistMp3Artist(mp3Artist);

    }

    @Override
    public void deleteMp3Artist(int mp3ArtistId) {
        em.remove(getMp3Artist(mp3ArtistId));
    }

    @Override
    public Mp3Artist getMp3Artist(int artistId) {
        return em.find(Mp3Artist.class, artistId);
    }

    public String getMp3ArtistNameByMp3Id(int mp3Id) {
        
         return em.find(Mp3.class, mp3Id).getArtist().getArtistName();
    }
    
     public String getMp3ArtistNameByArtistBean(Mp3Artist mp3Artist) {
         
         return mp3Artist.getArtistName();
    }

    @Override
    public List<Mp3Artist> getAllMp3Artist() {
        return em.createNamedQuery("Mp3Artist.getAll").getResultList();
    }

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
