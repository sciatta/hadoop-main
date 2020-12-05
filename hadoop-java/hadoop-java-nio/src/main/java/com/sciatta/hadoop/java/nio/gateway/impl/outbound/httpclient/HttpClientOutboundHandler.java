package com.sciatta.hadoop.java.nio.gateway.impl.outbound.httpclient;

import com.sciatta.hadoop.java.nio.gateway.impl.outbound.OutboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HttpClientOutboundHandler
 */
public class HttpClientOutboundHandler implements OutboundHandler {
    private CloseableHttpClient client;
    private String backendUrl;

    public HttpClientOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl;
        this.client = HttpClientBuilder.create().build();
    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("HttpClientOutboundHandler " + "开始执行...");
        CloseableHttpResponse response = null;
        try {
            final String url = router(this.backendUrl) + fullRequest.uri(); // 增加路由功能
            HttpGet get = new HttpGet(url);
            get.addHeader("nio", fullRequest.headers().get("nio"));

            // client -> gateway server -> gateway client -> server
            response = client.execute(get);

            HttpEntity entity = response.getEntity();
            FullHttpResponse result = new DefaultFullHttpResponse(HTTP_1_1,
                    OK,
                    Unpooled.wrappedBuffer(EntityUtils.toString(entity).getBytes(StandardCharsets.UTF_8)));
            result.headers().set("Content-Type", "application/json");
            result.headers().setInt("Content-Length", result.content().readableBytes());

            ctx.writeAndFlush(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
