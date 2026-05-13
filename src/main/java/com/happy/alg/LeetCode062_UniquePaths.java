package com.happy.alg;

import java.util.Arrays;

public class LeetCode062_UniquePaths {
    // Unique Paths
    // There is a robot on an m x n grid.
    // The robot is initially located at the top-left corner (i.e., grid[0][0]).
    // The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
    // The robot can only move either down or right at any point in time.
    //
    //Given the two integers m and n, return the number of possible unique paths
    //that the robot can take to reach the bottom-right corner.
    //
    //The test cases are generated so that the answer will be less than or equal to 2 * 109.

    // xample 1:
    //
    //
    //Input: m = 3, n = 7
    //Output: 28
    //Example 2:
    //
    //Input: m = 3, n = 2
    //Output: 3
    //Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    //1. Right -> Down -> Down
    //2. Down -> Down -> Right
    //3. Down -> Right -> Down

    public static void main() {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        return uniquePaths(m, n, 0, 0);
    }

    // Time Limit Exceeded
    private static int uniquePaths(int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i >= m) return 0;
        if (j >= n) return 0;

        if (i == m - 1 || j == n - 1) {
            return 1;
        }

        if (i < m && j < n) {
            return uniquePaths(m, n, i + 1, j) + uniquePaths(m, n, i, j + 1);
        }
        return 0;
    }

    static class Solution {
        // use DP
        // dp[i][j] = dp[i-1][j] + above dp[i][j-1]
        // currentRow[col] = currentRow[col - 1] + aboveRow[col];
        public int uniquePaths(int m, int n) {
            int[] aboveRow = new int[n];
            Arrays.fill(aboveRow, 1);

            for (int row = 1; row < m; row++) {
                int[] currentRow = new int[n];
                Arrays.fill(currentRow, 1);
                for (int col = 1; col < n; col++) {
                    currentRow[col] = currentRow[col - 1] + aboveRow[col];
                }
                aboveRow = currentRow;
            }

            return aboveRow[n - 1];
        }
    }

    static class SolutionChangeDPStructure  {
        static void main() {
            System.out.println(new SolutionChangeDPStructure().uniquePaths(3, 7));
        }
        // use DP
        // dp[i][j] = dp[i-1][j] + above dp[i][j-1]
        // currentRow[col] = currentRow[col - 1] + aboveRow[col];
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            Arrays.fill(dp[0], 1);
            for (int i = 1; i < m; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i-1][j] +dp[i][j-1];
                }
            }
            return dp[m - 1][n-1];
        }
    }
}

