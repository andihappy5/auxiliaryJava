package com.happy.review;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class JdkNioBasicDemo {
    public static void main(String[] args) throws IOException {
        // 1. 创建JDK原生 ServerSocketChannel（监听端口，还没有任何客户端连接）
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true); // 阻塞模式方便演示
        serverSocketChannel.bind(new InetSocketAddress(8888));
        System.out.println("服务启动，监听8888端口，等待客户端连接...");

        while (true) {
            // ✅ accept：阻塞等待客户端TCP连接，三次握手完成才返回SocketChannel
            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            System.out.println("收到客户端连接:" + clientSocketChannel.getRemoteAddress());

            // 读取客户端发来消息
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = clientSocketChannel.read(buffer);
            if(read > 0){
                buffer.flip();
                byte[] data = new byte[read];
                buffer.get(data);
                String msg = new String(data);
                System.out.println("收到客户端消息：" + msg);

                // 有了socketChannel就能够读写数据了
                clientSocketChannel.write(ByteBuffer.wrap(msg.getBytes()));
            }

            // 模拟业务耗时 sleep 1s
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("业务处理完成");

            clientSocketChannel.close();
        }
    }
}