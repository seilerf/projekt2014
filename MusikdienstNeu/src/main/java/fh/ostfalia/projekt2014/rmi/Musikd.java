/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import static com.sun.org.apache.xerces.internal.impl.XMLEntityManager.DEFAULT_BUFFER_SIZE;
import fh.ostfalia.projekt2014.model.Id3Tag;
import fh.ostfalia.projekt2014.model.Mp3;
import fh.ostfalia.projekt2014.model.Mp3Artist;
import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;


/**
 * Die Klasse Musikd realisiert den Musikdienst und verfügt, ebenfalls der Kommentardienst
 * über eine eigene Persistenz-Einheit. Des Weiteren hat der Musikdienst über das Commentd
 * Attribut Zugriff auf den Kommentardienst. Die im Kommentardienst implementierten Methoden
 * sind im Musikdienst ebenfalls definiert um die Weiterleitungsmöglichkeit zu gewährleisten. 
 * @author fseiler/ M.Ullmann
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{

    //Persistenz-Einheit, die für die Kommunikation mit der Datenbank benötigt wird
    @PersistenceContext
    private final EntityManager em;
    private final EntityTransaction et;
    
    //Id3Tag Attribut wird für die Upload-Methode benötigt
    private Id3Tag id3;
    //Kommentardienst Attribut, das für die Kommunikation mit dem Kommentardienst benötigt wird
    private Commentd commentd;
     
    //Konstruktor mit setzen der Persistenz-Einheit + dem Kommentardienst
    public Musikd() throws RemoteException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3");
        em = emf.createEntityManager();
        et = em.getTransaction();
        this.commentd = new Commentd();
    }
    
    /**
     * Registriert das Musikdienst Interface auf der angegebenen localhost Adresse über den Port 1099. Der Port
     * sowie die localhost-Adresse können beliebig ausgetauscht werden. Dies ist von Nöten, damit anderen das 
     * Interface des Musikdienstes zur Verfügung steht. Die Methode ist für 2 Musikdienste ausgelegt, die
     * unterschiedlich registiert werden.
     */
    public void registerForRmi(){
        System.out.println("Musikdienst: Dienste für RMI registrieren...");
        IMusikd intfMusikdienst = this;
        
        try {
            LocateRegistry.createRegistry(1099);
        }
        catch (RemoteException ex){
            System.err.println("Musikdienst: Port 1099 bereits belegt.");
        } 
        try {    
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind("rmi://localhost/Musikd_1", intfMusikdienst);
            System.out.println("Stub an \"/Musikd_1\" gebunden.");
        } 
        catch (RemoteException ex) {
            System.err.println("Musikdienst: RemoteException aufgetreten!");
            
        } catch (AlreadyBoundException ex) {
            try {
                Registry registry = LocateRegistry.getRegistry(1099);
                registry.bind("rmi://localhost/Musikd_2", intfMusikdienst);
                System.out.println("Stub an \"/Musikd_2\" gebunden.");
            } catch (RemoteException ex1) {
                Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (AlreadyBoundException ex1) {
                Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        System.out.println("Musikdienst: RMI-Dienste registriert!");        
    }
    
    /**
     * Testfunktion, welche für die Applikation keine weitere Bedeutung hat.
     * @return
     * @throws RemoteException 
     */
    @Override
    public String test() throws RemoteException {
        System.out.println("Im Musikdienst angekommen!!");
        return  this.commentd.getTest();
    }
   
    /**
     * Aufruf der addCommentForMp3 Funktion des Kommentardienstes.
     * @param comTitle  -> Referenz auf den Kommentar-Titel
     * @param comDesc   -> Referenz auf die Kommentar-Beschreibung 
     * @param refMp3    -> Referenz auf die Mp3-Id
     * @throws RemoteException 
     */
    @Override 
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3) throws RemoteException {
        this.commentd.addCommentForMp3(comTitle, comDesc, refMp3);
    }
    
    /**
     * Aufruf der addCommentForArt Funktion des Kommentardienstes.
     * @param comTitle  -> Referenz auf den Kommentar-Titel
     * @param comDesc   -> Referenz auf die Kommentar-Beschreibung
     * @param refArt    -> Referenz auf die Künstler-Id
     * @throws RemoteException 
     */
    @Override 
    public void addCommentForArt(String comTitle, String comDesc, int refArt) throws RemoteException {
        this.commentd.addCommentForArt(comTitle, comDesc, refArt);
    }
    
    /**
     * Aufruf der deleteComment Funktion des Kommentardienstes.
     * @param com_Id   -> Referenz auf die Kommentar-Id
     * @throws RemoteException 
     */
    @Override
    public void deleteComment(int com_Id) throws RemoteException {
        this.commentd.deleteComment(com_Id);
    }
    
    /**
     * Aufruf der getAllComment Funktion des Kommentardienstes.
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllComment() throws RemoteException {
       return commentd.getAllComment();
    }
    
    /**
     * Aufruf der getAllCommentForArt Funktion des Kommentardienstes.
     * @param refArt    -> Referenz auf die Künstler-Id
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllCommentForArt(int refArt) throws RemoteException {
        return commentd.getAllCommentForArt(refArt);     
    }
    
    /**
     * Aufruf der getAllCommentForTitle Funktion des Kommentardienstes.
     * @param refMp3    -> Referenz auf die Mp3-Id
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllCommentForTitle(int refMp3) throws RemoteException {
        return commentd.getAllCommentForTitle(refMp3);
    }
    
    /**
     * Die addMp3 Funktion fügt eine neue Mp3 der Mp3 Datenbank hinzu. Des Weiteren ebenfalls
     * der dazugehörige Künstler in die Datenbank persistiert, was zuvor mit Hilfe der 
     * Mp3Artist.findByName Query in Verbindung mit einer If-Abfrage überprüft wird. Für die
     * Persistierung wird die oben im Konstruktor definierte Persistenz-Einheit verwendet.
     * @param mp3_title     -> Referenz auf den Mp3-Titel
     * @param mp3_Artist    -> Referenz auf den Mp3-Künstler
     * @throws RemoteException 
     */
    @Override
    public void addMp3(String mp3_title, String mp3_Artist) throws RemoteException {
        Mp3 mp3 = new Mp3();
        et.begin();
        List<Mp3Artist> queryArtist = em.createNamedQuery("Mp3Artist.findByName").setParameter("name", mp3_Artist).getResultList();
        et.commit();
        
        if (queryArtist.size() >= 1)
            mp3.setArtist(queryArtist.get(0));
        else{
            Mp3Artist mp3Artist = new Mp3Artist();
            mp3Artist.setArtistName(mp3_Artist);
            et.begin();
            em.persist(mp3Artist);
            et.commit();
        }

        mp3.setMp3Title(mp3_title);
        mp3.setMp3File(null);
        
        et.begin();
        em.persist(mp3);
        et.commit();
    }
    
    /**
     * Die deleteMp3 Methode wird zum Löschen einer Mp3-Datei aus der Datenbank verwendet.
     * @param mp3_id    -> Referenz auf die Mp3-Id
     * @throws RemoteException 
     */
    @Override
    public void deleteMp3(int mp3_id) throws RemoteException {
        et.begin();
        em.remove(getMp3(mp3_id));
        et.commit();
    }

    /**
     * Die getMp3 Methode holt mit Hilfe der übergebene Mp3-Id eine Mp3 aus der Datenbank
     * und formatiert diese in einen String-Array.
     * @param mp3_id    -> Referenz auf die Mp3-Id
     * @return          -> Mp3 als String-Array
     * @throws RemoteException 
     */
    @Override
    public String[] getMp3(int mp3_id) throws RemoteException {
        et.begin();
        Mp3 mp3 = em.find(Mp3.class, mp3_id);
        System.out.println("Musikdienst: MP3 --> " + mp3.getMp3Title());
        et.commit();
        
        String[] mp3String = {String.valueOf(mp3.getMp3Id()), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
    }

    /**
     * Die getMp3ByArtist Methode holt mit Hilfe der übergebene Mp3Artist-Id einen Künstler aus der Datenbank
     * und formatiert diese in einen String-Array.
     * @param mp3ArtistId   -> Referenz auf die Künstler-Id
     * @return              -> Künstler als String-Array
     * @throws RemoteException 
     */
    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) throws RemoteException {
        et.begin();
        Mp3 mp3 = em.find(Mp3.class, mp3ArtistId);
        et.commit();
        
        String[] mp3String = {String.valueOf(mp3.getMp3Id()), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
    }

    /**
     * Die getAllMp3 Methode gibt eine List von String-Arrays zurück. In diesen sind die Mp3 gespeichert.
     * Dazu werden vorher mit Hilfe der Mp3.findAll Query alle Mp3s aus der Datenbank ausgelesen.
     * @return  -> List von Sting-Array, in denen die Mp3s gespeichert sind
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllMp3() throws RemoteException {
        et.begin();
        List<Mp3> mp3List = em.createNamedQuery("Mp3.findAll").getResultList();
        et.commit();
        
        List<String[]> mp3Strings = new LinkedList<>();
        int i;
        for (i=0; i<mp3List.size(); i++){
            Mp3 mp3 = mp3List.get(i);
            String[] mp3String = {String.valueOf(mp3.getMp3Id()), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
            boolean add = mp3Strings.add(mp3String);
        }
        
        return mp3Strings;
    }
    
    /**
     * Die getAllArtist Methode gibt eine List von String-Arrays zurück. In diesen sind die Künstler gespeichert.
     * Dazu werden vorher mit Hilfe der Mp3Artist.findAll Query alle Künstler aus der Datenbank ausgelesen.
     * @return  -> List von Sting-Array, in denen die Künstler gespeichert sind
     * @throws RemoteException 
     */
    @Override
    public List<String[]> getAllArtist() throws RemoteException {
        et.begin();
        List<Mp3Artist> artistList = em.createNamedQuery("Mp3Artist.findAll").getResultList();
        et.commit();
        
        List<String[]> mp3ArtistStrings = new LinkedList<>();
        int i;
        for(i=0; i<artistList.size(); i++) {
            Mp3Artist mp3Artist = artistList.get(i);
            String[] mp3ArtistString = { String.valueOf(mp3Artist.getArtistId()), mp3Artist.getArtistName()};
            boolean add = mp3ArtistStrings.add(mp3ArtistString);
        }
        return mp3ArtistStrings;
    }

    /**
     * Die upLoad funktion liest mit Hilfe des Id3Tag Attributes sowie des übergebenen Strings die Mp3-Datei
     * aus dem unter dem angegeben Pfad aus. Der Pfad des file Attributes muss dem Ordner entsprechen, in dem
     * sich die Mp3-Datein zum Upload befinden. Anschließend wird die ausgelesene Mp3 persistiert.
     * @param part 
     */
    @Override
    public void upLoad(String part) {
        this.id3 = new Id3Tag();
        File file = new File("C:\\Users\\AdminMax\\Documents\\NetBeansProjects\\projekt2014\\MusikdienstNeu\\Upload\\" + getFileName(part));
        
        Mp3 mp3 = new Mp3();
        mp3 = id3.readMp3File(file);
        
        this.persistMp3(mp3);
  
    }
   
    /**
     * Die getFileName Methode schneidet den übergebene String in die passende Form, damit ein
     * Auslesen der Mp3-Dateien möglich ist.
     * @param part  -> Referenz-String des unbearbeiteten Mp3-Pfades
     * @return      -> Liefert den bearbeiten Mp3-Pfade(Namen) zurück
     */
    public String getFileName(String part) {
        int firstPos;
        int secPos;
        String fileName;
        
        firstPos = part.indexOf("=") + 1;
        secPos = part.indexOf(",");
        
        fileName = part.substring(firstPos, secPos);
        return fileName;
    }

    /**
     * Die getAllMp3Artist Methode liest mit Hilfe der Mp3Artist.findAll Query alle Künstler aus der
     * Datenbank aus.
     * @return  -> Liefert eine Liste mit Mp3-Künstlern zurück
     */
   public List<Mp3Artist> getAllMp3Artist() {
       et.begin();
       List<Mp3Artist> mp3Art = em.createNamedQuery("Mp3Artist.findAll").getResultList();
       et.commit();
       return mp3Art;
   }
   
   /**
    * Die getMp3ArtistByName Methode liest mit Hilfe des Übergabeparameters alle entsprechenden Künstler
    * aus der Datenbank aus.
    * @param name   -> Referemz-String des Künstlers
    * @return       -> Liste mit entsprechenden Künstlern
    */
   public List<Mp3Artist> getMp3ArtistByName(String name) {
       et.begin();
       Query query = em.createNamedQuery("getName");
       query.setParameter("name", name);
       List<Mp3Artist> list = query.getResultList();
       et.commit();
       return list;
   }
   
   /**
    * Die checkArtistMp3Artist Methode überprüft ob ein Künstler mit dem übergebenen Namen
    * bereits existiert oder nicht. Falls ein Künstler schon exisitert wird er nicht erneut
    * neu angelegt.
    * @param name   -> Referenz-String auf den zu überprüfenden Künstler
    * @return       -> Gibt den entsprechenden Künstler zurück
    */
   public Mp3Artist checkArtistMp3Artist(String name) {
       Mp3Artist mp3Art = null;
       List<Mp3Artist> list = getMp3ArtistByName(name);
       if(list.size() > 0) {
           mp3Art = list.get(0);
       }
       return mp3Art;
   }
   
   /**
    * Die checkArtistMp3 Methode überprüft für die übergebene Mp3 mit Hilfe der
    * checkArtistMp3Artist Funktion ob der Künstler schon existiert. Wenn dieser
    * noch nicht existiert wird der Mp3 Künstler auf diesen Wert gesetzt, andernfalls
    * wird der Wert auf null gesetzt.
    * @param mp3    -> Referenz auf die Mp3
    * @return 
    */
   public Mp3 checkArtistMp3(Mp3 mp3) {
       Mp3Artist mp3Art = this.checkArtistMp3Artist(mp3.getArtistName());
       
       if(mp3Art != null) {
           mp3.setArtist(mp3Art);
       }
       return mp3;
   }
   
   /**
    * Die persistMp3 schreibt mit Hilfe der oben im Konstruktor definierten Persistenz-Einheit
    * die Mp3 in die entsprechende Datenbank. Zuvor durchläuft der Künstlername der Mp3 die
    * benötigten Test (checkForMp3,checkArtistMp3(checkArtistMp3Artist)) um ein doppeltes Eintragen von 
    * gleichen Künstlern oder Mp3(-Namen) zu vermeiden.
    * @param mp3    -> Referenz auf die Mp3
    */
   public void persistMp3(Mp3 mp3) {
      if(checkForMp3(mp3) == false) {
          mp3 = checkArtistMp3(mp3);
          et.begin();
          em.persist(mp3);
          et.commit();
      }
   }
   
   /**
    * Die checkForMp3 überprüft ob für den Namen der übergebenen Mp3 schon bereits ein
    * Eintrag in der Mp3 Datenbank vorhanden ist. Dies wird mit Hilfe des existAlready
    * Attributes, sowie der getNameForMp3 Query überprüft. Wenn der Mp3-Name schon existiert
    * wird existAlready auf true gesetzt, falls das nicht der Fall ist wird false zurück-
    * gegeben.
    * @param mp3
    * @return 
    */
   public boolean checkForMp3(Mp3 mp3) {
       boolean existAlready = false;
       
       et.begin();
       Query query = em.createNamedQuery("getNameForMp3");
       query.setParameter("name", mp3.getMp3Title());
       List<Mp3> list = query.getResultList();
       et.commit();
       if(list.size() > 0) {
           existAlready = true;
       }
       return existAlready;
   }
   
   /**
    * Die mp3Download Methode sorgt für den Download der Mp3-Datei, welche mit Hilfe der
    * getMp3ForFileId Funktion und der angegebene Mp3-Id gefunden wird. Falls der Download
    * erfolgreich ist gibt es einen Response im FacesContext.
    * @param title  -> Referenz auf den Titel der Mp3-Datei
    * @param mp3Id  -> Referenz auf die Mp3-Id
    */
   @Override
   public void mp3Download(String title, int mp3Id)  {
       
       byte[] mp3 = this.getMp3ForFileId(mp3Id);
       String mp3Title = title + ".mp3";
       FacesContext fContext = FacesContext.getCurrentInstance();
       HttpServletResponse httpResponse = (HttpServletResponse) fContext.getExternalContext().getResponse();
       
       httpResponse.reset();
       httpResponse.setBufferSize(DEFAULT_BUFFER_SIZE);
       httpResponse.setContentType("audio/mpeg");
       httpResponse.setHeader("Content-Length", String.valueOf(mp3.length));
       httpResponse.setHeader("Content-Disposition", "attachment;filename=\"" + mp3Title + "\"");
        try {
            httpResponse.getOutputStream().write(mp3);
        } catch (IOException ex) {
            Logger.getLogger(Musikd.class.getName()).log(Level.SEVERE, null, ex);
        }
       fContext.responseComplete();
        
      } 
   
   /**
    * Die getMp3ForFileId Methode sucht mit Hilfe der übergebenen Mp3-Id nach der entsprechenden
    * Datei und liefert, falls sie gefunden werden konnte den entsprechenden Bytestrom zurück.
    * @param mp3_id -> Referenz auf die Mp3-Id
    * @return       -> Gibt den Bytestrom der Mp3-Datei zurück
    */
   public byte[] getMp3ForFileId(int mp3_id) {
        et.begin();
       byte[]file = em.find(Mp3.class, mp3_id).getMp3File();
        et.commit();
        return file;
    }
 
}
