package com.sciatta.dev.java.nio.example.buffers;

import java.nio.ByteBuffer;

/**
 * Created by yangxiaoyu on 2021/3/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ByteBufferCompact
 */
public class ByteBufferCompact {
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
    
        // 读取两个数据
        buffer.get();
        System.out.println(buffer);
        buffer.get();
        System.out.println(buffer);
    
        System.out.println();
        
        // 重置position为0，内部数据不变
        buffer.compact();
        System.out.println(buffer);
    
        buffer.put((byte) 10);
        System.out.println(buffer);
    
        buffer.flip();  // 切换为读模式
        
        System.out.println();
        
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
