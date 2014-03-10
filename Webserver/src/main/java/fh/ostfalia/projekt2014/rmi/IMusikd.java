/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

/**
 *
 * @author fseiler
 */
public interface IMusikd {
    
    public String getTest();
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name);
    public void deleteMp3(int mp3_id);
    public Object getMp3(int mp3_id);
    public Object getMp3ByArtist (int mp3ArtistId);
    public Object getAllMp3();
    public void setServeradress1(String serveradress1);
    public void setServeradress2(String serveradress2);
    public String getServeradress1();
    public String getServeradress2();
}
