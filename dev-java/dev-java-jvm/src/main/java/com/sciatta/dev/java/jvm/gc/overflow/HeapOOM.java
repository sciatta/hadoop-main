package com.sciatta.dev.java.jvm.gc.overflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/3/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * HeapOOM <br>
 * -Xms1m -Xmx1m
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        
        try {
            while (true) {
                list.add(new Object());
            }
        } finally {
            System.out.println(list.size());
        }
    }
}
