package com.sciatta.hadoop.java.jvm.gc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by yangxiaoyu on 2020/10/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * GCLogAnalysis
 */
public class GCLogAnalysis {
    private Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        GCLogAnalysis gcLogAnalysis = new GCLogAnalysis();
        // 解析参数
        Args parsedArgs = gcLogAnalysis.parseArgs(args);
        // 生成垃圾
        gcLogAnalysis.generateGarbage(parsedArgs);
    }

    public void generateGarbage(Args args) {
        LongAdder counter = new LongAdder();
        Object[] cacheGarbage = new Object[args.cacheSize]; // 缓存一部分长期存在的对象，进入老年代

        long startTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.SECONDS.toMillis(args.during);

        System.out.println("正在执行...");

        while (System.currentTimeMillis() < endTime) {
            Object garbage = doGenerateGarbage(args.garbageSize);
            counter.increment();
            int randomIndex = random.nextInt(2 * args.cacheSize);
            // 三类对象：1、首次被缓存；2、替换已存在缓存（原缓存被回收）；3、被丢弃（被回收）
            if (randomIndex < args.cacheSize) {
                cacheGarbage[randomIndex] = garbage;
            }
        }

        System.out.println("执行结束！生成对象垃圾的次数是: " + counter.longValue());
    }

    private Object doGenerateGarbage(int maxSize) {
        // 生成垃圾的大小给予一定的随机性
        int randomSize = random.nextInt(maxSize);
        int type = randomSize % 4;
        Object result;

        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
            case 2:
                result = new double[randomSize];
            default:
                StringBuilder sb = new StringBuilder();
                String str = "randomString=nothing=";
                while (sb.length() < randomSize) {
                    sb.append(str);
                    sb.append(sb.length());
                    sb.append(str);
                }
                result = sb.toString();
                break;
        }
        return result;
    }

    public Args parseArgs(String[] args) {
        Args ret = new Args();

        try {
            if (args.length != Args.ARGS_NUM) {
                throw new Exception("参数不合法，个数是" + args.length);
            }

            ret.during = Long.parseLong(args[0]);
            ret.cacheSize = Integer.parseInt(args[1]);
            ret.garbageSize = Integer.parseInt(args[2]);

            System.out.println(ret.helpTips(ret));

        } catch (Exception e) {
            // 给予提示
            System.out.println(e.getMessage());
            System.out.println(ret.helpTips(null));
            System.exit(0);
        }

        return ret;
    }

    private static class Args {
        private static final int ARGS_NUM = 3;
        private long during;    // 运行时间，单位：秒
        private int cacheSize;  // 缓存垃圾数量
        private int garbageSize;    // 垃圾大小，单位：字节

        public String helpTips(Args args) {
            String tips;

            if (args == null) {
                tips = "参数：运行时间（秒） 缓存垃圾数量（个） 垃圾大小（字节）";
            } else {
                tips = String.format("参数：运行时间%d秒 缓存垃圾数量%d个 垃圾大小%d字节", args.during, args.cacheSize, args.garbageSize);
            }

            return tips;
        }
    }
}
