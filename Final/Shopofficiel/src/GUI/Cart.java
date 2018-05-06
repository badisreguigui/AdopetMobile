/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Lignedecommande;
import Entities.Panier;
import Entities.Produit;
import Entities.User;
//import Services.OrderServices;
import Services.ProduitServices;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.io.IOException;

import java.util.Calendar;

/**
 *
 * @author Badi
 */
public class Cart {

    public static int PrixTotalStatic = 0;
    Form f;
    private int cmpt;
    Label PrixTotal = new Label();
    //Resources theme;

    public Cart(Resources theme) throws IOException {
        f = new Form("Your Cart", BoxLayout.y());
        f.getStyle().setBgColor(0xFFFFFF);
Container c10 = new Container(BoxLayout.x());
        Button b = new Button("Store");
        Button checkout = new Button("Checkout");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new Products(theme).show();
            }
        });
      

        //**********************************instanciation du panier********************************************************
        Panier panier = Panier.getInstance();

        int prix = 0;
        //********************************Parcourir le panier**************************************************************
        ComponentGroup cg = new ComponentGroup();
        ComponentGroup c6 = new ComponentGroup();
        for (Lignedecommande c : panier.p) {
            Container c4 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c1 = new Container(BoxLayout.y());
            Container c5 = new Container(BoxLayout.y());
            Container c8 = new Container(BoxLayout.x());
            Container c9 = new Container(BoxLayout.x());
            Container c11 = new Container(BoxLayout.x());
            Label Quantite = new Label("Quantité : ");
            Quantite.setUIID("label2");
            Label prixLabel = new Label("Prix : ");
            prixLabel.setUIID("label2");
            prix += c.getQuantite() * c.getProduct().getPrix();
            c8.add(Quantite);
            c9.add(prixLabel);
            
            PrixTotal.setText(Integer.toString(prix));
            PrixTotal.setUIID("label2");
            PrixTotalStatic = Integer.parseInt(PrixTotal.getText());
            //***************************les elements du containers************************************************************
            ImageViewer iv = new ImageViewer();
            iv.setImage(Image.createImage("/" + c.getProduct().getImage()).scaled(150, 120));

            Label PP = new Label(Float.toString(c.getProduct().getPrix() * c.getQuantite())+"DT");
            PP.setUIID("label2");
            Label PQ = new Label(Integer.toString(c.getQuantite()));
            PQ.setUIID("label2");
            //c8.add(PQ);
            Button bt = new Button("X");

            bt.addActionListener(e -> {
                panier.removeLine(c);
                c4.remove();
                PrixTotal.remove();
                c8.remove();
                c9.remove();
                c11.remove();
                f.revalidate();
                c10.add(PrixTotal);
                System.out.println("LE NOUVEAU PRIX APRES + ===>" + PrixTotalStatic);
                PrixTotal.setText(Integer.toString(PrixTotalStatic -= c.getProduct().getPrix() * c.getQuantite())+"DT");

            });
            //********************les boutons de modif quantite******************************************
            Button plus = new Button("+");
            //plus.setWidth(10);
            Button minus = new Button("-");
            final int price2 = prix;
            plus.addActionListener(e -> {
                cmpt = c.getQuantite();
                cmpt++;
                ProduitServices ps = new ProduitServices();
                Produit pfound = ps.searchByName(c.getProduct().getId());

                if (cmpt <= pfound.getQuantite()) {
                    c.setQuantite(cmpt);
                    System.out.println("quantite : " + c.getQuantite());
                    PQ.remove();
                    PP.remove();
                    PrixTotal.remove();
                    f.revalidate();
                    PQ.setText(String.valueOf(cmpt));

                    //c2.add(PQ);
                    
                    c8.add(PQ);
                    PP.setText(Float.toString(c.getProduct().getPrix() * cmpt)+"DT");
                    c10.add(PrixTotal);

                    System.out.println("Nouveau prix + : " + PrixTotalStatic);
                    PrixTotal.setText(Integer.toString(PrixTotalStatic += c.getProduct().getPrix())+"DT");
                    //c2.add(PP);
                    c9.add(PP);

                }
            });

            minus.addActionListener(e -> {

                ProduitServices ps = new ProduitServices();
                Produit pfound = ps.searchByName(c.getProduct().getId());
                cmpt = c.getQuantite();
                cmpt--;
                if (cmpt <= 0) {
                    panier.removeLine(c);
                    c4.remove();
                    PQ.remove();
                    PP.remove();
                    plus.remove();
                    minus.remove();
                    c8.remove();
                    c9.remove();
                    c11.remove();
                    PrixTotal.setText(Integer.toString(PrixTotalStatic -= c.getProduct().getPrix())+"DT");
                    f.revalidate();
                } else if (cmpt <= pfound.getQuantite()) {
                    c.setQuantite(cmpt);
                    PQ.remove();
                    PP.remove();
                    PrixTotal.remove();
                    f.revalidate();
                    PQ.setText(String.valueOf(cmpt));
                    PP.setText(Float.toString(c.getProduct().getPrix() * cmpt)+"DT");
                    c10.add(PrixTotal);

                    System.out.println("Nouveau prix - ===>" + PrixTotalStatic);
                    PrixTotal.setText(Integer.toString(PrixTotalStatic -= c.getProduct().getPrix())+"DT");
                    
                    c8.add(PQ);
                    //c2.add(PP);
                    c9.add(PP);
                }
                f.revalidate();
            });

            bt.getStyle().setPadding(0, 0, 0, 0);
            plus.getStyle().setPadding(0, 0, 0, 0);
            minus.getStyle().setPadding(0, 0, 0, 0);

            //*****************************mettre le bouton X au milieu****************************************************
            Label lb1 = new Label(".");
            Label lb2 = new Label(".");
            lb1.setVisible(false);
            lb2.setVisible(false);
            Label lb3 = new Label(".");
            Label lb4 = new Label(".");
            lb3.setVisible(false);
            lb4.setVisible(false);
            c1.add(lb1);
            c1.add(bt);
            c1.add(lb2);
            c8.add(PQ);
            c11.add(c8);
            c11.add(plus);
            c11.add(minus);
          
            c4.add(c1);
            c4.add(iv);
            c2.add(c5);
            
            c9.add(PP);

            //c2.add(b2);
            //c3.add(new SpanLabel(c.getProduct().getName()));
            c3.add(c2);

            c4.add(c3);
            //c4.add(PP);

            cg.add(c4);
            //cg.add(c8);
            cg.add(c9);
            cg.add(c11);

        }
        
       Label prixTot=new Label("Prix total : ");
       prixTot.setUIID("label2");
        c10.add(prixTot);   
        c10.add(PrixTotal);
        c6.add(c10);
        f.add(cg);
        f.add(c6);
        
        f.add(checkout);
        f.add(b);

        checkout.addActionListener(e -> {
            System.out.println("Mon panier ==>" + panier.p);
            if (!panier.p.isEmpty()) {
                if (Dialog.show("Confirm", "Are you sure ?", "Ok", "Cancel")) {
                    User uss = User.getInstance();
                    /*Calendar now = Calendar.getInstance();
                    now.set(Calendar.HOUR_OF_DAY, 0);
                    now.set(Calendar.MINUTE, 0);
                    now.set(Calendar.SECOND, 0);
                    now.set(Calendar.MILLISECOND, 0);
                    now.getTime();*/
                    new ProfileForm(theme).show();
                }
            } else if (panier.p.isEmpty()) {
                Dialog dlg = new Dialog("Oups !");
                Style dlgStyle = dlg.getDialogStyle();
                dlgStyle.setBorder(Border.createEmpty());
                dlgStyle.setBgTransparency(255);
                dlgStyle.setBgColor(0xffffff);
                
                Label title = dlg.getTitleComponent();
                try {
                    title.setIcon(Image.createImage("/adopet.png").scaled(25, 25));
                } catch (IOException ex) {
                    System.out.println("check image error.png cart.java");
                }
                title.getUnselectedStyle().setFgColor(0xff);
                title.getUnselectedStyle().setAlignment(Component.LEFT);
                
                dlg.setLayout(BoxLayout.y());
                Label blueLabel = new Label();
                blueLabel.setShowEvenIfBlank(true);
                blueLabel.getUnselectedStyle().setBgColor(0xff);
                blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(blueLabel);
                TextArea ta = new TextArea("0 Products in cart");
                ta.setEditable(false);
                ta.setUIID("DialogBody");
                ta.getAllStyles().setFgColor(0);
                dlg.add(ta);
                
                Label grayLabel = new Label();
                grayLabel.setShowEvenIfBlank(true);
                grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(grayLabel);
                
                Button ok = new Button(new Command("OK"));
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.showDialog();
            }

        });

    }

    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
