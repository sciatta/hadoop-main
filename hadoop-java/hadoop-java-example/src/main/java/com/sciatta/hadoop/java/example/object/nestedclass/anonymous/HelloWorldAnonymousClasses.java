package com.sciatta.hadoop.java.example.object.nestedclass.anonymous;

/**
 * Created by yangxiaoyu on 2018/4/1<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * 匿名类，实现接口两个方法以上适用；如果只有一个方法可以使用lambda
 */
public class HelloWorldAnonymousClasses {
    // 定义一个接口
    interface HelloWorld {
        void greet();

        void greetSomeone(String someone);
    }

    public void sayHello() {

        // 实现接口
        class EnglishGreeting implements HelloWorld {
            private String name = "world";

            public void greet() {
                greetSomeone("world");
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
        // 实例化
        HelloWorld englishGreeting = new EnglishGreeting();

        // 匿名类表达式直接实例化，没有类名
        HelloWorld frenchGreeting = new HelloWorld() {
            private String name = "tout le monde";

            public void greet() {
                greetSomeone("tout le monde");
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };

        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";

            public void greet() {
                greetSomeone("mundo");
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
        spanishGreeting.greet();
    }
}
