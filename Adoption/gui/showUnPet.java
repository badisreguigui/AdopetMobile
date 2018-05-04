package com.mycompany.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entitie.Pet;
import com.mycompany.Service.ServicePet;
import java.io.IOException;


/**
 *
 * @author mac
 */
public class showUnPet extends BaseForm{
    
    public showUnPet(Resources res, int id) {
        super("Newsfeed", BoxLayout.y());
        
        ServicePet sc1=new ServicePet();
        Pet p=sc1.showPet(id);
        
        
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
//        Image img = Image.createImage("/img.jpg");

        
        
        System.out.println(p.getPet_image());
      
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        try {
            add(LayeredLayout.encloseIn(
                    sl,
                    BorderLayout.south(
                            GridLayout.encloseIn(3,
                                    facebook,
                                    FlowLayout.encloseCenter(
                                            new Label(Image.createImage("/img.jpg").scaled(100, 100), "PictureWhiteBackgrond")),
                                    twitter
                            )
                    )
            ));
        } catch (IOException ex) {
            //Logger.getLogger(showUnPet.class.getName()).log(Level.SEVERE, null, ex);
        }
        


        Label username = new Label(p.getName_pet());
        username.setUIID("TextFieldBlack");
        addStringValue("name pet", username);
        
        Label breed = new Label(p.getBreed());
        breed.setUIID("TextFieldBlack");
        addStringValue("Breed", breed);
        
        Label sex = new Label(p.getSex());
        sex.setUIID("TextFieldBlack");
        addStringValue("Sex", sex);
        
        Label age = new Label(""+p.getAge());
        age.setUIID("TextFieldBlack");
        addStringValue("Age", age);

        

        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
