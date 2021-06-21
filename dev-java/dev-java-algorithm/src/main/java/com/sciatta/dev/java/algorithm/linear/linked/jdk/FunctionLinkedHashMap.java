package com.sciatta.dev.java.algorithm.linear.linked.jdk;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/1/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FunctionLinkedHashMap
 */
public class FunctionLinkedHashMap {
    public static void main(String[] args) {
        // node放在比较小的数组中（默认16位），key 高16和低16异或，为了防止碰撞，使得高16位参与运算；
        // 解决碰撞：当链表长度大于8，但数组长度小于64位时，扩容数组，重新hash；如果数组长度大于64位时，则将链表转换为红黑树中
        Map<Integer, String> map = new LinkedHashMap<>();
    
        map.put(2, "2");
        map.put(4, "4");
        map.put(9, "9");
        map.put(1, "1");
    
        System.out.println(map);
    }
}
