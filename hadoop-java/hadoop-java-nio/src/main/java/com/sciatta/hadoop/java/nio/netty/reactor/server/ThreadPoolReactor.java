package com.sciatta.hadoop.java.nio.netty.reactor.server;

import com.sciatta.hadoop.java.nio.netty.reactor.PrintUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangxiaoyu on 2020/12/8<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 单Reactor，连接请求由主线程处理，每一个socket的读请求由线程池处理
 */
public class ThreadPoolReactor {
    public static void main(String[] args) {
        Reactor reactor = new Reactor(8888);
        reactor.run();
    }
    
    static class Reactor implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;
        
        public Reactor(int port) {
            try {
                serverSocketChannel = ServerSocketChannel.open();
                selector = Selector.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(port));
                serverSocketChannel.configureBlocking(false);
                SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                selectionKey.attach(new Accepter(selector, serverSocketChannel));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        dispatcher(iterator.next());
                        iterator.remove();
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
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private ExecutorService pool;
        
        public Accepter(Selector selector, ServerSocketChannel serverSocketChannel) {
            this.selector = selector;
            this.serverSocketChannel = serverSocketChannel;
            this.pool = Executors.newFixedThreadPool(3);
        }
        
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                PrintUtil.print("客户端连接：" + socketChannel.getLocalAddress());
                socketChannel.configureBlocking(false);
                SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                selectionKey.attach(new Worker(socketChannel, pool));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Worker implements Runnable {
        private SocketChannel socketChannel;
        private ExecutorService pool;
        
        public Worker(SocketChannel socketChannel, ExecutorService pool) {
            this.socketChannel = socketChannel;
            this.pool = pool;
        }
        
        @Override
        public void run() {
            try {
                PrintUtil.print("客户端读请求：" + socketChannel.getLocalAddress());
                ByteBuffer bb = ByteBuffer.allocate(1024);
                // 必须要先消费数据，然后再提交线程池处理，否则异步read，会导致select不会阻塞
                int size = socketChannel.read(bb);
                pool.execute(new Processor(socketChannel, bb, size));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Processor implements Runnable {
        private SocketChannel socketChannel;
        private ByteBuffer byteBuffer;
        private int size;
        private Random random = new Random();
        private int delay = 2000;
        
        public Processor(SocketChannel socketChannel, ByteBuffer byteBuffer, int size) {
            this.socketChannel = socketChannel;
            this.byteBuffer = byteBuffer;
            this.size = size;
        }
        
        @Override
        public void run() {
            try {
                String requestData = new String(this.byteBuffer.array(), StandardCharsets.UTF_8);
                PrintUtil.print("客户端请求数据：" + requestData + "(" + this.size + ")");
                Thread.sleep(random.nextInt(delay));
                socketChannel.write(ByteBuffer.wrap("数据收妥".getBytes(StandardCharsets.UTF_8)));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
