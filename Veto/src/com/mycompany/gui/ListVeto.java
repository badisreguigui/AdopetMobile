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
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompagny.Service.ServiceVeto;
import com.mycompany.Entite.Veto;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author TESNIME
 */
public class ListVeto {
    Form  f; 
 
    public ListVeto()  {
        f=new Form("Liste Des Vetos",BoxLayout.y());
     
           
         
        //*******************Recuperer la liste de produit de la base***************************************************
        
           ServiceVeto sv=new ServiceVeto();
           ArrayList<Veto> list=sv.showList();
           
          
       //********************************Parcourir la liste**************************************************************** 
        for(Veto v : list)
        {  
               Container c1 =new Container(BoxLayout.x());
               Container c3 =new Container(BoxLayout.y());
               
              c1.getStyle().setBorder(Border.createLineBorder(2));
              c1.getStyle().setMargin(1, 1, 1, 1);
              c1.getStyle().setPadding(20, 20, 0, 0);

        //****************************les elements du containers********************************************************
               
               ImageViewer iv=new ImageViewer();
               
           try {
               iv.setImage(Image.createImage("/"+ v.getImage()).scaled(100, 100));
           } catch (IOException ex) {
                    System.out.println("err");
           }
              
               //Button b=new Button(image2.scaled(30,30));
               Button b=new Button("View Veto");
               
                       
               Label l1=new Label("Adress: "+ v.getVille());
              

             //  l1.getStyle().set(0xC40C0C);
             
               c1.add(iv);
               c1.add(c3);
               c3.add(new SpanLabel("Dr "+ v.getNom()));
               c3.add(l1);
               
               c3.add(b);
               
               
          //  *******************************Action sur le bouton add*****************************************************
               
               b.addActionListener(e->{ 
                   try {
                       ShowUnVeto sc=new ShowUnVeto(v.getId());
                       sc.getF().show();//showUnPediatre(p.getId());
                   } catch (IOException ex) {
                       //Logger.getLogger(ListePediatre.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               });
               
       
           
            f.add(c1);
            
        }
         
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
