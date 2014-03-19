/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.util.List;

/**
 * Interface für den Musikdienst, mit allen benötigten Funktionsaufrufen für die
 * Kommunikationsketten zwischen Webserver <==> Loadbalancer <==> [Musikdienst <--> Kommentardienst]
 * @author fseiler/ M.Ullmann
 */
public interface IMusikd {
    
    public String test();
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name);
    public void deleteMp3(int mp3_id);
    public String[] getMp3(int mp3_id);
    public String[] getMp3ByArtist (int mp3ArtistId);
    public List<String[]> getAllMp3();
    public List<String[]> getAllArtist();
    public byte[] getFile(int mp3_id);
    
    public void setServeradress1(String serveradress1);
    public void setServeradress2(String serveradress2);
    public String getServeradress1();
    public String getServeradress2();
    
    public void setAnzserv(int anzserv) ;
    public int getAnzserv() ;
    public boolean getBalancemethod() ;
    public void setBalancemethod(boolean balancemethod);
    public void balanceMethod();
    public int getInterval()  ;
    public void setInterval(int interval)  ;
    
    public void upLoad(String part);
    public void mp3Download(String mp3_title, int mp3_id);
    public void addCommentForMp3(String comTitle, String comDesc,  int refMp3);
    public void addCommentForArt(String comTitle, String comDesc,  int refArt);
 
    public void addCommentForMp3();
    public void addCommentForArt();
    public void deleteComment();
    public void deleteComment(int com_Id);
    public List<String[]> getAllComment();
    public List<String[]> getAllCommentForArt(int refArt);
    public List<String[]> getAllCommentForTitle(int refMp3);
}
