package com.sciatta.dev.java.example.object.nestedclass.anonymous;

/**
 * Created by yangxiaoyu on 2018/4/1<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * 匿名类，类实现举例
 */
public class Books {

    private int number;

    // 定义类
    class Book {
        private String name;

        Book(String name) {
            this.name = name;
            number++;   // 可以使用外部类的私有变量
        }

        String getName() {
            return name;
        }

        public void read() {
            System.out.println(name + " is reading...");
        }
    }

    // 定义子类
    class BigBook extends Book {

        BigBook(String name) {
            super(name);
        }

        @Override
        public void read() {
            System.out.println(super.name + " is reading..., but it is so big that I have a little afraid");
        }
    }

    public void read() {
        // 实例化类
        Book nonmalBook = new Book("NORMAL");

        // 实例化子类BigBook
        Book bigBook = new BigBook("BIG");

        // 匿名类的声明和实例化同时在一个表达式中完成
        // 定义匿名类继承自Book
        Book specialBook = new Book("SPECIAL") {

            @Override
            public void read() {
                System.out.println("I am reading a special book that name is " + getName());
            }
        };

        nonmalBook.read();
        bigBook.read();
        specialBook.read();
        System.out.println("I am reading " + number + " books.");
    }
}
