package com.sciatta.hadoop.java.nio.httpserver.impl;

import com.sciatta.hadoop.java.nio.httpserver.HttpServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangxiaoyu on 2020/10/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于Netty的HttpServer
 */
public class NettyHttpServer extends HttpServer {
    private static Logger logger = LoggerFactory.getLogger(NettyHttpServer.class);

    private boolean ssl;
    private int port;

    public NettyHttpServer(boolean ssl, int port) {
        this.ssl = ssl;
        this.port = port;
    }

    public static void main(String[] args) {
        NettyHttpServer nettyHttpServer = new NettyHttpServer(false, PORT);
        nettyHttpServer.service();
    }

    @Override
    protected void service() {
        SslContext sslCtx = null;
        if (ssl) {
            try {
                SelfSignedCertificate ssc = new SelfSignedCertificate();
                sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
            } catch (CertificateException | SSLException e) {
                e.printStackTrace();
            }
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(3);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1000);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInitializer(sslCtx));

            Channel ch = b.bind(port).sync().channel();
            logger.info("开启netty http服务器，监听地址和端口为 " + (ssl ? "https" : "http") + "://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class HttpInitializer extends ChannelInitializer<SocketChannel> {
        private final SslContext sslCtx;

        public HttpInitializer(SslContext sslCtx) {
            this.sslCtx = sslCtx;
        }

        @Override
        public void initChannel(SocketChannel ch) {
            ChannelPipeline p = ch.pipeline();
            if (sslCtx != null) {
                p.addLast(sslCtx.newHandler(ch.alloc()));
            }
            p.addLast(new HttpServerCodec());
            //p.addLast(new HttpServerExpectContinueHandler());
            p.addLast(new HttpObjectAggregator(1024 * 1024));
            p.addLast(new HttpHandler());
        }
    }

    private class HttpHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.flush();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                logger.info("channelRead流量接口请求开始，时间为{}", df.format(new Date()));

                FullHttpRequest fullRequest = (FullHttpRequest) msg;
                String uri = fullRequest.uri();
                logger.info("接收到的请求url为{}", uri);

                doService(fullRequest, ctx, "Hello, NettyHttpServer!");

                logger.info("channelRead流量接口请求结束，时间为{}", df.format(new Date()));
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }

    }
}
