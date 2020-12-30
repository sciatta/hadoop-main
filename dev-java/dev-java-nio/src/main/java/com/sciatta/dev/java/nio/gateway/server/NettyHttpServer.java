package com.sciatta.dev.java.nio.gateway.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NettyHttpServer
 */
public class NettyHttpServer {
    private String name;

    public NettyHttpServer(String name) {
        this.name = name;
    }

    public void bind(int port) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec()) // HttpRequestDecoder & HttpResponseEncoder
                                .addLast(new HttpObjectAggregator(1024 * 1024)) // FullHttpRequest & FullHttpResponse
                                .addLast(new ServerHandler());
                    }
                });

        ChannelFuture f = bootstrap.bind(port).sync();
        if (f.isSuccess()) {
            System.out.println(name + " started");
        } else {
            System.out.println(name + " start fail!");
            f.cause().printStackTrace();
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

        f.channel().closeFuture().sync();
    }

    private class ServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof FullHttpRequest) {
                FullHttpRequest req = (FullHttpRequest)msg;

                System.out.println("*********************************************************************************");
                System.out.println("接收到客户端数据：");
                System.out.println(req);
                System.out.println("*********************************************************************************");

                String message = name + " response";
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK,
                        Unpooled.wrappedBuffer(message.getBytes(StandardCharsets.UTF_8)));
                response.headers().set("Content-Type", "application/json");
                response.headers().setInt("Content-Length", response.content().readableBytes());

                ctx.writeAndFlush(response);
            }
        }
    }
}
