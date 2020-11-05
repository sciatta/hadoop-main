package com.sciatta.hadoop.java.nio.netty.client;

import com.sciatta.hadoop.java.nio.netty.protocol.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ClientHandler
 */
public class ClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        // 处理服务端返回的数据
        System.out.println("接受到server响应数据：" + response.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

