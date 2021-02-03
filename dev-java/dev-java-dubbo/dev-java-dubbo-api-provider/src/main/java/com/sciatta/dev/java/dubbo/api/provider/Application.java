package com.sciatta.dev.java.dubbo.api.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.sciatta.dev.java.dubbo.api.DemoService;
import com.sciatta.dev.java.dubbo.api.provider.impl.DemoServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yangxiaoyu on 2021/2/2<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Application 通过 `java -jar arthas-boot.jar --telnet-port 9998 --http-port -1` 测试
 */
public class Application {
    
    public static ServiceConfig<DemoService> getService() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("test-protocol-random-port");
        
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("multicast://224.5.6.7:1234");
        
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");  // 设置协议名称
        protocol.setPort(20881);
        
        ServiceConfig<DemoService> demoService = new ServiceConfig<DemoService>();
        
        demoService.setInterface(DemoService.class);
        demoService.setRef(new DemoServiceImpl());
        demoService.setApplication(application);
        demoService.setRegistry(registry);
        demoService.setProtocol(protocol);
        
        return demoService;
    }
    
    public static void exit(ServiceConfig<DemoService> demoService) {
        System.out.println("press any key to exit...");
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        demoService.unexport();
    }
    
    public static void main(String[] args) {
        ServiceConfig<DemoService> demoService = Application.getService();
        demoService.export();
        
        Application.exit(demoService);
    }
}
