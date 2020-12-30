package com.sciatta.dev.java.nio.httpserver.impl;

import com.sciatta.dev.java.nio.httpserver.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangxiaoyu on 2020/10/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 固定线程池处理请求
 */
public class ThreadPoolHttpServer extends HttpServer {
    public static void main(String[] args) {
        ThreadPoolHttpServer threadPoolHttpServer = new ThreadPoolHttpServer();
        threadPoolHttpServer.service();
    }

    @Override
    protected void service() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(40);
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                executorService.execute(() -> {
                    doService(socket, "Hello, ThreadPoolHttpServer!");
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
