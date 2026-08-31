package com.happy.review;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThread {

    public static void main(String[] args) throws InterruptedException {
//        // 方式一：Thread.ofVirtual()
//        Thread vThread = Thread.ofVirtual()
//                .name("my-virtual-", 0)  // 命名前缀 + 起始编号
//                .start(() -> {
//                    System.out.println("Hello from virtual thread: "
//                            + Thread.currentThread());
//                });
//
//        vThread.join();
//
//      // 方式二：Thread.startVirtualThread()（最简）
//        Thread.startVirtualThread(() -> {
//            System.out.println("Running in virtual thread");
//        });
//
//        // 方式三：创建但不自动 start
//        Thread unstarted = Thread.ofVirtual().unstarted(() -> {
//            System.out.println("Running in virtual thread first unstarted ");
//        });
//        unstarted.start();

        // 每个任务一个新的虚拟线程，无需池化
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        try {

            for (int i = 0; i < 10_000; i++) {
                final int taskId = i;
                int finalI = i;
                executor.submit(() -> {
                    System.out.println(finalI);
                    // 模拟 I/O 阻塞
                    Thread.sleep(Duration.ofMillis(100));
                    System.out.println("Task " + taskId + " done by "
                            + Thread.currentThread());
                    return taskId;
                });
            }
        } // try-with-resources 自动 awaitTermination
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
