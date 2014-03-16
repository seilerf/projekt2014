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
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")})
@Entity
public class Comment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    //CommentID um den jeweiligen Kommentar eindeutig zu definieren
    private Integer commentID;
    //CommentTitel, welcher als Überschrift oder Schlagwort gedacht ist
    private String commentTitle;
    //Die comToMp3ID gibt immer die ID der Mp3 an, zu dem der Kommentar gehört
    private Integer comToMp3ID;
    //Beschreibung des Eigentlichen Kommentars
    private String commentDescription;
    //Die comToArtID gibt immer die ID des Künstlers an, zu dem der Kommentar gehört
    private Integer comToArtID;
    
    public Comment() {
    }
    
    public Comment(Integer commentID) {
        this.commentID = commentID;
    }

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getCommentID() {
        return commentID;
    }
    
     public void setCommentID(Integer commentID) {
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
    public Integer getCommentToMp3() {
        return this.comToMp3ID;
    }

    public void setCommentToMp3(Integer commentToMp3) {
        this.comToMp3ID = commentToMp3;
    }
    
    @Column(name = "comment_refart")
    public Integer getCommentToArt() {
        return this.comToArtID;
    }
    
    public void setCommentToArt(Integer comToArtID) {
        this.comToArtID = comToArtID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentID != null ? commentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the commentID fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentID == null && other.commentID != null) || (this.commentID != null && !this.commentID.equals(other.commentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fh.ostfalia.projekt2014.commentService.model.Comment[ id=" + commentID + " ]";
    }
    
}
