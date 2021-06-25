package com.sciatta.dev.java.designpattern.structure.proxy.dynamicproxy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ProductServiceTests
 */
public class ProductServiceTests {
    @Test
    public void testProductService() {
        ProductService productService = ProductServiceProxy.create(new Class[]{ProductService.class});
        
        Product test = productService.getProductById("test");
        assertEquals("test", test.getId());
    }
}
