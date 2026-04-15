package com.happy.alg;

//Given an array nums of n integers and an integer target, find three integers i

//n nums such that the sum is closest to target. Return the sum of the three integ
//ers. You may assume that each input would have exactly one solution.
//
//
// Example 1:
//
//
//Input: nums = [-1,2,1,-4], target = 1
//Output: 2
//Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
//
//
//
// Constraints:
//
//
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4
//
// Related Topics Array Two Pointers

import java.util.Arrays;

public class LeetCode016 {

    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int result = 0;
            int distance = Integer.MAX_VALUE;
            // 3 <= nums.length <= 10^3,so not check args first sort
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                // skip same elements
                if (i > 0 && nums[i - 1] == nums[i])
                    continue;
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    if (j > i + 1 && nums[j - 1] == nums[j]) {
                        j++;
                        continue;
                    }
                    int tmpSum = nums[i] + nums[j] + nums[k];
                    if (tmpSum == target) {
                        return target;
                    } else if (tmpSum < target) {
                        j++;
                        int compare = Math.abs(target - tmpSum);
                        if (compare < distance) {
                            distance = compare;
                            result = tmpSum;
                        }
                    } else {
                        k--;
                        int compare = Math.abs(target - tmpSum);
                        if (compare < distance) {
                            distance = compare;
                            result = tmpSum;
                        }
                    }
                }
            }
            return result;
        }
    }

    public static int threeSumClosest(int[] nums, int target) {
        Integer result = 0;
        Integer distance = Integer.MAX_VALUE;
        // 3 <= nums.length <= 10^3,so not check args
        // first sort
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // skip same elements
            if (i > 0 && nums[i - 1] == nums[i])
                continue;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    j++;
                    continue;
                }
                int tmpSum = nums[i] + nums[j] + nums[k];
                if (tmpSum == target) {
                    return target;
                } else if (tmpSum < target) {
                    j++;
                    int compare = Math.abs(target - tmpSum);
                    if (compare < distance) {
                        distance = compare;
                        result = tmpSum;
                    }
                } else {
                    k--;
                    int compare = Math.abs(target - tmpSum);
                    if (compare < distance) {
                        distance = compare;
                        result = tmpSum;
                    }
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
        System.out.println(threeSumClosest(new int[] { -1, 2, 5, -4, }, 1));

    }

    static class App {
        public static void main(String[] args) {
            App.Solution solution = new App().new Solution();
            System.out.println(solution.threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
            System.out.println(solution.threeSumClosest(new int[] { 0, 0, 0 }, 0));
        }

        class Solution {
            // -4,-1,1,2
            public int threeSumClosest(int[] nums, int target) {
                // first sort the array
                Arrays.sort(nums);
                int closest = Integer.MAX_VALUE;
                int result = 0;
                for (int i = 0; i < nums.length - 2; i++) {
                    if (i > 0 && nums[i] == nums[i - 1]) {
                        continue;
                    }
                    int left = i + 1;
                    int right = nums.length - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[left] + nums[right];
                        int diff = Math.abs(sum - target);
                        if (diff < Math.abs(closest)) {
                            closest = diff;
                            result = sum;
                        }
                        if (sum - target == 0) {
                            return target;
                        } else if (sum - target < 0) {
                            left++;
                            while (left < right && nums[left] == nums[left - 1])
                                left++;
                        } else {
                            right--;
                            while (left < right && nums[right] == nums[right + 1])
                                right--;
                        }
                    }
                }
                return result;
            }
        }
    }
}
