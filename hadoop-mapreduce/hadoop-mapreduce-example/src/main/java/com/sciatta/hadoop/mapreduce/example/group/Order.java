package com.sciatta.hadoop.mapreduce.example.group;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Order
 */
public class Order implements WritableComparable<Order> {
    private String orderId;
    private String productId;
    private Double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int compareTo(Order o) {
        int test = this.orderId.compareTo(o.orderId);
        if (test == 0) {
            // 订单相同，按价格倒序排列
            test = this.price.compareTo(o.price);
            return -test;   // 倒序
        }
        return test;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(productId);
        out.writeDouble(price);
    }

    public void readFields(DataInput in) throws IOException {
        orderId = in.readUTF();
        productId = in.readUTF();
        price = in.readDouble();
    }
}
