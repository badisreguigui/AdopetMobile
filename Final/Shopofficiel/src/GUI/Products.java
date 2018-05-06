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

package GUI;

import Entities.Lignedecommande;
import Entities.Panier;
import Entities.Produit;
import Entities.User;
import Services.ProduitServices;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The newsfeed form
 *
 * @author Badis
 */
public class Products extends BaseForm {
    Panier pan = Panier.getInstance();
    Form f;
    ImageViewer Pdp;
    User user = User.getInstance();
    int idproduit;
    Container c2;
    

    public Products(Resources res) {
        super("Adopet", BoxLayout.y());
        try {
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            tb.addMaterialCommandToOverflowMenu("Your Cart",FontImage.MATERIAL_SHOPPING_BASKET, (e)->{
                try {
                    Cart c=new Cart(res);
                    c.getF().show();
                } catch (IOException ex) {
                    System.out.println("ERREUR DANS VOIR PANIER");            }
//            tb.openSideMenu();
            });
            getTitleArea().setUIID("Container");
            setTitle("Adopet");
            getContentPane().setScrollVisible(false);
            
            super.addSideMenu(res);
            tb.addSearchCommand(e -> {});
            
            Tabs swipe = new Tabs();
            
            Label spacer1 = new Label();
            Label spacer2 = new Label();
            addTab(swipe,Image.createImage("/dog.jpg"), spacer1, "", "", "");
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
            for(int iter = 0 ; iter < rbs.length ; iter++) {
                rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
                rbs[iter].setPressedIcon(selectedWalkthru);
                rbs[iter].setUIID("Label");
                radioContainer.add(rbs[iter]);
            }
            
            rbs[0].setSelected(true);
            swipe.addSelectionListener((i, ii) -> {
                if(!rbs[ii].isSelected()) {
                    rbs[ii].setSelected(true);
                }
            });
            
            Component.setSameSize(radioContainer, spacer1, spacer2);
            add(LayeredLayout.encloseIn(swipe, radioContainer));
            
            ButtonGroup barGroup = new ButtonGroup();
            RadioButton all = RadioButton.createToggle("All", barGroup);
            all.setUIID("SelectBar");
            RadioButton featured = RadioButton.createToggle("Products", barGroup);
            featured.setUIID("SelectBar");
            RadioButton popular = RadioButton.createToggle("Popular", barGroup);
            popular.setUIID("SelectBar");
            RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
            myFavorite.setUIID("SelectBar");
            Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
            
            add(LayeredLayout.encloseIn(
                    GridLayout.encloseIn(1,featured),
                    FlowLayout.encloseBottom(arrow)
            ));
            
            
            all.setSelected(true);
            arrow.setVisible(false);
            addShowListener(e -> {
                arrow.setVisible(true);
                updateArrowPosition(featured, arrow);
            });
            bindButtonSelection(featured, arrow);
            
            // special case for rotation
            addOrientationListener(e -> {
                updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            });
            c2 = new Container(BoxLayout.y());
                ProduitServices ps = new ProduitServices();
                ArrayList<Produit> lis = ps.getList2();
                System.out.println(lis);
                c2.removeAll();
                c2 = new Container(BoxLayout.y());
                
                
                for (Produit p : lis) {
                    try {
                        
                        Container c1 = new Container(BoxLayout.x());
                        Container c3 = new Container(BoxLayout.y());
                        Container c4 = new Container(BoxLayout.x());
                        Container c5 = new Container(BoxLayout.x());
                        c1.getStyle().setBorder(Border.createLineBorder(2,0xFF6666));
                        c1.getStyle().setMargin(1, 1, 1, 1);
                        c1.getStyle().setPadding(20, 50,0, 0);
                        
                        c1.getStyle().setBgColor(0xFFFFFF);
                        ImageViewer iv = new ImageViewer();
                        iv.setImage(Image.createImage("/" + p.getImage()).scaledHeight(100).scaledWidth(100));
                        //Label Nom = new Label("Nom : ");
                        
                        Label PrixProd = new Label(" "+String.valueOf(p.getPrix())+"DT");
                        System.out.println(p.getRace());
                        Label race = new Label(" Race : "+p.getRace());
                        Label NomProd = new Label(" "+p.getNom());
                        Label quantite = new Label();
                        quantite.setText(String.valueOf(p.getQuantite()));
                        quantite.setVisible(false);
                        System.out.println(quantite.getText());
                        //NomProd.setWidth(2000);
                        NomProd.setUIID("label2");
                        PrixProd.setUIID("label2");
                        race.setUIID("label2");
                        Label IdProd = new Label(String.valueOf(p.getId()));
                        IdProd.setVisible(false);
                        Button ToCart = new Button("AddToCart");
                        Slider s=createStarRankSlider();
                        s.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent evt)
                            {
                                if(ps.checkR(p.getId(),user.getId())==1){
                                    ps.addRate(s.getProgress(),user.getId(),p.getId());
                                    s.setEditable(false);
                                    System.out.println(s.getProgress());
                                    if(s.getProgress()==0)
                                    {
                                        System.out.println("0");
                                    }
                                    else
                                    {
                                        Dialog dlg = new Dialog("     Notification !");
                                        Style dlgStyle = dlg.getDialogStyle();
                                        dlgStyle.setBorder(Border.createEmpty());
                                        dlgStyle.setBgTransparency(255);
                                        dlgStyle.setBgColor(0xffffff);
                                        
                                        Label title = dlg.getTitleComponent();
                                        try {
                                            title.setIcon(Image.createImage("/heart.png").scaled(25, 25));
                                        } catch (IOException ex) {
                                            System.out.println("check image error.png cart.java");
                                        }
                                        title.getUnselectedStyle().setFgColor(0xFF0000);
                                        title.getUnselectedStyle().setAlignment(Component.LEFT);
                                        
                                        dlg.setLayout(BoxLayout.y());
                                        Label blueLabel = new Label();
                                        blueLabel.setShowEvenIfBlank(true);
                                        blueLabel.getUnselectedStyle().setBgColor(0xff);
                                        blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                                        blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                                        dlg.add(blueLabel);
                                        TextArea ta = new TextArea("You voted : "+s.getProgress());
                                        ta.setEditable(false);
                                        ta.setUIID("DialogBody");
                                        ta.getAllStyles().setFgColor(0);
                                        dlg.add(ta);
                                        
                                        Label grayLabel = new Label();
                                        grayLabel.setShowEvenIfBlank(true);
                                        grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                                        grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                                        grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                                        dlg.add(grayLabel);
                                        
                                        Button ok = new Button(new Command("OK"));
                                        ok.getAllStyles().setBorder(Border.createEmpty());
                                        ok.getAllStyles().setFgColor(0);
                                        dlg.add(ok);
                                        dlg.showDialog();
                                    }
                                }
                                else{
                                    s.setEditable(false);
                                    Dialog.show("Warning", "You already voted for "+p.getNom(), "OK", null);
                                }
                                
                            }
                        }
                        );
                        int p2 = ps.DisplayRate(p.getId());
                        System.out.println();
                        if(p2==5){
                            
                            Label rating = new Label(Image.createImage("/5 stars rating.png").scaled(80, 30));
                            
                            c3.add(rating);
                        }
                        if(p2==4){
                            
                            Label rating = new Label(Image.createImage("/4 star rating.png").scaled(80, 30));
                            
                            c3.add(rating);
                        }
                        if(p2==3){
                            
                            Label rating = new Label(Image.createImage("/3 stars rating.png").scaled(80, 30));
                            
                            c3.add(rating);
                        }
                        if(p2==2){
                            
                            Label rating = new Label(Image.createImage("/2 stars rating.png").scaled(80, 30));
                            
                            c3.add(rating);
                        }
                        if(p2==1){
                            
                            Label rating = new Label(Image.createImage("/1starrating.png").scaled(80, 30));
                            
                            c3.add(rating);
                        }
                        
                        
                        
                        Container cBtoCart = new Container(new FlowLayout(Component.RIGHT,Component.TOP));
                        
                        cBtoCart.add(ToCart);
                        
                        
                        c1.add(iv);
                        System.out.println(s);
                        c1.add(c3);
                        c3.add(NomProd);
                        
                        
                        //c3.add(c4);
                        c3.add(PrixProd);
                        //c3.add(rating);
                        c3.add(race);
                        c3.add(FlowLayout.encloseCenter(s));
                        
                        //c3.add(s);
                        //c3.add(IdProd);
                        
                        c3.setPreferredSize(new Dimension(300, 200));
                        
                        
                        
                        ToCart.addActionListener(e1 -> {
                            Dialog dlg = new Dialog("     Notification !");
                            Style dlgStyle = dlg.getDialogStyle();
                            dlgStyle.setBorder(Border.createEmpty());
                            dlgStyle.setBgTransparency(255);
                            dlgStyle.setBgColor(0xffffff);
                            
                            Label title = dlg.getTitleComponent();
                            try {
                                title.setIcon(Image.createImage("/heart.png").scaled(25, 25));
                            } catch (IOException ex) {
                                System.out.println("check image error.png cart.java");
                            }
                            title.getUnselectedStyle().setFgColor(0xFF0000);
                            title.getUnselectedStyle().setAlignment(Component.LEFT);
                            
                            dlg.setLayout(BoxLayout.y());
                            Label blueLabel = new Label();
                            blueLabel.setShowEvenIfBlank(true);
                            blueLabel.getUnselectedStyle().setBgColor(0xff);
                            blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                            blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                            dlg.add(blueLabel);
                            TextArea ta = new TextArea("Added to Cart");
                            ta.setEditable(false);
                            ta.setUIID("DialogBody");
                            ta.getAllStyles().setFgColor(0);
                            dlg.add(ta);
                            
                            Label grayLabel = new Label();
                            grayLabel.setShowEvenIfBlank(true);
                            grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                            grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                            grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                            dlg.add(grayLabel);
                            
                            Button ok = new Button(new Command("OK"));
                            ok.getAllStyles().setBorder(Border.createEmpty());
                            ok.getAllStyles().setFgColor(0);
                            dlg.add(ok);
                            dlg.showDialog();
                            Produit p1 = ps.searchByName(Integer.valueOf(IdProd.getText()));
                            Lignedecommande c = new Lignedecommande(p1.getNom(), p1.getPrix(), 1,p1.getId(),user.getId(),p1);
                            pan.addLine(c);
                            
                            
                        });
                        
                        c2.add(c1);
                        if(quantite.getText().equals("0")){
                            Button epuise = new Button("EPUISE");
                            epuise.setEnabled(false);
                            c2.add(epuise);
                        }
                        else{
                            c2.add(cBtoCart);
                        }
                    }
                    //Toolbar tb=f.getToolbar();
                    catch (IOException ex) {
                        System.out.println("hhhh");
                    }
                    
                    
                }
                
                
                
                add(c2);
            
            /* addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
            addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
            addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
            addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);*/
        } catch (IOException ex) {
            System.out.println("hhhhhhhh");
        }
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
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
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
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
       if(!liked) {
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
            if(b.isSelected()) {
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
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
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
    private void showStarPickingForm() {
    Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
    hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
    hi.show();
}
}
