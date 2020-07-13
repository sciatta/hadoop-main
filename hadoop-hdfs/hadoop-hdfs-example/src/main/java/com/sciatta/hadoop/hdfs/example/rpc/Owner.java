package com.sciatta.hadoop.hdfs.example.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtocolProxy;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by yangxiaoyu on 2020/7/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Owner RPC客户端实现
 */
public class Owner {
    public static void main(String[] args) throws IOException {
        Dog proxy = RPC.getProxy(Dog.class, 1, new InetSocketAddress("localhost", 8888), new Configuration());

        // eat
        boolean ret = proxy.eat("meat");
        System.out.println(ret);

        // run
        proxy.run();
    }
}
