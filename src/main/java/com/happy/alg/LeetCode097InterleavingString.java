package com.happy.alg;

public class LeetCode097InterleavingString {
    /**
     * Given three strings s1, s2, and s3, find whether s3 is formed by an
     * interleaving of s1 and s2.
     * 
     * An interleaving of two strings s and t is a configuration where they are
     * divided into non-empty substrings such that:
     * 
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 +
     * t3 + s3 + ...
     * Note: a + b is the concatenation of strings a and b.
     * 
     * 
     * Example 1:
     * 
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     * Explanation: One way to obtain s3 is:
     * Split s1 into "aa" and "bcc", split s2 into "dbbc" and "a".
     * Interleave them to get "aa" + "dbbc" + "bcc" + "a" = "aadbbcbcac"
     * Example 2:
     * 
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * Output: false
     * Explanation: Notice how it is impossible to interleave s2 with any other
     * string to get s3.
     * Example 3:
     * 
     * Input: s1 = "", s2 = "", s3 = ""
     * Output: true
     * 
     * 
     * Constraints:
     * 
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1, s2, and s3 consist of lowercase English letters.
     */

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave("", "", ""));
    }

    // DP solution
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int p = s3.length();

        if (m + n != p) {
            return false;
        }

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    static class Solution {
        Boolean[][] dp;
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length())
                return false;
            dp = new Boolean[s1.length() + 1][s2.length() + 1];
            return backtrack(s1, s2, s3, 0, 0, 0);
        }

        private boolean backtrack(String s1, String s2, String s3, int i, int o, int s) {
            if (i >= s3.length()) {
                return o == s1.length() && s == s2.length();
            }
            if (dp[o][s] != null)
                return dp[o][s];
            boolean check = false;
            char c = s3.charAt(i);
            if (o < s1.length() && s1.charAt(o) == c) {
                check = backtrack(s1, s2, s3, i + 1, o + 1, s);
            }
            if (!check && s < s2.length() && s2.charAt(s) == c) {
                check = backtrack(s1, s2, s3, i + 1, o, s + 1);
            }
            return dp[o][s] = check;
        }
    }
}