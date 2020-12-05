package com.sciatta.hadoop.java.nio.gateway.impl.outbound.netty;

import com.sciatta.hadoop.java.nio.gateway.impl.outbound.OutboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于netty实现，借助netty将请求转发至后台服务
 */
public class NettyOutboundHandler implements OutboundHandler {
    private String backendUrl;
    private Bootstrap b;
    private EventLoopGroup workerGroup;
    private ChannelHandlerContext sourceContext;

    public NettyOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl;

        // 构建Bootstrap
        workerGroup = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.AUTO_READ, true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline()
                        .addLast(new HttpClientCodec()) // 不要和server端混淆，同时编码和解码要和server端一致
                        .addLast(new HttpObjectAggregator(1024 * 1024))
                        .addLast(new ClientHandler());
            }
        });
    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        try {
            System.out.println("NettyOutboundHandler " + "开始执行...");
            // 连接server
            URL url = new URL(router(this.backendUrl));
            ChannelFuture future = b.connect(url.getHost(), url.getPort()).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture arg0) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("连接服务器成功");
                    } else {
                        System.out.println("连接服务器失败");
                        future.cause().printStackTrace();
                        workerGroup.shutdownGracefully(); // 关闭线程组
                    }
                }
            });

            // 转发请求
            future.channel().writeAndFlush(fullRequest);

            // 保存上下文，用于回传响应
            this.sourceContext = ctx;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof FullHttpResponse) {
                FullHttpResponse res = (FullHttpResponse) msg;
                System.out.println("接收到服务器数据：" + res.content().toString(StandardCharsets.UTF_8));
                sourceContext.writeAndFlush(res);
            }
        }
    }
}