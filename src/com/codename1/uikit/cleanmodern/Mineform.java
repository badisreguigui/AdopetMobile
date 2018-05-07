/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entities.Event;
import Entities.Session;
import Services.EventServices;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author achref kh
 */
public class Mineform {
    Form f;
    SpanLabel lb;
  Session session = Session.getInstance();
    public Mineform() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        EventServices se = new EventServices();
        ArrayList<Event> lis=se.AfficherMesevents(session.IdSession());
        for (Event e : lis){
            Container cnt = null ;
            TextArea ta1 = new TextArea("Type : "+e.getType());
            ta1.setUIID("NewsTopLine");
            ta1.setEditable(false);
            Label spacer = new Label("------------------------------------------------------------------");
            cnt.add(BorderLayout.NORTH, spacer);
            cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta1));
//            cnt.add(BorderLayout.SOUTH, BoxLayout.encloseX(Btn2));
            f.add(cnt);
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
