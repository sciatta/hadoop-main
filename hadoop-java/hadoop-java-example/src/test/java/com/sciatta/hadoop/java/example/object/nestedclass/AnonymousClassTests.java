package com.sciatta.hadoop.java.example.object.nestedclass;

import com.sciatta.hadoop.java.example.object.nestedclass.anonymous.Books;
import com.sciatta.hadoop.java.example.object.nestedclass.anonymous.HelloWorldAnonymousClasses;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019/1/21<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * AnonymousClassTests
 */
public class AnonymousClassTests {
    @Test
    public void testAnonymousByClass() {
        Books books = new Books();
        books.read();
    }

    @Test
    public void testAnonymousByInterface() {
        HelloWorldAnonymousClasses helloWorldAnonymousClasses = new HelloWorldAnonymousClasses();
        helloWorldAnonymousClasses.sayHello();
    }
}
