package com.happy.day;

public class Day202604224 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <=3 ) return -1;
        int start = 0;
        int end = nums.length-1;
        while (start < end){
            if(start+2 == end){
                if (nums[start+1] > nums[end] && nums[start+1] > nums[start]){
                    return start+1;
                }
                return -1;
            }
            int mid = start + (end-start)/2;
            if (nums[mid] > nums[start] && nums[mid] > nums[mid-1]){
                start = mid+1;
            }

            if (nums[mid] > nums[start] && nums[mid] > nums[mid+1]){
                end = mid-1;
            }
        }
        return -1;
    }
}
