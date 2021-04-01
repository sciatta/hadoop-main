package com.sciatta.dev.java.concurrency.mutable;

/**
 * Created by yangxiaoyu on 2021/3/31<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 指令重排序
 */
public class MemoryOrdering {
    private static int a, b, x, y;
    
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        
        while (true) {
            i++;
            x = 0;    // 每次运行注意重新初始化
            y = 0;
            a = 0;
            b = 0;
            
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;  // 1
            });
            
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;  // 2
            });
            
            t1.start();
            t2.start();
            
            t1.join();
            t2.join();
            
            if (i % 10000 == 0) {
                System.out.println("第" + i + "次");
            }
            
            if (x == 0 && y == 0) { // 发生指令重排序，线程先执行1和2
                System.out.println("第" + i + "次异常");
                break;
            }
        }
    }
}
