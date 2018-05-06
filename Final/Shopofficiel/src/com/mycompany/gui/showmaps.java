/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author mac
 */
public class showmaps extends Form {
    public showmaps(Resources res)  {
        Form hi = new Form("Directions");

        final GoogleMap map = new GoogleMap();
        hi.setLayout(new BorderLayout());
        final TextField start = new TextField("Tunis");
        start.setHint("Tunis");
        final TextField end = new TextField("menzah");
        end.setHint("menzah");

        Container form = new Container();
        form.setLayout(new BorderLayout());
        form.addComponent(BorderLayout.NORTH, start);
        form.addComponent(BorderLayout.SOUTH, end);



        ActionListener routeListener = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ( !"".equals(start.getText()) && !"".equals(end.getText())){
                DirectionsRequest req = new DirectionsRequest();
                req.setTravelMode(DirectionsRequest.TRAVEL_MODE_DRIVING);
                req.setOriginName(start.getText());
                req.setDestinationName(end.getText());

    //            Coord a = new Coord(431.889, -87.622);
    //            req.setOriginCoord(a);

                map.route(req, new DirectionsRouteListener(){
                    public void routeCalculated(DirectionsResult result) {
                        System.out.println("Successfully mapped route");
                    }
                    });
                }
            }
        };

        start.addActionListener(routeListener);
        end.addActionListener(routeListener);
        hi.addComponent(BorderLayout.NORTH, form);
        hi.addComponent(BorderLayout.CENTER, map);
        hi.show();
        
    }
    
}
