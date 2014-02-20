/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.Mp3;
import java.util.List;

/**
 *
 * @author fseiler
 */
public interface IMp3Dao {
    void addMp3(Mp3 mp3);
    
    void deleteMp3(int mp3_id);
    
    Mp3 getMp3(int mp3_id);
    
    Mp3 getMp3ByArtist (int mp3ArtistId);

    List<Mp3> getAllMp3();
}
