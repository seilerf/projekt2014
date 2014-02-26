/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 *
 * @author AdminMax
 */
public class CommentDaoRmi implements IntfComment, Serializable {

    private IntfComment intfCommentService;
    private String serveraddress;
    
    public CommentDaoRmi() {
        super();
        this.lookupRMI();
    }
    
    public CommentDaoRmi(IntfComment intfCommentService) {
        this.intfCommentService = intfCommentService;
        this.lookupRMI();
    }
            
    private void lookupRMI() {
      try {
           
            Registry registry = LocateRegistry.getRegistry("localhost", 1088);
            System.out.println("Registry erkannt");
            this.intfCommentService = (IntfComment) registry.lookup("AccessToComment");
            System.out.println("Lookup für das CommentInterface ausgeführt");

        } catch (RemoteException ex) {
            System.err.println("RemotExeption beim RMI-Lookup aufgetreten (Klasse: "+ getClass().getName() +")");
        } catch (NotBoundException ex) {
            System.err.println("NotBoundException aufgetreten. Objekt nicht gefunden.");;
        }  
    }
    
    @Override
    public String getTest() {
        return intfCommentService.getTest();
    }

    @Override
    public void addComment(String comment_Title, String comment_Description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteComment(int comment_ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getComment(int comment_ID) {
        this.lookupRMI();
        //String[] comment = intfCommentService.getComment(comment_ID);
        //System.out.println("Comment_Title: " + comment[1]);
        System.out.println("Hier an der Stelle FUCK ---> Comment_ID:" + comment_ID);
        return intfCommentService.getComment(comment_ID);
    }

    @Override
    public List<String[]> getAllComment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
