package com.sciatta.hadoop.java.nio.netty.customprotocol.protocol;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcResponse
 */
public class RpcResponse {

    private String id;
    private Object data;
    // 0=success -1=fail
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RpcResponse{" + "id='" + id + '\'' + ", data=" + data + ", status=" + status + '}';
    }
}

