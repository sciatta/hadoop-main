package com.sciatta.dev.java.nio.gateway.server;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Server3
 */
public class Server3 {
    public static void main(String[] args) throws InterruptedException {
        NettyHttpServer server = new NettyHttpServer("Server3");
        server.bind(8803);
    }
}
