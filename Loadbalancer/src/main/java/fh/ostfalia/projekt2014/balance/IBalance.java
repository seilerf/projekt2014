/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.balance;

import java.rmi.Remote;

import java.rmi.RemoteException;


/**
 *
 * @author Anton
 */
public interface IBalance extends Remote {
    
    public String getAdress() throws RemoteException;
    
}