package com.sciatta.dev.java.example.aggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/4/10<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * RemoveDemo
 */
public class RemoveDemo {
    private static void hasOncurrentModificationException() throws InterruptedException {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1, 2, 3, 4}));
        // 查：ArrayList的iterator逻辑：modCount != expectedModCount抛出ConcurrentModificationException异常，说明其他线程修改了数据结构；
        //      而当调用iterator的remove方法时，会更新modCount和expectedModCount
        // 增、删：ArrayList内部的add，remove，clear会修改modCount
        // 线程一：增、删会改变内部数组内容；线程二：遍历，如果不检查modCount的话，如线程一clear(不是原子操作)，线程二遍历有数据，但获取到的是null
        
        // 多线程下仍会出现ConcurrentModificationException异常，因为iterator调用会生成一个新的Itr对象，拥有自己的expectedModCount
        // 当另一个线程修改了公共的modCount，则会出现ConcurrentModificationException异常
    
        Iterator<Integer> iterator = list.iterator();
        
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (iterator) {
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            
            synchronized (iterator) {
                while (iterator.hasNext()) {
                    if (Integer.valueOf(2).equals(iterator.next())) {
                        // 出现ConcurrentModificationException，不会更新iterator的expectedModCount
                        // list.remove(Integer.valueOf(2));
                        
                        // 单线程下会更新expectedModCount
                        iterator.remove();
                    }
                }
                System.out.println(list);
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
    
    public static void main(String[] args) throws InterruptedException {
        hasOncurrentModificationException();
    }
}
