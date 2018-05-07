/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;

/**
 *
 * @author achref kh
 */
public class ParticipationServices {
    public void Participer(int iduser,int idevent){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mobile/participerevent.php?iduser=" + iduser + "&idevent=" + idevent;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void Departiciper(int iduser,int idevent){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mobile/departiciper.php?iduser=" + iduser + "&idevent=" + idevent;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
