package com.sciatta.hadoop.java.nio.netty.customprotocol.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 编码
 */
public class RpcEncoder extends MessageToByteEncoder {

    // 目标对象类型进行编码
    private Class<?> target;

    public RpcEncoder(Class target) {
        this.target = target;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (target.isInstance(msg)) {
            byte[] data = JSON.toJSONBytes(msg); // 使用fastJson将对象转换为byte
            out.writeInt(data.length); // 消息头
            out.writeBytes(data); // 消息体
        }
    }
}
