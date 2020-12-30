package com.sciatta.dev.java.example.object.nestedclass;

import com.sciatta.dev.java.example.object.nestedclass.anonymous.Books;
import com.sciatta.dev.java.example.object.nestedclass.anonymous.HelloWorldAnonymousClasses;
import com.sciatta.dev.java.example.object.nestedclass.anonymous.UseArg;
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

    @Test
    public void testUseArg() {
        UseArg useArg = new UseArg();
        UseArg.In in = new UseArg.In("hello");

        UseArg.Out out = useArg.process(in);
        System.out.println(out.getInfo());

        out = useArg.echo("hi");
        System.out.println(out.getInfo());
    }
}
