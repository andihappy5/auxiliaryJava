package com.happy.alg;

public class LeetCode560 {
    //560. Subarray Sum Equals K
    /**
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     * Example 2:
     *
     * Input: nums = [1,2,3], k = 3
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 104
     * -1000 <= nums[i] <= 1000
     * -107 <= k <= 107
     * */

    public static void main(String[] args) {
        LeetCode560 leetCode560 = new LeetCode560();
        System.out.println(leetCode560.subarraySum(new int[]{1,1,1},2));
    }

    public int subarraySum(int[] nums, int k) {
        return dg(nums,k,0,nums.length-1);
    }
    public int dg(int[] nums,int k,int start,int end){
        if(start==end) return(nums[start]==k?1:0);
        if(nums[start]==k){
            return 1+help(nums,k-nums[start],start+1)+dg(nums,k,start+1,end);
        }else{
            return help(nums,k-nums[start],start+1)+dg(nums,k,start+1,end);
        }
    }
    public int help(int[] nums,int k,int start){
        int res=0,sum=0;
        for(int i=start;i<nums.length;i++){
            sum+=nums[i];
            if(sum==k) res++;
        }
        return res;
    }


}
