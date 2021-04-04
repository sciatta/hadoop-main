package com.sciatta.dev.java.concurrency.ref;

/**
 * Created by yangxiaoyu on 2021/4/2<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 强引用，没有引用的对象，GC会回收
 */
public class StrangeRef {
    static class StrangeInnerClass {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize invoke");
        }
    }
    
    
    public static void main(String[] args) {
        StrangeInnerClass strangeInnerClass = new StrangeInnerClass();
        strangeInnerClass = null; // help GC
        System.gc();
    }
}
