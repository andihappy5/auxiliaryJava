package com.happy.alg;

import java.util.Stack;

public class LeetCode032 {

    /**
     * Given a string containing just the characters '(' and ')', find the length of
     * the longest valid (well-formed) parentheses substring.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()".
     * Example 2:
     * 
     * Input: s = ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()".
     * Example 3:
     * 
     * Input: s = ""
     * Output: 0
     * 
     * 
     * Constraints:
     * 
     * 0 <= s.length <= 3 * 104
     * s[i] is '(', or ')'.
     * 
     **/

    public static boolean validate(String s) {
        if (null == s || s.length() <= 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (')' == s.charAt(i)) {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.peek();
                } else if ('(' == s.charAt(i)) {
                    stack.push(')');
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (validate(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;

    }

    public int longestValidParentheses_stack(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(longestValidParentheses("()()("));
    }

    static class Day202604210 {
        public static void main() {
            System.out.println(longestValidParentheses("(()"));
            System.out.println(longestValidParentheses(")()())"));
        }

        public static int longestValidParentheses(String s) {
            if (s == null || s.length() <= 1)
                return 0;
            int max = 0;
            int left = 0;
            int right = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    right++;
                }
                if (left == right) {
                    max = Math.max(left * 2, max);
                } else if (left < right) {
                    left = right = 0;
                }
            }

            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    right++;
                }
                if (left == right) {
                    max = Math.max(left * 2, max);
                } else if (left > right) {
                    left = right = 0;
                }
            }
            return max;
        }

        // dp solution
        // dp[i] represents the length of the longest valid parentheses substring ending
        // at index i
        // if s[i] is '(', then dp[i] = 0 because a valid substring cannot end with '('
        // if s[i] is ')', then we check the character before it:
        // if s[i-1] is '(', then we have a valid pair "()", so dp[i] = dp[i-2] + 2 (if
        // i >= 2)
        // if s[i-1] is ')', then we check if the character before the valid substring
        // ending at i-1 is '('
        // if s[i - dp[i-1] - 1] is '(', then we have a valid substring, so dp[i] =
        // dp[i-1] + 2 + dp[i - dp[i-1] - 2] (if i - dp[i-1] >= 2)
        // we keep track of the maximum length of valid parentheses substring found so
        // far
        public int longestValidParenthesesDP(String s) {
            if (s == null || s.length() <= 1)
                return 0;
            int n = s.length();
            int[] dp = new int[n];
            dp[0] = 0;
            int maxLen = 0;
            for (int i = 1; i < n; i++) {
                // only when s[i] is ')' can we have a valid substring ending at i
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
            return maxLen;
        }
    }
}
