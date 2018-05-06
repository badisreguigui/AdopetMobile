/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.RDV;

/**
 *
 * @author TESNIME
 */
public class ServiceRdv {
     public void AjoutRendezVous(RDV r) 
    
    {
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/ajouterRendezVous.php?id_veto="+r.getId_veto()+"&date_rdv="+new SimpleDateFormat("yyyy-MM-dd").format(r.getDate_rdv())+"&heure="+new SimpleDateFormat("hh:mm:ss").format(r.getHeure()));

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
     
    
}
