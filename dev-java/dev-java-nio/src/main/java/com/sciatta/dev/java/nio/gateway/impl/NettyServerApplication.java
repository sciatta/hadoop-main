package com.sciatta.dev.java.nio.gateway.impl;

import com.sciatta.dev.java.nio.gateway.impl.inbound.HttpInboundServer;

/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 网关入口
 */
public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";
    
    public static void main(String[] args) {
        
        // String proxyServer = System.getProperty("proxyServer", "http://localhost:8801,http://localhost:8802,http://localhost:8803"); // 后台服务地址
        
        // 单地址
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8801");
        
        String proxyPort = System.getProperty("proxyPort", "8888");  // 网关代理端口
        
        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service
        
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
