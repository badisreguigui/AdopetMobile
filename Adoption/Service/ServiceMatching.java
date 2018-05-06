/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entitie.Pet;

/**
 *
 * @author mac
 */
public class ServiceMatching {
    
    public void AjouterMatching(int id)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/matching.php?id_pet="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
           
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void AjouterUnMatching(int id)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/unmatch.php?id_pet="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
           
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
