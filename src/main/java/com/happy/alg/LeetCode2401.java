package com.happy.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode2401 {
    /*
     * 
     * You are given an array nums consisting of positive integers.
     * 
     * We call a subarray of nums nice if the bitwise AND of every pair of elements
     * that are in different positions in the subarray is equal to 0.
     * 
     * Return the length of the longest nice subarray.
     * 
     * A subarray is a contiguous part of an array.
     * 
     * Note that subarrays of length 1 are always considered nice.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,3,8,48,10]
     * Output: 3
     * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies
     * the conditions:
     * - 3 AND 8 = 0.
     * - 3 AND 48 = 0.
     * - 8 AND 48 = 0.
     * It can be proven that no longer nice subarray can be obtained, so we return
     * 3.
     * Example 2:
     * 
     * Input: nums = [3,1,5,11,13]
     * Output: 1
     * Explanation: The length of the longest nice subarray is 1. Any subarray of
     * length 1 can be chosen.
     * 
     * 
     * Constraints:
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println(1 & 1);
        LeetCode2401 leetCode2401 = new LeetCode2401();
        System.out.println(leetCode2401.longestNiceSubarray2(new int[] { 744437702, 379056602, 145555074, 392756761,
                560864007, 934981918, 113312475, 1090, 16384, 33, 217313281, 117883195, 978927664 }));
        System.out.println(leetCode2401.longestNiceSubarray(new int[] { 744437702, 379056602, 145555074, 392756761,
                560864007, 934981918, 113312475, 1090, 16384, 33, 217313281, 117883195, 978927664 }));
        System.out.println(leetCode2401.longestNiceSubarray(new int[] { 3, 8, 48 }));
        System.out.println(leetCode2401.longestNiceSubarray(new int[] { 1, 3, 8, 48 }));
        System.out.println(leetCode2401.longestNiceSubarray(new int[] { 1, 3, 8, 48, 10 }));
        System.out.println(leetCode2401.longestNiceSubarray(new int[] { 3, 1, 5, 11, 13 }));
    }

    // -------------------------------------暴力循环------------------------------
    // 暴力循环被证明为超时
    // Time Limit Exceeded
    public int longestNiceSubarray2(int[] nums) {
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (bitwisejudge(i, j, nums)) {
                    result = result > (j - i + 1) ? result : (j - i + 1);
                }
            }
        }
        return result;
    }

    private boolean bitwisejudge(int i, int j, int[] nums) {
        for (int m = i; m < j; m++) {
            for (int n = m + 1; n <= j; n++) {
                if ((nums[m] & nums[n]) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // ---------------------------------滑动窗口-----------------------------------
    public int longestNiceSubarray3(int[] nums) {
        int result = 1;
        int begin = 0;
        // map 存在的意义就在于减少运算的意义，直接通过 map 来判断是否可以滑动？！
        Map<Integer, Integer> map = new LinkedHashMap<>(nums.length);
        map.put(nums[0], begin);
        for (int i = 1; i < nums.length; i++) {
            int ivalue = nums[i];
            // 如果存在相同的元素，则永远不会是“bitwise”
            if (map.containsKey(ivalue)) {
                begin = map.get(ivalue) + 1;
                map.put(ivalue, i);
                result = result > (i - begin + 1) ? result : (i - begin + 1);
            } else {
                // 如果不存在 map 中，则要更新 begin 和处理 map 中的元素
                // TODO: 这里的逻辑是错误的，因为如果不存在 map 中，则要更新 begin 和处理 map 中的元素
                result = result > (i - begin + 1) ? result : (i - begin + 1);
            }

        }
        return result;
    }

    public int longestNiceSubarray(int[] nums) {
        int maxLength = 1; // Track the maximum nice subarray length found
        for (int start = 0; start < nums.length - maxLength; start++) {
            int currentLength = 1; // Length of current nice subarray
            int usedBits = nums[start]; // Track which bits are used in our subarray
            // Try to extend the subarray
            for (int end = start + 1; end < nums.length; end++) {
                // If no bits overlap between current number and used bits, we can extend
                if ((usedBits & nums[end]) == 0) {
                    usedBits |= nums[end]; // Add new number's bits to our tracker
                    currentLength++;
                }
                // If bits overlap, this number can't be part of our nice subarray
                else
                    break;
            }
            // Update max length if we found a longer nice subarray
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    class Solution {
        public int longestNiceSubarray(int[] nums) {
            int usedBits = 0; // Tracks bits used in current window
            int windowStart = 0; // Start position of current window
            int maxLength = 0; // Length of longest nice subarray found

            for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
                // If current number shares bits with window, shrink window from left
                // until there's no bit conflict
                while ((usedBits & nums[windowEnd]) != 0) {
                    usedBits ^= nums[windowStart]; // Remove leftmost element's bits
                    windowStart++;
                }

                // Add current number to the window
                usedBits |= nums[windowEnd];
                // Update max length if current window is longer
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1);

            }

            return maxLength;
        }
    }

}