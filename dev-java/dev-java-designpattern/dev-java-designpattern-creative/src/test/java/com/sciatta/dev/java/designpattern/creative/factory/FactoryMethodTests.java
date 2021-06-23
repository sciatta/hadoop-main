package com.sciatta.dev.java.designpattern.creative.factory;

import com.sciatta.dev.java.designpattern.creative.factory.product.Animal;
import com.sciatta.dev.java.designpattern.creative.factory.product.Cat;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FactoryMethodTests
 */
public class FactoryMethodTests {
    @Test
    public void testCreate() {
        Animal animal = FactoryMethod.create(FactoryMethod.CAT);
        assertTrue(animal instanceof Cat);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        Animal animal = FactoryMethod.create("robot");
    }
}
