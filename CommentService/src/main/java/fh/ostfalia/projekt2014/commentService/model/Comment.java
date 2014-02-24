/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentService.model;

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
 * @author AdminMax
 */
@Entity
@Table(name="Comment")
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "commnet_ID")
    @GeneratedValue(strategy = IDENTITY)
    private Integer commentID;
    
    @Column(name = "comment_Title")
    private String commentTitle;
    
    @Column(name = "comment_Description")
    private String commentDescription;
    
    @Column(name = "comment_RefID")
    /* Dieser Datenbankeintrag ist der Referenzverweisung(FK), der auf den Musiktitel verweißt, zu dem der Kommentar gehört*/
    private Integer refMusicTitleID;

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public Integer getRefMusicTitleID() {
        return refMusicTitleID;
    }

    public void setRefMusicTitleID(Integer refMusicTitleID) {
        this.refMusicTitleID = refMusicTitleID;
    }

    public Comment() {
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
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
