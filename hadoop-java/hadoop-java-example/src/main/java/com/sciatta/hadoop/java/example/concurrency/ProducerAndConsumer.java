package com.sciatta.hadoop.java.example.concurrency;

import java.util.Random;

/**
 * Created by yangxiaoyu on 2019-03-13<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ProducerAndConsumer
 */
public class ProducerAndConsumer {
    private static final String DONE = "Done";

    private static void sleep(int millis) {
        try {
            Thread.sleep(new Random().nextInt(millis));
        } catch (InterruptedException e) {
        }
    }

    public static void threadOut(String message) {
        System.out.format("[%s] %s%n", Thread.currentThread().getName(), message);
    }

    public static Thread producer(SynchronousMessage message, String name) {
        String[] messages = {"hello", "world", "java"};

        class Producer implements Runnable {
            SynchronousMessage message;

            Producer(SynchronousMessage message) {
                this.message = message;
            }

            @Override
            public void run() {
                for (String m : messages) {
                    threadOut("producer send message: " + m);
                    message.put(m);
                    sleep(50);
                }

                threadOut("producer send message: " + DONE);
                message.put(DONE);
            }
        }

        Thread t = new Thread(new Producer(message));
        t.setName(name);
        return t;
    }

    public static Thread consumer(SynchronousMessage message, String name) {
        class Consumer implements Runnable {
            SynchronousMessage message;

            Consumer(SynchronousMessage message) {
                this.message = message;
            }

            @Override
            public void run() {
                for (String m = message.take(); m != null && !m.equals(DONE); m = message.take()) {
                    threadOut("consumer received message: " + m);
                    sleep(100);
                }
            }
        }

        Thread t = new Thread(new Consumer(message));
        t.setName(name);
        return t;
    }

    static class SynchronousMessage {
        private String message;
        private boolean empty = true;

        public synchronized String take() {
            threadOut("get consumer lock");
            while (empty) {
                try {
                    // wait前要获得对象锁，最简单的方式就是同步方法；执行wait释放对象锁等待通知
                    threadOut("release consumer lock, wait for producer notify");
                    wait();
                    threadOut("got notify and get consumer lock again");
                    // 被通知后，重新获得对象锁
                } catch (InterruptedException e) {
                }
            }

            empty = true;
            threadOut("notify producer");
            notifyAll();
            return message;
        }

        public synchronized void put(String message) {
            threadOut("get producer lock");
            while (!empty) {
                try {
                    threadOut("release producer lock, wait for consumer notify");
                    wait();
                    threadOut("get notify and get producer lock again");
                } catch (InterruptedException e) {
                }
            }

            empty = false;
            this.message = message;
            threadOut("notify consumer");
            notifyAll();
        }
    }

    public static void main(String[] args) {
        SynchronousMessage message = new SynchronousMessage();

        ProducerAndConsumer.consumer(message, "Consumer").start();
        ProducerAndConsumer.producer(message, "Producer").start();
    }
}
