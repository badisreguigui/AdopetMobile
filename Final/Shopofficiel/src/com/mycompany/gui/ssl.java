package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.security.certfingerprint.CheckCert;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mac
 */
public class ssl{
    
    Form  hi; 
    public ssl(Resources res) throws IOException {
         hi = new Form("Check Server", BoxLayout.y());
        TextField url = new TextField("https://www.google.com/", "URL", 20, TextField.URL);
        TextArea result = new TextArea(3, 80);
        result.setHint("Results...");
        Button test = new Button("Test Fingerprint");
        System.out.println("grgrrgr");
        test.addActionListener(e -> {
            try {
                String f = CheckCert.getFingerprint(url.getText());
                result.setText(f);
                System.out.println("f");
                System.out.println(f);
            } catch(IOException err) {
                ToastBar.showErrorMessage(err.getMessage());
            }
        });
        hi.add(new SpanLabel("Type in the URL and press the test fingerprint button")).
                add(url).
                add(test).
                add(result);
        hi.show();
    }
    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }
}
