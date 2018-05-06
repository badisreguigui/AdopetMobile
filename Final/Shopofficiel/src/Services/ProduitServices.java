/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
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
 * @author Badis
 */
public class ProduitServices {
String str;
boolean test;


    public ArrayList<Produit> getList2() {
        ArrayList<Produit> listProducts = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProductServices/select.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("produit", products.remove("root"));
                    // System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("produit");

                    for (Map<String, Object> obj : list) {

                        Produit prod = new Produit();
                        float id = Float.parseFloat(obj.get("idproduit").toString());
                        prod.setId((int) id);
                        prod.setNom(obj.get("nomproduit").toString());
                        prod.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        prod.setImage((String) obj.get("imageproduit"));
                        prod.setRace((String) obj.get("nomraceproduit"));
                        prod.setQuantite(Integer.parseInt(obj.get("quantite").toString()));
                        listProducts.add(prod);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProducts;
    }

    public Produit searchByName(int id) {
        ArrayList<Produit> listProducts = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProductServices/getProduct.php?idproduit="+id);
           // System.out.println("L'ID =====> "+id);

        Produit prod = new Produit();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("produit", products.remove("root"));
                 //   System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("produit");

                    for (Map<String, Object> obj : list)
                    {
      
                        int id = Integer.parseInt(obj.get("idproduit").toString());
                        prod.setId(id);           
                        prod.setNom(obj.get("nomproduit").toString());
                        prod.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        prod.setImage((String) obj.get("imageproduit"));
                        prod.setQuantite(Integer.parseInt(obj.get("quantite").toString()));
                        
                        
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return prod;
        
    }
    
    public void addRate(double rate,int iduser,int idpr) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProductServices/addRate.php?rate="+rate+"&idpr="+idpr+"&iduser=" +iduser;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public int DisplayRate(int idpr) {
        ArrayList<Integer> rates = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProductServices/rating.php?idpr="+idpr;
        con.setUrl(Url);

         Produit prod = new Produit();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("rate", products.remove("root"));
                 //   System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("rate");

                    for (Map<String, Object> obj : list)
                    {
      
                     
                        int i=Integer.parseInt(obj.get("ratepr").toString());
                        System.out.println("i");
                        
                        rates.add(i);
                        
                        
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        int rate=0;
        for(int i :rates)
        {
            rate=i;
        }
        return rate;
    }
    
    public int checkR(int idpr,int iduser) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/productServices/checkRate.php?idpr="+idpr+"&iduser="+iduser;
        con.setUrl(Url);
        
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("rate", products.remove("root"));
                 //   System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("rate");

                    for (Map<String, Object> obj : list)
                    {
      
                     
                        int i=Integer.parseInt(obj.get("ratep").toString());
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
            if (s1==0)
                s=1;
            else 
                s=0;
        }
        System.out.println(s);
        return s;  
        
    }
    
    
    public Produit searchPanier(int id,int iduser,int etat) {
        ArrayList<Produit> listProducts = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProductServices/getProductPanier?id_produit="+id+"&iduser="+iduser+"etat="+etat);
           // System.out.println("L'ID =====> "+id);

        Produit prod = new Produit();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> products = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    products.put("panier", products.remove("root"));
                 //   System.out.println("PRODUCTS " + products);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("panier");

                    for (Map<String, Object> obj : list)
                    {
      
                        int id = Integer.parseInt(obj.get("id_produit").toString());
                        prod.setId(id);           
                        prod.setNom(obj.get("iduser").toString());
                        prod.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        prod.setImage((String) obj.get("etat"));
                        prod.setQuantite(Integer.parseInt(obj.get("quantite").toString()));
                        
                        
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return prod;
        
    }
    
    public void addProductPanier(int id,int iduser,int etat,int prix,int quantite) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProductServices/addProductPanier?id_produit="+id+"&iduser="+iduser+"&prix="+prix+"&quantite="+quantite+"&etat="+etat;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void updateQuantite(int id,int quantite) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProductServices/UpdateQuantite.php?quantite="+quantite+"&idproduit="+id;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    

}
