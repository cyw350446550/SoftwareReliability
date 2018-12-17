package view.allGraph;

import entity.GOmodel;
import entity.JMmodel;
import org.jfree.data.xy.XYSeries;
import util.DataUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: cyw35
 * @Date: 2018/11/28 21:41
 * @Description:
 */
public class PLRGraph {
    public XYSeries createPLRGraph() throws IOException {
        //读入数据
        String url = "src/main/resources/data.txt";
        DataUtil dataUtil = new DataUtil();
        int[] data = dataUtil.readData(url);
        int dataLen = data.length;
        //划分测试集、训练集
        int trainDataLen = (int) (dataLen * 0.7);
        int[] trainData = new int[trainDataLen];
        int[] testData = new int[dataLen - trainDataLen];
        for (int i = 0; i < trainDataLen; i++) {
            trainData[i] = data[i];
            if (i + trainDataLen < dataLen) {
                testData[i] = data[i + trainDataLen];
            }
        }
        //JM模型 训练得到fi N;
        JMmodel jMmodel = new JMmodel(trainData, 0.1, 0.1);
        jMmodel.first();
        double fi = jMmodel.getResΦ();
        double N = jMmodel.getResN();
        //GO模型 训练得到 a,b
        GOmodel gOmodel = new GOmodel(data,0.1);
        gOmodel.first();
        double a =gOmodel.getA();
        double b = gOmodel.getB();

        XYSeries uSeries = new XYSeries("PLR-graph");
        ArrayList<Double> yData = new ArrayList<Double>();
        ArrayList<Integer> xData = new ArrayList<Integer>();
        for(int i = 0 ;i< testData.length;i++){
            double sumTop = 1,sumBase = 1;
            for(int j = 0;j<=i;j++){
                sumTop *=  fi*(N -j + 1)*Math.exp(-fi*(N - j + 1)*testData[j]);
                sumBase *= 1/(a*(1- Math.exp(-b*(j + 1)))) * Math.exp(-testData[j]/(a*(1- Math.exp(-b*(j + 1)))));
            }

            xData.add(i+1);
            yData.add(sumTop/sumBase);
            uSeries.add(i+1,sumTop/sumTop);
            System.out.println(sumTop +" " + sumBase);
            System.out.println(" PLR比值为："+ sumTop/sumBase);
        }
       return uSeries;
    }

}
