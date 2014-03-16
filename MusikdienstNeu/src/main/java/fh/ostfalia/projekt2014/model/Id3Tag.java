/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

/**
 *
 * @author fseiler/M.Ullmann
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

    public Id3Tag(String mp3Upload) {
        mp3 = new Mp3();
        mp3Artist = new Mp3Artist();
        uploadDir = new File(mp3Upload);
    }

    private String readArtist(File file) throws TagException, IOException {
        mp3file = new MP3File(file);
        return mp3file.getID3v1Tag().getArtist();
    }

    private String readTitle(File file) throws TagException, IOException {
        mp3file = new MP3File(file);
        return mp3file.getID3v1Tag().getSongTitle();
    }
    
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

    public ArrayList<Mp3> initFiles(int readFiles) {

        ArrayList<Mp3> mp3List = new ArrayList();
        File[] fileList = uploadDir.listFiles();

        for (int i = 0; i <= readFiles - 1; i++) {
            File file = fileList[i].getAbsoluteFile();
            if (file.isFile()) {
                try {
                    Mp3 mp3 = new Mp3();
                    Mp3Artist mp3Artist = new Mp3Artist();

                    mp3Artist.setArtistName(this.readArtist(file));
                    
                    FileInputStream input = new FileInputStream(file);
                    
                    int read = 0;
                    byte[] bytes = new byte[1024];
                   
                    while ((read = input.read(bytes)) != -1) {
                        input.read(bytes, 0, read);
                    }

                    mp3.setMp3File(bytes);
                                   
                    mp3.setMp3Title(this.readTitle(file));
                    mp3List.add(mp3);
                } catch (TagException | IOException ex) {
                    Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return mp3List;
    }
}
