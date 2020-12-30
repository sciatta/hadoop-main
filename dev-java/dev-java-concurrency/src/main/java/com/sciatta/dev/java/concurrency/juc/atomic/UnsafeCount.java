package com.sciatta.dev.java.concurrency.juc.atomic;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 线程不安全的自增器
 */
public class UnsafeCount implements Count {
    private int num;

    @Override
    public void addOne() {
        num++;
    }

    @Override
    public int get() {
        return num;
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeCount count = new UnsafeCount();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count.addOne();
                }
            }).start();
        }

        Thread.sleep(1000); // 不精确
        System.out.println(count.get());
    }
}
