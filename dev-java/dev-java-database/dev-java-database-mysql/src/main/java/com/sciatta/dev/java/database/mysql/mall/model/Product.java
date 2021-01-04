package com.sciatta.dev.java.database.mysql.mall.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by yangxiaoyu on 2021/1/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Product
 */
@Data
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
}
