package com.happy.review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ThreadLocal 内存泄漏演示 + 增删改查示例
 *
 * 核心问题：当 ThreadLocal 的 Key 是弱引用，但 Value 是强引用。
 * 如果线程长期存活（如线程池中的线程），且不再使用 ThreadLocal 后没有调用 remove()，
 * 那么 Value 对象会一直附着在线程的 ThreadLocalMap 中，导致内存泄漏。
 * Thread-1                               Thread-2
 *    |                                       |
 *    |-- threadLocals (ThreadLocalMap)       |-- threadLocals (ThreadLocalMap)
 *           |                                       |
 *           |-- Entry[ ]                             |-- Entry[ ]
 *                 |                                       |
 *                 |-- key = threadLocalA (弱引用)        |-- key = threadLocalA (弱引用)
 *                 |-- value = "A's value in T1"         |-- value = "A's value in T2"
 *                 |
 *                 |-- key = threadLocalB (弱引用)
 *                 |-- value = "B's value in T1"
 *
 *
 * Thread 好比一个图书馆的书架（每个书架属于一个线程）。
 * ThreadLocalMap 好比书架上的格子。
 * ThreadLocal 好比一位图书管理员，他知道每一个读者（线程）应该从哪个书架取书。
 * 如果让你去找一本书，你会直接找管理员（ThreadLocal）：“帮我拿我的那本《Java 编程思想》”。管理员会根据你的身份（当前线程）找到你的专属书架，再从书架上找到对应的书给你。
 * 你不会要求书架自己提供“取书方法”，因为书架只是存储装置，不具有业务语义。同样，Thread 对象只是一个“拥有者”的角色，不是操作者。
 */
public class ThreadLocalLeakDemo {
    ReentrantLock lock = new ReentrantLock();

    // 定义一个 ThreadLocal，存储一个较大的对象（模拟占用内存的数据）
    private static final ThreadLocal<UserSession> threadLocal = new ThreadLocal<>();

    // 模拟一个“重量级”用户会话对象
    static class UserSession {
        private final String userId;
        private final byte[] dummyData;  // 模拟占用内存的数据（1MB）

        public UserSession(String userId) {
            this.userId = userId;
            this.dummyData = new byte[1024 * 1024]; // 1 MB
        }
        public String getUserId() {
            return userId;
        }
        @Override
        public String toString() {
            return "UserSession{userId='" + userId + "'}";
        }
    }

    /**
     * 增：设置 ThreadLocal 的值
     */
    private static void setSession(String userId) {
        UserSession session = new UserSession(userId);
        threadLocal.set(session);
        System.out.println(Thread.currentThread().getName() + " 设置了 session: " + session);
    }

    /**
     * 查：获取 ThreadLocal 的值
     */
    private static UserSession getSession() {
        UserSession session = threadLocal.get();
        System.out.println(Thread.currentThread().getName() + " 获取到 session: " + session);
        return session;
    }

    /**
     * 改：修改 ThreadLocal 的值（重新 set）
     */
    private static void updateSession(String newUserId) {
        UserSession newSession = new UserSession(newUserId);
        threadLocal.set(newSession);
        System.out.println(Thread.currentThread().getName() + " 更新了 session: " + newSession);
    }

    /**
     * 删：移除 ThreadLocal 的值（防止内存泄漏的关键）
     */
    private static void removeSession() {
        threadLocal.remove();
        System.out.println(Thread.currentThread().getName() + " 已移除 ThreadLocal 中的值");
    }

    /**
     * 演示内存泄漏的场景：
     * 使用固定大小的线程池，每个任务都往 ThreadLocal 里放数据，但从不调用 remove()。
     * 线程池中的线程会被复用，导致 ThreadLocalMap 中残留上一次的 value 对象，
     * 这些对象永远无法被 GC 回收（因为线程一直存活 + value 被强引用）。
     *
     * 运行后，可以通过 jmap 或 VisualVM 观察到堆内存持续增长。
     */
    public static void leakScenario() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                // 模拟每个请求不同用户
                String userId = "user-" + taskId;
                // 1. 增：设置 session
                setSession(userId);
                // 2. 查：获取并使用 session
                UserSession session = getSession();
                // 3. 改：更新 session（模拟用户切换）
                updateSession("updated-" + userId);
                // 4. 删：*** 关键点：这里故意注释掉 remove，导致内存泄漏 ***
                // removeSession();  // 如果取消注释，则不会泄漏
                System.out.println(Thread.currentThread().getName() + " 任务 " + taskId + " 结束\n");
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("所有任务结束，但线程池中的线程还保留着 ThreadLocal 的旧值，内存已泄漏");
    }

    /**
     * 正确的使用方式：在 finally 块中调用 remove()，确保无论是否异常都清理。
     */
    public static void correctUsage() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    String userId = "correct-user-" + taskId;
                    setSession(userId);
                    getSession();
                    updateSession("correct-updated-" + userId);
                } finally {
                    removeSession(); // 保证清理
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("正确使用方式结束，内存被及时清理");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== 演示内存泄漏场景 =====");
        leakScenario();
        System.out.println("\n===== 演示正确使用场景（finally中remove） =====");
        correctUsage();
        System.out.println("\n建议：运行后通过 jmap -histo:live <pid> | grep UserSession 观察存活对象数量");
    }
}
