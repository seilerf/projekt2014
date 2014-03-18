/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


/**
 * Die Klasse Commentd implementiert das Interface ICommentd. Und ist das Gegenstück zur Implemetierung im Kommentardienst.
 * Hier finden über das Interface die Funktionsaufrufe, sowie der Lookup des Kommentardienstes statt. 
 * @author M.Ullmann
 */
public class Commentd implements ICommentd {
    //Kommentardienst Interface
    private ICommentd iCommentd;
    
    //Getter für das Interface
    public ICommentd getiCommentd() {
        return iCommentd;
    }
    
    //Setter für das Interface
    public void setiCommentd(ICommentd iCommentd) {
        this.iCommentd = iCommentd;
    }

    //Konstruktor mit dem super-Aufruf(Kommentardienst) und lookupRmi 
    public Commentd() {
        super();
        this.lookupRmi(); 
    }
   
    /**
     * Die lookupRmi Methode sucht nachdem vom Kommentardienst registrierten Dienst. Dabei ist 
     * im wesentlichen darauf zu achten, dass der im Kommentardienst verwendete Port, sowie die
     * Adresse des localhost, über den der RMI-Vorgang abläuft, auf beiden Seiten die gleichen sind.
     */
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
    
    /**
     * Testfunktion hat im Applicationsrahmen keine funktionale Bedeutung.
     * @return 
     */
    @Override
    public String getTest() {
        System.out.println("Im LoadBalancer beim Commentd angekommen!!!");   
        System.out.println("getTest im Loadbalancer aufgerufen für den Commentdienst aufgerufen!!");
        System.out.println("geTest im Loadbalancer sollte für den Commentservice geklappt haben!");
        System.out.println("Im LoadBalancer auf dem Weg den Commentd zu verlassen!!");
        System.out.println(iCommentd.getTest());
            return iCommentd.getTest();  
    }
    
    /**
     * Aufruf der addCommentForMp3 Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @param comTitle  -> Referenz auf den Kommentar Titel
     * @param comDesc   -> Referenz auf die Kommentar Beschreibung
     * @param refMp3    -> Referenz auf die Mp3-Id
     */
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) {
        iCommentd.addCommentForMp3(comTitle, comDesc, refMp3);
    }
    
    /**
     * Aufruf der addCommentForArt Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @param comTitle  -> Referenz auf den Kommentar Titel
     * @param comDesc   -> Referenz auf die Kommentar Beschreibung
     * @param refArt    -> Referenz auf die Künstler-Id
     */
    public void addCommentForArt(String comTitle, String comDesc, int refArt) {
        iCommentd.addCommentForArt(comTitle, comDesc, refArt);
    }

    /**
     * Aufruf der deleteComment Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @param com_Id    -> Referenz auf die Kommentar-Id
     */
    public void deleteComment(int com_Id){
        iCommentd.deleteComment(com_Id);
    }
    
    /**
     * Aufruf der getComment Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @param com_Id    -> Referenz auf die Kommentar-Id
     * @return 
     */
    public String[] getComment(int com_Id) {
         return iCommentd.getComment(com_Id);
    }

    /**
     * Aufruf der getAllComment Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @return 
     */
    public List<String[]> getAllComment() {
        return iCommentd.getAllComment();
    }

    /**
     * Aufruf der getAllCommentForTitle Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @param refMp3    -> Referenz auf die Mp3-Id
     * @return 
     */
    @Override
    public List<String[]> getAllCommentForTitle(int refMp3) {
        return iCommentd.getAllCommentForTitle(refMp3);
    }
    
    /**
     * Aufruf der getAllCommentForArt Funktion aus dem Kommentardienstes über das
     * Kommentardienst Interface.
     * @param refArt    -> Referenz auf die Künstler-Id
     * @return 
     */
    @Override
    public List<String[]> getAllCommentForArt(int refArt) {
        return iCommentd.getAllCommentForArt(refArt);
        
    }
    
}
