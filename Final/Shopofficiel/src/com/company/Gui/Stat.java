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
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Entities.Publication;
import com.mycompany.Services.PublicationService;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author khalil
 */
public class Stat {
    

    PublicationService sp = new PublicationService();
    ArrayList<Publication> pubs = new ArrayList<>();
    //Form f;

    public Stat() {

        //f = new Form();
        //createPieChartForm();
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

    public Form createPieChartForm() {
  
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

        // Create a form and show it.
        Form f = new Form( new BorderLayout());
        f.add(BorderLayout.CENTER, c);
        return f;

    }

}
