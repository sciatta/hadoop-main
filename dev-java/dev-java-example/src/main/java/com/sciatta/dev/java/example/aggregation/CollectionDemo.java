package com.sciatta.dev.java.example.aggregation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoyu on 2020/12/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CollectionDemo
 */
public class CollectionDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 4, 7, 10, 6, 4, 3, 1, 6);
        list.forEach(System.out::println);  // 对象方法引用
        list.forEach(CollectionDemo::print); // 静态方法引用
        print(list);
        
        Collections.sort(list); // 排序
        print(list);
        
        Collections.reverse(list);  // 反转
        print(list);
        
        Collections.shuffle(list); // 洗牌
        print(list);
        
        int num = Collections.frequency(list, 6);    // 集合中获取指定元素的数量
        print(num);
        
        num = Collections.max(list);    // 最大值
        print(num);
        
        Collections.fill(list, 8); // 使用指定元素替换集合中的所有元素
        print(list);
        
        list = Collections.singletonList(99); // 包含指定元素的不可变集合
        print(list);
    }
    
    public void print() {
    
    }
    
    private static void print(Integer i) {
        System.out.println(i);
    }
    
    private static void print(List<Integer> list) {
        System.out.println(String.join(",", list.stream().map(Object::toString).collect(Collectors.toList())));
    }
}
