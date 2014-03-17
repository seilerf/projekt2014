/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import fh.ostfalia.projekt2014.model.Comment;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author M.Ullmann
 */
public class Commentd extends UnicastRemoteObject implements ICommentd{
        
    @PersistenceContext
    private final EntityManager em;
    private final EntityTransaction et;
    
    public Commentd() throws RemoteException {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("CommentServicePU");
       em = emf.createEntityManager();
       et = em.getTransaction();
    }
         
    public void registerRmi() {
        try {
            System.out.println("Server: Dienst für RMI registrieren...");
            
            ICommentd intfCommentService = this;
            
           // LocateRegistry.createRegistry(1088);
          //  Naming.rebind("rmi://localhost/AccessToComment", intfMusicService);
            LocateRegistry.createRegistry(1088);  
            Registry registry = LocateRegistry.getRegistry(1088);  
            registry.rebind("rmi://localhost/Commentd", intfCommentService);
            System.out.println("Stub an \"/Commentd\" gebunden.");
            
            System.out.println("Server: Remote-Dienst registriert!");
        } catch (RemoteException ex) {
           System.err.println("CommentService: RemoteException aufgetreten!");
        }
        
      /**  EntityManagerFactory emf = Persistence.createEntityManagerFactory("CommentServicePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Comment comment = em.find(Comment.class, 1);
       /** System.out.println("Commnet:" +comment.getCommentTitle());
        System.out.println("Comment_Beschreibung:" +comment.getCommentDescription());
        
        em.close();
        emf.close();**/
    }
    
    /**
     * TestFunktion: Um die Verbindung zum Server zu t
     * esten.
     * @return
     * @throws RemoteException 
     */
    
    public String getTest() throws RemoteException {
        return "Antwort vom Server bezueglich des Kommentardienstes! Ende der Kette!";
      
    }

    public void addComment(int refArt, int refMp3) throws RemoteException {
       Comment com = new Comment();
       com.setCommentToArt(refArt);
       com.setCommentToMp3(refMp3);
       
       et.begin();
       em.persist(com);
       et.commit();
    }

    @Override
    public void deleteComment(int comment_ID) throws RemoteException {
        et.begin();
        em.remove(getComment(comment_ID));
        et.commit();
    }

    @Override
    public String[] getComment(int comment_ID) throws RemoteException {
        
        
        et.begin();
        Comment comment = em.find(Comment.class, comment_ID);
        et.commit();
        
        
        String[] commentString = {String.valueOf(comment.getCommentID()),comment.getCommentTitle(),comment.getCommentDescription(),String.valueOf(comment.getCommentToMp3()),String.valueOf(comment.getCommentToArt())};
        
        return commentString;
    }

    public List<String[]> getAllComment() throws RemoteException {
        et.begin();
        List<Comment> commentList = em.createNamedQuery("Comment.findAll").getResultList();
        et.commit();
         
        List<String[]> commentStrings = new LinkedList();
        for (int i=0; i<commentList.size(); i++){
            Comment com = commentList.get(i);
            String[] commentString = {String.valueOf(com.getCommentID()),com.getCommentTitle(),com.getCommentDescription(),String.valueOf(com.getCommentToMp3()),String.valueOf(com.getCommentToArt())};
            boolean add = commentStrings.add(commentString);
        }
        
        return commentStrings;
    }
    
    public List<String[]> getAllCommentForArt(int refArt) throws RemoteException {
        et.begin();
        Query query = em.createNamedQuery("Comment.getAllWithArtist");
        query.setParameter("artId", refArt);
        List<Comment> comList = query.getResultList();
        et.commit();
         
        List<String[]> commentStrings = new LinkedList();
        for (int i=0; i<comList.size(); i++){
            Comment com = comList.get(i);
            String[] commentString = {String.valueOf(com.getCommentID()),com.getCommentTitle(),com.getCommentDescription(),String.valueOf(com.getCommentToMp3()),String.valueOf(com.getCommentToArt())};
            boolean add = commentStrings.add(commentString);
        }
        
        return commentStrings;
    }
    
      public List<String[]> getAllCommentForTitle(int refMp3) throws RemoteException {
        et.begin();
        Query query = em.createNamedQuery("Comment.getAllWithTitle");
        query.setParameter("titleId", refMp3);
        List<Comment> comList = query.getResultList();
        et.commit();
         
        List<String[]> commentStrings = new LinkedList();
        for (int i=0; i<comList.size(); i++){
            Comment com = comList.get(i);
            String[] commentString = {String.valueOf(com.getCommentID()),com.getCommentTitle(),com.getCommentDescription(),String.valueOf(com.getCommentToMp3()),String.valueOf(com.getCommentToArt())};
            boolean add = commentStrings.add(commentString);
        }
        
        return commentStrings;
    }
    
}
