package com.happy.review;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableBlockingExample {

    // 1. 模拟一个需要1秒响应的第三方支付接口
    static class PaymentCallable implements Callable<String> {
        private final String orderId;

        public PaymentCallable(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public String call() throws Exception {
            // 模拟耗时网络调用 (IO阻塞)
            Thread.sleep(9000);
            // 假设调用成功，返回第三方流水号
            return "TXN_" + System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        // 自定义业务线程池（虽然后面会被阻塞，但依然需要有界队列）
        ExecutorService executor = new ThreadPoolExecutor(
                5, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
                    private final AtomicInteger counter = new AtomicInteger(0);
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "pay-worker-" + counter.incrementAndGet());
                    }
                }
        );

        // 模拟Tomcat工作线程（主线程）
        System.out.println("主线程[" + Thread.currentThread().getName() + "] 开始调用第三方...");

        // 1. 提交异步任务
        Future<String> future = executor.submit(new PaymentCallable("ORDER_001"));

        try {
            // 2. 【致命伤】调用 get() 阻塞主线程，直到拿到结果或超时
            // 此时的 主线程 状态变为 WAITING (parking)
            String result = future.get(3, TimeUnit.SECONDS); 
            System.out.println("主线程收到结果: " + result);

        } catch (TimeoutException e) {
            System.out.println("主线程等待超时！");
            future.cancel(true); // 取消任务
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}