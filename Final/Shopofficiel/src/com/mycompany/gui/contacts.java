/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mac
 */
public class contacts {
    Form  hi; 
 
    public contacts(Resources res)  {
        hi = new Form("Contacts", new BoxLayout(BoxLayout.Y_AXIS));
        hi.add(new InfiniteProgress());
        int size = Display.getInstance().convertToPixels(5, true);
        FontImage fi = FontImage.createFixed("" + FontImage.MATERIAL_PERSON, FontImage.getMaterialDesignFont(), 0xff, size, size);

        Display.getInstance().scheduleBackgroundTask(() -> {
            Contact[] contacts = Display.getInstance().getAllContacts(true, true, false, true, false, false);
            Display.getInstance().callSerially(() -> {
                hi.removeAll();
                for(Contact c : contacts) {
                    MultiButton mb = new MultiButton(c.getDisplayName());
                    mb.setIcon(fi);
                    mb.setTextLine2(c.getPrimaryPhoneNumber());
                    
                    
                    mb.addActionListener(e -> {
                        Display.getInstance().dial(c.getPrimaryPhoneNumber());
                    });
                    
                    System.out.println(c.getPrimaryPhoneNumber());
                    hi.add(mb);
                    mb.putClientProperty("id", c.getId());
                    Display.getInstance().scheduleBackgroundTask(() -> {
                        Contact cc = ContactsManager.getContactById(c.getId(), false, true, false, false, false);
                        Display.getInstance().callSerially(() -> {
                            Image photo = cc.getPhoto();
                            if(photo != null) {
                                mb.setIcon(photo.fill(size, size));
                                mb.revalidate();
                            }
                        });
                    });
                }
                hi.getContentPane().animateLayout(150);
            });
        });
    }
    
    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }
}
