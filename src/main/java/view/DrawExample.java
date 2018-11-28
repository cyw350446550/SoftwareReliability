package view;

import org.jfree.data.xy.XYSeries;
import util.DrawUtil;
import view.allGraph.PLRGraph;
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
        DrawUtil drawUtil = new DrawUtil("");
        //U图
        UGraph uGraph = new UGraph();
        XYSeries uSeries = uGraph.createUGraph();
        drawUtil.addDataset(uSeries);
        //Y图
        YGraph yGraph = new YGraph();
        XYSeries ySeries = yGraph.createYGraph();
        drawUtil.addDataset(ySeries);

        //一次函数图像
        XYSeries xySeries = new XYSeries("Y=X");
        for(int i = 0;i<uSeries.getItemCount();i++){
            xySeries.add(uSeries.getX(i),uSeries.getX(i));
        }
        drawUtil.addDataset(xySeries);

        //U图KS距离
        XYSeries uGraphKSDis = new XYSeries("UGraphKSDis");
        double maxDis = 0;
        double maxXData = 0;
        double maxYData =0;
        for(int i = 0;i<uSeries.getItemCount();i++){
            if(Math.abs(uSeries.getX(i).doubleValue() - uSeries.getY(i).doubleValue())> maxDis){
                maxDis = Math.abs(uSeries.getX(i).doubleValue() - uSeries.getY(i).doubleValue());
                maxXData = uSeries.getX(i).doubleValue();
                maxYData = uSeries.getY(i).doubleValue();
            }
        }
        uGraphKSDis.add(maxXData,maxXData);
        uGraphKSDis.add(maxXData,maxYData);
        drawUtil.addDataset(uGraphKSDis);
        // Y图KS距离
        maxDis = 0;
        XYSeries yGraphKSDis = new XYSeries("YGraphKSDis");
        for(int i = 0;i<ySeries.getItemCount();i++){
            if(Math.abs(ySeries.getX(i).doubleValue() - ySeries.getY(i).doubleValue())> maxDis){
                maxDis = Math.abs(ySeries.getX(i).doubleValue() - ySeries.getY(i).doubleValue());
                maxXData = ySeries.getX(i).doubleValue();
                maxYData = ySeries.getY(i).doubleValue();
            }
        }
        yGraphKSDis.add(maxXData,maxXData);
        yGraphKSDis.add(maxXData,maxYData);
        drawUtil.addDataset(yGraphKSDis);

        //显示
        drawUtil.drawGraph();
        drawUtil.pack();
        drawUtil.setVisible(true);

        //画PLR图，由于分母为0 ，画不出来
//        DrawUtil drawUtil1 = new DrawUtil("");
//        PLRGraph plrGraph = new PLRGraph();
//        XYSeries series1 = plrGraph.createPLRGraph();
//        drawUtil1.addDataset(series1);
//        drawUtil1.drawGraph();
//        drawUtil1.pack();
//        drawUtil1.setVisible(true);

    }
}
