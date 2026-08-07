package com.happy.alg;

public class LeetCode044 {
    // Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
    //
    //'?' Matches any single character.
    //'*' Matches any sequence of characters (including the empty sequence).
    //The matching should cover the entire input string (not partial).
    //
    //
    //
    //Example 1:
    //
    //Input: s = "aa", p = "a"
    //Output: false
    //Explanation: "a" does not match the entire string "aa".
    //Example 2:
    //
    //Input: s = "aa", p = "*"
    //Output: true
    //Explanation: '*' matches any sequence.
    //Example 3:
    //
    //Input: s = "cb", p = "?a"
    //Output: false
    //Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
    //
    //
    //Constraints:
    //
    //0 <= s.length, p.length <= 2000
    //s contains only lowercase English letters.
    //p contains only lowercase English letters, '?' or '*'.
    //

    //-----------------------
    /**
     * 动态规划
     *
     * dp[i][j]表示s到i位置,p到j位置是否匹配!
     *
     * 初始化:
     *
     * dp[0][0]:什么都没有,所以为true
     * 第一行dp[0][j],换句话说,s为空,与p匹配,所以只要p开始为*才为true
     * 第一列dp[i][0],当然全部为False
     * 动态方程:
     *
     * 如果(s[i] == p[j] || p[j] == "?") && dp[i-1][j-1] ,有dp[i][j] = true
     *
     * 如果p[j] == "*" && (dp[i-1][j] = true || dp[i][j-1] = true) 有dp[i][j] = true
     *
     * note:
     *
     * dp[i-1][j],表示*代表非空任何字符,例如abcd,ab*
     * dp[i][j-1],表示*代表是空字符,例如ab,ab*
     * 例子 abcd 匹配 ab*：
     * dp[2][3]：s=ab 匹配 ab*（* 匹配 0 个） → true
     * dp [3][3] = dp [2][3] → true：* 吃掉 c（1 个字符）
     * dp [4][3] = dp [3][3] → true：* 吃掉 d（2 个字符）
     * 不需要去查 dp [4][0], dp [4][1], dp [4][2]，因为 dp [i‑1][j] 已经继承前面所有 “已经吞了 k 个字符” 的结果。
     * **DP 的状态已经封装了 “已经匹配任意长度前缀”，不需要暴力遍历所有 k。**
     * */

    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < p.length() + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j)=='?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
