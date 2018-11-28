package util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;

/**
 * @Auther: cyw35
 * @Date: 2018/11/28 19:35
 * @Description:
 */
public class DrawUtil extends ApplicationFrame {
    private XYSeriesCollection dataset;

    public DrawUtil(String title) {
        super(title);
    }

    public void addDataset(XYSeries series){
        if(dataset == null){
            dataset = new XYSeriesCollection();
        }
        dataset.addSeries(series);
    }

    public void drawGraph(){

        JFreeChart chart = ChartFactory.createXYLineChart("",
                "", "", dataset,
                PlotOrientation.VERTICAL, true, true, false
        );
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(1400, 1200));
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke( 0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.orange);
        renderer.setSeriesStroke( 1, new BasicStroke(2.0f));
        renderer.setSeriesPaint(2, Color.RED);
        renderer.setSeriesStroke( 2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(panel);
    }
}
