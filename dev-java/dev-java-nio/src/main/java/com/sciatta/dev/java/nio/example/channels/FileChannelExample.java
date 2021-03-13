package com.sciatta.dev.java.nio.example.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yangxiaoyu on 2021/3/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FileChannelExample
 */
public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        String filePath = FileChannelExample.class.getClassLoader().getResource("log4j.properties").getPath();
        RandomAccessFile raf = new RandomAccessFile(filePath, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(40);
        
        int i = channel.read(buffer); // 写buffer，position为下一个待写入位置
        while (i != -1) {
            System.out.println("read byte number is " + i);
            
            buffer.flip();  // buffer切换为读模式，position置为0
            
            System.out.print("read content is ");
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }
            System.out.println();
            
            buffer.clear();
            i = channel.read(buffer);
        }
    }
    
}
