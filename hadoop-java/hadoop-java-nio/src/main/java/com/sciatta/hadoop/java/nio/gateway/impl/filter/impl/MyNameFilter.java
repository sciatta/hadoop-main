package com.sciatta.hadoop.java.nio.gateway.impl.filter.impl;

import com.sciatta.hadoop.java.nio.gateway.impl.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by yangxiaoyu on 2020/11/6<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MyNameFilter
 */
public class MyNameFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("nio", "yangxiaoyu");
    }
}
