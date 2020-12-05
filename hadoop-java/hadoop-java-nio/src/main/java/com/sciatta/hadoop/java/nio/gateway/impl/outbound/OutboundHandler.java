package com.sciatta.hadoop.java.nio.gateway.impl.outbound;

import com.sciatta.hadoop.java.nio.gateway.impl.router.HttpEndpointRouter;
import com.sciatta.hadoop.java.nio.gateway.impl.router.impl.RandomRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Arrays;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OutboundHandler
 */
public interface OutboundHandler {
    void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx);
    
    default String router(String proxyServer) {
        HttpEndpointRouter router = new RandomRouter();
        return router.route(Arrays.asList(proxyServer.split(",")));
    }
}
