package com.sciatta.dev.java.nio.netty.customprotocol.server;

import com.sciatta.dev.java.nio.netty.customprotocol.protocol.RpcRequest;
import com.sciatta.dev.java.nio.netty.customprotocol.protocol.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ServerHandler
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接受client请求
        RpcRequest request = (RpcRequest) msg;  // 返回的对象和绑定的解码器一致

        // 业务处理
        System.out.println("client请求：" + request.toString());

        // 发送client响应
        RpcResponse response = new RpcResponse();
        response.setId(UUID.randomUUID().toString());
        response.setData("server响应：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        response.setStatus(1);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 接收完成
        System.out.println("服务端接收数据完毕");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 读操作时捕获到异常时调用
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端和服务端连接成功时触发
        System.out.println("收到客户端连接请求：" + ctx.channel().localAddress());
    }
}
