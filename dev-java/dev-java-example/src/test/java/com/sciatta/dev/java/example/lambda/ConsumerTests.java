package com.sciatta.dev.java.example.lambda;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by yangxiaoyu on 2021/4/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ConsumerTests
 */
public class ConsumerTests {
    @Test
    public void testConsumer() {
        Consumer<Person> consumer = System.out::println;
        
        Person person = new Person();
        person.setName("hello");
        consumer.accept(person);
    }
}
