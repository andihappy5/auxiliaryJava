package com.happy.alg.leetcode;

import java.util.HashMap;

public class LeetCode217 {
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length <= 2)
                return true;
            HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>(nums.length);
            for (int n : nums) {
                cache.put(n, cache.getOrDefault(n, 0) + 1);
                if (cache.get(n) >= 2) {
                    return true;
                }
            }
            return false;
        }
}
}
