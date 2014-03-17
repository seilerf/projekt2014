/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

//import fh.ostfalia.projekt2014.loadbalancer.Balance;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


/**
 *
 * @author M.Ullmann
 */
public class Commentd implements ICommentd {
    
    private ICommentd iCommentd;
    
    public ICommentd getiCommentd() {
        return iCommentd;
    }

    public void setiCommentd(ICommentd iCommentd) {
        this.iCommentd = iCommentd;
    }

  
    public Commentd() {
        super();
        this.lookupRmi(); 
    }
     
   private void lookupRmi(){
        try {
            Registry registry = LocateRegistry.getRegistry(1088);
            System.out.println("Registry erkannt");
            this.iCommentd = (ICommentd) registry.lookup("rmi://localhost/Commentd");
            System.out.println("Lookup für das CommentInterface ausgeführt");

        } catch (RemoteException ex) {
            System.err.println("RemotExeption beim RMI-Lookup aufgetreten (Klasse: "+ getClass().getName() +")");
        } catch (NotBoundException ex) {
            System.err.println("NotBoundException aufgetreten. Objekt nicht gefunden.");
        } 
    }
    
    
    @Override
    public String getTest() {
        System.out.println("Im LoadBalancer beim Commentd angekommen!!!");   
        System.out.println("getTest im Loadbalancer aufgerufen für den Commentdienst aufgerufen!!");
        System.out.println("geTest im Loadbalancer sollte für den Commentservice geklappt haben!");
        System.out.println("Im LoadBalancer auf dem Weg den Commentd zu verlassen!!");
        System.out.println(iCommentd.getTest());
            return iCommentd.getTest();  
    }

    public void addComment(int refArt, int refMp3) {
        iCommentd.addComment(refArt, refMp3);
    }

    public void deleteComment(int comment_ID){
        iCommentd.deleteComment(comment_ID);
    }

    public String[] getComment(int comment_ID) {
         return iCommentd.getComment(comment_ID);
    }

    public List<String[]> getAllComment() {
        return iCommentd.getAllComment();
    }

    @Override
    public List<String[]> getAllCommentForTitle(int refMp3) {
        return iCommentd.getAllCommentForArt(refMp3);
    }

    @Override
    public List<String[]> getAllCommentForArt(int refArt) {
         return iCommentd.getAllComment();
    }
    
}
