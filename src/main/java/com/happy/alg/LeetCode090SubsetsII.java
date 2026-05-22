package com.happy.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode090SubsetsII {
    /**
     * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
     * <p>
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,2]
     * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
     * Example 2:
     * <p>
     * Input: nums = [0]
     * Output: [[],[0]]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     *
     */
    static void main() {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsets(res, list, nums, 0);
        return res;
    }

    private static void subsets(List<List<Integer>> res, List<Integer> list, int[] nums, int i) {
        res.add(new ArrayList<>(list));
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            list.add(nums[j]);
            subsets(res, list, nums, j + 1);
            list.remove(list.size() - 1);

        }
    }
}
