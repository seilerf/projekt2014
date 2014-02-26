/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

import java.util.List;

/**
 *
 * @author AdminMax
 */
public interface IntfComment {
    
    public String getTest();
    public void addComment(String comment_Title, String comment_Description);
    public void deleteComment(int comment_ID);
    public String[] getComment(int comment_ID);
    public List<String[]> getAllComment();
    
}
