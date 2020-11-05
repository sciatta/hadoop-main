package com.sciatta.hadoop.java.nio.netty;

import com.sciatta.hadoop.java.nio.netty.client.NettyClient;
import com.sciatta.hadoop.java.nio.netty.protocol.RpcRequest;
import io.netty.channel.Channel;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ClientApp
 */
public class ClientApp {
    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient("127.0.0.1", 8080);
        client.start(); // 启动client服务

        // 通过channel发送数据
        Channel channel = client.getChannel();
        channel.writeAndFlush(buildRequest());
    }

    private static RpcRequest buildRequest() {
        RpcRequest request = new RpcRequest();
        request.setId(UUID.randomUUID().toString());
        request.setData("client.message");

        return request;
    }
}
