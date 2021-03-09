package com.sciatta.dev.java.jvm.gc.process;

/**
 * Created by yangxiaoyu on 2021/3/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * -XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 */
public class ToOld {
    public static void main(String[] args) {
        byte[] a1, a2, a3;
        // 注意：如果Survivor中相同年龄对象的大小之和大于Survivor空间的一半，则大于等于该年龄的对象直接晋升，MaxTenuringThreshold不起作用
        a1 = new byte[MinorGC._1M / 8];
        
        a2 = new byte[4 * MinorGC._1M];
        a2 = null;
        a3 = new byte[4 * MinorGC._1M]; // eden 8m，a1（0->1），第一次gc
        
        a3 = null;
        a3 = new byte[4 * MinorGC._1M]; // 第二次gc，a1（1->2）晋升到old
    }
}
