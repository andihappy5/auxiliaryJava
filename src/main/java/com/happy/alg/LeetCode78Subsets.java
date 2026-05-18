package com.happy.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode78Subsets {
    // 78. Subsets
    /**
     * 
     * Given an integer array nums of unique elements, return all possible subsets
     * (the power set).
     * 
     * The solution set must not contain duplicate subsets. Return the solution in
     * any order.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 
     * Example 2:
     * Input: nums = [0]
     * Output: [[],[0]]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * All the numbers of nums are unique.
     */

    static class Solution {
        public static void main(String[] args) {
            System.out.println(Solution.subsets(new int[] { 1, 2, 3, 4 }).toString());
        }

        public static List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            result.add(list);
            // size from 1 to nums.length
            // index from 0 to nums.length-1
            for (int i = 1; i <= nums.length; i++) {
                subsets(nums, result, list, 0, i);
            }
            return result;
        }

        private static void subsets(int[] nums,
                List<List<Integer>> result, List<Integer> list, int from, int size) {
            if (from > nums.length) {
                return;
            }
            if (list.size() == size) {
                result.add(new ArrayList<>(list));
                return;
            }
            for (int i = from; i < nums.length; i++) {
                list.add(nums[i]);
                subsets(nums, result, list, i + 1, size);
                list.remove(list.size() - 1);
            }
        }
    }

}
