/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.util.List;

/**
 * Interface für die Kommentardienst-Klasse im Musikdienst mit alle
 * Funktionsaufrufen, um mit dem Kommentardienst kommunizieren zu können.
 * @author M.Ullmann
 */
public interface ICommentd {
    
    public String getTest();
    public void addCommentForMp3(String comTitle, String comDesc, int refMp3); 
    public void addCommentForArt(String comTitle, String comDesc, int refArt); 
    public void deleteComment(int com_Id);
    public String[] getComment(int comment_ID);
    public List<String[]> getAllComment();
    public List<String[]> getAllCommentForTitle(int refMp3);
    public List<String[]> getAllCommentForArt(int refArt);
}
