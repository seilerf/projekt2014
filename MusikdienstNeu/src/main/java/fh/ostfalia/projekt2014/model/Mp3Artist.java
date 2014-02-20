/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author fseiler
 */

@Entity(name = "Mp3Artist")
@NamedQueries({
@NamedQuery(name = "Mp3Artist.getAll", query = "SELECT e FROM Mp3Artist e")})

public class Mp3Artist implements Serializable{
    private int artist_id;
    private String artist_name;
    private Set<Mp3> mp3Beans = new HashSet();
    
    public Mp3Artist(){
    }

    public Mp3Artist(String artist_name) {
        this.artist_name = artist_name;
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artist_id", unique = true, nullable = false)
    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }
    
    public void addMp3(Mp3 mp3){
        mp3Beans.add(mp3);
    }

    @Column(name = "artist_name")
    public String getArtistName() {
        return artist_name;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }

    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "Mp3Artist")
    public Set<Mp3> getMp3s() {
        return this.mp3Beans;
    }

    public void setMp3s(Set<Mp3> mp3s) {
        this.mp3Beans = mp3s;
    }
}
