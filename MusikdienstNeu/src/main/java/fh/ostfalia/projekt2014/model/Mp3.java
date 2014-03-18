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
 * Die Klasse Mp3 realisiert die Mp3s des Musikdienstes.
 * -> Klassische Getter/ Setter für die Mp3 Attribute 
 * Zudem sind die Getter der Attribute mit den entsprechenden Annotationen für das Datenbank-Schema für die Mp3-Datenbank versehen.
 * @see Mp3.findAll Query -> Gibt alle Mp3s aus der Datenbank zurück
 * @see Mp3.getNameForMp3 -> Gibt alle Mp3s aus der Datenkank zurück, bei denen der Titel dem übergebenen Parameter entspricht
 * @author M.Ullmann
 */
@Entity
@Table(name = "mp3")
@NamedQueries({
    @NamedQuery(name = "Mp3.findAll", query = "SELECT m FROM Mp3 m"),
    @NamedQuery(name = "getNameForMp3", query = "SELECT m FROM Mp3 m where m.mp3Title = :name")})
public class Mp3 implements Serializable {
    
    private static final long serialVersionUID = 1L;
    //Mp3-Id zur Einzigartigkeit
    private int mp3Id;
    //Byte-Array wird für die Mp3-Datei benötigt
    private byte[] mp3File;
    //String für den Titel der Mp3
    private String mp3Title;
    //Gibt den Künstler der Mp3-Datei zurück
    private Mp3Artist mp3Artist;
    
    //Default Konstruktor
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

    /**
     * Mit der ManyToOne- u.JoinColumn-Annotation wird für die Verknüpfung zur "artist_id" Spalte in der Mp3Artist-DB gesorgt.
     * @return -> Gibt den Künstler der Mp3-Datei zurück
     */
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

    /**
     * Die setByteCodeFromFile Funktion liest den Bytestrom der Dati aus und liest diesen
     * über einen InputStream ein. Diese Methode wird in der Id3Tag Klasse benötigt.
     * @param file 
     */
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
