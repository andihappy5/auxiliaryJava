package com.happy.hankerrank;

import java.util.Arrays;
import java.util.List;

public class HankerRankDP {
    public static void main(String[] args) {
       List<Integer> planSizes = List.of(1, 3, 4, 7);
       int targetBandwidth = 10;
       int result = findMinimumPlansForBandwidth(planSizes, targetBandwidth);
       System.out.println("Minimum plans needed: " + result);
    }

    public static int findMinimumPlansForBandwidth(
            List<Integer> planSizes, int targetBandwidth) {

        int unreachable = targetBandwidth + 1;
        int[] dp = new int[targetBandwidth + 1];

        Arrays.fill(dp, unreachable);
        dp[0] = 0;

        for (int size : planSizes) {
            // Ascending order allows the same plan to be reused.
            for (int s = size; s <= targetBandwidth; s++) {
                dp[s] = Math.min(dp[s],dp[s - size] + 1);
            }
            // System.out.println("After processing plan size " + size + ", dp array: " + Arrays.toString(dp));
        }

        return dp[targetBandwidth] == unreachable? -1 : dp[targetBandwidth];
    }

    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: 1 way to stay on the ground
        dp[1] = 1; // Base case: 1 way to reach the first step

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // The number of ways to reach step i
        }
        return dp[n];
    }
}