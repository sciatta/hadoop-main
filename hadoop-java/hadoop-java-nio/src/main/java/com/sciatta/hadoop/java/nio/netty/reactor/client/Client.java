package com.sciatta.hadoop.java.nio.netty.reactor.client;

import com.sciatta.hadoop.java.nio.netty.reactor.PrintUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Client
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8888));
            PrintUtil.print("客户端连接到服务端");
            
            Runnable r = () -> {
                while (true) {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        PrintUtil.print("等待读取服务端数据...");
                        inputStream.read(bytes);    // 阻塞
                        PrintUtil.print("服务端响应数据：" + new String(bytes, StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.start();
    
            manual(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void manual(Socket socket) throws IOException {
        Scanner scanner = new Scanner(System.in);
        PrintUtil.print("等待控制台输入数据...");
        while (scanner.hasNextLine()) { // 等待控制台输入阻塞
            String s = scanner.nextLine();
            if (s.equals("bye")) {
                socket.close(); // 关闭客户端连接
                System.exit(0);
            }
            PrintUtil.print("向服务端写入数据：" + s);
            socket.getOutputStream().write(s.getBytes());
            
            PrintUtil.print("等待控制台输入数据...");
        }
    }
}
