package example;

import entity.NeuronNet;
import util.DataUtil;

import java.io.IOException;

/**
 * @Auther: cyw35
 * @Date: 2018/12/15 21:58
 * @Description:
 */
public class BPExample {
    public static void main(String[] args) throws IOException {
        String url = "src/main/resources/data.txt";
        DataUtil dataUtil = new DataUtil();
        int[] data = dataUtil.readData(url);
        int len = data.length;
        double[][] X = new double[len][1];
        double[][] Y = new double[len][1];
        int maxData = 0;
        int minData = 0;
        for (int i = 0; i < len; i++) {
            X[i][0] = (double) data[i];
            maxData = maxData > data[i] ? maxData : data[i];
            minData = minData < data[i] ? minData : data[i];
        }
        //归一化
        for (int i = 0; i < len; i++) {
            Y[i][0] = (data[i] - minData) / (maxData - minData);
        }
        // 三层神经网络，每层神经元个数分别是3，5，8
        NeuronNet bpnn = new NeuronNet(new int[]{1, 5, 1});

        bpnn.train(X, Y);

        double[] output = bpnn.predict(new double[]{(double) len});
        double res = -1;
        for (int j = 0; j < output.length; ++j) {
            if (res < output[j]) {
                res = output[j];
            }
        }
        //反归一化
        res = res * (maxData - minData)+minData;
        System.out.println("对于下一个预测值为"+res);
    }
}