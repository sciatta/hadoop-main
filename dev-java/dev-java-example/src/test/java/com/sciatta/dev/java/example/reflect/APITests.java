package com.sciatta.dev.java.example.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-06-06<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {

    @Test
    public void testClass() {
        Animal dog = new Dog();

        Class<?> test = dog.getClass(); // object.getClass() => Class
        assertSame(Dog.class, test);

        test = Dog.class;
        assertSame(Dog.class, test);    // type.class => Class

        try {
            test = Class.forName("com.sciatta.dev.java.example.reflect.Dog"); // Class.forName() => Class
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        assertSame(Dog.class, test);
    }

    @Test
    public void testGetClasses() {
        Dog dog = new Dog();

        // 获得内部类
        Class<?>[] ss = dog.getClass().getClasses();    // 类的所有内部public class成员，包括继承来的public class成员
        System.out.println(Arrays.toString(ss));

        ss = dog.getClass().getDeclaredClasses();
        System.out.println(Arrays.toString(ss));    // 类所有声明的类成员，包括私有。不包括继承来的

        System.out.println();

        System.out.println(Arrays.toString(dog.property.getClass().getClasses()));
        assertNull(dog.property.getClass().getEnclosingClass());    // 不是匿名类没有包含类

        System.out.println(dog.anonymousProperty.getClass().getEnclosingClass());   // 获取匿名类的包含类（即外部类）
    }

    @Test
    public void testModifier() {
        Dog dog = new Dog();
        System.out.println(dog.getClass().getCanonicalName());  // 完全限定名称

        System.out.println();

        System.out.println(Modifier.toString(dog.getClass().getModifiers()));   // 修饰符

        System.out.println();

        // 泛型类型
        for (TypeVariable tv : dog.getClass().getTypeParameters()) {
            System.out.println(tv.getName());
        }

        System.out.println();

        for (Type t : dog.getClass().getGenericInterfaces()) {
            System.out.println(t.getTypeName());
        }
    }

    @Test
    public void testAccessPrivateField() throws NoSuchFieldException, IllegalAccessException {
        Field eggs = Dog.class.getDeclaredField("eggs");
        // 设置可以访问私有属性
        eggs.setAccessible(true);
        Object o = eggs.get(new Dog<String, String>());
        System.out.println(o.toString());
    }
}
