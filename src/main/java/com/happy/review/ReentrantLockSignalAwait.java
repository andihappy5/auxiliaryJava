package com.happy.review;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSignalAwait {
    private static final int MAX = 100;
    private static int current = 1;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition oddCondition = lock.newCondition();
    private static final Condition evenCondition = lock.newCondition();

    public static void main(String[] args) {
        Thread odd = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (current > MAX) {
                        evenCondition.signal(); // 防止偶数线程永远等待
                        break;
                    }
                    if (current % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " -> " + current++);
                        evenCondition.signal();   // 唤醒偶数线程
                    } else {
                        oddCondition.await();      // 等待奇数
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    lock.unlock();
                }
            }
        }, "OddThread");

        Thread even = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (current > MAX) {
                        oddCondition.signal();
                        break;
                    }
                    if (current % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " -> " + current++);
                        oddCondition.signal();
                    } else {
                        evenCondition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    lock.unlock();
                }
            }
        }, "EvenThread");

        odd.start();
        even.start();
    }

    static class PrintBySpin2 {
        private static final int MAX = 100;
        private static final AtomicInteger current = new AtomicInteger(1);
        private static volatile boolean oddTurn = true; // true: 轮到奇数

        public static void main(String[] args) {
            Thread odd = new Thread(() -> {
                while (current.get() <= MAX) {
                    if (oddTurn && (current.get() % 2 == 1)) {
                        System.out.println("Odd -> " + current.getAndIncrement());
                        oddTurn = false;
                    }
                    // 让出 CPU 防止过度循环（可选）
                    Thread.yield();
                }
            });

            Thread even = new Thread(() -> {
                while (current.get() <= MAX) {
                    if (!oddTurn && (current.get() % 2 == 0)) {
                        System.out.println("Even -> " + current.getAndIncrement());
                        oddTurn = true;
                    }
                    Thread.yield();
                }
            });

            odd.start();
            even.start();
        }
    }
}





