package com.happy.alg;

import java.util.Arrays;

/***
 * 
 * 26. Remove Duplicates from Sorted Array
 * Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once. The
 * relative order of the elements should be kept the same. Then return the
 * number of unique elements in nums.
 * 
 * Consider the number of unique elements of nums to be k, to get accepted, you
 * need to do the following things:
 * 
 * Change the array nums such that the first k elements of nums contain the
 * unique elements in the order they were present in nums initially. The
 * remaining elements of nums are not important as well as the size of nums.
 * Return k.
 * Custom Judge:
 * 
 * The judge will test your solution with the following code:
 * 
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * 
 * int k = removeDuplicates(nums); // Calls your implementation
 * 
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements
 * of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * Example 2:
 * 
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements
 * of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class LeetCode026 {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(Arrays.toString(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));
        System.out.println(removeDuplicates(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));
        LeetCode026 leetcode = new LeetCode026();
        LeetCode026.Solution solution = leetcode.new Solution();
        System.out.println(solution.removeDuplicates(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));

        System.out.println(removeDuplicates(new int[] { 1, 1, 2 }));
        System.out.println(solution.removeDuplicates(new int[] { 1, 1, 2 }));

    }

    class Solution2 {
        // use two pointers, i is the index of the last unique element, j is the index
        // of the current element.
        // it is different from usually two pointers, because usually two pointers are
        // used to a front and back,
        // but here we use two pointers are in the same direction. the first pointer i
        // is the index of the last unique element,
        // the second pointer j is the index of the current element. so that we can move
        // the current element to the last unique element.
        // and confirm 0 to i is the unique elements.
        // and then we can return the index of the last unique element + 1 as the length
        // of the unique elements.
        public int removeDuplicates(int[] nums) {
            int len = nums.length;
            int i = 0;
            for (int j = 0; j < len; j++) {
                if (nums[i] != nums[j]) {
                    i += 1;
                    nums[i] = nums[j];
                }
            }
            return i + 1;
        }
    }

    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            if (nums.length == 1)
                return 1;
            // nums is sorted in non-decreasing .
            int i = 1, dif = 0, count = 1;
            while (i <= nums.length - 1) {
                if (nums[i] > nums[dif]) {
                    count++;
                    dif++;
                    swap(nums, i, dif);
                }
                i++;

            }
            return count;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /*
     * 
     * Given a sorted array nums, remove the duplicates in-place such that each
     * element appears only once and returns the new length.
     * 
     * Do not allocate extra space for another array, you must do this by modifying
     * the input array in-place with O(1) extra memory.
     * 
     * Clarification:
     * 
     * Confused why the returned value is an integer but your answer is an array?
     * 
     * Note that the input array is passed in by reference, which means a
     * modification to the input array will be known to the caller as well.
     * 
     * Internally you can think of this:
     * 
     * // nums is passed in by reference. (i.e., without making a copy)
     * int len = removeDuplicates(nums);
     * 
     * // any modification to nums in your function would be known by the caller.
     * // using the length returned by your function, it prints the first len
     * elements.
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2]
     * Explanation: Your function should return length = 2, with the first two
     * elements of nums being 1 and 2 respectively. It doesn't matter what you leave
     * beyond the returned length.
     * 
     * Example 2:
     * 
     * Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4]
     * Explanation: Your function should return length = 5, with the first five
     * elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
     * It doesn't matter what values are set beyond the returned length.
     * 
     * 
     * Constraints:
     * 
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums is sorted in ascending order.
     */

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        // nums is sorted in ascending order.
        int i = 1, result = i - 1;
        while (i < nums.length) {
            if (nums[i] == nums[result]) {
                i++;
            } else {
                result++;
                nums[result] = nums[i];
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return result + 1;
    }
}
