package com.sciatta.dev.java.concurrency.ref.weak;

import java.lang.ref.WeakReference;

/**
 * Created by yangxiaoyu on 2021/4/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 弱引用，GC看到弱引用就会回收资源
 */
public class WeakRef {
    static class WeakClass {
        private String name;
    
        public WeakClass(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("WeakClass finalize invoke");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        WeakReference<WeakClass> weakReference = new WeakReference<>(new WeakClass("test"));
        System.out.println(weakReference.get().getName());  // 弱引用可以获取被引用的对象
        System.gc();
        Thread.sleep(500);
    }
}
