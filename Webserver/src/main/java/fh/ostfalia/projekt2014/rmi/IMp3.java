/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.util.List;

/**
 *
 * @author fseiler
 */
public interface IMp3 {
    
    public String getTest();
    public void addMp3(String mp3_title, String mp3_Artist, String mp3_name);
    public void deleteMp3(int mp3_id);
    public String[] getMp3(int mp3_id);
    public String[] getMp3ByArtist (int mp3ArtistId);
    public List<String[]> getAllMp3();
    
}
