package com.sciatta.hadoop.java.nio.gateway.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/10/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于HttpClient
 */
public class UseHttpClient {
    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;

        HttpGet request = new HttpGet("http://localhost:8801");
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                System.out.println("响应长度：" + entity.getContentLength());
                System.out.println("响应内容：" + EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (response != null) {
                    response.close();
                }

                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
