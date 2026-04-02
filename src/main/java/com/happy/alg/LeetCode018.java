package com.happy.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array nums of n integers and an integer target, are there elements a,
// b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets
// in the array which gives the sum of target.
//
// Notice that the solution set must not contain duplicate quadruplets.
//
//
// Example 1:
// Input: nums = [1,0,-1,0,-2,2], target = 0
//Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// Example 2:
// Input: nums = [], target = 0
//Output: []
//
//
// Constraints:
//
//
// 0 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109
//
// Related Topics Array Hash Table Two Pointers
// 👍 2760 👎 382

public class LeetCode018 {

    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode018 l = new LeetCode018();
        LeetCode018.Solution2 s = l.new Solution2();
        System.out.println(l.fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
        System.out
                .println(l.fourSum(new int[] { 0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000 }, 1000000000));
        System.out.println(l.fourSum(new int[] { -2, -1, -1, 1, 1, 2, 2 }, 0));
        System.out.println(l.fourSum(new int[] { 1, -2, -5, -4, -3, 3, 3, 5 }, -11));
        System.out.println(l.fourSum(new int[] { 1000000000, 1000000000, 1000000000, 1000000000 }, -294967296));
    }

    // sum to target, so we can use two pointers to find the solution,
    // but we need to sort the array first,
    // and skip the same elements to avoid duplicate quadruplets.
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // deal the special case
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        // sort the array first, then use two pointers to find the solution
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>(nums.length / 4);
        // loop the array, and skip the same elements
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int m = j + 1;
                int n = length - 1;
                while (m < n) {
                    // judge if the sum of nums[i], nums[j], nums[m], nums[n] will overflow,
                    // nums[i], nums[j], nums[m], nums[n] are positive or not,
                    // if so, break the loop
                    long sl = (long) nums[i] + nums[j] + nums[m] + nums[n];
                    if (sl > Integer.MAX_VALUE || sl < Integer.MIN_VALUE) {
                        break;
                    }
                    int sum = target - nums[i] - nums[j] - nums[m] - nums[n];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        while (m < n && nums[m] == nums[m + 1]) {
                            m++;
                        }
                        m++;
                        while (m < n && nums[n] == nums[n - 1]) {
                            n--;
                        }
                        n--;
                    } else if (sum > 0) {
                        m++;
                    } else {
                        n--;
                    }
                }
            }
        }
        return result;
    }

    class Solution2 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 4) {
                return res;
            }
            Arrays.sort(nums);
            int length = nums.length;
            for (int i = 0; i < length - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                    break;
                }
                if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                    continue; // prevent integer overflow
                }
                for (int j = i + 1; j < length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                        break;
                    }
                    if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                        continue; // prevent integer overflow
                    }
                    int left = j + 1, right = length - 1;
                    while (left < right) {
                        long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            left++;
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            right--;
                        } else if (sum < target) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
            return res;
        }
    }

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            // special case
            if (nums == null || nums.length < 4)
                return result;
            Arrays.sort(nums); // sort first, then use two pointers
            for (int i = 0; i < nums.length - 3; i++) {
                if (nums[i] > target / 4)
                    return result;
                // skip same element
                if (i > 0 && nums[i] == nums[i - 1])
                    continue;
                for (int j = i + 1; j < nums.length - 2; j++) {
                    // skip same elements
                    if (j > i + 1 && nums[j] == nums[j - 1])
                        continue;
                    int m = j + 1;
                    int n = nums.length - 1;
                    while (m < n) {
                        int sum = nums[i] + nums[j] + nums[m] + nums[n];
                        if (sum == target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                            m++;
                            // nums[m] already be added, so move to next,and skip same elements
                            while (m < n && nums[m] == nums[m - 1])
                                m++;
                            n--;
                            // nums[n] already be added, so move to next,and skip same elements
                            while (m < n && nums[n] == nums[n + 1])
                                n--;
                        } else if (sum < target) {
                            m++;
                            // skip same elements
                            while (m < n && nums[m] == nums[m - 1])
                                m++;
                        } else {
                            n--;
                            // skip same elements
                            while (m < n && nums[n] == nums[n + 1])
                                n--;
                        }
                    }
                }
            }
            return result;
        }
    }

    class solution3 {
        // Notice that the solution set must not contain duplicate quadruplets.
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 4)
                return result;
            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 3; i++) {
                // skip same element
                if (i > 0 && nums[i - 1] == nums[i])
                    continue;
                for (int j = i + 1; j < nums.length - 2; j++) {
                    // skip same elements
                    if (j > i + 1 && nums[j - 1] == nums[j])
                        continue;

                    int m = j + 1;
                    int n = nums.length - 1;
                    while (m < n) {
                        // skip same elements
                        if (m > j + 1 && nums[m - 1] == nums[m]) {
                            m++;
                            continue;
                        }
                        int tmpSum = nums[i] + nums[j] + nums[m] + nums[n];
                        if (tmpSum == target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                            m++;
                            n--;

                        } else if (tmpSum < target) {
                            m++;
                        } else {
                            n--;
                        }
                    }
                }
            }
            return result;
        }
    }
}
