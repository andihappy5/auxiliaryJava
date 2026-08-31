package com.happy.review;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableFutureNonBlockingExample {
    public static void main(String[] args) throws Exception {
        // 1. 模拟专用隔离线程池（对应之前讲的“第三方舱壁”）
        ExecutorService payExecutor = new ThreadPoolExecutor(
                5, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
                    private final ThreadGroup group = new ThreadGroup("PayGroup");
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(group, r, "pay-async-" + System.currentTimeMillis() % 100);
                    }
                }
        );

        System.out.println("=== 1. Tomcat主线程 [" + Thread.currentThread().getName() + "] 接收到请求 ===");
        // 2. 定义异步任务（Supplier 是 Callable 的函数式替代，无入参，有返回值）
        Supplier<String> payTask = () -> {
            // 这里才是真正干活的工作线程（来自 payExecutor）
            String workerName = Thread.currentThread().getName();
            System.out.println("   >>> 工作线程 [" + workerName + "] 开始调用第三方支付...");
            try {
                Thread.sleep(1000); // 模拟IO阻塞
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "FAILED";
            }
            return "TXN_" + System.currentTimeMillis();
        };

        // 3. 发起异步调用（主线程瞬间返回，不会被阻塞）
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(payTask, payExecutor)   // 提交任务到隔离线程池
                .orTimeout(1500, TimeUnit.MILLISECONDS) // 1.5秒超时（JDK 9+）
                // 4. 回调链：结果回来了，继续处理（由 payExecutor 中的线程执行）
                .thenApply(result -> {
                    System.out.println("   >>> 回调线程 [" + Thread.currentThread().getName() + "] 处理结果: " + result);
                    // 模拟结果转换（如解析第三方JSON）
                    return "SUCCESS: " + result;
                })
                // 5. 异常降级（如果超时或报错，走这里）
                .exceptionally(throwable -> {
                    System.err.println("   >>> 回调线程 [" + Thread.currentThread().getName() + "] 捕获异常: " + throwable.getMessage());
                    return "FALLBACK: 支付繁忙，请稍后重试";
                })
                // 【修正点】用 thenApply 替代 thenAccept，把值再传出去
                .thenApply(finalResult -> {
                    System.out.println("日志记录线程 [" + Thread.currentThread().getName() + "] 记录: " + finalResult);
                    return finalResult; // 原样返回，不改变类型
                });

        // 7. 关键点：主线程在这里就释放了！它不会调用 get()，而是直接返回给 Tomcat
        System.out.println("=== 2. Tomcat主线程 [" + Thread.currentThread().getName() + "] 立即释放，去处理其他请求啦！ ===");
        System.out.println("=== 主线程此时结束，但异步任务还在后台运行... ===");

        // 【注意】这里加 await 只是为了不让 main 方法退出，方便观察后台线程输出。
        // 在真实的 Spring Web 中，主线程已经返回给浏览器，这里无需阻塞。
        // 为了控制台演示，我们等待异步任务彻底结束。
        future.join(); // 注意：演示用的join，实际Controller里绝对不要写！
        System.out.println("=== 3. 所有异步逻辑执行完毕 ===");

        payExecutor.shutdown();
    }
}