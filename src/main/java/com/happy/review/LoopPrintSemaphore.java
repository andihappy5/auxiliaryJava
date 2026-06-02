package com.happy.review;

import java.util.concurrent.Semaphore;

public class AlternatePrintSemaphore {
    private static final int MAX = 100;
    private static int current = 1;
    private static final Semaphore oddSem = new Semaphore(1);
    private static final Semaphore evenSem = new Semaphore(0);

    public static void main(String[] args) {
        new Thread(() -> {
            while (current <= MAX) {
                try {
                    oddSem.acquire();
                    if (current <= MAX && current % 2 == 1) {
                        System.out.println("Odd -> " + current++);
                    }
                    evenSem.release();
                } catch (InterruptedException e) {}
            }
        }).start();

        new Thread(() -> {
            while (current <= MAX) {
                try {
                    evenSem.acquire();
                    if (current <= MAX && current % 2 == 0) {
                        System.out.println("Even -> " + current++);
                    }
                    oddSem.release();
                } catch (InterruptedException e) {}
            }
        }).start();
    }
}
