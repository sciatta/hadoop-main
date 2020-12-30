package com.sciatta.dev.java.nio.netty.customprotocol.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by yangxiaoyu on 2020/11/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 解码
 */
public class RpcDecoder extends ByteToMessageDecoder {

    // 目标对象类型进行解码
    private Class<?> target;

    public RpcDecoder(Class target) {
        this.target = target;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) { // 消息头是int类型，占用4字节；不符合协议，丢弃
            return;
        }
        in.markReaderIndex(); // 标记当前readIndex的位置
        int dataLength = in.readInt(); // 读取消息头；ByteBuf的readInt()方法会使readerIndex增加4

        // 读到的消息体长度小于消息头预设的消息长度，则resetReaderIndex。配合markReaderIndex使用，可以把readerIndex重置到mark的地方
        if (in.readableBytes() < dataLength) {
            // 没收全
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = JSON.parseObject(data, target); // 将byte数据转化为对象
        out.add(obj);
    }
}

