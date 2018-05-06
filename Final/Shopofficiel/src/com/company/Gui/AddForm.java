/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Gui;

import com.mycompany.Entities.Publication;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
//import com.mycompany.Entities.User;
import Entities.User;
import com.mycompany.Services.PublicationService;
import java.io.IOException;

/**
 *
 * @author bayrem
 */
public class AddForm extends BaseForm {

    public String image, image2;
    TextArea tf_type, tf_description;
    Label l_type, l_description;
    Button btnajout, btnaff, btnUpload;
    User user = User.getInstance();

    public static int x;

    public AddForm(Resources res) {
        super("Add", BoxLayout.y());
        try {
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle("Add");
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
            all.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    NewsfeedForm nf = new NewsfeedForm(res);
                    nf.show();
                }
            });
            
            RadioButton featured = RadioButton.createToggle("Add Post", barGroup);
            featured.setUIID("SelectBar");
            
            Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
            
            add(LayeredLayout.encloseIn(
                    GridLayout.encloseIn(2, all, featured),
                    FlowLayout.encloseBottom(arrow)
            ));
            
            featured.setSelected(true);
            arrow.setVisible(false);
            addShowListener(e -> {
                arrow.setVisible(true);
                updateArrowPosition(featured, arrow);
            });
            bindButtonSelection(all, arrow);
            bindButtonSelection(featured, arrow);
            // special case for rotation
            addOrientationListener(e -> {
                updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            });
            
            //Formulaire d'ajout
            tf_type = new TextArea();
            tf_description = new TextArea();
            l_type = new Label("Veuillez saisir le Type :");
            l_description = new Label("Veuillez saisir la Description :");
            
            String[] characters = {"Autres", "Dressage", "Nourriture", "Soins"};
            
            Picker p = new Picker();
            p.setStrings(characters);
            p.setSelectedString(characters[0]);
            p.addActionListener(e -> ToastBar.showMessage("You picked " + p.getSelectedString(), FontImage.MATERIAL_INFO));
            
            btnUpload = new Button("Upload File");
            btnajout = new Button("Ajouter");
            add(l_type);
            add(p);
            add(l_description);
            add(tf_description);
            add(btnUpload);
            add(btnajout);
            
            btnajout.addActionListener((e) -> {
                if(tf_description.getText().equals(""))
                {
                    Dialog.show("IMPOSSIBLE", "Vous ne pouvez pas publier un post sans description.", "Fermer", "Ok");
                }
                else {
                    
                    PublicationService ser = new PublicationService();
                    Publication t = new Publication(p.getSelectedString(), tf_description.getText(), image, user.getId());
                    ser.ajoutPub(t);
                    new NewsfeedForm(res).show();
                }
            });
            
            btnUpload.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ev) {
                            if (ev != null && ev.getSource() != null) {
                                String filePath = (String) ev.getSource();
                                int fileNameIndex = filePath.lastIndexOf("/") + 1;
                                String fileName = filePath.substring(fileNameIndex);
                                System.out.println("filepath: " + filePath);
                                System.out.println("fileName: " + fileName);
                                image = fileName;
                                MultipartRequest req = new MultipartRequest();
                                req.setUrl("http://localhost/Shopofficiel/filepath.php?imagename=" + image);
                                try {
                                    req.addData("file", filePath, "image/jpeg");
                                } catch (IOException ex) {
                                    System.out.println("Error");
                                }
                                req.addResponseListener((NetworkEvent ev1) -> {
                                    String res = new String(req.getResponseData());
                                    if (res.equals("ok")) {
                                        Dialog.show("", "Sauvgarde effectuée", "ok", null);
                                    } else {
                                        Dialog.show("", "Erreur", "ok", null);
                                    }
                                });
                                NetworkManager.getInstance().addToQueue(req);
                            }
                        }
                    }, Display.GALLERY_IMAGE);
                }
            });
        } catch (IOException ex) {
            System.out.println("hokokokok");
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
