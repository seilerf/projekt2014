package fh.ostfalia.projekt2014.main;



import fh.ostfalia.projekt2014.rmi.Commentd;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author M.Ullmann
 */
public class App {
    public static void main( String[] args ) {
        Commentd commentd;
        try {
            commentd = new Commentd();
            commentd.registerRmi();
        } catch (RemoteException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
