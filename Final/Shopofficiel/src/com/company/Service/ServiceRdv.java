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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.company.Entity.RDV;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
    
     public void envoyerSMSRendezVous(RDV r) 
    
    {
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPI/web/app_dev.php/envoyerSMS?heure="+r.getHeure()+"&date_rdv="+r.getDate_rdv());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
     
    public int VerifRendezVous(int id_veto,Picker date_rdv) 
    
    {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(date_rdv.getDate()));
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date_rdv.getDate()); // sets calendar time/date
        cal.add(Calendar.MINUTE, -30); // adds one hour
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(cal.getTime())); // returns new date object, one hour in the future
        Calendar cal2 = Calendar.getInstance(); // creates calendar
        cal2.setTime(date_rdv.getDate()); // sets calendar time/date
        cal2.add(Calendar.MINUTE, 30); // adds one hour
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(cal2.getTime())); // returns new date object, one hour in the future
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date_rdv.getDate()));
        
        
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/VerifierRendezVous.php?id_veto="+id_veto+"&date_rdv="+new SimpleDateFormat("yyyy-MM-dd").format(date_rdv.getDate())+"&heure1="+new SimpleDateFormat("hh:mm:ss").format(cal.getTime())+"&heure2="+new SimpleDateFormat("hh:mm:ss").format(cal2.getTime()));
        List<Integer> liste = new ArrayList<Integer>();
        con.addResponseListener(new ActionListener<NetworkEvent>()
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Vetos = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    Vetos.put("Veto", Vetos.remove("root"));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Vetos.get("Veto");
                    
                    System.out.println(list);
                    System.out.println(Vetos);
                    
                    for (Map<String, Object> obj : list) {

                      int count = Integer.parseInt(obj.get("count").toString());
                      liste.add(count);

                    }
                } catch (IOException ex) {
                }  
           
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return liste.get(0);
    
    }
     
    
}
