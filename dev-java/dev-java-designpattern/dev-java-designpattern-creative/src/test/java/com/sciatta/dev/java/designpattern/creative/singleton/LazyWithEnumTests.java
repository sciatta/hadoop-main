package com.sciatta.dev.java.designpattern.creative.singleton;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithEnumTests
 */
public class LazyWithEnumTests {
    @Test
    public void testGetInstance() {
        LazyWithEnum i1 = LazyWithEnum.instance;
        
        LazyWithEnum i2 = LazyWithEnum.instance;
        
        assertSame(i1, i2);
        
        LazyWithEnum[] values = LazyWithEnum.values();
        assertEquals(1, values.length);
        
        LazyWithEnum instance = LazyWithEnum.valueOf("instance");
        assertNotNull(instance);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        // 通过name找不到枚举，抛出异常
        LazyWithEnum instance = LazyWithEnum.valueOf("instance1");
    }
    
    @Test
    public void testSerializable() {
        LazyWithEnum i1 = LazyWithEnum.instance;
        LazyWithEnum i2 = null;
        
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(i1);    // 序列化只会输出name
            
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            i2 = (LazyWithEnum) ois.readObject();   // 反序列化通过name查找enum的静态属性values，获取枚举值（已被class缓存）
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        assertSame(i1, i2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testReflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = LazyWithEnum.class;
        Constructor c = aClass.getDeclaredConstructor(String.class, int.class);
        c.setAccessible(true);  // 枚举类的构造函数是private
        Object o = c.newInstance(null); // java.lang.IllegalArgumentException: Cannot reflectively create enum objects
    }
}
