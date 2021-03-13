package com.sciatta.dev.java.nio.example.buffers;

import java.nio.ByteBuffer;

/**
 * Created by yangxiaoyu on 2021/3/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ByteBufferClear
 */
public class ByteBufferClear {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        System.out.println(buffer); // pos=0 lim=6 cap=6
        
        buffer.put((byte) 0);
        System.out.println(buffer); // pos=1 lim=6 cap=6
    
        buffer.put((byte) 55);
        System.out.println(buffer); // pos=2 lim=6 cap=6
    
        buffer.put((byte) 99);
        System.out.println(buffer); // pos=3 lim=6 cap=6
    
        buffer.put((byte) 7);
        System.out.println(buffer); // pos=4 lim=6 cap=6
    
        System.out.println();
    
        buffer.flip();  // 切换为读模式
        System.out.println(buffer);
        
        buffer.get(); // 读取一个数据
        System.out.println(buffer);
    
        System.out.println();
        
        // 重置position为0，内部数据不变
        buffer.clear();
        System.out.println(buffer);
    }
}
