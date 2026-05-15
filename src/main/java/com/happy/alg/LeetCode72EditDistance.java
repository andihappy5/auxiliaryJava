package com.happy.alg;

import java.util.Arrays;

public class LeetCode72EditDistance {
    // 72. Edit Distance
    /**
     * Given two strings word1 and word2, return the minimum number of operations
     * required to convert word1 to word2.
     * 
     * You have the following three operations permitted on a word:
     * 
     * Insert a character
     * Delete a character
     * Replace a character
     * 
     * 
     * Example 1:
     * 
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * Explanation:
     * horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     * Example 2:
     * 
     * 
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5
     * Explanation:
     * intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     * 
     * Constraints:
     * 
     * 0 <= word1.length, word2.length <= 500
     * word1 and word2 consist of lowercase English letters.
     * 
     */

    // have no clue or idea，how to think？
    // I know is possible to use DP or backtracting,How?
    // analysis
    // DP
    /**
     * word1 = "a b d"
     * word2 = "a c d"
     * if(w[1] == w[2]) {
     * [i,j] = [i+1,j+1]
     * }else{
     * insert: [i,j] = [i,j+1]+1
     * delete: [i,j] = [i+1,j]+1
     * replace: [i,i] = [i+1,j+1]
     * }
     * 
     */

    public static void main(String[] args) {
        // System.out.println(minDistance("intention", "execution"));
        // System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("sea", "ate"));
        System.out.println(minDistance2("sea", "ate"));

    }

    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }

        // print(dp);
        return dp[word1.length()][word2.length()];

    }

    public static int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            cost[i][0] = i;
        for (int i = 1; i <= n; i++)
            cost[0][i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        // print(cost);
        return cost[m][n];
    }

    private static void print(int[][] dp) {
        for (int[] is : dp) {
            System.out.println(Arrays.toString(is));
        }
    }
}
