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

import com.mycompany.Entities.User;
import com.mycompany.Services.UserServices;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        UserServices us=new UserServices();
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> {
             User u1=us.CheckLogin(username.getText(),password.getText());
  
            
                          System.out.println("USER ===> "+u1.getUsername());
                            User.setInstance(u1);
             if(u1.getUsername()!=null)
             {
               
                 System.out.println("username "+u1.getUsername());
                 
                 new NewsfeedForm(res).show();
                 
             }
             else{
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
                    TextArea ta = new TextArea("No No No");
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

            });
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
    }
    
}
