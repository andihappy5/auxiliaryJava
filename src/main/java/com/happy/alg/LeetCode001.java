package com.happy.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 
//## HashMap
//## Cache
public class LeetCode001 {
    public static void main(String[] args) {
        Solution T = new Solution();
        int[] result = T.twoSum2(new int[] { 1, 2, 3, 4, 5 }, 6);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(T.twoSum3(new int[] { 1, 2, 3, 4, 5 }, 6)));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[2];
            }
            // data structure hashmap used to cache the value and index of the array
            HashMap<Integer, Integer> cache = new HashMap<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                if (cache.containsKey(target - nums[i])) {
                    return new int[] { cache.get(target - nums[i]), i };
                }
                cache.put(nums[i], i);
            }
            return new int[2];
        }

        public int[] twoSum2(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int j = nums.length - 1 - i;
                if (i > j) {
                    return null;
                }
                if (map.containsKey(nums[i])) {
                    return new int[] { map.get(nums[i]), i };
                } else {
                    // why put target - nums[i] instead of nums[i]?
                    // Because we want to find the complement of the current number in the map,
                    // which is target - nums[i]. If we put nums[i] in the map,
                    // we would be looking for the same number instead of its complement.
                    map.put(target - nums[i], i);
                }
                if (map.containsKey(nums[j])) {
                    return new int[] { map.get(nums[j]), j };
                } else {
                    map.put(target - nums[j], j);
                }
            }
            return null;
        }

        public int[] twoSum3(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int j = nums.length - 1 - i;
                if (i > j) {
                    return null;
                }
                if (map.containsKey(target - nums[i])) {
                    return new int[] { map.get(target - nums[i]), i };
                } else {
                    // why put target - nums[i] instead of nums[i]?
                    // Because we want to find the complement of the current number in the map,
                    // which is target - nums[i]. If we put nums[i] in the map,
                    // we would be looking for the same number instead of its complement.
                    map.put(nums[i], i);
                }
                if (map.containsKey(target - nums[j])) {
                    return new int[] { map.get(target - nums[j]), j };
                } else {
                    map.put(nums[j], j);
                }
            }
            return null;
        }
    }
}
