package com.sciatta.dev.java.nio.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于Netty
 */
public class UseNetty {
    private Channel channel;

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 构建Bootstrap
        Bootstrap b = new Bootstrap();
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

        // 连接server
        ChannelFuture future = b.connect(host, port).sync();
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

        // 保存channel，由其发送数据
        channel = future.channel();
        channel.writeAndFlush(buildRequest());
    }

    private FullHttpRequest buildRequest() {
        String message = "hello server";
        FullHttpRequest request = new DefaultFullHttpRequest(HTTP_1_1,
                HttpMethod.GET,
                "/",
                Unpooled.wrappedBuffer(message.getBytes(StandardCharsets.UTF_8)));
        request.headers().setInt("Content-Length", request.content().readableBytes());
        return request;
    }

    private class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof FullHttpResponse) {
                FullHttpResponse res = (FullHttpResponse) msg;
                System.out.println("接收到服务器数据：" + res.content().toString(StandardCharsets.UTF_8));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        UseNetty client = new UseNetty();
        client.connect("127.0.0.1", 8801);
    }
}
