/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entities.Publication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class PublicationService {

    String result;

    public void ajoutPub(Publication p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPi/web/app_dev.php/api/pubs/new?desc=" + p.getDescription() + "&image=" + p.getImage() + "&type=" + p.getType() + "&iduser=" + p.getIduser();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Publication> getList2() {
        ArrayList<Publication> listPublications = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPi/web/app_dev.php/api/pubs/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> publications = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" + publications.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) publications.get("root");

                    for (Map<String, Object> obj : list) {
                        Publication publication = new Publication();

                        publication.setIdpub(obj.get("id").toString());
                        publication.setDescription(obj.get("description").toString());
                        publication.setTime(obj.get("time").toString());
                        publication.setDate(obj.get("date").toString());
                        publication.setImage(obj.get("image").toString());
                        publication.setType(obj.get("type").toString());
                        publication.setUsername(obj.get("username").toString());

                        listPublications.add(publication);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPublications;
    }

    public Publication getPub(String id) {
        Publication publication = new Publication();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPi/web/app_dev.php/api/pubs/find/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> publications = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" + publications.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) publications.get("root");

                    for (Map<String, Object> obj : list) {
                        publication.setIdpub(obj.get("id").toString());
                        publication.setDescription(obj.get("description").toString());
                        publication.setTime(obj.get("time").toString());
                        publication.setDate(obj.get("date").toString());
                        publication.setImage(obj.get("image").toString());
                        publication.setType(obj.get("type").toString());
                        publication.setUsername(obj.get("username").toString());
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return publication;
    }

    public void signalPub(String idPub, String idUser) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPi/web/app_dev.php/api/pubs/signaler?idPub=" + idPub + "&idUser=" + idUser;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public String count(String type) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Shopofficiel/count.php?type=" + type);

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
