/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author M.Ullmann
 */

@Table(name = "comment")
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.getAllWithArtist", query = "SELECT c FROM Comment c WHERE c.commentToArt = :artId"),
    @NamedQuery(name = "Comment.getAllWithTitle", query = "SELECT c FROM Comment c WHERE c.commentToMp3 = :titleId")})
@Entity
public class Comment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    //CommentID um den jeweiligen Kommentar eindeutig zu definieren
    private int commentID;
    //CommentTitel, welcher als Überschrift oder Schlagwort gedacht ist
    private String commentTitle;
    //Die comToMp3ID gibt immer die ID der Mp3 an, zu dem der Kommentar gehört
    private int comToMp3ID;
    //Beschreibung des Eigentlichen Kommentars
    private String commentDescription;
    //Die comToArtID gibt immer die ID des Künstlers an, zu dem der Kommentar gehört
    private int comToArtID;
    
    public Comment() {
    }
    
    public Comment(int commentID) {
        this.commentID = commentID;
    }

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = AUTO)
    public int getCommentID() {
        return commentID;
    }
    
     public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    
    @Column(name = "comment_title")
    public String getCommentTitle() {
        return commentTitle;
    }
    
    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }
    
    @Column(name = "comment_description")
    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }
    
   
    @Column(name = "comment_refmp3")
    public int getCommentToMp3() {
        return this.comToMp3ID;
    }

    public void setCommentToMp3(int commentToMp3) {
        this.comToMp3ID = commentToMp3;
    }
    
    @Column(name = "comment_refart")
    public int getCommentToArt() {
        return this.comToArtID;
    }
    
    public void setCommentToArt(int comToArtID) {
        this.comToArtID = comToArtID;
    }


    @Override
    public String toString() {
        return "fh.ostfalia.projekt2014.commentService.model.Comment[ id=" + commentID + " ]";
    }
    
}
