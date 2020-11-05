package com.sciatta.hadoop.java.nio.netty.protocol;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcRequest
 */
public class RpcRequest {

    private String id;
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RpcRequest{" + "id='" + id + '\'' + ", data=" + data + '}';
    }
}
