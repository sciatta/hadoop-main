package com.sciatta.dev.java.nio.netty.reactor.server;

import com.sciatta.dev.java.nio.netty.reactor.PrintUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangxiaoyu on 2020/12/6<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * BIOServer
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ServerSocket socket = new ServerSocket(8888);
        Random random = new Random();
        int delay = 2000;
        
        while (true) {
            PrintUtil.print("等待客户端连接...");
            Socket accept = socket.accept();    // 等待连接，阻塞
            PrintUtil.print("客户端连接：" + accept.getLocalAddress());
            
            service.execute(() -> {
                boolean running = true;
                while (running) {
                    try {
                        byte[] bytes = new byte[1024];
                        PrintUtil.print("等待读取客户端数据...");
                        int state = accept.getInputStream().read(bytes);    // 等待读取数据，阻塞
                        PrintUtil.print("客户端请求数据：" + new String(bytes, StandardCharsets.UTF_8) + "(" + state + ")");
                        Thread.sleep(random.nextInt(delay));
                        accept.getOutputStream().write("数据收妥".getBytes(StandardCharsets.UTF_8));
                    } catch (IOException | InterruptedException e) {
                        if (accept != null) {
                            try {
                                accept.close();
                            } catch (IOException ioException) {
                            }
                        }
                        running = false;
                    }
                }
            });
        }
    }
}
