/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompagny.Service.ServiceVeto;
import com.mycompany.Entite.RDV;
import com.mycompany.Entite.Veto;
import java.io.IOException;

/**
 *
 * @author TESNIME
 */
public class ShowUnVeto {
     Form  f;

    public ShowUnVeto(int id) throws IOException  {
        f=new Form("");
        
        //*******************************Recuperer le produit selectionné*************************************************
        
        ServiceVeto sv1=new ServiceVeto();
        Veto v=sv1.showVeto(id);
        
        
   
         Container c1=new Container(BoxLayout.x());
         Container c2=new Container(BoxLayout.y());
         Container c3=new Container();
         
         c1.getStyle().setMargin(10, 0, 60, 0);
         c2.getStyle().setBorder(Border.createLineBorder(2));
         

         
        //*******************************Les elements du container*********************************************************
       ImageViewer iv=new ImageViewer();
        
        iv.setImage(Image.createImage("/"+ v.getImage()).scaled(200, 200));

       SpanLabel l=new SpanLabel("Dr. "+v.getNom()+ " "+v.getPrenom());
 
       
        Button b = new Button("Rendez vous");
        Button b1 = new Button("Budget");
       

         
        //*********************************Action sur le bouton*************************************************************
        
        c1.add(iv);
        c2.add(l);
//        c2.add(l2);
//        c2.add(l3);
//        c2.add(l4);
        c3.add(b);
        c3.add(b1);
 

         b.addActionListener(e->{ 
             try {
                       PrendreRdv rv=new PrendreRdv(v.getId());
                       rv.getF().show();//showUnPediatre(p.getId());
                   } catch (IOException ex) {
                       //Logger.getLogger(ListePediatre.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               });

         
         f.add(c1);
         f.add(c2);
         f.add(c3);
        
    
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
