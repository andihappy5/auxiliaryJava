package com.happy.alg;

public class LeetCode096UniqueBinarySearchTrees {
    /**
     * Given an integer n, return the number of structurally unique BST's (binary
     * search trees) which has exactly n nodes of unique values from 1 to n.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: n = 3
     * Output: 5
     * Example 2:
     * 
     * Input: n = 1
     * Output: 1
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 19
     * 
     */

    public static void main(String[] args) {
        System.out.println("Andihappy!");
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;// 0 个节点的树只有一种情况，就是空树,这里的定义是一种特殊情况，被使用的一种情况
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) { // 这里选则是 j 作为根节点的情况
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
