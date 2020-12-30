package com.sciatta.dev.java.jvm.bytecode;

/**
 * Created by yangxiaoyu on 2020/10/18<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MovingAverage
 */
public class MovingAverage {
    private int count = 0;
    private double sum = 0.0D;

    public void submit(double value) {
        this.count++;
        sum += value;
    }

    public double getAvg() {
        if (0 == count) return sum;
        return sum / count;
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        movingAverage.submit(num1);
        movingAverage.submit(num2);
        double avg = movingAverage.getAvg();
    }
}
