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
 * Klasse Commentd stellt den Kommentardienst da, welcher dem Musikdienst zur Verfügung gestellt wird.
 * Dafür wird der Kommentardienst mit Hilfe der Funktion registerRmi() bereitgestellt.
 * @author M.Ullmann
 */
public class Commentd extends UnicastRemoteObject implements ICommentd{
        
    //Persistenz- + Transaktion-Einheit um Datenbank Zugriffe und Abfrage durchführen zu können.
    @PersistenceContext
    private final EntityManager em;
    private final EntityTransaction et;
    
    //Konstruktor mit der Erstellung einer Persistenz-Einheit -> CommentServicePu.
    public Commentd() throws RemoteException {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("CommentServicePU");
       em = emf.createEntityManager();
       et = em.getTransaction();
    }
         
    /**
     * Die registerRmi-Funktion, registeriert den Kommentardienst über den Port 1088, um ihn für den Musikdienst nutzbar zu machen.
     * Dazu wird das Interface von Typ "ICommentd", mit dem Namen intfCommentService mitübergeben.
     * Die hier verwendete localhost-Adresse kann durch jede beliebige ersetzt werden.
     */
    public void registerRmi() {
        try {
            System.out.println("Server: Dienst für RMI registrieren...");
            
            ICommentd intfCommentService = this;

            LocateRegistry.createRegistry(1088);  
            Registry registry = LocateRegistry.getRegistry(1088);  
            registry.rebind("rmi://localhost/Commentd", intfCommentService);
            System.out.println("Stub an \"/Commentd\" gebunden.");
            
            System.out.println("Server: Remote-Dienst registriert!");
        } catch (RemoteException ex) {
           System.err.println("CommentService: RemoteException aufgetreten!");
        }
    }
    
    /**
     * TestFunktion: Um die Verbindung zum Server zu testen.
     * @return
     * @throws RemoteException 
     */
    public String getTest() throws RemoteException {
        return "Antwort vom Server bezueglich des Kommentardienstes! Ende der Kette!";
    }
    
    /**
     * Die Funktion addCommentForMp3 legt mit Hilfe der Übergabeparameter eine neues Kommentar an und persistiert dieses.
     * @param comTitle  -> Referenz auf den Titel des Kommentars 
     * @param comDesc   -> Referenz auf die Beschreibung des Kommentars 
     * @param refMp3    -> Referenz auf die Mp3-Datei-Id, zu dem der Kommentar angelegt werden soll
     * @throws RemoteException 
     */
    @Override
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) throws RemoteException {
       Comment com = new Comment();
       com.setCommentTitle(comTitle);
       com.setCommentDescription(comDesc);
       com.setCommentToArt(0);
       com.setCommentToMp3(refMp3);
       
       et.begin();
       em.persist(com);
       et.commit();
    }
      
    /**
     * Die Funktion addCommentForArt legt mit Hilfe der Übergabeparameter eine neues Kommentar an und persistiert dieses.
     * @param comTitle  -> Referenz auf den Titel des Kommentars 
     * @param comDesc   -> Referenz auf die Beschreibung des Kommentars
     * @param refArt    -> Referenz auf den Künstler-Id, zu dem der Kommentar angelegt wird
     * @throws RemoteException 
     */
     @Override
     public void addCommentForArt(String comTitle, String comDesc, int refArt) throws RemoteException {
       Comment com = new Comment();
       com.setCommentTitle(comTitle);
       com.setCommentDescription(comDesc);
       com.setCommentToArt(refArt);
       com.setCommentToMp3(0);
       
       et.begin();
       em.persist(com);
       et.commit();
    }

     /**
      * Die Funktion deleteComment löscht mit Hilfe der mit angegebenen Id ein Kommentar aus der Datenbank.
      * -> Die Funktion getCommentForDelete liefert den entsprechenden Kommentar, der gelöscht werden soll.
      * @param com_Id -> Referenz auf die Kommentar-Id, des zu löschenden Kommentars
      * @throws RemoteException 
      */
    @Override
    public void deleteComment(int com_Id) throws RemoteException {
        et.begin();
        em.remove(getCommentForDelete(com_Id));
        et.commit();
    }
    
    /**
     * Die Funktion getCommentForDelete liefert das Kommentar-Objekt zurück, welches gelöscht werden soll.
     * @param com_Id -> Referenz auf die Kommentar-Id, des gesuchten Kommentars
     * @return 
     */
    public Comment getCommentForDelete(int com_Id) {
      
        Comment comment = em.find(Comment.class, com_Id);
       
        
        return comment;
    }

    /**
     * Findet im Anwendungsbetrieb keine Verwendung, diente zu Testzwecken.
     * @param comment_ID    -> Referenz auf die Kommentar-Id
     * @return              -> Gibt das Kommentar-Objekt als String-Array zurück
     * @throws RemoteException 
     */
    @Override
    public String[] getComment(int comment_ID) throws RemoteException {
        
        
        et.begin();
        Comment comment = em.find(Comment.class, comment_ID);
        et.commit();
        
        
        String[] commentString = {String.valueOf(comment.getCommentID()),comment.getCommentTitle(),comment.getCommentDescription(),String.valueOf(comment.getCommentToMp3()),String.valueOf(comment.getCommentToArt())};
        
        return commentString;
    }

    /**
     * Die Funktion getAllComment liefert alle in der Datenbank enthaltenen Kommentare zurück.
     * Dazu wird eine Liste von Kommentaren mit Hilfe der oben beschrieben Query Comment.findAll angelegt.
     * In der Folge werden die einzelnen Werte jedes einzelnen Kommentars ausgelesen und in einem String-Array 
     * gespeichert, der wiederum in einer Liste von String-Arrays gespeichert wird.
     * @return  -> Liefert eine Liste von String-Arrays zurück, in der alle Kommentare in Form von String-Arrays gespeichert sind
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllComment() throws RemoteException {
        et.begin();
        List<Comment> commentList = em.createNamedQuery("Comment.findAll").getResultList();
        et.commit();
         
        List<String[]> commentStrings = new LinkedList<>();
        
        for (int i=0; i<commentList.size(); i++){
            Comment com = commentList.get(i);
            String[] commentString = {String.valueOf(com.getCommentID()),com.getCommentTitle(),com.getCommentDescription(),String.valueOf(com.getCommentToMp3()),String.valueOf(com.getCommentToArt())};
            boolean add = commentStrings.add(commentString);
        }
        
        return commentStrings;
    }
    
    /**
     * Die Funktion getAllCommentForArt ist simultan zu getAllComment, allerdings werden hier nur die Kommentare
     * ausgelesen, welche mit der Referenz-Id des Künstlers in der Datenbank gespeichert sind.
     * @param refArt    -> Referenz auf die Künstler-Id
     * @return          -> Liefert eine Liste von String-Arrays zurück, in der alle Kommentare in Form von String-Arrays gespeichert sind
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllCommentForArt(int refArt) throws RemoteException {
        et.begin();
        Query query = em.createNamedQuery("Comment.getAllWithArtist");
        query.setParameter("artId", refArt);
        List<Comment> comList = query.getResultList();
        et.commit();
         
        List<String[]> commentStrings = new LinkedList<>();
        
        for (int i=0; i<comList.size(); i++){
            Comment com = comList.get(i);
            String[] commentString = {String.valueOf(com.getCommentID()),com.getCommentTitle(),com.getCommentDescription(),String.valueOf(com.getCommentToMp3()),String.valueOf(com.getCommentToArt())};
            boolean add = commentStrings.add(commentString);
        }
        return commentStrings;
    }
        
       /**
        * Die Funktion getAllCommentForTitle ist simultan zu getAllComment, allerdings werden hier nur die Kommentare
        * ausgelesen, welche mit der Referenz-Id der Mp3-Datei in der Datenbank gespeichert sind.
        * @param refMp3 -> Referenz auf die Mp3-Id
        * @return       -> Liefert eine Liste von String-Arrays zurück, in der alle Kommentare in Form von String-Arrays gespeichert sind
        * @throws RemoteException 
        */
      @Override
      public List<String[]> getAllCommentForTitle(int refMp3) throws RemoteException {
        et.begin();
        Query query = em.createNamedQuery("Comment.getAllWithTitle");
        query.setParameter("titleId", refMp3);
        List<Comment> comList = query.getResultList();
        et.commit();
         
        List<String[]> commentStrings = new LinkedList<>();
       
        
        for (int i=0; i<comList.size(); i++){
            Comment com = comList.get(i);
            String[] commentString = {String.valueOf(com.getCommentID()),com.getCommentTitle(),com.getCommentDescription(),String.valueOf(com.getCommentToMp3()),String.valueOf(com.getCommentToArt())};
            boolean add = commentStrings.add(commentString);
        }
        
        return commentStrings;
    }
    
}
