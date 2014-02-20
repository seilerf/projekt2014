/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author fseiler
 */

@Entity(name = "Mp3")
@NamedQueries({
@NamedQuery(name = "Mp3.getAll", query = "SELECT e FROM Mp3 e")})

public class Mp3 implements Serializable{
    private Mp3Artist mp3Artist;
    private int mp3_id;
    private byte[] mp3_file;
    private String mp3_title;

    public Mp3() {
    }

    public Mp3(Mp3Artist mp3Artist, byte[] mp3_file, String mp3_title) {
        this.mp3Artist = mp3Artist;
        this.mp3_file = mp3_file;
        this.mp3_title = mp3_title;
    }

    @Id
    @Column(name = "mp3_id")
    @GeneratedValue(strategy = IDENTITY)
    public int getMp3_id() {
        return mp3_id;
    }

    public void setMp3_id(int mp3_id) {
        this.mp3_id = mp3_id;
    }

    @Column(name = "mp3_file")
    @Lob
    public byte[] getMp3_file() {
        return mp3_file;
    }

    public void setMp3_file(byte[] mp3_file) {
        this.mp3_file = mp3_file;
    }

    @Column(name = "mp3_title")
    public String getMp3_title() {
        return mp3_title;
    }

    public void setMp3_title(String mp3_title) {
        this.mp3_title = mp3_title;

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    public Mp3Artist getMp3Artist() {
        return this.mp3Artist;
    }

    public void setMp3Artist(Mp3Artist mp3Artist) {
        this.mp3Artist = mp3Artist;
    }
    
    public int getArtistId(){
        return mp3Artist.getArtist_id();
    }

    public void setMp3ByteCodeFromFile(File file) {

        try {
            mp3_file = new byte[(int) file.length()];

            FileInputStream inputStream = new FileInputStream(file);
            try {
                inputStream.read(mp3_file);
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Mp3.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mp3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
