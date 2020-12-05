package com.sciatta.hadoop.java.nio.gateway.server;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Server1
 */
public class Server1 {
    public static void main(String[] args) throws InterruptedException {
        NettyHttpServer server = new NettyHttpServer("Server1");
        server.bind(8801);
    }
}
