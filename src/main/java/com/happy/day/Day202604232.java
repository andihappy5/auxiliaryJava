package com.happy.day;

public class Day202604232 {

    public static void main() {
        System.out.println(jump(new int[]{1,2,8,1,1,1,1,1,1,1,1,1,1,1,1,1}));

        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
    //涉及到定义具体子问题
    // 1,2,1,1,1  dp[0]=1,dp[1]=4,dp[2]=5
    public static  int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int min = 0;int max = nums.length-1;
        int[] dp = new int[nums.length+1]; //i步能达到的最远的距离
//        dp[i]=j 第 i step 最大能到距离
        dp[0] = 0;
        dp[1] = nums[0];
        for (int step = 2; step <= nums.length; step++) { //从第 2 步开始
                //计算的是第 2 步，能到的最大位置
            for (int i = dp[step-1]; i < dp[step-1]+nums[2]; i++) {
                dp[step] = Math.max(dp[step],i+ nums[i]); //这个地方不对，不应该写当前步，而应该写下一步！
            }
            if (dp[step] >= max) {
                return step;
            }
        }
        return 0;
    }
}