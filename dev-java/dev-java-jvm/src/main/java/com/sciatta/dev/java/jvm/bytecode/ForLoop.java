package com.sciatta.dev.java.jvm.bytecode;

/**
 * Created by yangxiaoyu on 2020/10/18<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ForLoop
 */
public class ForLoop {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        MovingAverage movingAverage = new MovingAverage();

        for (int number : numbers) {
            movingAverage.submit(number);
        }

        double avg = movingAverage.getAvg();
    }
}
