/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentService.dao;

import fh.ostfalia.projekt2014.commentService.model.Comment;
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
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author AdminMax
 */
public class CommentDao implements IntfCommentDao {
    
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction ut;
    
    public void addCommentToList(ArrayList<Comment> commentList) {
        try {
            ut.begin();
            
            for(int i=0; i<=commentList.size();i++) {
                Comment tempComment = commentList.get(i);
                em.persist(tempComment);
            }
            
            ut.commit();
            
        } catch (SystemException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.transaction.RollbackException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addComment(Comment c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteComment(Integer comment_ID) {
        em.remove(getComment(comment_ID));
    }

    public Comment getComment(Integer comment_ID) {
        return em.find(Comment.class, comment_ID);
    }

    @Override
    public List<Comment> getAllComment() {
        return em.createNamedQuery("Comment.getAll").getResultList();
    }
    
    public String getComment_Title(Integer comment_ID) {
        return em.find(Comment.class, comment_ID).getCommentTitle();
    }
    
    public Integer getComment_ID(Integer comment_ID) {
        return em.find(Comment.class, comment_ID).getCommentID();
    }
    
    public String getComment_Description(Integer comment_ID) {
        return em.find(Comment.class, comment_ID).getCommentDescription();
    }
    
    public Integer getComment_RefMusicTitleID(Integer comment_ID) {
        return em.find(Comment.class, comment_ID).getRefMusicTitleID();
    }
   
    //Methode zum Extrahieren der ID aus der URI, um das Comment zu identifizieren
    private String passedParameter;
    
    public String getPassedParameter() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.passedParameter = (String) facesContext.getExternalContext().getRequestParameterMap().get("ID");
        return this.passedParameter;
    }
    
    public void SetPassedParameter(String passedParameter) {
        this.passedParameter = passedParameter;
    }
    
}
