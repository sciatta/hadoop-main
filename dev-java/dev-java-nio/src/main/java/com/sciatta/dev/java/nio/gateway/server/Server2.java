package com.sciatta.dev.java.nio.gateway.server;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Server2
 */
public class Server2 {
    public static void main(String[] args) throws InterruptedException {
        NettyHttpServer server = new NettyHttpServer("Server2");
        server.bind(8802);
    }
}
