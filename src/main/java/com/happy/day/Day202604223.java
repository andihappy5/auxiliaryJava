package com.happy.day;

import java.util.Arrays;

public class Day202604223 {

    public static void main() {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},7)));
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        if (nums.length == 1) return nums[0]==target?new int[]{0,0}:new int[]{-1,-1};
        int p = findPosition(nums,target,false);
        int q = findPosition(nums,target,true);
        return new int[]{p,q};
    }

    private static int findPosition(int[] nums, int target,boolean isFirst) {
        int start = 0;int end = nums.length-1;
        int res = -1;
        while (start <= end){
            int mid = start + (end-start)/2;
            if (nums[mid] == target){
                res = mid;
                if(isFirst){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }else if (nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return res;
    }

    private static int findPositionLast(int[] nums, int target) {
        return 0;
    }


    public static int[] searchRangeWhile(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        if (nums.length == 1) return nums[0]==target?new int[]{0,0}:new int[]{-1,-1};
        int p = findPosition(nums,target);
        if (p == -1) {
            return new int[]{-1,-1};
        }else{
            int from = p,end = p;
            while(from>=0 && nums[from-1]==target){from--;}
            while(end< nums.length&& nums[end+1]==target){end++;}
            return new int[]{from,end};
        }
    }

    private static int findPosition(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }
        }
        return -1;
    }
}
