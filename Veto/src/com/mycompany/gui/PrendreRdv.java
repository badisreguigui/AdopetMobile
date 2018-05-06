/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
import com.mycompagny.Service.ServiceRdv;
import com.mycompagny.Service.ServiceVeto;
import com.mycompany.Entite.RDV;
import com.mycompany.Entite.Veto;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author TESNIME
 */
public class PrendreRdv {
     Form f;

    public PrendreRdv(int id) throws IOException {
        f = new Form("");
        
        //*******************************Recuperer le produit selectionné*************************************************
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
         
          TextField t1 = new TextField();
        SpanLabel l3 = new SpanLabel("Date : ");
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Button b = new Button("prendre rendez-vous");

       
        c1.add(t1);
        c2.add(l3);
        c2.add(date);

        //*********************************Action sur le bouton*************************************************************
       
        
            b.addActionListener(e -> {

            
        if((t1.getText().length()!=8)||(t1.getText().length()==0))
        {
            Dialog.show("champs vide", "entrer numero", "ok", "cancel");
        }
        else
        {
             Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date.getDate()); // sets calendar time/date
        cal.add(Calendar.HOUR, 1);
        
         
         if (Dialog.show("Confirmation", "date : " + new SimpleDateFormat("yyyy-MM-dd").format(date.getDate()) + " time : " + new SimpleDateFormat("hh:mm:s").format(date.getDate()), "ok", "cancel")) {
              
                r.setId_veto(v.getId());
     
                String Dateee = new SimpleDateFormat("yyyy-MM-dd").format(date.getDate());
                //System.out.println("STRING TO DATE ==>" + Dateee);
                
                String Timeee = new SimpleDateFormat("hh:mm:ss").format(cal.getTime());
                //System.out.println("STRING TO Time ==>" + Timeee);
      
                r.setDate_rdv(Dateee);
                r.setHeure(Timeee);
                //System.out.println("RR=>"+r);
                ServiceRdv srv = new ServiceRdv();
                
//                if(srv.VerifRendezVous(.getId(),date)>0)
//                {
//                    Dialog.show("verification", "cette date est reservee", "ok","cancel");
//                }
//                if(srv.VerifRendezVous(p.getId(),date)==0)
//                {
                    srv.AjoutRendezVous(r);
//                    srv.EnvoyerSmsRendezVous(p,t1.getText(),r.getDate_rdv(),r.getHeure());
//                    srv.EnvoyerMailRendezVous(p,t1.getText(),r.getDate_rdv(),r.getHeure());

                   
                   
                    
                //}     
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
