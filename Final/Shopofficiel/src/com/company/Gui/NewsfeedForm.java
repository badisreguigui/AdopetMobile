package com.company.Gui;

import com.mycompany.Entities.Publication;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
//import com.mycompany.Entities.User;
import Entities.User;
import com.mycompany.Services.PublicationService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class NewsfeedForm extends BaseForm {
User user = User.getInstance();
    public NewsfeedForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        try {
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle("Newsfeed");
            getContentPane().setScrollVisible(false);
            
            System.out.println(user);
            
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
            RadioButton featured = RadioButton.createToggle("Add Post", barGroup);
            featured.setUIID("SelectBar");
            featured.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    AddForm af = new AddForm(res);
                    af.show();
                }
            });
            RadioButton stat = RadioButton.createToggle("Stat", barGroup);
            stat.setUIID("SelectBar");
            stat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    StatsForm s = new StatsForm(res);
                    s.show();
                }
            });
            
            Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
            
            add(LayeredLayout.encloseIn(
                    GridLayout.encloseIn(3, all, featured, stat),
                    FlowLayout.encloseBottom(arrow)
            ));
            
            all.setSelected(true);
            arrow.setVisible(false);
            addShowListener(e -> {
                arrow.setVisible(true);
                updateArrowPosition(all, arrow);
            });
            bindButtonSelection(all, arrow);
            bindButtonSelection(featured, arrow);
            
            // special case for rotation
            addOrientationListener(e -> {
                updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            });
            
            // Affichage
            List<Publication> publications = new ArrayList<>();
            PublicationService ser = new PublicationService();
            publications = ser.getList2();
            
            for (Publication l : publications) {
                URLImage icon1 = URLImage.createToStorage((EncodedImage) res.getImage("news-item-1.jpg"), l.getImage() + ".png", "http://localhost/ProjetPi/web/images/" + l.getImage() + ".png");
                icon1.fetch();
                int height = Display.getInstance().convertToPixels(18f);
                int width = Display.getInstance().convertToPixels(18f);
                Button imv2 = new Button(icon1.fill(width, height));
                imv2.setUIID("Label");
                
                Container cnt = BorderLayout.west(imv2);
                
                TextArea ta1 = new TextArea(l.getType());
                ta1.setUIID("NewsType");
                ta1.setEditable(false);
                
                TextArea ta = new TextArea(l.getDescription());
                ta.setUIID("NewsTopLine");
                ta.setEditable(false);
                
                TextArea ta2 = new TextArea("By: " + l.getUsername());
                ta2.setUIID("NewsTopLine");
                ta2.setEditable(false);
                
                String dp = l.getDate() + " à " + l.getTime();
                Label datePub = new Label(dp);
                
                Button Btn2 = new Button("Share on Facbook");
                Btn2.addActionListener(e -> {
                    String accessToken = "EAACEdEose0cBAAXfXHaY5IKJlm0efViGsv9FZBsAVZAW86wvfBFo8Ko8C9PulFYbmSfrZCuN6w13avaI87dVUDZB1c07bZBEda2p3kFKwGvQckMozoUfRw42yG7LfqqVEF2XZAJVU8HYEQ6JHRyTrINZAfFlzZC0HRJmQXSZBcmhpPfsj81IfVZB98hYAU89wwcEmOoQlKU5dZBBAZDZD";
                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                            Parameter.with("message", "Je recommande cette publication \n " + "Contenu : " + l.getDescription())
                    );
                    System.out.println("Votre evenement à été publié sur facebook");
                    System.out.println("fb.com/" + response.getId());
                });
                
                String Idd = l.getIdpub();
                
                imv2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println(Idd);
                        PostForm pf = new PostForm(res, "72");
                        pf.show();
                    }
                });
                
                Button btnSignal = new Button("Signaler");
                if (user.getUsername().equals(l.getUsername())) {
                    btnSignal.setVisible(false);
                }
                btnSignal.addActionListener((e) -> {
                    String idUser = Integer.toString(user.getId());
                    ser.signalPub(l.getIdpub(), idUser);
                });
                if (user.getUsername() == l.getUsername()) {
                    btnSignal.setVisible(false);
                }
                Label spacer = new Label("------------------------------------------------------------------");
                cnt.add(BorderLayout.NORTH, spacer);
                cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta2, ta1, ta, datePub));
                cnt.add(BorderLayout.SOUTH, BoxLayout.encloseX(btnSignal, Btn2));
                add(cnt);
            }
        } catch (IOException ex) {
            System.out.println("hhhhhhhhhhhhhhhhhhhh");
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
