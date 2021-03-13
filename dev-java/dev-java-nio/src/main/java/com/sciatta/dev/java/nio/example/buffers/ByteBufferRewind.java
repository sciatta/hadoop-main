package com.sciatta.dev.java.nio.example.buffers;

import java.nio.ByteBuffer;

/**
 * Created by yangxiaoyu on 2021/3/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ByteBufferRewind
 */
public class ByteBufferRewind {
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
    
        // 切换为读模式
        buffer.flip();
        
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        
        buffer.rewind();    // 重读数据
    
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
