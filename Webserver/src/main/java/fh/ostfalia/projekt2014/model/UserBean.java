/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.model;

import fh.ostfalia.projekt2014.dao.UserDao;
import java.io.Serializable;
import java.security.Principal;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Yannick
 */

public class UserBean implements Serializable{

    
  private String username;
  private String password;
  private String loginBean;
  private User current;
  private EntityManager userService;
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
    
    public String login() {
    
    System.out.println("Ich wurde aufgerufen! :D");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request;
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        System.out.println(username);
         System.out.println(password);
        try {
            System.out.println("Login wird gestartet ... ");
            request.login(username, password);
                        System.out.println("Login wird gestartet ... 2");

            Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("user")) {
                String msg = "User: " + principal.getName() + ", Role: user";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "musikservice";
                    
                    
                
            } else if     (request.isUserInRole("admin")) {
                String msg = "User: " + principal.getName() + ", Role: admin";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "adminseite";
            }
            return "du_musst_die_rollen_noch_definieren";
        } catch (ServletException e) {

            fc.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
    }
    

  public String login2() {

        System.out.println("Ich wurde aufgerufen! :D");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request;
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        System.out.println(username);
         System.out.println(password);
        try {
            System.out.println("Login wird gestartet ... ");
                request.login(this.username, this.password);                        
                System.out.println("Login wird gestartet ... 2");

            Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("admin")) {
                String msg = "User: " + principal.getName() + ", Role: admin";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "musikservice";
            } else  {
                String msg = "User: " + principal.getName() + ", Role: user";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "registrierung";
            }
        } catch (ServletException e) {

            fc.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
    }
  
  public String login1 () {
      
     FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		try {
			//Login per Servlet 3.0
			request.login(username, password);
 
			// Der Principal entspricht dem Usernamen
			Principal principal = request.getUserPrincipal();
 
			// Wir können hier nur abfragen, ob der User eine Rolle hat (isUserInRole('whatever')),
			// aber wir können NICHT die Rolle aktiv erfragen (z.B. mit getUserRole(...))
			if (request.isUserInRole("admin")) {
				String msg = "User: " + principal.getName() + ", Role: admin";
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
				return "adminseite";
			} else if (request.isUserInRole("User")) {
				String msg = "User: " + principal.getName() + ", Role: User";
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
				return "musikservice";
			} 
			return "du_musst_die_rollen_noch_definieren";	// hier sollte etwas sinnvolles passieren ;-)
		} catch (ServletException e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error Occured: Login failed", null));
			e.printStackTrace();
		}
		return "loginFailed";
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

    
    public Reference getReference() throws NamingException {
        return new Reference(
	    UserBean.class.getName(),
	    new StringRefAddr("loginBean", loginBean),
	    null,
	    null);          // Factory location
    }
  

}
