package com.happy.alg.customUtil;

import java.util.Arrays;

public class CoinChange {

    // Coin Change II（Count Combinations）
    class SolutionForMethods {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1; // base case: 0 has 1 way (empty)

            for (int coin : coins) {          // outer: each coin type
                for (int i = coin; i <= amount; i++) { // inner: amount ascending
                    dp[i] += dp[i - coin];
                }
            }
            return dp[amount];
        }
    }


    class SolutionMinNum {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1); // sentinel: impossible
            dp[0] = 0;

            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (coin <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    class Solution {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i] = i; // worst case: all 1s
                for (int j = 1; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            return dp[n];
        }
    }

    class Solution2 {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int i = 1; i <= target; i++) {       // outer = amount（排列）
                for (int num : nums) {
                    if (num <= i) {
                        dp[i] += dp[i - num];
                    }
                }
            }
            return dp[target];
        }
    }


}
