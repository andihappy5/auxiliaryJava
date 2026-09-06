package com.happy.review;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 原生JDK NIO：分离Accept线程(Boss) 和 IO读写线程(Worker)
 * Boss线程：只处理 OP_ACCEPT，拿到SocketChannel后注册给Worker的Selector
 * Worker线程：只处理 OP_READ / OP_WRITE，处理客户端数据
 */
public class JdkNioSeparateAcceptIoDemo {

    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        // 1. 开启ServerSocketChannel，绑定端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));

        // Boss Selector：只负责 ACCEPT事件 (Netty BossGroup原型)
        Selector bossSelector = Selector.open();
        serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);

        // Worker Selector：只负责 READ / WRITE (Netty WorkerGroup原型)
        Selector workerSelector = Selector.open();

        // Boss线程：专门处理accept
        Thread bossThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int select = bossSelector.select();
                    if (select <= 0) {
                        continue;
                    }
                    Set<SelectionKey> keys = bossSelector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isAcceptable()) {
                            // 只做accept！获取客户端SocketChannel
                            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                            SocketChannel clientChannel = ssc.accept();
                            clientChannel.configureBlocking(false);
                            System.out.println("[Boss‑Accept线程] 收到客户端连接：" + clientChannel.getRemoteAddress());

                            // ✅关键：把新建客户端SocketChannel注册到Worker的Selector，交给IO线程处理读写
                            clientChannel.register(workerSelector, SelectionKey.OP_READ);
                            // 唤醒worker selector，让它感知新注册的channel
                            workerSelector.wakeup();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Nio‑Boss‑Accept‑Thread");


        // Worker线程：专门处理 read/write IO事件
        Thread workerIoThread = new Thread(() -> {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int select = workerSelector.select();
                    if (select <= 0) {
                        continue;
                    }
                    Set<SelectionKey> keys = workerSelector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();

                        if (key.isReadable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            buf.clear();
                            int readLen = client.read(buf);
                            if (readLen <= 0) {
                                // 客户端关闭连接
                                System.out.println("[Worker‑IO线程] 客户端断开 " + client.getRemoteAddress());
                                client.close();
                                key.cancel();
                                continue;
                            }
                            buf.flip();
                            byte[] data = new byte[buf.remaining()];
                            buf.get(data);
                            String msg = new String(data);
                            System.out.printf("[Worker‑IO线程] 收到消息:%s%n", msg);

                            // 模拟业务sleep 1s，对应你之前业务逻辑
                            Thread.sleep(1000);

                            // 回写响应
                            String resp = "server echo:" + msg;
                            ByteBuffer respBuf = ByteBuffer.wrap(resp.getBytes());
                            client.write(respBuf);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Nio‑Worker‑IO‑Thread");

        bossThread.start();
        workerIoThread.start();

        System.out.println("服务启动，端口:" + PORT);
    }
}
