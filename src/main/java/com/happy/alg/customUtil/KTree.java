package com.happy.alg.customUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KTree {

    private final PriorityQueue<Integer> Heap = new PriorityQueue<>(k);;

    public void KMaxTree(int k, int[] nums) {
        for (int num : nums) {
            Heap.offer(num);
            if (Heap.size() > k) Heap.poll();
        }
    }

    public void KMinTree(int k, int[] nums){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) maxHeap.poll();
        }
    }

    
}
