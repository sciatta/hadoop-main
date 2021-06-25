package com.sciatta.dev.java.designpattern.structure.proxy.dynamicproxy;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Product
 */
public class Product {
    private String id;
    
    public Product(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
