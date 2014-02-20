/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.Mp3Artist;
import java.util.List;

/**
 *
 * @author fseiler
 */
public interface IMp3ArtistDao {
    void persistMp3Artist(Mp3Artist mp3Artist);
    
    void deleteMp3Artist(int mp3ArtistId);
    
    Mp3Artist getMp3Artist(int mp3ArtistId);

    List<Mp3Artist> getAllMp3Artist();
}
