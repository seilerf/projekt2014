/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.dao;

import fh.ostfalia.projekt2014.model.Comment;
import java.util.List;

/**
 *
 * @author AdminMax
 */
public interface IntfCommentDao {
    void addComment(Comment c);
    void deleteComment(int comment_ID);
    Comment getComment(int comment_ID);
    List<Comment> getAllComment();    
}
