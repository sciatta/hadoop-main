package com.sciatta.dev.java.nio.httpserver.impl;

import com.sciatta.dev.java.nio.httpserver.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yangxiaoyu on 2020/10/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 单线程处理请求
 */
public class SingleThreadHttpServer extends HttpServer {
    public static void main(String[] args) {
        SingleThreadHttpServer singleThreadHttpServer = new SingleThreadHttpServer();
        singleThreadHttpServer.service();
    }

    @Override
    protected void service() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                doService(socket, "Hello, SingleThreadHttpServer!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
