package entity;

import static java.lang.Math.abs;

/**
 * @Auther: cyw35
 * @Date: 2018/11/5 19:05
 * @Description:
 */
public class JMmodel implements Model{
    private int []data;
    private double ex = 0.1;
    private double ey = 0.1;
    private double resN;
    private double resΦ;
    private double p;

    public JMmodel(int[] data) {
        this.data = data;
    }

    public JMmodel(int []data, double ex, double ey) {
        this.data = data;
        this.ex = ex;
        this.ey = ey;
    }

    public double getEx() {
        return ex;
    }

    public void setEx(double ex) {
        this.ex = ex;
    }

    public double getEy() {
        return ey;
    }

    public void setEy(double ey) {
        this.ey = ey;
    }

    public double getResN() {
        return resN;
    }

    public void setResN(double resN) {
        this.resN = resN;
    }

    public double getResΦ() {
        return resΦ;
    }

    public void setResΦ(double resΦ) {
        this.resΦ = resΦ;
    }

    private double F( double x) {
        int n = data.length - 1;
        double tmp = 0;
        for (int i = 1; i <= n; i++) {
            tmp += 1 / (double) (x - i + 1);
        }
        tmp -= n / (x - p);
        return tmp;
    }

    private double fifth(double root) {
        int n = data.length - 1;
        double N = root;
        double tmp = 0;
        for (int i = 1; i <= n; i++) {
            tmp += (i - 1) * (data[i] - data[i - 1]);
        }
        double res = n / (N * data[n] - tmp);
        resN = N;
        resΦ = res;
        return res;
    }


    private double third(double l, double r) {
        double root = (l + r) / 2;
        if (abs(r - l) < ex) {
            return fifth(root);
        } else {
            //步骤4
            if (F( root) >= -ey && F( root) <= ey) {
                return fifth(root);
            } else if (F( root) > ey) {
                l = root;
            } else {
                r = root;
            }
            return third( l, r);
        }
    }

    public void first() {
        calculatorP();
        int n = data.length - 1;
        if (p > (n - 1) / 2) {
             second(n - 1, n);
        }
    }

    private double second(double l, double r) {
        while (F(r) > ey) {
            l = r;
            r++;
        }
        if (F(r) <= -ey) {
            return third(l, r);
        } else {
            return fifth(r);
        }
    }

    private void calculatorP() {
        double tmp = 0;
        int len = data.length - 1;
        for (int i = 1; i <= len; i++) {
            tmp += (i - 1) * (data[i] - data[i - 1]);
        }
        p = tmp / data[len];
    }
}
