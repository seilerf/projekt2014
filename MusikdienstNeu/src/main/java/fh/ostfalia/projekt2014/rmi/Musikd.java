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
import java.util.ArrayList;
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
 *
 * @author fseiler/ M.Ullmann
 */
public class Musikd extends UnicastRemoteObject implements IMusikd{

    @PersistenceContext
    private final EntityManager em;
    private final EntityTransaction et;
    
    private Id3Tag id3;
    private String passedParamter;
    private Commentd commentd;
            
    public Musikd() throws RemoteException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mp3");
        em = emf.createEntityManager();
        et = em.getTransaction();
        this.commentd = new Commentd();
    }
    
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

    @Override
    public String test() throws RemoteException {
        System.out.println("Im Musikdienst angekommen!!");
        return  this.commentd.getTest();
    }

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

    @Override
    public void deleteMp3(int mp3_id) throws RemoteException {
        et.begin();
        em.remove(getMp3(mp3_id));
        et.commit();
    }

    @Override
    public String[] getMp3(int mp3_id) throws RemoteException {
        et.begin();
        Mp3 mp3 = em.find(Mp3.class, mp3_id);
        System.out.println("Musikdienst: MP3 --> " + mp3.getMp3Title());
        et.commit();
        
        String[] mp3String = {String.valueOf(mp3.getMp3Id()), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
    }


    @Override
    public String[] getMp3ByArtist(int mp3ArtistId) throws RemoteException {
        et.begin();
        Mp3 mp3 = em.find(Mp3.class, mp3ArtistId);
        et.commit();
        
        String[] mp3String = {String.valueOf(mp3.getMp3Id()), mp3.getMp3Title(), mp3.getArtist().getArtistName()};
        
        return mp3String;
    }

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
    
    @Override
    public List<String[]> getAllArtist() {
        et.begin();
        List<Mp3Artist> artistList = em.createNamedQuery("Mp3Artist.findAll").getResultList();
        et.commit();
        
        List<String[]> mp3ArtistStrings = new LinkedList<>();
        for(int i=0; i<artistList.size(); i++) {
            Mp3Artist mp3Artist = artistList.get(i);
            String[] mp3ArtistString = { String.valueOf(mp3Artist.getArtistId()), mp3Artist.getArtistName()};
            boolean add = mp3ArtistStrings.add(mp3ArtistString);
        }
        return mp3ArtistStrings;
    }

   /** @Override
    public byte[] getFile(int mp3_id) throws RemoteException {
        et.begin();
        byte[] mp3File = em.find(Mp3.class, mp3_id).getMp3File();
        et.commit();
        return mp3File;
    }*/
    
    public String getPassedParameter() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.passedParamter = (String) facesContext.getExternalContext().getRequestParameterMap().get("id");
        return this.passedParamter;
    }
    
    public void setPassedParameter(String passedParameter) {
        this.passedParamter = passedParameter;
    }
    
      public void addMp3List(ArrayList<Mp3> mp3List) {
        for(int i=0; i <= mp3List.size(); i++) {
            Mp3 tempMp3 = mp3List.get(i);
            et.begin();
            em.persist(tempMp3);
            et.commit();
        }
    }
    
    @Override
    public void upLoad(String part) {
        this.id3 = new Id3Tag();
        File file = new File("C:\\Users\\AdminMax\\Documents\\NetBeansProjects\\projekt2014\\MusikdienstNeu\\Upload\\" + getFileName(part));
        
        Mp3 mp3 = new Mp3();
        mp3 = id3.readMp3File(file);
        
        this.persistMp3(mp3);
  
    }
    
    public String getFileName(String part) {
        int firstPos;
        int secPos;
        String fileName;
        
        firstPos = part.indexOf("=") + 1;
        secPos = part.indexOf(",");
        
        fileName = part.substring(firstPos, secPos);
        return fileName;
    }

   public List<Mp3Artist> getAllMp3Artist() {
       et.begin();
       List<Mp3Artist> mp3Art = em.createNamedQuery("Mp3Artist.findAll").getResultList();
       et.commit();
       return mp3Art;
   }
   
   public List<Mp3Artist> getMp3ArtistByName(String name) {
       et.begin();
       Query query = em.createNamedQuery("getName");
       query.setParameter("name", name);
       List<Mp3Artist> list = query.getResultList();
       et.commit();
       return list;
   }
   
   public Mp3Artist checkArtistMp3Artist(String name) {
       Mp3Artist mp3Art = null;
       List<Mp3Artist> list = getMp3ArtistByName(name);
       if(list.size() > 0) {
           mp3Art = list.get(0);
       }
       return mp3Art;
   }
   
   public Mp3 checkArtistMp3(Mp3 mp3) {
       Mp3Artist mp3Art = this.checkArtistMp3Artist(mp3.getArtistName());
       
       if(mp3Art != null) {
           mp3.setArtist(mp3Art);
       }
       return mp3;
   }
   
   public void persistMp3(Mp3 mp3) {
      if(checkForMp3(mp3) == false) {
          mp3 = checkArtistMp3(mp3);
          et.begin();
          em.persist(mp3);
          et.commit();
      }
   }
   
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
   
   
   @Override
   public void mp3Download(String title, int mp3Id)  {
       
       byte[] mp3 = this.getMp3_file(mp3Id);
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
   
   
   public byte[] getMp3_file(int mp3_id) {
        et.begin();
       byte[]file = em.find(Mp3.class, mp3_id).getMp3File();
        et.commit();
        return file;
    }

    
}
