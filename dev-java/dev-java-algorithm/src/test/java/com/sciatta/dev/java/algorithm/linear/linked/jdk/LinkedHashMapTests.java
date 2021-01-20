package com.sciatta.dev.java.algorithm.linear.linked.jdk;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/1/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedHashMapTests
 */
public class LinkedHashMapTests {
    @Test
    public void testKey() {
        // node放在比较小的数组中（默认16位），key 高16和低16异或，为了防止碰撞，使得高16位参与运算；如果碰撞，且冲突8个，则放到红黑树中，否则放到链表中
        Map<Integer, String> map = new LinkedHashMap<>();
        
        map.put(2, "2");
        map.put(4, "4");
        map.put(9, "9");
        map.put(1, "1");
        
        System.out.println(map);
    }
}
