/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.company.Entity.RDV;
import com.company.Entity.Veto;
import com.company.Service.ServiceRdv;
import com.company.Service.ServiceVeto;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author TESNIME
 */
public class PrendreRdv {
     Form f;
     
    public PrendreRdv(int id) throws IOException {
        f = new Form("");
       
        ServiceVeto sv1 = new ServiceVeto();
        Veto v = sv1.showVeto(id);
        RDV r = new RDV();
     
        Toolbar tool = new Toolbar();
        tool=f.getToolbar();
        tool.addCommandToLeftBar("Back", null,e->{ 
                     ShowUnVeto sv;
            try {
                sv = new ShowUnVeto(v.getId());
                sv.getF().show();
            } catch (IOException ex) {
                
            }
                     
                           } );
        Container c1 = new Container(BoxLayout.y());
        Container c2 = new Container(BoxLayout.x());

        //*******************************Les elements du container*********************************************************
         
     
        SpanLabel l3 = new SpanLabel("Date: ");
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Button b = new Button("prendre un rendez-vous");

       
       
        c2.add(l3);
        c2.add(date);

        //*********************************Action sur le bouton*************************************************************
       
        
            b.addActionListener(e -> {

            int a=0;
        if(a>1)
        {
            Dialog.show("champs vide", "entrer numero", "ok", "cancel");
        }
        else
        {
             Calendar cal = Calendar.getInstance(); // creates calendar
             cal.setTime(date.getDate()); // sets calendar time/date
        
        
         
         if (Dialog.show("Confirmation", "date : " + new SimpleDateFormat("yyyy-MM-dd").format(date.getDate()) + " time : " + new SimpleDateFormat("hh:mm:s").format(date.getDate()), "ok", "cancel")) {
              
                r.setId_veto(v.getId());
     
                String Dateee = new SimpleDateFormat("yyyy-MM-dd").format(date.getDate());
                //System.out.println("STRING TO DATE ==>" + Dateee);
                
                String Timeee = new SimpleDateFormat("hh:mm:ss").format(cal.getTime());
                //System.out.println("STRING TO Time ==>" + Timeee);
      
                r.setDate_rdv(Dateee);
                r.setHeure(Timeee);
                ServiceRdv srv = new ServiceRdv();
                
                if(srv.VerifRendezVous(v.getId(),date)>0)
                {
                    Dialog.show("SORRY", "cette date est reservee", "ok","cancel");
                }
                Date dateJour = new Date();
                System.out.println("datejour "+dateJour);
                if(date.getDate().toString().compareTo(dateJour.toString())<0)
                {  Dialog.show("SORRY"," date du jour est inferieur d aujourd'hui","ok","annuler");
                }
                else if(srv.VerifRendezVous(v.getId(),date)==0)
                {
                    srv.AjoutRendezVous(r);
                   // srv.envoyerSMSRendezVous(r);
//                    

                   
                   
                    
                }     
         } 
        }
        
       
       
         
        

        });
        
        
        

        f.add(c1);
        f.add(c2);
        f.add(b);
        

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
