package com.sciatta.dev.java.example.aggregation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/4/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ArraysDemo
 */
public class ArraysDemo {
    public static void main(String[] args) {
        Integer[] its = new Integer[]{3, 2, 1};
        List<Integer> l1 = Arrays.asList(its);
        System.out.println(l1.size());    // 3
        System.out.println(l1.get(0));    // 3
        
        System.out.println();
        
        int[] is = new int[]{3, 2, 1};
        List<int[]> l2 = Arrays.asList(is); // 泛型方法，传入的参数必须是对象数组；否则，如果是基本数据类型数组，则看作是一个数组的一个对象
        System.out.println(l2.size());  // 1
        System.out.println(l2.get(0));  // 数组对象
        System.out.println(l2.get(0)[0]);   // 3
    }
}
