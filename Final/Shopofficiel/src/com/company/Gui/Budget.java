/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.company.Entity.Tarif;
import com.company.Entity.Veto;
import com.company.Service.ServiceTarif;
import com.company.Service.ServiceVeto;
import java.io.IOException;

/**
 *
 * @author TESNIME
 */
public class Budget {
    int somme = 0;
    Form  f;
        boolean test =true;
         boolean test1= true;
         boolean test2 =true;
         boolean test3 =true;
         boolean test4 =true;
         boolean test5 =true;
         boolean test6 =true;
         boolean test7 =true;
         boolean test8 =true;
         boolean test9 =true;

    public Budget(int id) throws IOException  {
        
        f=new Form("",BoxLayout.y());
        
        //*******************************Recuperer le produit selectionné*************************************************
        
        ServiceTarif st1=new ServiceTarif();
        Tarif t=st1.showTarif(id);
        
        
            Container c12=new Container(BoxLayout.x());

         Container c1=new Container(BoxLayout.x());
                  Container c11=new Container(BoxLayout.x());
         Container c111=new Container(BoxLayout.x());
         Container c1111=new Container(BoxLayout.x());
         Container c11111=new Container(BoxLayout.x());

         Container c2=new Container(BoxLayout.y());
         Container c3=new Container(BoxLayout.y());
         
         
         
         
         
         c1.getStyle().setMargin(0, 0, 0, 0);
         c2.getStyle().setBorder(Border.createLineBorder(2));
         

         
        //*******************************Les elements du container*********************************************************
       Label x=new Label("Total: ");
       Label x1=new Label(" ");

       SpanLabel l=new SpanLabel("Consultation: "+t.getConsultation());
        Button b = new Button("+");
        Button b1 = new Button("-");
       SpanLabel l1=new SpanLabel("Sterilisation: "+t.getSterilisation());
        Button b2 = new Button("+");
        Button b3 = new Button("-");
       SpanLabel l2=new SpanLabel("Vaccination Chat: "+t.getVaccinationChat());
       Button b4 = new Button("+");
        Button b5 = new Button("-");
       SpanLabel l3=new SpanLabel("Vaccination Chien: "+t.getVaccinationChien());
       Button b6 = new Button("+");
        Button b7 = new Button("-");
       SpanLabel l4=new SpanLabel("Analyses: "+t.getAnalyses());
       Button b8 = new Button("+");
        Button b9 = new Button("-");
      
       
//       CheckBox cb1 = new CheckBox("Consultation: "+t.getConsultation());
//       if (cb1.isSelected()){
//            System.out.println("ok");
//           
//        }
//       CheckBox cb2 = new CheckBox("Sterilisation: "+t.getSterilisation());
//       CheckBox cb3 = new CheckBox("Vaccination Chat: "+t.getVaccinationChat());
//       CheckBox cb4 = new CheckBox("Vaccination Chien: "+t.getVaccinationChien());
//       CheckBox cb5 = new CheckBox("Analyses: "+t.getAnalyses());
     
//       
//       
 
       
        
       

         
        //*********************************Action sur le bouton*************************************************************
        
        c12.add(x);
        c12.add(x1);
        c1.add(l);
        c1.add(b);
        c1.add(b1);
        c11.add(l1);
        c11.add(b2);
        c11.add(b3);
        c111.add(l2);
        c111.add(b4);
        c111.add(b5);
        c1111.add(l3);
        c1111.add(b6);
        c1111.add(b7);
        c11111.add(l4);
        c11111.add(b8);
        c11111.add(b9);

//        c3.add(cb1);
//        c3.add(cb2);
//        c3.add(cb3);
//        c3.add(cb4);
//        c3.add(cb5);

      
         c2.add(c12);
        c2.add(c1);
         c2.add(c11);
         c2.add(c111);
         c2.add(c1111);
         c2.add(c11111);
         //c3.add(c2);
                 
         f.add(c2);
         
         /////////////////////////***************//////////////
         
         
         
         b.addActionListener(e->{
             if(test==true){
            
                somme+=t.getConsultation();
                 x1.setText(""+somme+" TND");
                 test1=false;
                 test=false;
                 System.out.println(test1);
                 System.out.println(test);
             }
               });
         
        
         
         b1.addActionListener(e->{ 
             if(test1==false){
            
                somme-=t.getConsultation();
                 x1.setText(""+somme+" TND");
                 test=true;
                 test1=true;
             }      
               });
         
         
         b2.addActionListener(e->{ 
             if(test2==true){
            
                somme+=t.getSterilisation();
                 x1.setText(""+somme+" TND");
                 test2=false;
                 test3=false;
             }
            
                
               
               });
         b3.addActionListener(e->{ 
             if(test3==false){
            
                somme-=t.getSterilisation();
                 x1.setText(""+somme+" TND");
                 test3=true;
                 test2=true;
             }      
             
            
                
               
               });
         
         b4.addActionListener(e->{ 
             if(test4==true){
            
                somme+=t.getVaccinationChat();
                 x1.setText(""+somme+" TND");
                 test4=false;
                 test5=false;
             }
            
                
               
               });
          b5.addActionListener(e->{ 
              if(test5==false){
            
                somme-=t.getVaccinationChat();
                 x1.setText(""+somme+" TND");
                 test4=true;
                 test5=true;
             }      
            
                
               
               });
           b6.addActionListener(e->{ 
               if(test6==true){
            
                somme+=t.getVaccinationChien();
                 x1.setText(""+somme+" TND");
                 test6=false;
                 test7=false;
             }
            
                
               
               });
           b7.addActionListener(e->{ 
               if(test7==false){
            
                somme-=t.getVaccinationChien();
                 x1.setText(""+somme+" TND");
                 test6=true;
                 test7=true;
             }      
            
                
               
               });
           
                b8.addActionListener(e->{ 
                    if(test8==true){
            
                somme+=t.getAnalyses();
                 x1.setText(""+somme+" TND");
                 test9=false;
                 test8=false;
             }
            
                
               
               });
         
              b9.addActionListener(e->{ 
                  if(test9==false){
            
                somme-=t.getAnalyses();
                 x1.setText(""+somme+" TND");
                 test9=true;
                 test8=true;
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
