package com.happy.review;

public class AlternatePrint {

    private static final int MAX = 100;
    private static int current = 1;
    private static final Object lock = new Object();

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (current > MAX) {
                        lock.notify(); // 确保其他线程不会永远等待
                        break;
                    }
                    if (current % 2 == 1) { // 奇数
                        System.out.println(Thread.currentThread().getName() + " -> " + current);
                        current++;
                        lock.notify(); // 唤醒偶数线程
                    } else {
                        try {
                            lock.wait(); // 不是奇数则等待
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (current > MAX) {
                        lock.notify();
                        break;
                    }
                    if (current % 2 == 0) { // 偶数
                        System.out.println(Thread.currentThread().getName() + " -> " + current);
                        current++;
                        lock.notify(); // 唤醒奇数线程
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread odd = new Thread(new OddPrinter(), "OddThread");
        Thread even = new Thread(new EvenPrinter(), "EvenThread");
        odd.start();
        even.start();
    }
}
