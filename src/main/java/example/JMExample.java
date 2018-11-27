package example;

import Util.DataHandler;
import model.JMmodel;

import java.io.IOException;

/**
 * @Auther: cyw35
 * @Date: 2018/11/15 14:34
 * @Description:
 */
public class JMExample {
    public static void main(String[] args) throws IOException {
        String url = "src/main/resources/data.txt";
        DataHandler dataHandler = new DataHandler();
        int[] data = dataHandler.readData(url);
        JMmodel jMmodel = new JMmodel(data,0.1, 0.1);
        jMmodel.first();
        System.out.println("N = " + jMmodel.getResN());
        System.out.println("Φ = " + jMmodel.getResΦ());
    }
}