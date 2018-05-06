/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Lignedecommande;
import Entities.Panier;
import Entities.Produit;
import static GUI.Cart.PrixTotalStatic;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Badis
 */
public class Payement {

    Panier pan = Panier.getInstance();
    Label prix ;
    Form f;
    ImageViewer Pdp;
    TextField card ;
TextField NomProd;
    String c = "";
    Resources res;
    
    public Payement() throws IOException
    {
         f=new Form(BoxLayout.y());
         f.getAllStyles().setBgImage(Image.createImage("/login.png"));
//        Label prixTotal=new Label("25000");
         card=new TextField();
         Container c1=new Container();
          NomProd=new TextField();
          NomProd.getStyle().setBorder(Border.createLineBorder(1));
          NomProd.setText(String.valueOf(PrixTotalStatic));
          NomProd.setUIID("label2");
          Button Submit=new Button("Checkout");
          Label prix = new Label("Price");
          prix.setUIID("label2");
          Label Card = new Label("Card Number");
          Card.setUIID("label2");
          c1.getStyle().setBorder(Border.createLineBorder(2,0xFF6666));
          c1.add(prix);
         c1.add(NomProd);
         c1.add(Card);
         c1.add(card);
         c1.add(Submit);
        // f.add(prix);
         f.add(c1);
         
             Submit.addActionListener(e->{ 
              
                      payer();
               
               });
      
       
    }
    
    

    public void payer() 
    {
        if (card.getText().equals("4000000000000119")) {
            c = "tok_mastercard";
        }
        if (card.getText().equals("4242424242424241")) {
            c = "tok_visa";
        }
        if (card.getText().equals("4000000000000069")) {
            c = "tok_visa_debit";
        }

        if (card.getText().equals("4000000000000101")) {
            c = "tok_mastercard_prepaid";
        }

        if ((card.getText().equals("4000000000000119")) || (card.getText().equals("4242424242424241")) || (card.getText().equals("4000000000000069")) || (card.getText().equals("4000000000000101"))) {
            try {
                
                int a = (int) Integer.parseInt(NomProd.getText());
                System.out.println("Payé : ===>"+a);
                Stripe.apiKey = "sk_test_XA950iYFnIrsHN5H9xjtp1In";
                Map<String, Object> chargeParams = new HashMap<String, Object>();

                chargeParams.put("amount", a * 100);
                chargeParams.put("currency", "usd");
                chargeParams.put("description", "Charge for Reguigui Badis");
                chargeParams.put("source", c);
                Charge.create(chargeParams);

            } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException ex) {
                System.out.println("Erreur1");
            }

           
            Panier panier = Panier.getInstance();
            panier.p.clear();
            new Products(res).show();
           
            
                

        } else {
            System.out.println("Erreur1");
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
