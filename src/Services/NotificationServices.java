/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Notification;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author achref kh
 */
public class NotificationServices {

    public ArrayList<Notification> getnotifs(int id) {

        ArrayList<Notification> listnotif = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/selectnotif.php?id=" + id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> notifs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(events);
//                    System.out.println(events.get("root"));
//                    System.out.println("aze");
                    notifs.put("notifs", notifs.remove("root"));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) notifs.get("notifs");

                    for (Map<String, Object> obj : list) {

                        Notification notif = new Notification();
                        float id = Float.parseFloat(obj.get("idnotif").toString());
                        notif.setIdnotif((int) id);
                        notif.setIduser(Integer.parseInt(obj.get("iduser").toString()));
                        notif.setContenu((String) obj.get("contenu"));
                        notif.setEtat(Integer.parseInt(obj.get("etat").toString()));
                        listnotif.add(notif);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listnotif;

    }

    public void setnotif(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mobile/setetatnotif.php?id="+id;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
//        
    }

}
