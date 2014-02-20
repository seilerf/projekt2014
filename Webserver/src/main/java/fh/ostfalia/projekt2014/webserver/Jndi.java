/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.webserver;

import fh.ostfalia.projekt2014.dao.UserDao;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author KingDCB
 */
public class Jndi {

    Hashtable env = new Hashtable();
    String name = new String();
    UserDao rmiRegister;
    //LoginBean client = new LoginBean();

    public void jndi() {
        try {

            name = "java:global/WEBSERVER/UserDao";

            rmiRegister = new UserDao();
            //env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.fscontext.RefFSContextFactory");

            //Context ctx = new InitialContext(env);
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost/rmifi", rmiRegister);
            System.out.println("RMI-Facade wurde registriert!");

            //ctx.bind(name, client);
            //ctx.rebind(name, client);
            //Object obj = ctx.lookup(name);
            // Print it
            //System.out.println(name + " is bound to: " + obj);
            //ctx.close();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Jndi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
