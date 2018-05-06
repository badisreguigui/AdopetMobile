/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ext.codescan.CodeScanner;
import com.codename1.ext.codescan.ScanResult;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author mac
 */
public class scan {
    Form  hi; 
    private Form current;
    public scan(Resources res) throws IOException {
        hi = new Form("Codescan Demo");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        final RadioButton qr = new RadioButton("QR Code");
        final RadioButton bar = new RadioButton("Bar Code");
        bg.add(qr);
        bg.add(bar);
        hi.addComponent(new Label("Code Type"));
        hi.addComponent(qr);
        hi.addComponent(bar);
        
        Button scanBtn = new Button("Scan Code");
        scanBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                final Container cnt = hi;

                if(qr.isSelected()){
                    CodeScanner.getInstance().scanQRCode(new ScanResult() {

                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                            //barCode.setText("Bar: " + contents);
                            cnt.addComponent(new Label(contents));
                            cnt.revalidate();
                        }

                        public void scanCanceled() {
                            System.out.println("cancelled");
                        }

                        public void scanError(int errorCode, String message) {
                            System.out.println("err " + message);
                        }
                    });
                }else{
                    CodeScanner.getInstance().scanBarCode(new ScanResult() {

                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                            //barCode.setText("Bar: " + contents);
                            cnt.addComponent(new Label(contents));
                            
                            cnt.revalidate();
                        }

                        public void scanCanceled() {
                            System.out.println("cancelled");
                        }

                        public void scanError(int errorCode, String message) {
                            System.out.println("err " + message);
                        }
                    });        
                }
            }
            
        });
        if (CodeScanner.isSupported()) {
            hi.addComponent(scanBtn);
        } else {
            hi.addComponent(new SpanLabel("Sorry.  Codescanner not supported on this platform"));
        }
        hi.show();
    }
    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }
}
