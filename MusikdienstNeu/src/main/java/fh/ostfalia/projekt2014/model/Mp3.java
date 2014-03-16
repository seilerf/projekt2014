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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author M.Ullmann
 */
@Entity
@Table(name = "mp3")
@NamedQueries({
    @NamedQuery(name = "Mp3.findAll", query = "SELECT m FROM Mp3 m"),
    @NamedQuery(name = "getNameForMp3", query = "SELECT m FROM Mp3 m where m.mp3Title = :name")})
public class Mp3 implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int mp3Id;
    private byte[] mp3File;
    private String mp3Title;
    private Mp3Artist mp3Artist;

    public Mp3() {
    }

    public Mp3(int mp3Id) {
        this.mp3Id = mp3Id;
    }

    @Id
    @Column(name = "mp3_id")
    @GeneratedValue(strategy = AUTO)
    public int getMp3Id() {
        return mp3Id;
    }

    public void setMp3Id(int mp3Id) {
        this.mp3Id = mp3Id;
    }

    @Column(name = "mp3_file")
    @Lob
    public byte[] getMp3File() {
        return mp3File;
    }

    public void setMp3File(byte[] mp3File) {
        this.mp3File = mp3File;
    }
    @Column(name = "mp3_title")
    public String getMp3Title() {
        return mp3Title;
    }

    public void setMp3Title(String mp3Title) {
        this.mp3Title = mp3Title;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity=Mp3Artist.class)
    @JoinColumn(name = "artist_id", nullable = true)
    public Mp3Artist getArtist(){
        return this.mp3Artist;
    }
    
    public void setArtist(Mp3Artist mp3Artist){
        this.mp3Artist = mp3Artist;
    }

    public int getArtistId() {
        return this.mp3Artist.getArtistId();
    }

    public void setByteCodeFromFile(File file) {
        try {
            mp3File = new byte[(int) file.length()];

            FileInputStream inputStream = new FileInputStream(file);
            try {
                inputStream.read(mp3File);
                inputStream.close();
            } catch (IOException ex) {
                System.err.println("Fehler beim Lesen der MP3-Datei: IOException.");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Fehler beim Lesen der MP3-Datei: Datei nicht gefunden.");
        }
    }
    
    public void setNewArtistID(int artist_id) {
        mp3Artist.setArtistId(artist_id);
    }
    
    public String getArtistName() {
        return this.mp3Artist.getArtistName();
    }

    @Override
    public String toString() {
        return "fh.ostfalia.projekt2014.model.Mp3[ mp3Id=" + mp3Id + " ]";
    }
    
}
