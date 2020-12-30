package com.sciatta.dev.java.nio.netty.customprotocol.server;

import com.sciatta.dev.java.nio.netty.customprotocol.protocol.RpcDecoder;
import com.sciatta.dev.java.nio.netty.customprotocol.protocol.RpcEncoder;
import com.sciatta.dev.java.nio.netty.customprotocol.protocol.RpcRequest;
import com.sciatta.dev.java.nio.netty.customprotocol.protocol.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于Netty的Server实现
 */
public class NettyServer {
    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // bossGroup即parentGroup，负责处理TCP/IP连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // workerGroup即childGroup，负责处理Channel(通道)的I/O事件

        // 构建ServerBootstrap
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128) // 初始化服务端可连接队列，指定队列的大小128
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 保持长连接
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 绑定客户端连接时触发操作
                    @Override
                    protected void initChannel(SocketChannel sh) throws Exception {
                        sh.pipeline()
                                .addLast(new RpcDecoder(RpcRequest.class)) // 绑定解码器
                                .addLast(new RpcEncoder(RpcResponse.class)) // 绑定编码器
                                .addLast(new ServerHandler()); // 使用ServerHandler类来处理接收到的消息
                    }
                });

        // 绑定监听端口，调用sync同步阻塞方法等待绑定操作完
        ChannelFuture future = sb.bind(port).sync();

        if (future.isSuccess()) {
            System.out.println("服务端启动成功");
        } else {
            System.out.println("服务端启动失败");
            future.cause().printStackTrace();
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

        // 成功绑定到端口之后，给channel增加一个管道关闭的监听器并同步阻塞，直到channel关闭，线程才会往下执行结束进程
        future.channel().closeFuture().sync();
    }
}

