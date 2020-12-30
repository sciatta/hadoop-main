package com.sciatta.dev.java.example.object.constructor;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * NewParent
 */
public class NewParent {
    // 子类继承父类，父类隐含继承Object，Object有默认的构造函数，从而自下而上，组成构造函数链
    // 1、如果没有定义构造函数，编译器自动增加默认构造函数
    // 2、如果定义构造函数，即使没有默认构造函数，编译器也不会自动增加
    // 3、子类定义的构造函数，没有明确调用父类构造函数情况下，会自动调用父类的默认构造函数；此时，如果父类没有默认构造函数，则编译错误
    // 4、子类定义的构造函数，明确调用父类的构造函数；此时，不管父类有没有默认构造函数，都合法
    NewParent() {
        System.out.println("NewParent default");
    }

    NewParent(int i) {
        System.out.println("NewParent " + i);
    }
}
