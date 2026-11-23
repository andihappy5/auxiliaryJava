package com.happy.hot100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Utils {

    public static void main(String[] args) {

        // Test sorting of V objects
        V[] objects = new V[]{new V(1),new V(2),new V(0)};
        Arrays.sort(objects, Comparator.comparingInt((V v) -> v.age).thenComparingInt(v -> v.b));


        // Test PriorityQueue with int arrays
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(entry -> entry[1]));
        queue.offer(new int[]{1, 2});
        queue.offer(new int[]{2, 1});
        queue.offer(new int[]{3, 3});
    }

}
