/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package GUI;

import Entities.Lignedecommande;
import Entities.Panier;
import Entities.Produit;
import Entities.User;
import static GUI.Cart.PrixTotalStatic;
import Services.ProduitServices;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
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
 * The user profile form
 *
 * @author Badis
 */
public class ProfileForm extends BaseForm {
    Panier pan = Panier.getInstance();
    User user = User.getInstance();
    Label prix ;
    Form f;
    ImageViewer Pdp;
    TextField card ;
TextField NomProd;
    String c = "";
    Resources theme;
    public ProfileForm(Resources res) {
        super("Adopet", BoxLayout.y());
        try {
            getTitleArea().setUIID("Container");
            setTitle("Checkout");
            getContentPane().setScrollVisible(false);
            
            //super.addSideMenu(res);
            Image img = Image.createImage("/dog.jpg");
            if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
                img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
            }
            ScaleImageLabel sl = new ScaleImageLabel(img);
            sl.setUIID("BottomPad");
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            
            add(LayeredLayout.encloseIn(
                    sl,
                    BorderLayout.south(
                            GridLayout.encloseIn(3,
                                    FlowLayout.encloseCenter(
                                            new Label(Image.createImage("/stripe.png").scaled(100, 100), "PictureWhiteBackgrond"))
                            )
                    )
            ));
            
            NomProd = new TextField();
            NomProd.setText(String.valueOf(PrixTotalStatic)+"DT");
            NomProd.setEditable(false);
            NomProd.setUIID("TextFieldBlack");
            addStringValue("Price : ", NomProd);
            
            card = new TextField();
            
            card.setUIID("TextFieldBlack");
            addStringValue("Card Number : ", card);
            
            Button Submit=new Button("Checkout");
            Submit.addActionListener(e->{
                
                payer();
                Cart c;
                try {
                    c = new Cart(res);
                    c.getF().show();
                } catch (IOException ex) {
                    System.out.println("hhhh");
                }
            });
            Button ToCart=new Button("Cart");
            ToCart.addActionListener(e->{
                
                Cart c;
                try {
                    c = new Cart(res);
                    c.getF().show();
                } catch (IOException ex) {
                    System.out.println("hhhh");
                }
            });
           
            add(Submit);
             add(ToCart);
        } catch (IOException ex) {
            System.out.println("hhhhhhh");
        }
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
        
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
                
                int a = PrixTotalStatic;
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

            Dialog.show("Notification", "You payed : "+PrixTotalStatic+"DT","OK",null);
            Panier panier = Panier.getInstance();
            for(Lignedecommande c : panier.p){
                System.out.println(c);
                System.out.println(c.getPrixTotal());
                System.out.println(c.getQuantite());
                ProduitServices ps = new ProduitServices();
                Produit p1 = ps.searchByName(c.getIdProduit());
                int b = p1.getQuantite()-c.getQuantite();
                System.out.println(b);
                ps.updateQuantite(p1.getId(),b);
                ps.addProductPanier(p1.getId(),user.getId(),1,(int) c.getPrixTotal()*c.getQuantite(),c.getQuantite());
            }
            panier.p.clear();
           
               
           
            
                

        } else {
            System.out.println("Erreur1");
            Dialog.show("Warning", "Invalid Card","ok",null);
        }
    }
}
