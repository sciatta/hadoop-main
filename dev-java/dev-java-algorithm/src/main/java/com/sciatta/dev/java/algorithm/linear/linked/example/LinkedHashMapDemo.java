package com.sciatta.dev.java.algorithm.linear.linked.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by yangxiaoyu on 2021/1/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedHashMapDemo
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // node放在比较小的数组中（默认16位），key 高16和低16异或，为了防止碰撞，使得高16位参与运算；如果碰撞，且冲突8个，则放到红黑树中
        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(2,"2");
        map.put(4,"4");
        map.put(9,"9");
        map.put(1,"1");
    
        System.out.println(map);
    }
}
