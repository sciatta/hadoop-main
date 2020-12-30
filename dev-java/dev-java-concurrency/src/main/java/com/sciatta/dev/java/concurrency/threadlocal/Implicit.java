package com.sciatta.dev.java.concurrency.threadlocal;

/**
 * Created by yangxiaoyu on 2020/11/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 隐式传递例子
 */
public class Implicit {
    private static ThreadLocal<Integer[]> local = new ThreadLocal<>();

    public int sum() {
        Integer[] integers = local.get();

        int sum = 0;
        for (Integer i : integers) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
        local.set(new Integer[]{1, 2, 3});

        Implicit implicit = new Implicit();
        int sum = implicit.sum();
        System.out.println(sum);
    }
}
