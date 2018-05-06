/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author mac
 */
public class botchat {
    Form  sb; 
    private Form current;
    private String userName;
    private Image userPicture;
    private TTS tts = (TTS)NativeLookup.create(TTS.class); 
    
    
    public botchat(Resources res) throws IOException {
        showSbaitso();
    }
    
    
    private DataChangedListener createSearchListener(final TextField searchField, final Container discussion, final Button ask) {
        return new DataChangedListener() {
            private boolean animateLock;
            public void dataChanged(int type, int index) {
                String t = searchField.getText();
                int count = discussion.getComponentCount();
                if(t.length() == 0) {
                    ask.setEnabled(true);
                    for(int iter = 0 ; iter < count ; iter++) {
                        Component c = discussion.getComponentAt(iter);
                        c.setPreferredSize(null);
                        c.setVisible(true);
                    }
                    animateChanage();
                    return;
                }              
                t = t.toLowerCase();
                ask.setEnabled(false);
                for(int iter = 0 ; iter < count ; iter++) {
                    SpanLabel tt = (SpanLabel)discussion.getComponentAt(iter);
                    if(tt.getText().toLowerCase().indexOf(t) < 0) {
                        tt.setPreferredSize(new Dimension(1, 1));
                        tt.setVisible(false);
                    } else {
                        tt.setPreferredSize(null);
                        tt.setVisible(true);
                    }
                }
                animateChanage();
            }
            private void animateChanage() {
                if(!animateLock) {
                    animateLock = true;
                    discussion.animateLayoutAndWait(300);
                    animateLock = false;
                }
            }
        };        
    }
    
    void showSbaitso() {
        sb = new Form();
        sb.setFormBottomPaddingEditingMode(true);
        Toolbar t = new Toolbar();
        sb.setToolBar(t);
        final TextField searchField = new TextField();
        searchField.setHint("Search For Answers...");
        t.setTitleComponent(searchField);
        sb.setLayout(new BorderLayout());
        final TextField ask = new TextField();
        ask.setHint("Ask The Dr.");
        Container askContainer = new Container(new BorderLayout());
        askContainer.addComponent(BorderLayout.CENTER, ask);
        Button askButton = new Button("Ask");
        askContainer.addComponent(BorderLayout.EAST, askButton);        
        sb.addComponent(BorderLayout.SOUTH, askContainer);
        final Container discussion = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        sb.addComponent(BorderLayout.CENTER, discussion);
        discussion.setScrollableY(true);
        sb.show();
        Display.getInstance().callSerially(new Runnable() {
            public void run() {
                String w = "HELLO HUMAN, MY NAME IS ROSS.\n\nI AM HERE TO HELP YOU.\n" +
                                            "SAY WHATEVER IS IN YOUR MIND FREELY," +
                                            "'Sticks and stones may break your bones but words can hurt like hell'.\n";
                say(discussion, w, false);
                if(tts != null && tts.isSupported()) {
                    tts.say(w);
                }
            }
        });
        searchField.addDataChangeListener(createSearchListener(searchField, discussion, askButton));
        ActionListener askEvent = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String t = ask.getText();
                if(t.length() > 0) {
                    ask.setText("");
                    say(discussion, t, true);
                    answer(t, discussion);
                }
            }
        };
        ask.setDoneListener(askEvent);
        askButton.addActionListener(askEvent);
    }
    
    void answer(String question, Container dest) {
        try {
            String resp = AI.getResponse(question);
            say(dest, resp, false);
            if(tts != null && tts.isSupported()) {
                tts.say(resp);
            }
        } catch (IOException ex) {
            System.out.println("okokok");
        }
    }
    
    void say(Container destination, String text, boolean question) {
        SpanLabel t = new SpanLabel(text);
        t.setWidth(destination.getWidth());
        t.setX(0);
        t.setHeight(t.getPreferredH());
        
        if(question) {
            t.setY(Display.getInstance().getDisplayHeight());
            t.setTextUIID("BubbleUser");
            t.setIconPosition(BorderLayout.EAST);
            t.setIcon(userPicture);
            t.setTextBlockAlign(Component.RIGHT);
        } else {
            t.setY(0);
            t.setTextUIID("BubbleSbaitso");
            t.setTextBlockAlign(Component.LEFT);
        }
        destination.addComponent(t);
        destination.animateLayoutAndWait(400);
        destination.scrollComponentToVisible(t);
    }
    
    private Image roundImage(Image img) {
        int width = img.getWidth();
        Image roundMask = Image.createImage(width, width, 0xff000000);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        img = img.applyMask(mask);
        return img;
    }
    
    private Image captureRoundImage() {
        try {
            int width = userPicture.getWidth();
            String result = Capture.capturePhoto(width, -1);
            if(result == null) {
                return userPicture;
            }
            Image capturedImage = Image.createImage(result);
            if(capturedImage.getHeight() != width) {
                if(capturedImage.getWidth() < capturedImage.getHeight()) {
                    capturedImage = capturedImage.subImage(0, capturedImage.getHeight() / 2 - width / 2, width, width, false);
                } else {
                    Image n = Image.createImage(width, width);
                    n.getGraphics().drawImage(capturedImage, 0, width / 2- capturedImage.getHeight() / 2);
                    capturedImage = n;
                }
            }
            return roundImage(capturedImage);
        } catch (IOException err) {
            err.printStackTrace();
            return userPicture;
        }
    }
    
    public Form getSb() {
        return sb;
    }

    public void setSb(Form sb) {
        this.sb = sb;
    }
}
