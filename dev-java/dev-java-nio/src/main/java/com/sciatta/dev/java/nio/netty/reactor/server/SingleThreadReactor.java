package com.sciatta.dev.java.nio.netty.reactor.server;

import com.sciatta.dev.java.nio.netty.reactor.PrintUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Set;

/**
 * Created by yangxiaoyu on 2020/12/8<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 单Reactor，连接请求和读请求由单线程顺序处理
 */
public class SingleThreadReactor {
    public static void main(String[] args) throws IOException {
        new Reactor(8888).run();
    }
    
    static class Reactor implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;
        
        public Reactor(int port) throws IOException {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            // ServerSocket向selector注册
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 只有一个Accepter
            selectionKey.attach(new Accepter(selector, serverSocketChannel));
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();  // 阻塞
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();  // 一个或多个监听的socket响应
                    for (SelectionKey selectionKey : selectionKeys) {
                        // 分发任务 连接请求 or 读请求
                        dispatcher(selectionKey);
                        // 消费完要移除
                        selectionKeys.remove(selectionKey);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        private void dispatcher(SelectionKey selectionKey) {
            Object attachment = selectionKey.attachment();
            if (attachment instanceof Runnable) {
                Runnable task = (Runnable) attachment;
                task.run();
            }
        }
    }
    
    static class Accepter implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;
        
        public Accepter(Selector selector, ServerSocketChannel serverSocketChannel) {
            this.selector = selector;
            this.serverSocketChannel = serverSocketChannel;
        }
        
        @Override
        public void run() {
            try {
                // 已经触发连接事件，所以这里不会阻塞
                SocketChannel socketChannel = serverSocketChannel.accept();
                PrintUtil.print("客户端连接：" + socketChannel.getLocalAddress());
                
                socketChannel.configureBlocking(false);
                // Socket向selector注册
                SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                // 每个连接对应一个Worker
                selectionKey.attach(new Worker(socketChannel));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Worker implements Runnable {
        private SocketChannel socketChannel;
        private Random random = new Random();
        private int delay = 2000;
        
        public Worker(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }
        
        @Override
        public void run() {
            try {
                ByteBuffer bb = ByteBuffer.allocate(1024);
                int size = socketChannel.read(bb);
                String requestData = new String(bb.array(), StandardCharsets.UTF_8);
                PrintUtil.print("客户端请求数据：" + requestData + "(" + size + ")");
                Thread.sleep(random.nextInt(delay));
                socketChannel.write(ByteBuffer.wrap("数据收妥".getBytes(StandardCharsets.UTF_8)));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
