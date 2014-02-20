/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmibsp;

import java.rmi.*;
/**
 *
 * @author KingDCB
 */
public interface DienstServer extends Remote {
    Object[] getDienstListe() throws RemoteException;
    
    Dienst getDienst(Object dienstSchlüssel) throws RemoteException;
}
