package com.happy.hot100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class L347TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> times = new HashMap<Integer,Integer>();
        for(int n : nums){
            times.put(n,times.getOrDefault(n,0)+1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : times.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] result = new int[Math.max(0, Math.min(k, times.size()))];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = queue.poll()[0];
        }
        return result;
    }
}
