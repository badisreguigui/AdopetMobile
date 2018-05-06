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
    
//    public void checkMatching(int idU, int idP)
//    {
//        ConnectionRequest con = new ConnectionRequest();
//        //con.setUrl("http://localhost/mobile/unmatch.php?id_pet="+id);
//        con.setUrl("http://localhost/mobile/matchingcheck.php?id_user="+idU+"&id_pet="+idP);
//
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//               
//           
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//    }
    
    public int checkMatching(int idU,int idP) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mobile/matchingcheck.php?id_user="+idU+"&id_pet="+idP;
        con.setUrl(Url);
        
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("count", products.remove("root"));
                 //   System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("count");

                    for (Map<String, Object> obj : list)
                    {
                        int i=Integer.parseInt(obj.get("counting").toString());
                        System.out.println("i");    
                       list1.add(i);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        int s=0;
        for(int s1 : list1){
            if (s1==1)
                s=1;
            else 
                s=0;
        }
        
        System.out.println(s);
        
        return s; 
    }

    
    
    
}
