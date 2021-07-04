package com.sciatta.dev.java.designpattern.behavior.strategy;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BubbleSort
 */
public class BubbleSort implements Sort{
    @Override
    public void sort() {
        System.out.println("Bubble");
    }
}
