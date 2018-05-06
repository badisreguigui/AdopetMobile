/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Lignedecommande;
import Entities.Panier;
import Entities.Produit;
import Entities.User;
import Services.ProduitServices;
import com.codename1.charts.views.ClickableArea;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Badis
 */
public class AfficherProduits {

    Panier pan = Panier.getInstance();
    Form f;
    ImageViewer Pdp;
    User user = User.getInstance();
    int idproduit;

    public AfficherProduits(Resources res) throws IOException {
    
        f = new Form(BoxLayout.y());
        f.getStyle().setBgColor(0xFFE5CC);

        ProduitServices ps = new ProduitServices();
        ArrayList<Produit> lis = ps.getList2();
        System.out.println(lis);

        Container c2 = new Container(BoxLayout.y());

        for (Produit p : lis) {
            Container c1 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.y());
            Container c4 = new Container(BoxLayout.x());
            Container c5 = new Container(BoxLayout.x());
            c1.getStyle().setBorder(Border.createLineBorder(1));
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
                      
                     ps.addRate(s.getProgress(),user.getId(),p.getId());                     
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
            
            c3.setPreferredSize(new Dimension(500, 200));
            
            

            ToCart.addActionListener(e -> {
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
                Lignedecommande c = new Lignedecommande(p1.getNom(), p1.getPrix(), 1, p1);
                pan.addLine(c);
               

            });
            
            c2.add(c1);
            c2.add(cBtoCart);

            
        }
        Toolbar tb=f.getToolbar();
       
            tb.addMaterialCommandToOverflowMenu("Your Cart",FontImage.MATERIAL_SHOPPING_BASKET, (e)->{
            try {
                Cart c=new Cart(res);
                c.getF().show();
            } catch (IOException ex) {
                System.out.println("ERREUR DANS VOIR PANIER");            }
//            tb.openSideMenu();
        });

           
        
        f.add(c2);
        
        //f.getStyle().setBgColor(0xC40C0C);

        //f.show();
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

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
