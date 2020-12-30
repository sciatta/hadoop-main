package com.sciatta.dev.java.example.generics;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangxiaoyu on 2019-02-17<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test
    public void testCast() {
        List l = new ArrayList();
        l.add("hello");
        String comp = (String) l.get(0);    // need cast
        assertEquals("hello", comp);

        List<String> li = new ArrayList<>();    // 泛型不需要做类型转换
        li.add("hi");
        comp = li.get(0);   // 编译时检查，泛型推断出类型
        assertEquals("hi", comp);
    }

    @Test
    public void testGenericType() {
        // Box<T> => T is type parameter
        // Box<Integer> => Integer is type argument
        Box<Integer> box = new Box<>(); // 1.7实例化参数化类型时用<>代替
        box.setContent(1);
        assertEquals(Integer.valueOf(1), box.getContent());

        class NewBox<T extends Number> extends Box<T> {

        }

        NewBox<Number> newBox = new NewBox<>();
        Box<Number> oldBox = new Box<>();

        // Box.testBox(box);    error Box<Integer>不是Box<Number>的子类
        Box.testBox(newBox);    // NewBox<Number>是Box<Number>的子类
        Box.testBox(oldBox);

        Box.testAnyBox(box);    // Box<Integer>是Box<?>的子类
        Box.testAnyBox(newBox);
        Box.testAnyBox(oldBox);
    }

    @Test
    public void testGenericMethod() {
        Pairs<String, Integer> p1 = new Pairs<>("one", 1);
        Pairs<String, Integer> p2 = new Pairs<>("one", 1);

        // assertTrue(Pairs.<String, Integer>equals(p1, p2));
        assertTrue(Pairs.equals(p1, p2));  // <String, Integer> 可以省略

        assertFalse(p1.valueLess(p2));

        p1 = new Pairs<>("one", 1);
        p2 = new Pairs<>("two", 2);

        assertTrue(p1.valueLess(p2));
        assertFalse(p2.valueLess(p1));
    }

    @Test
    public void testWildcard() {
        List<Number> ln = Arrays.asList(1, 2);
        List<Integer> li = Arrays.asList(3, 4);
        List<String> ls = Arrays.asList("a", "b");

        ProcessNumber.process(ln);
        // List<Integer>传入错误；因为Integer是Number的子类，但List<Integer>不是List<Number>的子类，类型不匹配
        // ProcessNumber.process(li);

        // 上界
        ProcessNumber.processAnyNumber(ln);
        ProcessNumber.processAnyNumber(li);

        // 无界
        ProcessNumber.processAny(ln);
        ProcessNumber.processAny(li);
        ProcessNumber.processAny(ls);

        // 下界
        List<Number> list = new ArrayList<>();
        ProcessNumber.addAnyNumber(list);
        assertEquals(10, list.size());
    }

    @Test
    public void testSubtypeWildcard() {
        class A {

        }

        class B extends A {

        }

        B b = new B();
        A a = b;    // B是A的子类

        List<B> lb = new ArrayList<>();
        // List<A> la = lb; // error List<A>和List<B>没有关系

        List<? extends A> la = lb;  // List<B>是List<? extends A>的子类型

        List<A> sa = new ArrayList<>();
        List<? super B> sb = sa;    // List<A>是List<? super B>的父类型
    }

    @Test
    public void testScenarioUsingWildcard() {
        class NumberImpl extends Number {


            private static final long serialVersionUID = 2588782116919105750L;

            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        }

        // 上界 in 只读
        List<?> noWildcard;
        List<Integer> no = new ArrayList<>();
        noWildcard = no;
        // noWildcard.add(new Object());    // error 认为Object，但实际持有的是Integer
        noWildcard.add(null);
        noWildcard.size();

        // 上界 in 只读
        List<? extends Number> upperWildcard;
        List<Integer> upper = new ArrayList<>();
        upperWildcard = upper;
        // upperWildcard.add(new NumberImpl());    // error 认为Number，但实际持有的是Integer；不可向Integer中add类型Number
        upperWildcard.add(null);
        upperWildcard.size();

        // 下界 out
        List<? super Integer> lowerWildcard;
        List<Number> lower = new ArrayList<>();
        lowerWildcard = lower;

        lowerWildcard.add(Integer.valueOf(1));  // 认为Integer，但实际是Number；Integer是Number的子类，所以可以add
    }
}