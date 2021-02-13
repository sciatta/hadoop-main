package com.sciatta.dev.java.database.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by yangxiaoyu on 2021/2/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CacheApplication
 */
@SpringBootApplication(scanBasePackages = "com.sciatta.dev.java.database.cache")
@EnableCaching  // 开启 spring cache 支持
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
