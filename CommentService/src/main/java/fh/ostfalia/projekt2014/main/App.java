package fh.ostfalia.projekt2014.main;

import fh.ostfalia.projekt2014.dao.CommentDao;
import fh.ostfalia.projekt2014.rmi.CommentDaoRmi;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "CommentService" );
        //CommentDao commentDao = new CommentDao();
        CommentDaoRmi commentDaoRmi;
        
        try {
            commentDaoRmi = new CommentDaoRmi(null);
            commentDaoRmi.registerRMI();
        } catch(RemoteException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("CommentServicePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        /**Comment 
         * 
        comment = em.find(Comment.class, 1);
        System.out.println("Commnet:" +comment.getCommentTitle());
        System.out.println("Comment_Beschreibung:" +comment.getCommentDescription());
        */
        em.close();
        emf.close();
    }
}
