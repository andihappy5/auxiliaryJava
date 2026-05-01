package com.happy.alg;

import java.util.Arrays;

public class LeetCode031 {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        LeetCode031 l = new LeetCode031();
        LeetCode031.Solution s = l.new Solution();
        int[] nums = new int[] { 3, 2, 1 };
        System.out.println(Arrays.toString(nums));// [1,3,2]
        s.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));// [1,3,2]
    }

    class Solution {
        // [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]
        public void nextPermutation(int[] nums) {
            int n = nums.length;
            // 第一步: 从右向左找第一个小于右侧相邻数字的数num[i]
            int i = n - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }

            // 如果找到了，进入第二步；否则跳过第二步，反转整个数组
            if (i >= 0) {
                // 第二步：从右到左找到nums[i] 右边最小的大于 nums[i]的数 nums[j]
                int j = n - 1;
                while (nums[j] <= nums[i]) {
                    j--;
                }
                // 交换 nums[i] 和 nums[j]
                swap(nums, i, j);
            }
            // 第三步：反转 【j+1,n-1】 (如果上面跳过了第二步，此时i=-1)
            reverse(nums, i + 1, n - 1);
        }

        private void reverse(int[] nums, int left, int right) {
            while (left < right) {
                swap(nums, left++, right--);
            }
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    /**
     * 
     * Implement next permutation, which rearranges numbers into the
     * lexicographically next greater permutation of numbers.
     * 
     * If such an arrangement is not possible, it must rearrange it
     * as the lowest possible order (i.e., sorted in ascending order).
     * 
     * The replacement must be in place and use only constant extra memory.
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,3]
     * Output: [1,3,2]
     * Example 2:
     * 
     * Input: nums = [3,2,1]
     * Output: [1,2,3]
     * Example 3:
     * 
     * Input: nums = [1,1,5]
     * Output: [1,5,1]
     * Example 4:
     * 
     * Input: nums = [1]
     * Output: [1]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * 
     */

    // 依据：
    // https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1])
            i--; // Find 1st id i that breaks descending order

        if (i >= 0) { // If not entirely descending
            int j = nums.length - 1; // Start from the end
            while (nums[j] <= nums[i])
                j--; // Find rightmost first larger id j
            swap(nums, i, j); // Switch i and j
        }
        reverse(nums, i + 1, nums.length - 1); // Reverse the descending sequence
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while (i < j)
            swap(A, i++, j--);
    }

    static class Day202604200 {
        static void main() {
            int[] nums = new int[] { 6, 2, 1, 5, 4, 3, 0 };
            nextPermutation(nums);
            System.out.println(Arrays.toString(nums));

            nums = new int[] { 1, 2, 3 };
            nextPermutation(nums);
            System.out.println(Arrays.toString(nums));

            nums = new int[] { 3, 2, 1 };
            nextPermutation(nums);
            System.out.println(Arrays.toString(nums));

            nums = new int[] { 1, 1, 5 };
            nextPermutation(nums);
            System.out.println(Arrays.toString(nums));

            nums = new int[] { 5, 1, 1 };
            nextPermutation(nums);
            System.out.println(Arrays.toString(nums));
            nums = new int[] { 6, 1, 5, 4, 3, 2 };
            nextPermutation(nums);
            System.out.println(Arrays.toString(nums));
        }

        // [6,1,5,4,3,2] please according this example explain the code in detail
        // First, we find the position of i. this position is the first position
        // from right to left that breaks the descending order.
        // in this example, i=1, nums[i]=2
        // Second, we find the position of j.
        // this position is the first position from right to left that is greater than
        // nums[i].
        // in this example, j=5, nums[j]=2
        // Third, we swap nums[i] and nums[j].
        // in this example, after swap, nums=[6,2,1,5,4,3]
        // Fourth, we reverse the descending sequence from i+1 to the end of the array.
        // in this example, after reverse, nums=[6, 2, 1, 3, 4, 5]
        public static void nextPermutation(int[] nums) {
            // find the first choince
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            Arrays.sort(nums, i + 1, nums.length);
        }
    }

}
