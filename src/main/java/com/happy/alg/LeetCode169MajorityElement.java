package com.happy.alg;

public class LeetCode169MajorityElement {
    // 169. Majority Element
    /*
     * Given an array nums of size n, return the majority element.
     * 
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You
     * may assume that the majority element always exists in the array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [3,2,3]
     * Output: 3
     * Example 2:
     * 
     * Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     * 
     * 
     * Constraints:
     * 
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     * The input is generated such that a majority element will exist in the array.
     * 
     * 
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     * 
     * 
     */

    public static void main(String[] args) {
        LeetCode169MajorityElement solution = new LeetCode169MajorityElement();
        int[] nums = new int[] { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println(solution.majorityElement(nums));
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int result = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (result == nums[i]) {
                times++;
            } else {
                if (times == 1) {
                    result = nums[i];
                } else {
                    times--;
                }
            }
        }
        return result;
    }
}
