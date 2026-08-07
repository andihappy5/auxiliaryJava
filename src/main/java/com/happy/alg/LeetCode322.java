package com.happy.alg;

import java.util.Arrays;

public class LeetCode322 {
    //You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
    //
    //Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
    //
    //You may assume that you have an infinite number of each kind of coin.
    //
    //
    //
    //Example 1:
    //
    //Input: coins = [1,2,5], amount = 11
    //Output: 3
    //Explanation: 11 = 5 + 5 + 1
    //Example 2:
    //
    //Input: coins = [2], amount = 3
    //Output: -1
    //Example 3:
    //
    //Input: coins = [1], amount = 0
    //Output: 0
    //
    //
    //Constraints:
    //
    //1 <= coins.length <= 12
    //1 <= coins[i] <= 231 - 1
    //0 <= amount <= 104


    class SolutionBacktracking {
        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] memo = new int[n][amount + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1); // -1 表示没有计算过
            }

            int ans = dfs(n - 1, amount, coins, memo);
            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }

        private int dfs(int i, int c, int[] coins, int[][] memo) {
            if (i < 0) {
                return c == 0 ? 0 : Integer.MAX_VALUE / 2; // 除 2 防止下面 + 1 溢出
            }
            if (memo[i][c] != -1) { // 之前计算过
                return memo[i][c];
            }
            if (c < coins[i]) { // 只能不选
                return memo[i][c] = dfs(i - 1, c, coins, memo);
            }
            // 不选 vs 继续选
            return memo[i][c] = Math.min(dfs(i - 1, c, coins, memo), dfs(i, c - coins[i], coins, memo) + 1);
        }
    }

    class SolutionBacktrackingWithCache {
        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] f = new int[n + 1][amount + 1];
            Arrays.fill(f[0], Integer.MAX_VALUE / 2); // 除 2 防止下面 + 1 溢出
            f[0][0] = 0;
            for (int i = 0; i < n; i++) {
                for (int c = 0; c <= amount; c++) {
                    if (c < coins[i]) {
                        f[i + 1][c] = f[i][c];
                    } else {
                        f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - coins[i]] + 1);
                    }
                }
            }
            int ans = f[n][amount];
            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }
    }

    class SolutionDP {
        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] f = new int[2][amount + 1];
            Arrays.fill(f[0], Integer.MAX_VALUE / 2);
            f[0][0] = 0;
            for (int i = 0; i < n; i++) {
                for (int c = 0; c <= amount; c++) {
                    if (c < coins[i]) {
                        f[(i + 1) % 2][c] = f[i % 2][c];
                    } else {
                        f[(i + 1) % 2][c] = Math.min(f[i % 2][c], f[(i + 1) % 2][c - coins[i]] + 1);
                    }
                }
            }
            int ans = f[n % 2][amount];
            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }
    }

    class SolutionDP2 {
        public int coinChange(int[] coins, int amount) {
            int[] f = new int[amount + 1];
            Arrays.fill(f, Integer.MAX_VALUE / 2);
            f[0] = 0;
            for (int x : coins) {
                for (int c = x; c <= amount; c++) {
                    f[c] = Math.min(f[c], f[c - x] + 1);
                }
            }
            int ans = f[amount];
            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }
    }
}
