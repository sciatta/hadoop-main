package com.sciatta.dev.java.jvm.gc;

/**
 * Created by yangxiaoyu on 2021/3/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FinalizeEscapeGC<br>
 * 当对象可被回收时，会先放到回收队列中，当确要回收时调用一次finalize方法，且此对象销毁只会调用finalize方法一次
 */
public class FinalizeEscapeGC {
    private static FinalizeEscapeGC test;
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("give me a chance!");
        test = this;
    }
    
    public static void main(String[] args) throws InterruptedException {
        test = new FinalizeEscapeGC();
        
        test = null;
        System.gc();    // 触发垃圾回收
        
        Thread.sleep(500); // 等待finalize被调用
        
        if (test != null) {
            System.out.println("alive");
        } else {
            System.out.println("dead");
        }
    
        System.out.println("=============");
        // 再次测试
        test = null;
        System.gc();    // 触发垃圾回收
    
        Thread.sleep(500); // 等待finalize被调用
    
        if (test != null) {
            System.out.println("alive");
        } else {
            System.out.println("dead");
        }
    }
}
