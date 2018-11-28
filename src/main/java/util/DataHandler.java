package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: cyw35
 * @Date: 2018/11/15 15:15
 * @Description:
 */
public class DataHandler {

    public int[] readData(String url) throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        FileReader fr = new FileReader(url);
        BufferedReader bf = new BufferedReader(fr);
        String str;
        while ((str = bf.readLine()) != null) {
            arrayList.add(str);
        }
        bf.close();
        fr.close();
        int length = arrayList.size();
        int[] array = new int[length + 1];
        array[0] = 0;   //为了后面的公式好写
        for (int i = 1; i <= length; i++) {
            String s = arrayList.get(i - 1).split("\t")[1];
            array[i] = Integer.parseInt(s);
        }
        return array;
    }


}
