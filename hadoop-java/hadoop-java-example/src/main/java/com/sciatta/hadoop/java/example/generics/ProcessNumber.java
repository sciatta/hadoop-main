package com.sciatta.hadoop.java.example.generics;

import java.util.List;

/**
 * Created by yangxiaoyu on 2019-02-18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ProcessNumber
 */
public class ProcessNumber {
    public static void process(List<Number> list) {
        for (Number item : list) {
            item.toString();
        }
    }

    public static void processAnyNumber(List<? extends Number> list) {
        for (Number item : list) {
            item.toString();
        }
    }

    public static void processAny(List<?> list) {
        for (Object item : list) {
            item.toString();
        }
    }

    public static void addAnyNumber(List<? super Integer> list) {
        for (int i = 0; i < 10; ) {
            list.add(i++);
        }
    }
}
