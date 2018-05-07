/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author achref kh
 */
public class EventServices {    
    
    
    public ArrayList<Event> AfficherMesevents(int id){
        ArrayList<Event> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/selectmyevents.php?id="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(events);
//                    System.out.println(events.get("root"));
//                    System.out.println("aze");
                    events.put("event", events.remove("root"));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("event");

                    for (Map<String, Object> obj : list) {

                        Event ev = new Event();
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        ev.setId((int) id);
                        ev.setNom(obj.get("nom").toString());
                        ev.setLieu((String) obj.get("lieu"));
                        ev.setDescription((String) obj.get("description"));
                        ev.setType((String) obj.get("type"));
                        ev.setNbrpart(Integer.parseInt(obj.get("nbrpart").toString()));
                        listEvents.add(ev);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    
    public ArrayList<Event> AfficherMesParticipations(int id){
        ArrayList<Event> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/selectParticipations.php?id="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(events);
//                    System.out.println(events.get("root"));
//                    System.out.println("aze");
                    events.put("event", events.remove("root"));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("event");

                    for (Map<String, Object> obj : list) {

                        Event ev = new Event();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                        ev.setNom(obj.get("nom").toString());
                        ev.setLieu((String) obj.get("lieu"));
                        ev.setDescription((String) obj.get("description"));
                        ev.setType((String) obj.get("type"));
                        ev.setNbrpart(Integer.parseInt(obj.get("nbrpart").toString()));
                        listEvents.add(ev);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    
    }
    
    public void ajoutEvent(Event ev){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mobile/ajouterevent.php?nom="+ ev.getNom() +"&type="+ ev.getType()+"&lieu="+ ev.getLieu()+"&description="+ ev.getDescription()+"&datedebut="+ ev.getDatedebut()+"&datefin="+ ev.getDatefin()+"&organisateur=" + ev.getOrganisateur();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
//        
    }
    
    public ArrayList<Event> AfficherTousEvents(int id){
        ArrayList<Event> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/selectAllevents.php?id="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(events);
//                    System.out.println(events.get("root"));
//                    System.out.println("aze");
                    events.put("event", events.remove("root"));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("event");

                    for (Map<String, Object> obj : list) {

                        Event ev = new Event();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                        ev.setNom(obj.get("nom").toString());
                        ev.setLieu((String) obj.get("lieu"));
                        ev.setDescription((String) obj.get("description"));
                        ev.setType((String) obj.get("type"));
                        ev.setNbrpart(Integer.parseInt(obj.get("nbrpart").toString()));
                        listEvents.add(ev);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
}
