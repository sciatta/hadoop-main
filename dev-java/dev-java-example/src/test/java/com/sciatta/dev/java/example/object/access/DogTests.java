package com.sciatta.dev.java.example.object.access;


import com.sciatta.dev.java.example.object.access.impl.DogFactory;
import com.sciatta.dev.java.example.object.access.common.AbstractAnimal;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019/1/18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * DogTests
 */
public class DogTests {
    @Test
    public void testAccess() {
        // ---- class level
        // error Dog是default，非此包不得访问，包括在子包也不可以
        // AbstractAnimal dog = new Dog();

        // right DogFactory是public
        AbstractAnimal dog = DogFactory.create("lucky", 2, "white");

        // ---- member level
        // error run是protected，非子类，并且不在同一个包中
        // dog.run();

        dog.doSth();
    }
}
