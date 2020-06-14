package com.sciatta.hadoop.java.example.ife;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019/1/31<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * AnimalTests
 */
public class AnimalTests {
    @Test
    public void testAnimal() {
        Animal dog = new Dog();
        dog.eat();
        dog.say();

        System.out.println();

        Animal cat = new Cat();
        cat.eat();
        cat.say();
        cat.sleep();

        System.out.println();

        Animal horse = new Horse();
        horse.eat();
        horse.say();
        horse.sleep();

        System.out.println();

        Animal blackHorse = new Horse();
        horse.tame();

        System.out.println();

        Animal robotCat = new RobotCat();
        robotCat.eat();
        robotCat.say();
        robotCat.sleep();
    }

    @Test
    public void testAndroid() {
        Android android = new Android();
    }

    @Test
    public void testStaticExtends() {
        // 父接口的static方法无法被继承，只能父接口自己使用
        Parent.staticMethod();

        // 静态属性，子类可以引用
        assertEquals("1", Parent.constant);
        assertEquals("1", Child.child_constant);
        assertEquals("1", Child.constant);
        assertEquals("1", CP.constant);

        // 接口的default方法，需要由实现类的对象来使用
        new CC().defaultMethod();
    }

    interface Parent {
        public static final String constant = "1";

        //  静态方法需要实现
        static void staticMethod() {
            System.out.println("static method");
        }
    }

    static class CP implements Parent {
        // 实现类无法继承接口静态方法
    }

    interface Child extends Parent {
        public static final String child_constant = constant;

        default void defaultMethod() {
            System.out.println("default method");
        }
    }

    static class CC implements Child {

    }
}
