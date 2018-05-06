/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Lignedecommande;
import Entities.Produit;
import Entities.User;
import Services.UserServices;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author Badis
 */
public class Login {
    Form f;
    User userinfo;
    
    public Login()
    {
        f=new Form("Welcome",BoxLayout.y());
        TextField username=new TextField();
        TextField Password = new TextField();
        Button Login=new Button("Login");
        UserServices us=new UserServices();
      //  Login.setUIID("custombtn");
        f.add(username);
        f.add(Password);
        f.add(Login);
        
         Login.addActionListener(e -> {
             User u1=us.CheckLogin(username.getText(),Password.getText());
  
            
                          System.out.println("USER ===> "+u1.getUsername());
                            User.setInstance(u1);
             if(u1.getUsername()!=null)
             {
                 /*AfficherProduits h;
                 try {
                     h = new AfficherProduits();
                     h.getF().show();
                 } catch (IOException ex) {
                     System.out.println("ERREUR DANS REDIRECTION APRES LOGIN ");                 }*/
                 
             }

            });
        
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
