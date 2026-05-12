package com.happy.alg;

public class LeetCode055 {
    public static boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return true;
        if(nums.length == 1 && nums[0] >= 0) return true;
        boolean[] use = new boolean[nums.length];
        use[0] = nums[0]!=0;
        for (int i = 0; i < nums.length; i++) {
            if(!use[i]) return false;
            for (int j = 0; j <= nums[i]; j++) {
                if(i+j < nums.length){
                    use[i+j] = true;
                }else{
                    return true;
                }
            }
        }
        return use[nums.length-1];
    }

    //Use DP
    static class Solution {
        static void main() {
            System.out.println(new LeetCode055.Solution().canJump(new int[]{2,0,0}));
            System.out.println(new LeetCode055.Solution().canJump(new int[]{3,2,1,0,4}));
        }
        public boolean canJump(int[] nums) {
            if(nums == null || nums.length == 0) return true;
            if(nums.length == 1 && nums[0]> 0) return true;
            int[] dp = new int[nums.length+1]; // dp[i] 第 i 步能到的最大距离
            dp[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                dp[i] = Math.max(dp[i-1], i+nums[i-1]);
                if(dp[i] >= nums.length ){
                    return true;
                }
                if(dp[i] <= i && nums[i-1] <= 0 ) {
                    return false;
                }
            }
            return true;
        }
    }
}
