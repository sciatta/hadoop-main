package com.sciatta.hadoop.java.example.object.method;

import com.sciatta.hadoop.java.example.object.method.cls.Animal;
import com.sciatta.hadoop.java.example.object.method.cls.Cat;
import com.sciatta.hadoop.java.example.object.method.cls.Dog;
import com.sciatta.hadoop.java.example.object.method.ife.*;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * MethodTests
 */
public class MethodTests {
    @Test
    public void testClass() {
        Cat cat = new Cat();
        cat.drink();    // 继承实例方法
        Cat.eat();  // 继承静态方法

        System.out.println();

        Animal dog = new Dog();
        dog.drink();    // override
        Animal.eat();   // hide
        Dog.eat();

        System.out.println();

        Animal tomato = new Cat();
        // 需要转型为Cat，才能调用其public的echo方法，因为Animal的echo方法是protected
        ((Cat) tomato).echo();
    }

    @Test
    public void testInterface() {
        Run car = new Car();
        car.run();  // 实例方法优先于接口的default方法（本质是覆盖）
        car.echo();

        System.out.println();

        Run newCar = new NewCar();
        newCar.run();   // 当多个父类型有同一个祖先时，被覆盖的默认方法忽略（本质是覆盖）

        System.out.println();

        Run runCar = new RunCar();
        runCar.run();   // 当多个父类型的默认方法冲突，要明确使用哪一个父接口的默认方法（Supertype.super.method()）

        System.out.println();

        Run.help(); // 接口中的静态方法不能被继承

        // error 不可以在子类中使用
        // NewRun.help();

        // error 不可以在实例中使用
        // car.help();
    }

}
