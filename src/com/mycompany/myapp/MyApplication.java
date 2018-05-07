package com.mycompany.myapp;

import Entities.Event;
import Services.EventServices;
import Services.NotificationServices;
import Services.ParticipationServices;
import Services.UserServices;
import com.codename1.db.Database;
import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.io.NetworkEvent;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.codename1.uikit.cleanmodern.WalkthruForm;
import java.util.Date;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {
//    
//    public void start() {
//        if(current != null){
//            current.show();
//            return;
//        }
//        Form hi = new Form("Welcome", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
//        final Label apple = new Label(theme.getImage("apple-icon.png")); 
//        final Label android = new Label(theme.getImage("android-icon.png")); 
//        final Label windows = new Label(theme.getImage("windows-icon.png")); 
//        Button getStarted = new Button("Let's Get Started!");
//        FontImage.setMaterialIcon(getStarted, FontImage.MATERIAL_LINK);
//        getStarted.setUIID("GetStarted");
//        hi.addComponent(BorderLayout.CENTER, 
//                LayeredLayout.encloseIn(
//                        BoxLayout.encloseY(
//                                new Label(theme.getImage("duke-no-logos.png")),
//                                getStarted
//                        ),
//                        FlowLayout.encloseRightMiddle(apple)
//                    )
//        );
//        
//        getStarted.addActionListener((e) -> {
//            execute("https://www.codenameone.com/developers.html");
//        });
//        
//        new UITimer(() -> {
//            if(apple.getParent() != null) {
//                apple.getParent().replace(apple, android, CommonTransitions.createFade(500));
//            } else {
//                if(android.getParent() != null) {
//                    android.getParent().replace(android, windows, CommonTransitions.createFade(500));
//                } else {
//                    windows.getParent().replace(windows, apple, CommonTransitions.createFade(500));
//                }                
//            }
//        }).schedule(2200, true, hi);
//        hi.show();
//    }
private Form current;
    private Resources theme;
    private Database db;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Disable the global toolbar as we want a layered toolbar
        Toolbar.setGlobalToolbar(false);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
//        try{
//        db = Database.openOrCreate("DB_user");
//        db.execute("drop table user;");
//            System.out.println("done");
//            } catch (IOException ex) {
//            System.out.println(ex);
//        }

        new SignInForm(theme).show();
        
        //mail
//Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
//m.setMimeType(Message.MIME_HTML);
//
//// notice that we provide a plain text alternative as well in the send method
//boolean success = m.sendMessageViaCloudSync("Codename One", "nourchene.fakhfakh@esprit.com", "Name Of User", "Message Subject",
//                            "Check out Codename One at https://www.codenameone.com/");
//            Message m1 = new Message("You are notified");
//Display.getInstance().sendMessage(new String[] {"nounoufakhfakh@gmail.com"}, "subject", m1);


//        EventServices ev = new EventServices();
//        ParticipationServices paser = new ParticipationServices();
//        paser.Participer(1, 15);
//        paser.Departiciper(1, 9);
//        Event e = new Event("teazesqdcergsst", "tesst", "testtt","teeeest", "2018-05-27", "2018-05-27", 1);
//        ev.ajoutEvent(e);
//        System.out.println(ev.AfficherMesParticipations(1));
//        System.out.println("+");
//        System.out.println(ev.AfficherMesevents(1));
//        System.out.println("+");
//        System.out.println(ev.AfficherTousEvents(2));
//NotificationServices notser = new NotificationServices();
//        System.out.println(notser.getnotifs(1));
//        notser.setnotif(2);
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }
}