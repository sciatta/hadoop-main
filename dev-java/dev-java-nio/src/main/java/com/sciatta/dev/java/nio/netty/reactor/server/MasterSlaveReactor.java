package com.sciatta.dev.java.nio.netty.reactor.server;

import com.sciatta.dev.java.nio.netty.reactor.PrintUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangxiaoyu on 2020/12/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于主从Reactor，MasterReactor负责处理连接请求，SlaveReactor负责处理读请求；处理读请求消耗时间远大于连接请求
 */
public class MasterSlaveReactor {
    public static void main(String[] args) {
        MasterReactor masterReactor = new MasterReactor(8888);
        masterReactor.run();
    }
    
    static class MasterReactor implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;
        
        public MasterReactor(int port) {
            try {
                this.selector = Selector.open();
                this.serverSocketChannel = ServerSocketChannel.open();
                this.serverSocketChannel.socket().bind(new InetSocketAddress(port));
                this.serverSocketChannel.configureBlocking(false);
                SelectionKey selectionKey = this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                selectionKey.attach(new Acceptor(serverSocketChannel));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();  // 阻塞连接请求
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        dispatcher(selectionKey);
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
    
    static class SlaveReactor implements Runnable {
        private Selector selector;
        
        public SlaveReactor() throws IOException {
            this.selector = Selector.open();
        }
        
        public Selector getSelector() {
            return selector;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();  // 阻塞读请求；初始化后没有注册channel，当有连接请求时，在其上注册读请求，需要首先对其唤醒
                    Thread.sleep(2);    // 给MasterReactor注册读请求机会
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        dispatcher(selectionKey);
                        iterator.remove();
                    }
                } catch (IOException | InterruptedException e) {
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
    
    static class Acceptor implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private SlaveReactor slaveReactor;
        private ExecutorService pool;
        
        public Acceptor(ServerSocketChannel serverSocketChannel) {
            try {
                this.serverSocketChannel = serverSocketChannel;
                this.slaveReactor = new SlaveReactor();
                this.pool = Executors.newFixedThreadPool(3);
                
                new Thread(slaveReactor).start(); // 启动SlaveReactor线程
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                PrintUtil.print("客户端连接：" + socketChannel.getLocalAddress());
                socketChannel.configureBlocking(false);
                // 唤醒
                slaveReactor.getSelector().wakeup();
                
                // 必须select是非阻塞状态才可以注册
                SelectionKey selectionKey = socketChannel.register(slaveReactor.getSelector(), SelectionKey.OP_READ);
                selectionKey.attach(new ThreadPoolReactor.Worker(socketChannel, pool));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
