package com.sciatta.dev.java.designpattern.behavior.iterator;

import org.junit.Test;

import java.util.Iterator;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MyCollectionTests
 */
public class MyCollectionTests {
    @Test
    public void testIterator() {
        MyCollection<Integer> collection = getCollection();
        
        Iterator<Integer> iterator = collection.iterator();
        
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }
    }
    
    private MyCollection<Integer> getCollection() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.addData(1);
        collection.addData(2);
        collection.addData(3);
        return collection;
    }
}
