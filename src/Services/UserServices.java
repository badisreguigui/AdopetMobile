/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author achref kh
 */
public class UserServices {
     String result; 
    public String login(String username,String password) {
        
         
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/selectuser.php?username="+username+"&password="+password);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                              
                //renvoi une map avec clé = root et valeur le reste
                result = new String(con.getResponseData());             
                    
                    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return result;
    }
}
