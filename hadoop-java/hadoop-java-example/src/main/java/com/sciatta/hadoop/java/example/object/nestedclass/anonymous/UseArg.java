package com.sciatta.hadoop.java.example.object.nestedclass.anonymous;

/**
 * Created by yangxiaoyu on 2020/7/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UseArg
 */
public class UseArg {
    public static class In {
        private String info;

        public In(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    // 内部接口都是static的
    public interface Out {
        String getInfo();
    }

    public Out process(In in) {
        // 匿名类的声明和实例化在同一个表达式中，可以使用局部变量
        return new Out() {
            @Override
            public String getInfo() {
                return in.info;
            }
        };
    }

    public Out echo(String s) {
        return () -> s;
    }
}
