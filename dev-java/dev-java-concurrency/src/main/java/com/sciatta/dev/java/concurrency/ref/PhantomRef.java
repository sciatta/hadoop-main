package com.sciatta.dev.java.concurrency.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by yangxiaoyu on 2021/4/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 虚引用，GC看到虚引用对象就会回收，永远无法获取到引用的对象，唯一的作用就是引用的对象GC回收前，会将虚引用放到队列并通知客户端进行处理
 */
public class PhantomRef {
    static class Buffer {
    }
    
    static class BufferRef extends PhantomReference<Buffer> {
        private Runnable task;
        private static ReferenceQueue<Buffer> q = new ReferenceQueue<>();
        
        public BufferRef(Buffer referent, ReferenceQueue<? super Buffer> q) {
            super(referent, q);
        }
        
        public BufferRef(Buffer referent, Runnable task) {
            this(referent, q);
            this.task = task;
            monitor(q);
        }
        
        @Override
        public void clear() {
            if (this.task != null) {
                task.run();
            }
            super.clear();
        }
    }
    
    private static void monitor(ReferenceQueue<Buffer> queue) {
        new Thread(() -> {
            while (true) {
                Reference<? extends Buffer> bufferRef = null;
                bufferRef = queue.poll();
                
                if (bufferRef != null) {    // 垃圾回收虚引用
                    System.out.println("包裹对象回收，PhantomReference进入队列待处理...");
                    bufferRef.clear();
                }
            }
        }).start();
        
    }
    
    public void execute() {
        Buffer buffer = new Buffer();
        BufferRef bufferRef = new BufferRef(buffer, () -> {
            System.out.println("开始清理...");
        });
        
        System.out.println("虚引用不可get=" + bufferRef.get());    // null
        
        buffer = null;  // 取消强引用
        System.gc();    // 回收虚引用
    }
    
    public static void main(String[] args) throws InterruptedException {
        PhantomRef phantomRef = new PhantomRef();
        phantomRef.execute();
    }
}
