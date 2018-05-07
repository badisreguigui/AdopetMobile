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

import Entities.User;
import Services.UserServices;
import com.codename1.components.FloatingHint;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import Entities.Session;
import com.codename1.ui.Dialog;
import java.io.IOException;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    private Database db;
    Session session = Session.getInstance();

    public SignInForm(Resources res) {

        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
//        Button signUp = new Button("Sign Up");
//        signUp.addActionListener(e -> new SignUpForm(res).show());
//        signUp.setUIID("Link");
//        Label doneHaveAnAccount = new Label("Don't have an account?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn
//                ,
//                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        try {
            db = Database.openOrCreate("DB_user");
            db.execute("create table if not "
                    + "exists user (username TEXT, password TEXT);");
            System.out.println("table user created");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        String un = null, pwd = null;
        try {
            Cursor c = db.executeQuery("select * from user");
            while (c.next()) {
                Row r = c.getRow();
                un = r.getString(0);
                pwd = r.getString(1);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        if (un == null) {
            System.out.println("username empty");
        } else {
            username.setText(un);
        }
        if (pwd == null) {
            System.out.println("password empty");
        } else {
            password.setText(pwd);
        }
        signIn.addActionListener(e -> {

            UserServices us = new UserServices();
            if (!us.login(username.getText(), password.getText()).equals("try again")) {
                try {
                    db.execute("DELETE from user ;");
                    System.out.println("table user now empty");
                    db.execute("insert into user (username, password) values ('" + username.getText() + "', '" + password.getText() + "' );");
                    System.out.println("user added");
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                User c = new User(Integer.parseInt(us.login(username.getText(), password.getText())), username.getText(), password.getText());
                session.addSession(c);
                new NewsfeedForm(res).show();
            }else{
                Dialog.show("error", "login ou pwd invalid", "ok", null);
            }
        });
    }

}
