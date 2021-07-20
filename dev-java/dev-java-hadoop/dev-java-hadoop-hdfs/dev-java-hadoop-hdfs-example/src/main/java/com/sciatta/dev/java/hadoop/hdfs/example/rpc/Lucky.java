package com.sciatta.dev.java.hadoop.hdfs.example.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/7/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Lucky RPC服务端实现
 */
public class Lucky implements Dog {
    @Override
    public boolean eat(String sth) {
        System.out.println(sth + " eating...");
        return true;
    }

    @Override
    public void run() {
        System.out.println("run...");
    }

    public static void main(String[] args) throws IOException {
        // 默认RPC引擎WritableRpcEngine
        RPC.Server server = new RPC.Builder(new Configuration())
                .setProtocol(Dog.class) // 协议接口
                .setInstance(new Lucky())   // 协议实现实例
                .setBindAddress("localhost")
                .setPort(8888)
                .build();

        server.start();
    }
}
