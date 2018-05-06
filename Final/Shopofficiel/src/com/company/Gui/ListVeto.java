/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.company.Entity.Veto;
import com.company.Service.ServiceVeto;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
/**
 *
 * @author TESNIME
 */
public class ListVeto extends BaseForm {
     
 
    public ListVeto(Resources res)  {
        
        super("Newsfeed", BoxLayout.y());
        try {
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle("Newsfeed");
            getContentPane().setScrollVisible(false);
            
            
            
            super.addSideMenu(res);
            tb.addSearchCommand(e -> {
            });
            
            Tabs swipe = new Tabs();
            
            Label spacer1 = new Label();
            Label spacer2 = new Label();
            addTab(swipe,Image.createImage("/dog.jpg"), spacer1, "   ", "   ", "");
            
            swipe.setUIID("Container");
            swipe.getContentPane().setUIID("Container");
            swipe.hideTabs();
            
            ButtonGroup bg = new ButtonGroup();
            int size = Display.getInstance().convertToPixels(1);
            
            Image unselectedWalkthru = Image.createImage(size, size, 0);
            Graphics g = unselectedWalkthru.getGraphics();
            g.setColor(0xffffff);
            g.setAlpha(100);
            g.setAntiAliased(true);
            g.fillArc(0, 0, size, size, 0, 360);
            
            Image selectedWalkthru = Image.createImage(size, size, 0);
            g = selectedWalkthru.getGraphics();
            g.setColor(0xffffff);
            g.setAntiAliased(true);
            g.fillArc(0, 0, size, size, 0, 360);
            
            RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
            FlowLayout flow = new FlowLayout(CENTER);
            flow.setValign(BOTTOM);
            Container radioContainer = new Container(flow);
            for (int iter = 0; iter < rbs.length; iter++) {
                rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
                rbs[iter].setPressedIcon(selectedWalkthru);
                rbs[iter].setUIID("Label");
                radioContainer.add(rbs[iter]);
            }
            
            rbs[0].setSelected(true);
            swipe.addSelectionListener((i, ii) -> {
                if (!rbs[ii].isSelected()) {
                    rbs[ii].setSelected(true);
                }
            });
            
            Component.setSameSize(radioContainer, spacer1, spacer2);
            add(LayeredLayout.encloseIn(swipe, radioContainer));
            
            ButtonGroup barGroup = new ButtonGroup();
            
            RadioButton all = RadioButton.createToggle("Show All", barGroup);
            all.setUIID("SelectBar");
            
            
            Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
            
            add(LayeredLayout.encloseIn(
                    GridLayout.encloseIn(1, all),
                    FlowLayout.encloseBottom(arrow)
            ));
            
            all.setSelected(true);
            arrow.setVisible(false);
            addShowListener(e -> {
                arrow.setVisible(true);
                updateArrowPosition(all, arrow);
            });
            bindButtonSelection(all, arrow);
            
            
            // special case for rotation
            addOrientationListener(e -> {
                updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            });
            
            ServiceVeto sv=new ServiceVeto();
            ArrayList<Veto> list=sv.showList();
            
            for (Veto l : list) {
                URLImage icon1 = URLImage.createToStorage((EncodedImage) res.getImage("news-item-1.jpg"), l.getImage(), "http://localhost/ProjetPi/web/images/Edin_Muhic-480x4800.jpg");
                icon1.fetch();
                System.out.println(l.getImage());
                int height = Display.getInstance().convertToPixels(18f);
                int width = Display.getInstance().convertToPixels(18f);
                Button imv2 = new Button(icon1.fill(width, height));
                imv2.setUIID("Label");
                
                Container cnt = BorderLayout.west(imv2);
                
                TextArea ta1 = new TextArea("DR "+l.getNom());
                TextArea ta3 = new TextArea("Adresse: "+l.getVille());
                
                ta1.setUIID("NewsType");
                ta1.setEditable(false);
                
                ta3.setUIID("NewsType");
                ta3.setEditable(false);
                
                
                
                Button Btn2 = new Button("Voir Veto");
                Btn2.addActionListener(e->{
                    try {
                        ShowUnVeto sc=new ShowUnVeto(l.getId());
                        
                        sc.getF().show();
                    } catch (IOException ex) {
                        //Logger.getLogger(ListePediatre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                
//
                
                Label spacer = new Label("------------------------------------------------------------------");
                cnt.add(BorderLayout.NORTH, spacer);
                cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta1,ta3));
                cnt.add(BorderLayout.SOUTH, BoxLayout.encloseX(Btn2));
                
                add(cnt);
            }
            
            
            
            
            
            
            
//
//        f=new Form("Liste Des Vetos",BoxLayout.y());
//
//
//         
//        //*******************Recuperer la liste de produit de la base***************************************************
//        
//           ServiceVeto sv=new ServiceVeto();
//           ArrayList<Veto> list=sv.showList();
//
//
//       //********************************Parcourir la liste**************************************************************** 
//        for(Veto v : list)
//        {  
//               Container c1 =new Container(BoxLayout.x());
//               Container c3 =new Container(BoxLayout.y());
//               
//              c1.getStyle().setBorder(Border.createLineBorder(2));
//              c1.getStyle().setMargin(1, 1, 1, 1);
//              c1.getStyle().setPadding(20, 20, 0, 0);
//
//        //****************************les elements du containers********************************************************
//               
//               ImageViewer iv=new ImageViewer();
//               
//           try {
//               iv.setImage(Image.createImage("/"+ v.getImage()).scaled(100, 100));
//           } catch (IOException ex) {
//                    System.out.println("err");
//           }
//              
//               //Button b=new Button(image2.scaled(30,30));
//               Button b=new Button("View Veto");
//
//                       
//               Label l1=new Label("Adress: "+ v.getVille());
//
//
//             //  l1.getStyle().set(0xC40C0C);
//             
//               c1.add(iv);
//               c1.add(c3);
//               c3.add(new SpanLabel("Dr "+ v.getNom()));
//               c3.add(l1);
//               
//               c3.add(b);
//
//               
//          //  *******************************Action sur le bouton add*****************************************************
//
//               b.addActionListener(e->{ 
//                   try {
//                       ShowUnVeto sc=new ShowUnVeto(v.getId());
//                       sc.getF().show();//showUnPediatre(p.getId());
//                   } catch (IOException ex) {
//                       //Logger.getLogger(ListePediatre.class.getName()).log(Level.SEVERE, null, ex);
//                   }
//               
//               });
//
//
//           
//            f.add(c1);
//            
//        }
        } catch (IOException ex) {
            
        }
         
        
    }

    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Label comments = new Label(commentsStr);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1 = LayeredLayout.encloseIn(image, overlay, BorderLayout.south(BoxLayout.encloseY(
                new SpanLabel(text, "LargeWhiteText"),
                FlowLayout.encloseIn(likes, comments),
                spacer
        )
        )
        );

        swipe.addTab("", page1);
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}
