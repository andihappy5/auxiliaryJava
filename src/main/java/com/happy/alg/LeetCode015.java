package com.happy.alg;//Given an array nums of n integers, are there elements a, b, c in nums such tha

//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro. 
//
// Notice that the solution set must not contain duplicate triplets. 
//
// 
// Example 1: 
// Input: nums = [-1,0,1,2,-1,-4]
// Output: [[-1,-1,2],[-1,0,1]]
// Example 2: 
// Input: nums = []
//Output: []
// Example 3: 
// Input: nums = [0]
//Output: []
// 
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics Array Two Pointers 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode015 {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            // special case
            if (nums == null || nums.length < 3)
                return result;
            // sort,Sorts the specified array into ascending numerical order.
            Arrays.sort(nums);
            // two points
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i] > 0)
                    break; // as the array is sorted, if the first number is > 0, no need to continue
                // skip same element
                if (i > 0 && nums[i - 1] == nums[i])
                    continue;
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    if (j > i + 1 && nums[j - 1] == nums[j]) {
                        j++;
                        continue;
                    }
                    int tmpSum = nums[i] + nums[j] + nums[k];
                    if (tmpSum == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;
                        k--;
                    } else if (tmpSum > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
            return result;
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1)
            return result;

        // sort
        Arrays.sort(nums);

        // two points
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0)
                break;
            // skip same element
            if (i > 0 && nums[i - 1] == nums[i])
                continue;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    j++;
                    continue;
                }
                int tmpSum = nums[i] + nums[j] + nums[k];
                if (tmpSum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (tmpSum > 0) {
                    k--;
                } else {
                    j++;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[] { -4, -1, -1, 0, 1, 2 }));
        System.out.println(threeSum(new int[] { -2, 0, 0, 2, 2 }));

    }

    static class App {
        public static void main(String[] args) {
            App.Solution solution = new App().new Solution();
            System.out.println(Arrays.toString(solution.threeSum(new int[] { -4, -1, -1, 0, 1, 2 }).toArray()));
            System.out.println(Arrays.toString(solution.threeSum(new int[] { 0, 0, 0 }).toArray()));
        }

        class Solution {
            public List<List<Integer>> threeSum(int[] nums) {
                List<List<Integer>> result = new ArrayList<>();
                if (nums == null || nums.length < 3) {
                    return result;
                }
                Arrays.sort(nums);
                // three sum,deal special case
                if (nums[0] > 0 || nums[nums.length - 1] < 0) {
                    return result;
                }
                // in the loop,i is from 0 to length-3,because we need to find three numbers,
                // so the last two numbers are from i+1 to length-1
                for (int i = 0; i < nums.length - 2; i++) {
                    int left = i + 1;
                    int right = nums.length - 1;
                    // if the current number is the same as the previous number,skip it
                    if (i > 0 && nums[i] == nums[i - 1]) {
                        continue;
                    }
                    while (left < right) {
                        if (nums[i] + nums[left] + nums[right] == 0) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[left]);
                            list.add(nums[right]);
                            result.add(list);
                            left++;
                            while (left < right && nums[left] == nums[left - 1])
                                left++;
                            right--;
                            while (left < right && nums[right] == nums[right + 1])
                                right--;
                        } else if (nums[i] + nums[left] + nums[right] < 0) {
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

/**
 * 
 * some information confirmed
 * In the 3Sum problem, "duplicate triplets" refer to triplets that contain the
 * same three numbers in any order. For example, [0, 0, 0] and [0, 0, 0] are
 * considered the same triplet because the elements are identical (order doesn't
 * matter).
 * 
 * For the input nums = [0,0,0,0,0,0], all possible triplets are (0, 0, 0).
 * Since there's only one unique triplet, the output should contain just one
 * result: [[0,0,0]].
 * 
 * Hint for implementation: To avoid duplicate triplets, a common approach is to
 * sort the array and skip over duplicate values while iterating or using two
 * pointers.
 * 
 */
