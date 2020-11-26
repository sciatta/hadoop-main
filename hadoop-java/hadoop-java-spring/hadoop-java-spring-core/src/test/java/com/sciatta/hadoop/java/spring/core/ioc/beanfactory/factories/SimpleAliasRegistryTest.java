package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.factories;

import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.core.SimpleAliasRegistry;

/**
 * Created by yangxiaoyu on 2018/10/4<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * SimpleAliasRegistryTest
 */
public class SimpleAliasRegistryTest {

    @Test
    public void testAlias() {
        SimpleAliasRegistry registry = new SimpleAliasRegistry();

        registry.registerAlias("A", "A1");
        registry.registerAlias("A", "A2");
        registry.registerAlias("A", "A3");

        assertArrayEquals(new String[]{"A1", "A2", "A3"}, registry.getAliases("A"));
    }

    @Test(expected = IllegalStateException.class)
    public void testAliasCircle() {
        SimpleAliasRegistry registry = new SimpleAliasRegistry();

        // A -> A1
        registry.registerAlias("A", "A1");
        registry.registerAlias("A", "A2");
        registry.registerAlias("A", "A3");

        // 循环引用
        // A1 -> A
        registry.registerAlias("A1", "A");
    }

    @Test(expected = IllegalStateException.class)
    public void testAliasCircleRecursion() {
        SimpleAliasRegistry registry = new SimpleAliasRegistry();

        registry.registerAlias("A", "A1");
        registry.registerAlias("A", "A2");
        registry.registerAlias("A", "A3");

        // A -> A1 -> B1
        registry.registerAlias("A1", "B1");

        // 循环引用
        // B1 -> A
        registry.registerAlias("B1", "A");
    }

    @Test
    public void testSameAlias() {
        SimpleAliasRegistry registry = new SimpleAliasRegistry();

        // A has three alias names, A1, A2, A3, and A is a bean;
        registry.registerAlias("A", "A1");
        registry.registerAlias("A", "A2");
        registry.registerAlias("A", "A3");

        assertEquals(3, registry.getAliases("A").length);

        // when A1 is a bean also that alias is A1, so remove alias A1 from bean A
        registry.registerAlias("A1", "A1");

        assertEquals(2, registry.getAliases("A").length);
        assertEquals(0, registry.getAliases("A1").length);
    }

    @Test
    public void retrieveAlias() {
        SimpleAliasRegistry registry = new SimpleAliasRegistry();

        registry.registerAlias("A", "B");
        registry.registerAlias("B", "C");
        registry.registerAlias("C", "D");

        assertArrayEquals(new String[]{"B", "C", "D"}, registry.getAliases("A"));
    }
}
