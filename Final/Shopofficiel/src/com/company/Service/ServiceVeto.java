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
import com.company.Entity.Veto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TESNIME
 */
public class ServiceVeto {
     public ArrayList<Veto> showList() 
    
    {
        ArrayList<Veto> listVeto = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/showListVeto.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Vets = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Vets.put("Veto", Vets.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Vets.get("Veto");

                    for (Map<String, Object> obj : list) {

                        Veto v = new Veto();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                        v.setNom(obj.get("nom").toString());
                        v.setVille(obj.get("ville").toString());
                        v.setImage(obj.get("image").toString());
                        
                        
                        
                        v.setId((int) id);


                        
                        listVeto.add(v);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVeto;
    }
     
     public Veto showVeto(int id)
    {
        
        Veto v = new Veto();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/showProfilVeto.php?id="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Vets = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Vets.put("Veto", Vets.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Vets.get("Veto");

                    for (Map<String, Object> obj : list) {

                    
                         float id = Float.parseFloat(obj.get("id").toString());
                        v.setNom(obj.get("nom").toString());
                        v.setPrenom(obj.get("prenom").toString());
                        v.setImage(obj.get("image").toString());
                        v.setRue(obj.get("rue").toString());
                        v.setVille(obj.get("ville").toString());
                        v.setGouvernorat(obj.get("gouvernorat").toString());
                        v.setTelephone(Integer.parseInt(obj.get("telephone").toString()));
                        v.setEmail(obj.get("email").toString());
                        //v.setDescription(obj.get("description").toString());

                              
                        v.setId((int) id);


                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return v;
    }
    
}
