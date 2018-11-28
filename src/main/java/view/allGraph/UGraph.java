package view.allGraph;

import model.JMmodel;
import model.Model;
import org.jfree.data.xy.XYSeries;
import util.DataHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Auther: cyw35
 * @Date: 2018/11/28 17:05
 * @Description:
 */
public class UGraph {
    public XYSeries createUGraph() throws IOException {
        String url = "src/main/resources/data.txt";
        DataHandler dataHandler = new DataHandler();
        int[] data = dataHandler.readData(url);
        int dataLen = data.length;
        int trainDataLen = (int) (dataLen * 0.7);
        int[] trainData = new int[trainDataLen];
        int[] testData = new int[dataLen - trainDataLen];
        for (int i = 0; i < trainDataLen; i++) {
            trainData[i] = data[i];
            if (i + trainDataLen < dataLen) {
                testData[i] = data[i + trainDataLen];
            }
        }

        //训练得到fi N;
        JMmodel jMmodel = new JMmodel(trainData, 0.1, 0.1);
        jMmodel.first();
        double fi = jMmodel.getResΦ();
        double N = jMmodel.getResN();


        int testDataLen = testData.length;
        TreeSet<Double> xData = new TreeSet<Double>();

        for (int i = 0; i < testDataLen; i++) {
            xData.add(1 - Math.exp(-fi * (N - i + 1) * testData[i]));
        }
//        System.out.println(xData.size());
        TreeSet<Double> yData = new TreeSet<Double>();

        for (int i = 0; i < xData.size(); i++) {
            yData.add(i / (double) (xData.size() + 1));
        }
        XYSeries uSeries = new XYSeries("U-graph");
        double x, y, y2;
        while (xData.size() != 1) {
            x = xData.pollFirst();
            y = yData.pollFirst();
            uSeries.add(x, y);
            y2 = yData.pollFirst();
            uSeries.add(x, y2);
            yData.add(y2);
        }
        x = xData.pollFirst();
        y = yData.pollFirst();
        uSeries.add(x, y);
        return uSeries;
    }

}
