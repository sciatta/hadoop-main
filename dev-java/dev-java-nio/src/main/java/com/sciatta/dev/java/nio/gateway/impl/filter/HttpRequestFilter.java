package com.sciatta.dev.java.nio.gateway.impl.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HttpRequestFilter
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
