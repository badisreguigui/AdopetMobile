package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entitie.Pet;
import com.mycompany.Service.ServiceMatching;
import com.mycompany.Service.ServicePet;
import java.io.IOException;
import java.util.ArrayList;


    
public class tinder extends Form {

    public tinder(Resources res) throws IOException {
        super(new BorderLayout());
        getTitleArea().setUIID("Container");
        setUIID("Welcome");
        Tabs t = new Tabs();
        t.hideTabs();
        t.setUIID("Container");
        t.getContentPane().setUIID("Container");
        add(BorderLayout.CENTER, t);
        
        ServicePet sv=new ServicePet();
        ArrayList<Pet> list=sv.showListLimit();
        
        
        String a = "";
        
        for(Pet v : list){
//            ScaleImageLabel page1 = new ScaleImageLabel(res.getImage("welcome-slide-1.png"));
            ScaleImageLabel page2 = new ScaleImageLabel(Image.createImage("/"+v.getPet_image()));
            ScaleImageLabel page1 = new ScaleImageLabel(Image.createImage("/like.png"));
            ScaleImageLabel page3 = new ScaleImageLabel(Image.createImage("/dislike.png"));
            
//            page1.setUIID("Container");
//            page1.getAllStyles().setBgTransparency(0);
//            t.addTab("", page1);
            page2.setUIID("Container");
            page1.setUIID("Container");
            page3.setUIID("Container");
            
            page2.getAllStyles().setBgTransparency(0);
            page1.getAllStyles().setBgTransparency(0);
            page3.getAllStyles().setBgTransparency(0);
            
            t.addTab("", page3);
            t.addTab("", page2);
            t.addTab("", page1);
            System.out.println(v.getName_pet());
            System.out.println(v.getPet_image());
           
            a = v.getName_pet() +", "+ v.getBreed()+"\n"+ v.getDescription();
     
        String[] messages = {
            "Like",
            a,
            "Dislike"
        };
        SpanLabel message = new SpanLabel(messages[1], "WelcomeMessage");
        

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xcccccc);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xff2d55);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        
        t.setSelectedIndex(1);
        
        RadioButton[] rbs = new RadioButton[t.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
           
        }
                
        rbs[1].setSelected(true);
        t.addSelectionListener((i, ii) -> {
//            if(!rbs[ii].isSelected()) {
//                rbs[ii].setSelected(true);
//                message.setText(messages[ii]);
//                System.out.println(ii);
//                System.out.println("a");
//            }

            //like
            if (ii == 2) {
                 System.out.println("2222");
                 ServiceMatching sc1=new ServiceMatching();
                 sc1.AjouterMatching(v.getId_pet());
                try {
                    new tinder(res).show();
                } catch (IOException ex) {
                    //Logger.getLogger(tinder.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
           
            //dislike
             if (ii == 0) {
                 System.out.println("0000");
                 ServiceMatching sc1=new ServiceMatching();
                 sc1.AjouterUnMatching(v.getId_pet());
                 
                try {
                    new tinder(res).show();
                } catch (IOException ex) {
                    //Logger.getLogger(tinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
          
        Button skip = new Button("Like");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new SignInForm(res).show());
        
        Button skip1 = new Button("Dislike");
        skip1.setUIID("SkipButton");
        skip1.addActionListener(e -> new SignInForm(res).show());
        
        
        Container welcomeNoteArea = BoxLayout.encloseY(message,
                LayeredLayout.encloseIn(
                        radioContainer,
                        BorderLayout.east(skip),
                        BorderLayout.west(skip1)
                )
        );
        welcomeNoteArea.setUIID("WelcomeNoteArea");
        add(BorderLayout.SOUTH, welcomeNoteArea);
    }
        }
    
}
