package com.happy.review;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LoopPrintQueue {
    private static final int MAX = 100;
    private static int current = 1;
    // 使用两个队列分别代表“轮到奇数”和“轮到偶数”的许可
    private static final BlockingQueue<Integer> oddQueue = new ArrayBlockingQueue<>(1);
    private static final BlockingQueue<Integer> evenQueue = new ArrayBlockingQueue<>(1);

    static {
        try {
            oddQueue.put(1); // 初始让奇数线程持有令牌
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread odd = new Thread(() -> {
            while (true) {
                try {
                    oddQueue.take(); // 获取奇数令牌
                    if (current > MAX) break;
                    System.out.println("Odd -> " + current++);
                    evenQueue.put(1); // 交给偶数线程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread even = new Thread(() -> {
            while (true) {
                try {
                    evenQueue.take();
                    if (current > MAX) break;
                    System.out.println("Even -> " + current++);
                    oddQueue.put(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        odd.start();
        even.start();
    }
}
