package com.sciatta.hadoop.java.nio.gateway.impl.router;

import java.util.List;

/**
 * Created by yangxiaoyu on 2020/11/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HttpEndpointRouter
 */
public interface HttpEndpointRouter {
    String route(List<String> endpoints);
}