/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.rmi;

/**
 *
 * @author fseiler
 */
public class Mp3DaoRmi implements IMp3{
    
    private IMp3 intf;
    
    public String[] getAllMp3() {
        return intf.getAllMp3();
    }
    
}
