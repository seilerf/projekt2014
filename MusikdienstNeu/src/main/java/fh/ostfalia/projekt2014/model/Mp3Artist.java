/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Die Klasse Mp3Artist realisiert die Mp3 Künstler, welche vom Musikdienst benötigt werden.
 * -> Klassische Getter/ Setter für die Mp3-Künstler Attribute 
 * Zudem sind die Getter der Attribute mit den entsprechenden Annotationen für das Datenbank-Schema für die Mp3-Künstler-Datenbank versehen.
 * @see Mp3Artist.findAll Query -> Gibt alle Künstler aus der Künstler Datenbank zurück
 * @see getName Query -> Gibt alle Künstler aus der Künstler Datenbank zurück, die den Künstlernamen entsprechend des übergebenen Paramters haben 
 * @author fseiler/ M.Ullmann
 */
@Entity
@Table(name = "mp3artist")
@NamedQueries({
    @NamedQuery(name = "Mp3Artist.findAll", query = "SELECT m FROM Mp3Artist m"),
    @NamedQuery(name = "getName", query = "SELECT m FROM Mp3Artist m WHERE m.artistName = :name")})
public class Mp3Artist implements Serializable {
    
    private static final long serialVersionUID = 1L;
    //Eindeutige Id für den Künstler
    private int artistId;
    //String für den Künstlernamen
    private String artistName;

    //Default Konstruktor
    public Mp3Artist() {
    }

    public Mp3Artist(int artistId) {
        this.artistId = artistId;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "artist_id")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
    
    @Size(max = 255)
    @Column(name = "artist_name")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }


    @Override
    public String toString() {
        return "fh.ostfalia.projekt2014.model.Mp3Artist[ artistId=" + artistId + " ]";
    }
    
    
}
