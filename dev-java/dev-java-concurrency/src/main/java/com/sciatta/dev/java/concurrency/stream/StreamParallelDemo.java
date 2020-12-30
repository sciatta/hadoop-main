package com.sciatta.dev.java.concurrency.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by yangxiaoyu on 2020/11/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * StreamParallelDemo
 */
public class StreamParallelDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 10000).forEach(i -> list.add(i));
        BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue(10000);
        List<Long> longList = list.stream().parallel()
                .map(i -> i.longValue())
                .sorted()
                .collect(Collectors.toList());

        // 并行，默认使用CPU * 2个线程
        longList.stream().parallel().forEach(
                i -> {
                    try {
                        blockingQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println("blockingQueue:" + blockingQueue.toString());
    }
}
