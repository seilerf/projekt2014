/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.webserver;


import fh.ostfalia.projekt2014.dao.UserDao;
import java.io.Serializable;
import java.security.Principal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginBean implements Serializable, Referenceable {

  private String username;
  private String password;
  private String loginBean;

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

  
  
  public String login () {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpServletRequest request;
      request = (HttpServletRequest) fc.getExternalContext().getRequest();
      
    try {
      request.login(this.username, this.password);//Fehler...
      Principal principal = request.getUserPrincipal();
      if (request.isUserInRole("admin")) {
				String msg = "User: " + principal.getName() + ", Role: admin";
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
				return "adminseite";
      }
       else if (request.isUserInRole("user")) {
				String msg = "User: " + principal.getName() + ", Role: user";
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
				return "musikservice";
       } 
        return "du_musst_die_rollen_noch_definieren";
    } catch (ServletException e) {
   
        fc.addMessage(null, new FacesMessage("Login failed."));
        return "error";
    }   
  }

  public void logout() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest)
        fc.getExternalContext().getRequest();
    try {
      request.logout();
    } catch (ServletException e) {
  
      fc.addMessage(null, new FacesMessage("Logout failed."));
    }
  }

    @Override
    public Reference getReference() throws NamingException {
        return new Reference(
	    LoginBean.class.getName(),
	    new StringRefAddr("loginBean", loginBean),
	    null,
	    null);          // Factory location
    }
  

}
