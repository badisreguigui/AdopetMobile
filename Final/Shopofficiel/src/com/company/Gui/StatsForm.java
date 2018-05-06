/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
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
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entities.Publication;
import com.mycompany.Entities.User;
import com.mycompany.Services.PublicationService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class StatsForm extends BaseForm {

    public StatsForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        try {
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle("Newsfeed");
            getContentPane().setScrollVisible(false);
            User user = User.getInstance();
            
            PublicationService sp = new PublicationService();
            ArrayList<Publication> pubs = new ArrayList<>();
            
            super.addSideMenu(res);
            tb.addSearchCommand(e -> {
            });
            
            Tabs swipe = new Tabs();
            
            Label spacer1 = new Label();
            Label spacer2 = new Label();
            addTab(swipe,Image.createImage("/dog.jpg"), spacer1, "   ", "   ", "");
            
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
            
            RadioButton all = RadioButton.createToggle("Show All", barGroup);
            all.setUIID("SelectBar");
            RadioButton featured = RadioButton.createToggle("Add Post", barGroup);
            featured.setUIID("SelectBar");
            featured.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    AddForm af = new AddForm(res);
                    af.show();
                }
            });
            RadioButton stat = RadioButton.createToggle("Stat", barGroup);
            stat.setUIID("SelectBar");
            stat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Stat s = new Stat();
                    s.createPieChartForm().show();
                }
            });
            
            Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
            
            add(LayeredLayout.encloseIn(
                    GridLayout.encloseIn(3, all, featured, stat),
                    FlowLayout.encloseBottom(arrow)
            ));
            
            all.setSelected(true);
            arrow.setVisible(false);
            addShowListener(e -> {
                arrow.setVisible(true);
                updateArrowPosition(all, arrow);
            });
            bindButtonSelection(all, arrow);
            bindButtonSelection(featured, arrow);
            
            // special case for rotation
            addOrientationListener(e -> {
                updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
            });
            
            //Stats
            double[] values = new double[4];
            
            values[0] = Integer.parseInt(sp.count("Nourriture"));
            values[1] = Integer.parseInt(sp.count("Dressage"));
            values[2] = Integer.parseInt(sp.count("Soins"));
            values[3] = Integer.parseInt(sp.count("Autres"));
            
            // Set up the renderer
            int[] colors = new int[]{ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.GRAY};
            DefaultRenderer renderer = buildCategoryRenderer(colors);
            renderer.setZoomButtonsVisible(true);
            renderer.setZoomEnabled(true);
            renderer.setChartTitleTextSize(20);
            renderer.setDisplayValues(false);
            renderer.setShowLabels(false);
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
            PieChart chart = new PieChart(buildCategoryDataset("Type pubs", values), renderer);
            
            // Wrap the chart in a Component so we can add it to a form
            ChartComponent c = new ChartComponent(chart);
            
            Container cnt = BorderLayout.center(c) ;
            add(cnt);
        } catch (IOException ex) {
            System.out.println("okokokokok");
        }
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

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(17);
        renderer.setMargins(new int[]{20, 30, 15, 0});

        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("Nourriture", values[0]);
        series.add("Dressage", values[1]);
        series.add("Soins", values[2]);
        series.add("Autres", values[3]);

        return series;
    }

}
