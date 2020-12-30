package com.sciatta.dev.java.example.object.nestedclass;

/**
 * Created by yangxiaoyu on 2019-02-27<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * LazyInitialization
 */
public class LazyInitialization {
    public static class Holder {
        // 静态块
        static {
            System.out.println("Holder class initialization");
        }

        // 实例块
        {
            System.out.println("Holder instance initialization");
        }

        private Holder() {
            System.out.println("Holder constructor initialization");
        }
    }

    // 帮助类
    private static class Helper {
        // 当调用时才会初始化静态成员holder，并且只初始化一次
        static final Holder holder = getHolder();
        static final Integer i = getInteger();

        static {
            System.out.println("Helper class initialization");
        }

        {
            System.out.println("Helper instance initialization");
        }

        private static Holder getHolder() {
            System.out.println("static member -- holder");
            return new Holder();
        }

        private static Integer getInteger() {
            System.out.println("static member -- i");
            return Integer.valueOf(0);
        }
    }

    public static Holder getSimpleHolder() {
        return new Holder();
    }

    public static Holder getHolder() {
        return Helper.holder;
    }

    public static String echo(String echo) {
        return echo;
    }
}
