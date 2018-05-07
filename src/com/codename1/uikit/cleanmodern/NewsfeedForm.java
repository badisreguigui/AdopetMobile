/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.cleanmodern;

import Entities.Event;
import Entities.Session;
import Services.EventServices;
import Services.ParticipationServices;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class NewsfeedForm extends BaseForm {

    Session session = Session.getInstance();
    Container cnt, cnt1;
    EventServices ev = new EventServices();

    public NewsfeedForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        System.out.println(session.IdSession());
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
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

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
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Mine", barGroup);
        featured.setUIID("SelectBar");
        featured.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                cnt.removeAll();
//                removeComponent(cnt);
                Label spacer1 = new Label("------------------------------------------------------------------");
                cnt1.add(BorderLayout.NORTH, spacer1);
                add(cnt1);

            }
        });
        /*
        for (Event e : events) {
//            URLImage icon1 = URLImage.createToStorage((EncodedImage) res.getImage("news-item-1.jpg"), l.getImage() + ".png", "http://localhost/AdoPet/Images/" + l.getImage() + ".png");
//            icon1.fetch();
//            int height = Display.getInstance().convertToPixels(18f);
//            int width = Display.getInstance().convertToPixels(18f);
            Button imv2 = new Button(/*icon1.fill(width, height));*/
 /*    imv2.setUIID("Label");

            Container cnt = BorderLayout.west(imv2);
            
            TextArea ta1 = new TextArea(e.getNom());
            ta1.setUIID("NewsType");
            ta1.setEditable(false);
            
            TextArea ta = new TextArea(e.getDescription());
            ta.setUIID("NewsTopLine");
            ta.setEditable(false);
            
//            TextArea ta2 = new TextArea(e.getNom());
//            ta2.setUIID("NewsTopLine");
//            ta2.setEditable(false);
//            
//            String dp = l.getDate()+ " à " + l.getTime();
//            Label datePub = new Label(dp);
//
//            Button Btn2 = new Button("Share on Facbook");
//            Btn2.addActionListener(e -> {
//                String accessToken = "EAACEdEose0cBAAXfXHaY5IKJlm0efViGsv9FZBsAVZAW86wvfBFo8Ko8C9PulFYbmSfrZCuN6w13avaI87dVUDZB1c07bZBEda2p3kFKwGvQckMozoUfRw42yG7LfqqVEF2XZAJVU8HYEQ6JHRyTrINZAfFlzZC0HRJmQXSZBcmhpPfsj81IfVZB98hYAU89wwcEmOoQlKU5dZBBAZDZD";
//                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                FacebookType response = fbClient.publish("me/feed", FacebookType.class,
//                        Parameter.with("message", "Je recommande cette publication \n " + "Contenu : " + l.getDescription())
//                );
//                System.out.println("Votre evenement à été publié sur facebook");
//                System.out.println("fb.com/" + response.getId());
//            });

//            String Idd = l.getIdpub();
//            
//            imv2.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    System.out.println(Idd);
//                    PostForm pf = new PostForm(res, "72");
//                    pf.show();
//                }
//            });
            
//            Button btnSignal = new Button("Signaler");
//            btnSignal.addActionListener((e) -> {
//                String idUser = Integer.toString(user.getId());                        
//                ser.signalPub(l.getIdpub(),idUser );
//        });
            Label spacer = new Label("------------------------------------------------------------------");
            cnt.add(BorderLayout.NORTH, spacer);
            cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(/*ta2,, ta, datePub));
//            cnt.add(BorderLayout.SOUTH, BoxLayout.encloseX(/*btnSignal,Btn2));
          /*  add(cnt);
        }
            }
        });*/
        RadioButton popular = RadioButton.createToggle("Add event", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("Participations", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(true);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        List<Event> events = new ArrayList<>();

        events = ev.AfficherTousEvents(session.IdSession());
        System.out.println(events);

        for (Event e : events) {
//            URLImage icon1 = URLImage.createToStorage((EncodedImage) res.getImage("news-item-1.jpg"), l.getImage() + ".png", "http://localhost/AdoPet/Images/" + l.getImage() + ".png");
//            icon1.fetch();
//            int height = Display.getInstance().convertToPixels(18f);
//            int width = Display.getInstance().convertToPixels(18f);
            Button imv2 = new Button();
            imv2.setUIID("Label");

//            cnt = BorderLayout.west(imv2);

            TextArea ta1 = new TextArea("Type : " + e.getType());
            ta1.setUIID("NewsTopLine");
            ta1.setEditable(false);

            TextArea ta = new TextArea("Description : " + e.getDescription());
            ta.setUIID("NewsTopLine");
            ta.setEditable(false);

            TextArea ta2 = new TextArea(e.getNom());
            ta2.setUIID("NewsType");
            ta2.setEditable(false);

            TextArea ta3 = new TextArea("Lieu : " + e.getLieu());
            ta3.setUIID("NewsTopLine");
            ta3.setEditable(false);

            Button Btn2 = new Button("Participer");
            Button Btn1 = new Button("aaaaaaaaaaaaaaar");
            Btn1.setVisible(false);
            Btn2.addActionListener(a -> {
                ParticipationServices paser = new ParticipationServices();
                paser.Participer(session.IdSession(), e.getId());
            });
            Label spacer = new Label("------------------------------------------------------------------");
            cnt = BorderLayout.north(spacer);
//            cnt.add(BorderLayout.NORTH, spacer);
            cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta2, ta1, ta, ta3));
            cnt.add(BorderLayout.SOUTH, BoxLayout.encloseX(Btn1,Btn2));
            add(cnt);
        }

//        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
//        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
//        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
//        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
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
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
