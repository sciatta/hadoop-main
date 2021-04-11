package com.sciatta.dev.java.example.reflect;

/**
 * Created by yangxiaoyu on 2021/4/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * NewSlowly
 */
public class NewSlowly {
    public static long timeDiff(long old) {
        return System.currentTimeMillis() - old;
    }
    
    public static void main(String[] args) throws Exception {
        
        long numTrials = (long) Math.pow(10, 7);
        
        long millis;
        
        millis = System.currentTimeMillis();
        
        for (int i=0; i<numTrials; i++) {
            // 直接实例化
            new B();
        }
        System.out.println("Normal instaniation took: "
                + timeDiff(millis) + "ms");
        
        millis = System.currentTimeMillis();
        
        Class<B> c = (Class<B>) Class.forName("com.sciatta.dev.java.example.reflect.B");
        
        for (int i=0; i<numTrials; i++) {
            // 查找+实例化：7592ms
            // Class.forName("com.sciatta.dev.java.example.reflect.B").newInstance();
            
            // 实例化：80ms
            c.newInstance();
        }
        
        System.out.println("Reflecting instantiation took:"
                + timeDiff(millis) + "ms");
        
    }
}

class B {

}

