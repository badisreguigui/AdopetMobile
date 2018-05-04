/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entitie.Pet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class ServicePet {
    
    public ArrayList<Pet> showList() 
    
    {
        ArrayList<Pet> listVeto = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/showListPet.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Vets = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Vets.put("Pet", Vets.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Vets.get("Pet");

                    for (Map<String, Object> obj : list) {

                        Pet v = new Pet();
                        float id = Float.parseFloat(obj.get("id_pet").toString());
                        
                        
                        v.setName_pet(obj.get("name_pet").toString());
                        
                        v.setPet_image(obj.get("pet_image").toString());
                        
                        
                        v.setId_pet((int) id);

                        
                        


                        
                        listVeto.add(v);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVeto;
    }
    
    public ArrayList<Pet> showListLimit() 
    
    {
        ArrayList<Pet> listVeto = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/showlistpetlimit.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Vets = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Vets.put("Pet", Vets.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Vets.get("Pet");

                    for (Map<String, Object> obj : list) {

                        Pet v = new Pet();
                        float id = Float.parseFloat(obj.get("id_pet").toString());
                        
                        
                        v.setName_pet(obj.get("name_pet").toString());
                        v.setPet_image(obj.get("pet_image").toString());
                        v.setBreed(obj.get("breed").toString());
                        v.setDescription(obj.get("description").toString());
                        
                        
                        
                        v.setId_pet((int) id);

                        
                        


                        
                        listVeto.add(v);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVeto;
    }
    
    public Pet showPet(int id)
    {
        
        Pet p = new Pet();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/showProfilPet.php?id="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Pet = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Pet.put("pet", Pet.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Pet.get("pet");

                    for (Map<String, Object> obj : list) {

                        ///Pediatre p = new Pediatre();
                         float id = Float.parseFloat(obj.get("id_pet").toString());
                        p.setName_pet(obj.get("name_pet").toString());
                        p.setBreed(obj.get("breed").toString());
                        p.setSex(obj.get("sex").toString());
                        p.setAge(Integer.parseInt(obj.get("age").toString()));
                        p.setDescription(obj.get("description").toString());
                        p.setPet_image(obj.get("pet_image").toString());
                        p.setId_pet((int) id);


                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return p;
    }
    
    
    
}
