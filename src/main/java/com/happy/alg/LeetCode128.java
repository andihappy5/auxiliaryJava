package com.happy.alg;

import java.util.Arrays;

public class LeetCode128 {
    /**
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     *
     * You must write an algorithm that runs in O(n) time.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [100,4,200,1,3,2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     * Example 2:
     *
     * Input: nums = [0,3,7,2,5,8,4,6,0,1]
     * Output: 9
     * Example 3:
     *
     * Input: nums = [1,0,1,2]
     * Output: 3
     *
     *
     * Constraints:
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * */

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null) {return 0;}
        if (nums.length <= 1) {return nums.length;}
        Arrays.sort(nums);
        int maxLen = 1;
        int curLen = 1;
        int plus=1;
        int curIndex = 1;
        while(curIndex < nums.length ) {
            if (nums[curIndex] == nums[curIndex - 1] +plus) {
                curLen++;
                plus=1;
                maxLen = Math.max(maxLen, curLen);
            }else if (nums[curIndex] == nums[curIndex - 1] +plus) {
                plus=-1;
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else if (nums[curIndex] == nums[curIndex - 1]) {
                curIndex++;
                continue;
            } else{
                curLen=1;
            }
            curIndex++;
        }
        return maxLen;
    }
}
