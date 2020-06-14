package com.sciatta.hadoop.java.example.exception;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2019-02-21<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test(expected = Exception.class)
    public void testCheckedException() throws Exception {
        // throw new Exception();  // checked exception
        throw new IOException();
    }

    @Test(expected = RuntimeException.class)
    public void testRuntimeException() {
        // throw new RuntimeException();   // unchecked exception
        throw new IndexOutOfBoundsException();
    }

    @Test(expected = Error.class)
    public void testError() {
        throw new Error();  // unchecked exception
    }

    @Test
    public void testTryCatchBlock() {
        try {
            int a = 1 / 0;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("clean up");
        }
    }

    @Test
    public void testTryWithResource() {
        class EchoException extends Exception {

        }
        class CloseException extends Exception {

        }
        class Resource implements AutoCloseable {
            public void echo(String echo) throws Exception {
                // System.out.println(echo);
                throw new EchoException();
            }

            @Override
            public void close() throws Exception {
                // System.out.println("auto clean up");
                throw new CloseException();
            }
        }
        try (Resource r = new Resource()) {
            // 执行echo后，自动执行AutoCloseable接口的close方法
            // 执行echo异常，close方法可以正常执行，之后处理异常
            // 执行echo正常，在执行close方法时出现异常，可以正常catch到close抛出的异常
            // 执行echo异常，在执行close方法时也出现异常，close的异常被作为echo异常的Suppressed Exception
            // since 1.7
            r.echo("hello");
        } catch (Exception e) {
            System.out.println(e.getClass());
            // Suppressed Exception 被抑制的异常
            int n = e.getSuppressed().length;
            for (int i = 0; i < n; ) {
                System.out.println(e.getSuppressed()[i++].getClass());
            }
        } finally {
            System.out.println("executed finish");
        }
    }

    @Test
    public void testSuppressedException() {
        class A extends Exception {

        }
        class B extends Exception {

        }
        try {
            // 1 try block 先抛出异常A，然后finally抛出异常B，最后只能接收到B异常，A异常消失
            // 2 利用Suppressed Exception，将finally抛出异常B作为A异常的Suppressed，后续处理时根据情况处理异常按逻辑恢复
            A a = new A();
            B b = new B();
            try {
                throw a;
            } finally {
                a.addSuppressed(b);
                throw a;
            }
        } catch (Exception e) {
            System.out.println("exception:" + e.getClass());
            int n = e.getSuppressed().length;
            for (int i = 0; i < n; ) {
                System.out.println("suppressed exception:" + e.getSuppressed()[i++].getClass());
            }
        }

    }
}
