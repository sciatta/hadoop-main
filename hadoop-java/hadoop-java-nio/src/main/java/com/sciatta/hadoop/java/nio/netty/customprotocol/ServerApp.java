package com.sciatta.hadoop.java.nio.netty.customprotocol;

import com.sciatta.hadoop.java.nio.netty.customprotocol.server.NettyServer;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ServerApp
 */
public class ServerApp {
    public static void main(String[] args) throws Exception {
        // 启动server服务
        new NettyServer().bind(8080);
    }
}
