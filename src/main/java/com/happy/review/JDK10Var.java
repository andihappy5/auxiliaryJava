package com.happy.review;

public class JDK10Var {
    public static final Object lock = new Object();
    private static int completedCount = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("JDK10Var");
        var a = "var ";
        System.out.println(a.length());
        Thread virtualThread = Thread.ofVirtual().unstarted(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    System.out.println("Virtual thread 3 - iteration " + i);
                }
            } finally {
                synchronized (lock) {
                    completedCount++;
                    lock.notifyAll();
                }
            }
        });

        testVirtualThread(lock, virtualThread);

        synchronized (lock) {
            while (completedCount < 3) {
                lock.wait();
            }
            System.out.println("Main thread finished");
        }
        int aa = 10;
        int bb = 11;
        switch(aa+bb){
            case 1:
            return ;
        }

    }

    public static void testVirtualThread(Object lock, Thread v) {
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    System.out.println("Virtual thread 1 - iteration " + i);
                }
            } finally {
                synchronized (lock) {
                    completedCount++;
                    lock.notifyAll();
                }
            }
        });

        Thread virtualThread2 = Thread.ofVirtual().start(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    System.out.println("Virtual thread 2 - iteration " + i);
                }
            } finally {
                synchronized (lock) {
                    completedCount++;
                    lock.notifyAll();
                }
            }
        });
        v.start();
    }

    //泛型
    private static <T extends Number> double add(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }
}