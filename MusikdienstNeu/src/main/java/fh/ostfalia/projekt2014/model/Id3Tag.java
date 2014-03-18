/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

/**
 * Die Klasse Id3Tag verarbeitet die Mp3-Datei um sie in der Datenbank für den Musikdienst zu persistieren.
 * Der Im Konstruktor angegebene Pfad muss auf den gesetzt werden, in dem sich der Ordner mit den Mp3-Dateien
 * die zum Upload zur Verfügung stehen sollen.
 * @author M.Ullmann
 */
public class Id3Tag {
    private MP3File mp3file;
    private final File uploadDir;
    private Mp3 mp3;
    Mp3Artist mp3Artist;

    public Id3Tag() {
        mp3 = new Mp3();
        mp3Artist = new Mp3Artist();
        uploadDir = new File("C:\\Users\\AdminMax\\Documents\\NetBeansProjects\\projekt2014\\MusikdienstNeu\\Upload");
    }
    //Konstruktor der tu Testzwecken zum Einsatz kam.
    public Id3Tag(String mp3Upload) {
        mp3 = new Mp3();
        mp3Artist = new Mp3Artist();
        uploadDir = new File(mp3Upload);
    }
    
    /**
     * Die Funktion readArtist ließt aus der übergebenen File den Künstler der Mp3 heraus.
     * @param file  -> Pfad zur Mp3-Datei
     * @return      -> Künstlername als String
     * @throws TagException
     * @throws IOException 
     */
    private String readArtist(File file) throws TagException, IOException {
        mp3file = new MP3File(file);
        return mp3file.getID3v1Tag().getArtist();
    }

    /**
     * Die Funktion readTitle ließt aus der übergebenen File den Titel der Mp3 heraus.
     * @param file  -> Pfad zur Mp3-Datei
     * @return      -> Mp3-Titel als String
     * @throws TagException
     * @throws IOException 
     */
    private String readTitle(File file) throws TagException, IOException {
        mp3file = new MP3File(file);
        return mp3file.getID3v1Tag().getSongTitle();
    }
    
    /**
     * Die Funktion readMp3File ließt und setzt mit Hilfe des angegebenen Pfades und den zuvor beschriebenen
     * Funktionen die Werte des Mp3Artists und der Mp3.
     * @param file  -> Pfad zur Mp3-Datei
     * @return      -> Liefert das Mp3-Objekt zurück
     */
    public Mp3 readMp3File(File file){
        try {

            mp3Artist.setArtistName(this.readArtist(file));
            mp3.setMp3Title(this.readTitle(file));
            mp3.setArtist(mp3Artist);
            mp3.setByteCodeFromFile(file);
            
        } catch (TagException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3;    
    }
}
