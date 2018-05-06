/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.Entity.Tarif;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TESNIME
 */
public class ServiceTarif {
     public Tarif showTarif(int id_veto)
    {
        
        Tarif v = new Tarif();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/budget.php?id_veto="+id_veto);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Tarif = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Tarif.put("Tarif", Tarif.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Tarif.get("Tarif");

                    for (Map<String, Object> obj : list) {

                        ///Pediatre p = new Pediatre();
                        // float id = Float.parseFloat(obj.get("id_veto").toString());
                        v.setConsultation(Integer.parseInt(obj.get("consultation").toString()));
                        v.setSterilisation(Integer.parseInt(obj.get("sterilisation").toString()));
                        v.setVaccinationChat(Integer.parseInt(obj.get("vaccinationChat").toString()));
                         v.setVaccinationChien(Integer.parseInt(obj.get("vaccinationChien").toString()));
                        v.setAnalyses(Integer.parseInt(obj.get("analyses").toString()));
                        v.setId_veto(Integer.parseInt(obj.get("id_veto").toString()));

                        //v.setId_veto((int) id);


                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return v;
    }
    
       
}
