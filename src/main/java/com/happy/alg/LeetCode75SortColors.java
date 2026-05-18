package com.happy.alg;

import java.util.Arrays;

public class LeetCode75SortColors {
    // 75. Sort Colors
    /**
     * Given an array nums with n objects colored red, white, or blue, sort them
     * in-place so that objects of the same color are adjacent, with the colors in
     * the order red, white, and blue.
     * We will use the integers 0, 1, and 2 to represent the color red, white, and
     * blue, respectively.
     * You must solve this problem without using the library's sort function.
     * 
     * Example 1:
     * Input: nums = [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     * Example 2:
     * Input: nums = [2,0,1]
     * Output: [0,1,2]
     * 
     * Constraints:
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] is either 0, 1, or 2.
     * Follow up: Could you come up with a one-pass algorithm using only constant
     * extra space?
     */

    public static void main(String[] args) {
        int[] n = new int[] { 2, 0, 2, 1, 1, 0 };
        sortColors(n);
        System.out.println(Arrays.toString(n));
        n = new int[] { 2, 0, 1 };
        sortColors(n);
        System.out.println(Arrays.toString(n));
    }

    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int index0 = 0;
        int index2 = nums.length - 1;
        int i = 0;
        while (i <= index2 && i >= index0) {
            if (nums[i] == 0) {
                swap(nums, i, index0);
                index0++;
                if (i < index0) {
                    i = index0;
                }
            } else if (nums[i] == 2) {
                swap(nums, i, index2);
                index2--;
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int index0) {
        int tmp = nums[i];
        nums[i] = nums[index0];
        nums[index0] = tmp;
    }
}
