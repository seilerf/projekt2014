/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Diese Klasse enthält das User Model und die getter und setter für den User
 * @author anton
 */
@Entity
@Table
@NamedQueries({@NamedQuery(name="User.getAll",query="SELECT e FROM User e")})
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (name = "userid")
    private int userid;
    @Column (name = "username")
    private String username;
    @Column (name = "password")
    private String password;
    @Column (name = "groupname")
    private String groupname="user";

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

 

    public User( String username, String password) {
        
        this.username = username;
        this.password = password;
       
    }
    
    


    
    
}
