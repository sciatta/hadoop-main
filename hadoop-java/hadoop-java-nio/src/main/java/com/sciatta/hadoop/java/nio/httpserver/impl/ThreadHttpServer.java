package com.sciatta.hadoop.java.nio.httpserver.impl;

import com.sciatta.hadoop.java.nio.httpserver.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yangxiaoyu on 2020/10/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 多线程处理请求，每一个请求由一个独立的线程处理
 */
public class ThreadHttpServer extends HttpServer {
    public static void main(String[] args) {
        ThreadHttpServer threadHttpServer = new ThreadHttpServer();
        threadHttpServer.service();
    }

    @Override
    protected void service() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread() {
                    @Override
                    public void run() {
                        doService(socket, "Hello, ThreadHttpServer!");
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
