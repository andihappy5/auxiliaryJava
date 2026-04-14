package com.happy.alg;

public class LeetCode1871 {
    /*
     * 1871. Jump Game VII
     * You are given a 0-indexed binary string s and two integers minJump and
     * maxJump. In the beginning, you are standing at index 0, which is equal to
     * '0'. You can move from index i to index j if the following conditions are
     * fulfilled:
     * 
     * i + minJump <= j <= min(i + maxJump, s.length - 1), and
     * s[j] == '0'.
     * Return true if you can reach index s.length - 1 in s, or false otherwise.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "011010", minJump = 2, maxJump = 3
     * Output: true
     * Explanation:
     * In the first step, move from index 0 to index 3.
     * In the second step, move from index 3 to index 5.
     * Example 2:
     * 
     * Input: s = "01101110", minJump = 2, maxJump = 3
     * Output: false
     * 
     * 
     * Constraints:
     * 
     * 2 <= s.length <= 105
     * s[i] is either '0' or '1'.
     * s[0] == '0'
     * 1 <= minJump <= maxJump < s.length
     */

    class Solution {
        // first thought is to use dp. dp[i] means whether we can reach index i from
        // index 0, and then we can use a loop to update dp.
        // but we can find that dp[i] is true if dp[k] is true and s[i] is '0',
        // where k is from i - maxJump to i - minJump,
        // so we can use a loop to update dp, and the time complexity is
        // O(n*(maxJump-minJump)),
        public boolean canReach(String s, int minJump, int maxJump) {
            if (s.charAt(s.length() - 1) == '1')
                return false;
            boolean[] dp = new boolean[s.length()];
            dp[0] = true;
            for (int i = 0; i < dp.length; i++) {
                if (s.charAt(i) == '0') {
                    for (int j = i + minJump; j <= Math.min(i + maxJump, s.length() - 1); j++) {
                        {
                            dp[j] = s.charAt(j) == '0';
                        }
                    }
                }
            }
            return dp[s.length() - 1];
        }

        // the above solution is time limit exceeded, because we need to check whether
        // dp[k] is true
        // for k from i - maxJump to i - minJump, so we can use a variable to store the
        // number of true in dp from index 0 to index i - minJump, and then we can
        // update the variable when we update dp, so the time complexity is O(n).

        public boolean canReach2(String s, int minJump, int maxJump) {
            if (s.charAt(s.length() - 1) == '1')
                return false;
            boolean[] dp = new boolean[s.length()];
            dp[0] = true;
            int pre = 0; // the number of true in dp from index 0 to index i - minJump
            /*
             * pre 是一个“滚动维护的窗口计数器”，用来 O(1) 判断窗口里是否存在 true
             * 当 i = 4 时： 窗口是：j ∈ [4-3, 4-2] = [1,2] 你要看： dp[1], dp[2]
             * 当 i = 5 时： 窗口是：j ∈ [5-3, 5-2] = [2,3] 你要看： dp[2], dp[3]
             * + dp[i - minJump] // 加入新进入窗口的元素
             * - dp[i - maxJump - 1] // 移除旧离开窗口的元素
             */
            for (int i = minJump; i < dp.length; i++) {
                if (dp[i - minJump]) {
                    pre++;
                }
                if (i > maxJump && dp[i - maxJump - 1]) {
                    pre--;
                }
                dp[i] = pre > 0 && s.charAt(i) == '0';
            }
            return dp[s.length() - 1];
        }

    }

    public static void main(String[] args) {
        Solution solution = new LeetCode1871().new Solution();
        System.out.println(solution.canReach2("00111010", 3, 5));
        System.out.println(solution.canReach2("011010", 2, 3));
        System.out.println(solution.canReach2("01101110", 2, 3));
    }
}