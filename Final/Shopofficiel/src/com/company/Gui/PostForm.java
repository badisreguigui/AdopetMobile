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
package com.company.Gui;

import com.mycompany.Entities.Publication;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Services.PublicationService;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class PostForm extends BaseForm {

    public float x;

    public PostForm(Resources res, String Idd) {
        super("Post Form", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Post Form");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("SP.png"), spacer1, "   ", " ", "Welcome to your best Social Experience ");

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

        RadioButton all = RadioButton.createToggle("Post Details", barGroup);
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

        // Affichage
        Publication l = new Publication();
        PublicationService ser = new PublicationService();
        l = ser.getPub(Idd);

        URLImage icon1 = URLImage.createToStorage((EncodedImage) res.getImage("news-item-1.jpg"), l.getImage() + ".png", "http://localhost/AdoPet/Images/" + l.getImage() + ".png");
        icon1.fetch();
        String dp = l.getDate() + " at " + l.getTime();
        addButton(icon1, l.getType(), l.getDescription(), dp, l.getIdpub(), res);

    }

    private void addButton(Image img, String type, String description, String time, String id, Resources res) {
        Label l1 = new Label("Post details");
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        int height = Display.getInstance().convertToPixels(40f);
        int width = Display.getInstance().convertToPixels(40f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");

        Label l3 = new Label("Publié le :");
        TextArea timeTf = new TextArea(time);
        timeTf.setEditable(false);
        timeTf.getUnselectedStyle().setFgColor(-16777216);

        Label l4 = new Label("Type :");
        TextArea contentTf = new TextArea(type);
        contentTf.setEditable(false);
        contentTf.getUnselectedStyle().setFgColor(-16777216);

        Label l2 = new Label("Description :");
        TextArea sgTf = new TextArea(description);
        sgTf.setEditable(false);
        sgTf.getUnselectedStyle().setFgColor(-16777216);

        Label l5 = new Label("Notez la publication:");

        Button rateBtn = new Button("Rate");
        Button editBtn = new Button("Edit");
        Button signalBtn = new Button("Signaler");
        Button backBtn = new Button("Back");

        l2.getUnselectedStyle().setFont(l1_font);
        l3.getUnselectedStyle().setFont(l1_font);
        l4.getUnselectedStyle().setFont(l1_font);
        l5.getUnselectedStyle().setFont(l1_font);

        add(image);
        add(l3);
        add(timeTf);
        add(l2);
        add(sgTf);
        add(l4);
        add(contentTf);
        add(l5);
        Slider stars = createStarRankSlider();
        add(FlowLayout.encloseCenter(stars));
        add(rateBtn);
        add(editBtn);
        add(signalBtn);
        add(backBtn);

        String Idd = id;
        /*editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UpdateForm uf = new UpdateForm(res, Idd);
                uf.show();
            }
        });*/

 /*int nvSg = signal + 1;
        signalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://localhost/CleanModernUIKit-master/signaler.php?signal="
                        + nvSg + "&id=" + Idd
                );
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        String response = new String(con.getResponseData());
                        if (response.equals("success")) {
                            Dialog.show("Signalisation", "Publication Signalée", "ok", null);
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(con);
            }
        });

        rateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                x=stars.getProgress();
                float rate = x/2;
                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://localhost/CleanModernUIKit-master/rating.php?rate=" + rate + "&id=" + Idd);
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        String response = new String(con.getResponseData());
                        if (response.equals("success")) {
                            Dialog.show("Rating", "Publication notée", "Ok", null);
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(con);
            }
        });
         */
        backBtn.addActionListener(e -> new NewsfeedForm(res).show());
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

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
