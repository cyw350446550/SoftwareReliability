package example;

import util.DataHandler;
import model.GOmodel;

import java.io.IOException;

/**
 * @Auther: cyw35
 * @Date: 2018/11/15 15:14
 * @Description:
 */
public class GOExample {
    public static void main(String[] args) throws IOException {
        String url = "src/main/resources/data.txt";
        DataHandler dataHandler = new DataHandler();
        int[] data = dataHandler.readData(url);
        GOmodel gOmodel = new GOmodel(data,0.1);
        gOmodel.first();
        System.out.println("a = "+ gOmodel.getA());
        System.out.println("b = "+ gOmodel.getB());
    }
}
