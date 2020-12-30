package com.sciatta.dev.java.nio.gateway.impl.router.impl;

import com.sciatta.dev.java.nio.gateway.impl.router.HttpEndpointRouter;

import java.util.List;
import java.util.Random;

/**
 * Created by yangxiaoyu on 2020/11/6<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 随机选择后端服务器
 */
public class RandomRouter implements HttpEndpointRouter {
    Random random = new Random();

    @Override
    public String route(List<String> endpoints) {
        int index = random.nextInt(endpoints.size());
        return endpoints.get(index);
    }
}
