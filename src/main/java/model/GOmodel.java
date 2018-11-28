package model;

import java.util.Map;

/**
 * @Auther: cyw35
 * @Date: 2018/11/15 14:33
 * @Description:
 */
public class GOmodel implements Model {

    private int []data;
    private double D;
    private double YV = 0.1;
    private double a;
    private double b;

    public GOmodel(int[] data) {
        this.data = data;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public GOmodel(int[] data, double YV) {
        this.data = data;
        this.YV = YV;
    }

    private void calculatorD() {
        double res = 0;
        int len = data.length - 1;
        for (int i = 1; i <= len; i++) {
            res += data[i];
        }
        D =  res / len / data[len];
    }

    public void first() {
        calculatorD();
        double xl = 0, xr = 0;
        if (D >= 0.5) {
            return;
        } else if (0 < D && D < 0.5) {
            xl = (1 - 2 * D) / 2;
            xr = 1 / D;
        }
        second(xl, xr);
    }

    private void second(double xl, double xr) {
        double xm = (xl + xr) / 2;
        if (Math.abs(xr - xl) <= YV) {
            int len = data.length - 1;
            b = xm / (double) data[len];
            a = len / (1- Math.pow(Math.E,-b*data[len]));
            return;
        }
        //步骤3
        double f = (1 - D * xm) * Math.pow(Math.E, xm) + (D - 1) * xm - 1;
        if (f > YV) {
            xl = xm;
        } else if (f < -YV) {
            xr = xm;
        }
        second(xl, xr);
    }

}
