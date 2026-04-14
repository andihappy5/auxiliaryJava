package com.happy.alg;

//Given a string s, return the longest palindromic substring in s.
//
// 
// Example 1: 
//
// 
//Input: s = "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: "bb"
// 
//
// Example 3: 
//
// 
//Input: s = "a"
//Output: "a"
// 
//
// Example 4: 
//
// 
//Input: s = "ac"
//Output: "a"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consist of only digits and English letters (lower-case and/or upper-case), 
//

public class LeetCode005 {

    class Solution1 {
        // Longest Palindromic Substring
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2)
                return s;
            int max = 0, from = 0;
            for (int i = 0; i < s.length(); i++) {
                int len = findLongestPalindrome(s, i, i);
                if (len > max) {
                    max = len;
                    from = i - (len / 2);
                }
                int len2 = findLongestPalindrome(s, i, i + 1);
                if (len2 > max) {
                    max = len2;
                    from = i - (len2 / 2 - 1);
                }
            }
            return s.substring(from, from + max);
        }

        private static int findLongestPalindrome(String s, int i, int j) {
            int length = 0;
            while (i > -1 && j < s.length()) {
                if (s.charAt(i) == s.charAt(j)) {
                    length = j - i + 1;
                } else {
                    break;
                }
                i--;
                j++;
            }
            return length;
        }
    }

    public static String longestPalindrome_dp(String s) {
        if (s == null || s.length() < 2)
            return s;
        int i = s.length();
        int max = 0, from = 0, to = 0;
        boolean[][] dp = new boolean[i][i];
        for (int j = dp.length - 1; j >= 0; j--) {
            for (int j2 = j; j2 < dp.length; j2++) {
                dp[j][j2] = (j2 - j <= 1 && s.charAt(j) == s.charAt(j2))
                        || (dp[j + 1][j2 - 1] && s.charAt(j) == s.charAt(j2));
                if (dp[j][j2] && (j2 - j + 1 >= max)) {
                    max = j2 - j + 1;
                    from = j;
                    to = j2;
                }
            }
        }
        return s.substring(from, to + 1);
    }

    class solution {
        public String longestPalindrome_DP(String s) {
            // deal special case
            if (s == null || s.length() < 2)
                return s;
            // use dp method，dp[i][j] represents from i index to j index of string s is
            // palindrome or not
            boolean[][] dp = new boolean[s.length()][s.length()];
            // initialize dp, the length of palindrome substring is 1, so dp[i][i] is true
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }
            int max = 1; // the length of longest palindrome substring
            int from = 0; // the start index of longest palindrome substring
            int to = 0; // the end index of longest palindrome substring

            // update dp, the length of palindrome substring is from 2 to s.length(), so we
            // can use a loop to update dp
            for (int j = 0; j < s.length(); j++) {
                for (int i = 0; i < j; i++) {
                    if ((j - i <= 1 && s.charAt(i) == s.charAt(j)
                            || (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)))) {
                        dp[i][j] = true;
                    }
                    if (dp[i][j] && j - i + 1 > max) {
                        max = j - i + 1;
                        from = i;
                        to = j;
                    }
                }
            }
            return s.substring(from, to + 1);
        }

        public String longestPalindrome(String s) {
            // deal special case
            if (s == null || s.length() < 2) {
                return s;
            }
            int len = 1;
            int index = 0;
            // beacse the longest palindrome substring must be less than or equal to the
            // length of s, the loop can be from 0 to s.length()
            // so we can use a loop to find the longest palindrome substring
            for (int i = 1; i < s.length(); i++) {
                int length = isPalindrome(s, i);
                if (length > len) {
                    len = length;
                    index = i;
                }
            }
            if (len % 2 == 0) {
                return s.substring(index - (len / 2 - 1), index + len / 2 + 1);
            } else {
                return s.substring(index - len / 2, index + len / 2 + 1);
            }
        }

        private int isPalindrome(String s, int i) {
            // there are two cases, one is the length of the palindrome substring is odd,
            // the other is the length of the palindrome substring is even
            int len1 = 1;
            int len2 = 0;
            // case 1: the length of the palindrome substring is odd
            for (int j = 1; i - j >= 0 && i + j < s.length(); j++) {
                if (s.charAt(i - j) == s.charAt(i + j)) {
                    len1 += 2;
                } else {
                    break;
                }
            }
            // case 2: the length of the palindrome substring is even
            for (int j = 0; i - j >= 0 && i + j + 1 < s.length(); j++) {
                if (s.charAt(i - j) == s.charAt(i + j + 1)) {
                    len2 += 2;
                } else {
                    break;
                }
            }
            return Math.max(len1, len2);
        }
    }

    public static void main(String[] args) {
        LeetCode005 leetCode005 = new LeetCode005();
        LeetCode005.solution solution = leetCode005.new solution();
        System.out.println(solution.longestPalindrome("cabac"));
        System.out.println(longestPalindrome_dp("cabac"));
        System.out.println(solution.longestPalindrome_DP("cabac"));

        System.out.println(longestPalindrome_dp("cbbd"));
        System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindrome_DP("cbbd"));

        System.out.println(longestPalindrome_dp("eabcb"));
        System.out.println(solution.longestPalindrome("eabcb"));
        System.out.println(solution.longestPalindrome_DP("eabcb"));

        System.out.println(longestPalindrome_dp("babad"));
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome_DP("babad"));

    }
}
