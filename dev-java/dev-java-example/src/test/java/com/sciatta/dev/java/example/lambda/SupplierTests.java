package com.sciatta.dev.java.example.lambda;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.function.Supplier;

/**
 * Created by yangxiaoyu on 2021/4/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SupplierTests
 */
public class SupplierTests {
    @Test
    public void testSupplier() {
        Supplier<Person> supplier = Person::new;
        Person person = supplier.get();
        String name = "hello";
        person.setName(name);
        assertEquals(name, person.getName());
    }
}
