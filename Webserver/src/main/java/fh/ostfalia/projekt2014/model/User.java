/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.model;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author anton
 */
@Entity
@Table
@NamedQueries({@NamedQuery(name="User.getAll",query="SELECT e FROM User e")})
public class User implements Serializable{
    @Id
    @Column (name = "userid")
    private int userid;
    @Column (name = "username")
    private String username;
    @Column (name = "password")
    private String password;
  


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

    

 

    public User( String username, String password) {
        
        this.username = username;
        this.password = password;
       
    }
    
    


    
    public User(){}
}
