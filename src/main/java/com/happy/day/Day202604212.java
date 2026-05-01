package com.happy.day;

public class Day202604212 {
    static void main() {
        System.out.println("Keep Happy!");
        System.out.println(search(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1},2));
        System.out.println(search(new int[]{1,0,1,1,1},0));
        System.out.println(search(new int[]{2,5,6,0,0,1,2},0));
        System.out.println(search(new int[]{2,5,6,0,0,1,2},3));

    }
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            if(left==right){
                return nums[left]==target;
            }
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                return true;
            }
            if(mid == left){
                return nums[right]==target;
            }

            // Ambiguous case due to duplicates
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue;
            }

            if(nums[mid]>nums[left]){
//                2,5,6,mid,0,0,1,2
//                0,1,2,3,mid,5,6,7,8
                if (nums[left]<=target && target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }else{
//                2,5,6,0,0,mid,1,2
                if (nums[mid]<target && target<=nums[right]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
        return false;
    }
}
