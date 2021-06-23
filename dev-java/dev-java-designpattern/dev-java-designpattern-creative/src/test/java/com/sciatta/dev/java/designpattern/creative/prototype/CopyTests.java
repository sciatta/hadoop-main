package com.sciatta.dev.java.designpattern.creative.prototype;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CopyTests
 */
public class CopyTests {
    @Test
    public void testShallowCopy() throws CloneNotSupportedException {
        // 秒、毫秒、微秒、纳秒、皮秒、飞秒每两级之间的换算进率为1000
        long start = System.nanoTime();
        Copy origin = new Copy(1, new Copy.NumberObj(2));  // 对象创建非常耗时
        long end = System.nanoTime();
        System.out.println("create origin obj takes " + TimeUnit.NANOSECONDS.toSeconds(end - start) + "s");
        
        start = System.nanoTime();
        // x.clone() != x
        // x.clone().getClass() == x.getClass()
        //  x.clone().equals(x)
        Copy copy = (Copy) origin.clone();    // 通过clone方式获得不同的对象
        end = System.nanoTime();
        System.out.println("create shallow copy obj takes " + TimeUnit.NANOSECONDS.toSeconds(end - start) + "s");
        
        assertNotSame(origin, copy);    // 原型模式
        assertEquals(origin.getNumber(), copy.getNumber());
        assertSame(origin.getNumberObj(), copy.getNumberObj()); // 引用地址相同
    }
    
    @Test
    public void testDeepCopy() {
        long start = System.nanoTime();
        Copy origin = new Copy(1, new Copy.NumberObj(2));  // 对象创建非常耗时
        long end = System.nanoTime();
        System.out.println("create origin obj takes " + TimeUnit.NANOSECONDS.toSeconds(end - start) + "s");
        
        start = System.nanoTime();
        Copy copy = (Copy) origin.deepCopy();    // 通过序列化方式深拷贝
        end = System.nanoTime();
        System.out.println("create deep copy obj takes " + TimeUnit.NANOSECONDS.toSeconds(end - start) + "s");
        
        assertNotSame(origin, copy);    // 原型模式
        assertEquals(origin.getNumber(), copy.getNumber());
        assertNotSame(origin.getNumberObj(), copy.getNumberObj()); // 引用对象拷贝
    }
}
