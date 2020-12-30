package com.sciatta.dev.java.nio.gateway.impl.inbound;

import com.sciatta.dev.java.nio.gateway.impl.filter.HttpRequestFilter;
import com.sciatta.dev.java.nio.gateway.impl.filter.impl.MyNameFilter;
import com.sciatta.dev.java.nio.gateway.impl.outbound.OutboundHandler;
import com.sciatta.dev.java.nio.gateway.impl.outbound.asynchttpclient.AsyncHttpClientOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HttpInboundHandler
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    private OutboundHandler handler;
    private HttpRequestFilter filter;

    public HttpInboundHandler(String proxyServer) {
        // 过滤器
        filter = new MyNameFilter();

        // 处理器
        // async httpclient
        handler = new AsyncHttpClientOutboundHandler(proxyServer);

        // httpclient
        // handler = new HttpClientOutboundHandler(proxyServer);

        // netty
        // handler = new NettyOutboundHandler(proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;

            filter.filter(fullRequest, ctx);
            handler.handle(fullRequest, ctx);// 转发请求

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}