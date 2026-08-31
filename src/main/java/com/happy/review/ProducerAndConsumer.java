package com.happy.review;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


///** @serial Main lock guarding all access */
//final ReentrantLock lock;
//
///** @serial Condition for waiting takes */
//@SuppressWarnings("serial")  // Classes implementing Condition may be serializable.
//private final Condition notEmpty;
//
///** @serial Condition for waiting puts */
//@SuppressWarnings("serial")  // Classes implementing Condition may be serializable.
//private final Condition notFull;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        AtomicInteger i = new AtomicInteger();
        Thread producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    queue.put(i + "");
                    System.out.println("add " + i);
                    i.getAndIncrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        });
        producer.start();
        Thread condumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("take" + queue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        condumer.start();

        try {
            Thread.sleep(1000*10);
            producer.interrupt();
            condumer.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
