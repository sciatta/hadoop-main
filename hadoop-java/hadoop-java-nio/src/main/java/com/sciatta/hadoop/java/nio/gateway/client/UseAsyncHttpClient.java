package com.sciatta.hadoop.java.nio.gateway.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yangxiaoyu on 2020/12/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UseAsyncHttpClient
 */
public class UseAsyncHttpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        CloseableHttpAsyncClient client = HttpAsyncClients.custom().build();
        client.start();
        
        CountDownLatch latch = new CountDownLatch(1);
        
        HttpGet request = new HttpGet("http://localhost:8888");
        
        client.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse response) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try {
                        System.out.println("响应长度：" + entity.getContentLength());
                        System.out.println("响应内容：" + EntityUtils.toString(entity));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            }
            
            @Override
            public void failed(final Exception ex) {
                try {
                    request.abort();
                    ex.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
            
            @Override
            public void cancelled() {
                try {
                    request.abort();
                } finally {
                    latch.countDown();
                }
            }
        });
        
        latch.await();
        client.close();
    }
}
