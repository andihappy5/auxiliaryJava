package com.happy.alg;

import java.util.Arrays;

public class LeetCode64MinimumPathSum {
    // 64. Minimum Path Sum
    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top
     * left to bottom right, which minimizes the sum of all numbers along its path.
     * 
     * Note: You can only move either down or right at any point in time.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
     * Example 2:
     * 
     * Input: grid = [[1,2,3],[4,5,6]]
     * Output: 12
     * 
     * 
     * Constraints:
     * 
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 200
     */

    public static void main(String[] args) {
        System.out.println(new LeetCode64MinimumPathSum().minPathSum(
                new int[][] {
                        { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 }
                }));
    }

    public int minPathSum(int[][] grid) {
        return minPath1(grid, grid.length - 1, grid[0].length - 1);
    }

    // one： from bottom to up
    // but Time Limit Exceeded
    private int minPath1(int[][] grid, int i, int j) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        return grid[i][j] + Math.min(minPath1(grid, i - 1, j), minPath1(grid, i, j - 1));
    }

    // 2. from top to bottom
    // but Time Limit exceeded
    public int minPathSum2(int[][] grid) {
        return calculatePathSum(grid, 0, 0, 0);
    }

    private int calculatePathSum(int[][] grid, int i, int j, int sum) {
        // Base case: we have reached the bottom right corner
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return sum + grid[i][j];
        }

        // Calculate the minimum path sum by moving right and down
        // this is very clever！
        int rightSum = Integer.MAX_VALUE;
        int downSum = Integer.MAX_VALUE;
        if (i < grid.length - 1) {
            downSum = calculatePathSum(grid, i + 1, j, sum + grid[i][j]);
        }
        if (j < grid[0].length - 1) {
            rightSum = calculatePathSum(grid, i, j + 1, sum + grid[i][j]);
        }
        // Return the minimum path sum
        return Math.min(rightSum, downSum);
    }

    // 3. Dynamic Programming Approach (Top-Down)
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Create a memoization matrix to store the minimum path sum for each cell
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // Call the helper function to find the minimum path sum
        return minPathSum(grid, 0, 0, memo);
    }

    private int minPathSum(int[][] grid, int i, int j, int[][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        // Check if we have already calculated the minimum path sum for this cell
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Base case: we have reached the bottom right corner
        if (i == m - 1 && j == n - 1) {
            memo[i][j] = grid[i][j];
            return memo[i][j];
        }
        // Calculate the minimum path sum by moving right and down
        int rightSum = Integer.MAX_VALUE;
        int downSum = Integer.MAX_VALUE;
        if (j < n - 1) {
            rightSum = minPathSum(grid, i, j + 1, memo);
        }
        if (i < m - 1) {
            downSum = minPathSum(grid, i + 1, j, memo);
        }
        // Store the minimum path sum for this cell in the memoization matrix
        memo[i][j] = Math.min(rightSum, downSum) + grid[i][j];
        // Return the minimum path sum
        return memo[i][j];
    }

    // 4 Dynamic Programming Approach (Bottom-Up)

    public int minPathSum4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Create a memoization matrix to store the minimum path sum for each cell
        int[][] memo = new int[m][n];
        // Calculate the minimum path sum for the first row and first column
        memo[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            memo[i][0] = memo[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            memo[0][j] = memo[0][j - 1] + grid[0][j];
        }

        // Traverse the remaining cells and calculate the minimum path sum
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                memo[i][j] = Math.min(memo[i - 1][j], memo[i][j - 1]) + grid[i][j];
            }
        }
        // Return the minimum path sum to reach the bottom right corner
        return memo[m - 1][n - 1];
    }
}
