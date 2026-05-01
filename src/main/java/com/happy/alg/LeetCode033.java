package com.happy.alg;

/**
 33. Search in Rotated Sorted Array
 There is an integer array nums sorted in ascending order (with distinct values).

 Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

 Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

 You must write an algorithm with O(log n) runtime complexity.
 Example 1:

 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4
 Example 2:

 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1
 Example 3:

 Input: nums = [1], target = 0
 Output: -1


 Constraints:

 1 <= nums.length <= 5000
 -104 <= nums[i] <= 104
 All values of nums are unique.
 nums is an ascending array that is possibly rotated.
 -104 <= target <= 104
 * */
public class LeetCode033 {

    /**

     33. Search in Rotated Sorted Array

     You are given an integer array nums sorted in ascending order (with distinct values), and an integer target.
       给定了一个升序的数组，一个目标值
     Suppose that nums is rotated 旋转 at some pivot 轴 unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     If target is found in the array return its index, otherwise, return -1.



     Example 1:

     Input: nums = [4,5,6,7,0,1,2], target = 0
     Output: 4
     Example 2:

     Input: nums = [4,5,6,7,0,1,2], target = 3
     Output: -1
     Example 3:

     Input: nums = [1], target = 0
     Output: -1


     Constraints:

     1 <= nums.length <= 5000
     -104 <= nums[i] <= 104
     All values of nums are unique.
     nums is guaranteed to be rotated at some pivot.
     -104 <= target <= 104

     */

    public static int search(int[] nums, int target) {
        if(null == nums || nums.length == 0) return -1;
        if(nums.length == 1) return nums[0]==target?0:-1;
        return search(nums,nums.length,target);
    }



    static int search(int A[], int n, int target) {
        //find the index of the smallest value using binary search.
        int from = 0; int to = n-1;
        while(from < to){
            int mid = from+ (to-from)/2;
            if(A[mid] > A[to]){
                from=mid+1;
            }else{
                to = mid;
            }
        }

//        to  will be the rotated index
        int rotated = to, lo=0,hi=n-1;
        while(lo <= hi){
            int mid = lo+(hi-lo)/2;
            int realmmid = (mid+rotated)%n;
            if(A[realmmid] == target) return realmmid;
            if(A[realmmid] > target){
                hi = mid-1;
            }else {
                lo = mid+1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println("keep happy ！");
//        System.out.println(search(new int[]{4,5,6,7,0,1,2},1));
        System.out.println(search(new int[]{4,5,6,7,0,1,2},5));
    }

    static class Day202604211 {
        public static void main(String[] args) {
            System.out.println("Keep Happy!");
            System.out.println(search(new int[]{1,3,5},5));
            System.out.println(search(new int[]{1,3},3));
            System.out.println(search(new int[]{3,1},1));
            System.out.println(search(new int[]{4,5,6,7,0,1,2},1));
            System.out.println(search(new int[]{4,5,6,7,8,0,1,2},1));
            System.out.println(search(new int[]{4,5,0,1,2},1));
        }

        class Solution {
            public int search(int[] nums, int target) {
                int start = 0, end = nums.length - 1;
                while (start <= end) {
                    int mid = start + (end - start) / 2;
                    if (nums[mid] == target) return mid;
                    // Check if left part is sorted
                    if (nums[mid] >= nums[start]) {
                        if (target >= nums[start] && target < nums[mid]) {
                            end = mid - 1; // move left
                        } else {
                            start = mid + 1; // move right
                        }
                    }
                    // Right part is sorted
                    else {
                        if (target > nums[mid] && target <= nums[end]) {
                            start = mid + 1; // move right
                        } else {
                            end = mid - 1; // move left
                        }
                    }
                }
                return -1;
            }
        }

        /**
         Input: nums = [4,5,6,7,0,1,2], target = 0
         Output: 4
         Input: nums = [4,5,6,7,0,1,2], target = 3
         Output: -1
         * */
        public static  int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) return -1;
            return search(nums, target,0,nums.length - 1);
        }

        private static int search(int[] nums, int target, int from, int end) {
            if (from > end) return -1;
            if (from == end) return nums[from]==target?from: -1;
            int mid = from +(end-from)/2;
            if (nums[mid] == target) return mid;
            if (mid == from) return nums[end] == target?end: -1;

            // //4,5,6,7,0,1,2
            //
            //首先确定 mid 所在的位置，这个位置的判断，可以使用 nums[mid] 和 nums[from] 比较 还是nums[mid] 和 nums[ end] 比较
            // nums[mid] 和 nums[from] 比较,容易判断 mid 在rotated前，还是rotated后，如果是小于nums[from]，则包含rotated，如果大于，则不包含
            if (nums[mid] > nums[from]){
                // 4,5,6,mid,7,0,1,2
                // 0,1,2,3,4,mid 5,6,7
                if (target > nums[mid]){
                    return search(nums, target, mid+1, end);
                }else {
//                nums[mid] > nums[from]  && target < nums[mid]
                    if (nums[mid] < nums[end]){
                        // target < nums[mid] && target < nums[end]
                    }
                }
                return search(nums, target, from, mid-1);
            }else{
                // 4,5,6,7,0,mid,1,2
                return search(nums, target, mid+1, end);
            }
        }
    }

}