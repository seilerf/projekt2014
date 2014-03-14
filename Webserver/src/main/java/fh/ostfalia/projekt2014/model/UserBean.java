
package fh.ostfalia.projekt2014.model;

import java.io.Serializable;
import javax.annotation.security.DeclareRoles;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
* Diese Klasse dient zum Login und Logout
*
* @author Anton
*/
@DeclareRoles({"user", "admin"})
public class UserBean implements Serializable{

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
    
    /**
     * Versucht den login durchzuführen wenn geprüft, schaut er die Rolle des Users nach und
     * gibt entweder die Adminseite für admins zurück oder die Musikseite
     * Bei fehlschlägen die error Seite
     * @return die entsprechende Webseite
     */
    public String login () {
      
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.login(username, password);

            if (request.isUserInRole("admin"))
                return "adminseite";
            
            else if (request.isUserInRole("user"))
                return "musik";

            return "error";
        } catch (ServletException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error Occured: Login failed", null));
        }
        return "error";
    }

    /**
     * holt sich den aktuellen request um ihn auszuloggen
     */
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
            new StringRefAddr("UserBean", loginBean),
                        null,
                        null) ; // Factory location
                            }
}