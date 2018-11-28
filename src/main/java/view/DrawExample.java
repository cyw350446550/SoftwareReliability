package view;

import org.jfree.data.xy.XYSeries;
import util.DrawUtil;
import view.allGraph.UGraph;
import view.allGraph.YGraph;

import java.io.IOException;

/**
 * @Auther: cyw35
 * @Date: 2018/11/28 19:45
 * @Description:
 */
public class DrawExample {
    public static void main(String[] args) throws IOException {
        //U图
        UGraph uGraph = new UGraph();
        DrawUtil drawUtil = new DrawUtil("");
        XYSeries series = uGraph.createUGraph();
        drawUtil.addDataset(series);
        //Y图
        YGraph yGraph = new YGraph();
        drawUtil.addDataset(yGraph.createYGraph());
        //一次函数图像
        XYSeries xySeries = new XYSeries("Y=X");
        for(int i = 0;i<series.getItemCount();i++){
            xySeries.add(series.getX(i),series.getX(i));
        }
        drawUtil.addDataset(xySeries);
        //显示
        drawUtil.drawGraph();
        drawUtil.pack();
        drawUtil.setVisible(true);
    }
}
