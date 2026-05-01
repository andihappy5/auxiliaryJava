package com.happy.alg;

/**
 * /**
 * 
 * Implement strStr().
 * 
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Clarification:
 * 
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string.
 * This is consistent to C's strstr() and Java's indexOf().
 * 
 * Example 1:
 * 
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * 
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * 
 * Example 3:
 * Input: haystack = "", needle = ""
 * Output: 0
 * 
 * 
 * Constraints:
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack and needle consist of only lower-case English characters.
 *
 */
public class LeetCode028 {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(strStr("keepmovekeephappy", "move"));
        System.out.println(strStr_2("a", "a"));
        LeetCode028 leetcode = new LeetCode028();
        LeetCode028.Solution solution = leetcode.new Solution();
        System.out.println(solution.strStr("keepmovekeephappy", "move"));
        System.out.println(solution.strStr("a", "a"));
        System.out.println(solution.strStr("leetcode", "leeto"));
    }

    class Solution2 {
        public int strStr(String ss, String pp) {
            int n = ss.length(), m = pp.length();
            char[] s = ss.toCharArray(), p = pp.toCharArray();
            // 枚举原串的「发起点」
            for (int i = 0; i <= n - m; i++) {
                // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
                int a = i, b = 0;
                while (b < m && s[a] == p[b]) {
                    a++;
                    b++;
                }
                // 如果能够完全匹配，返回原串的「发起点」下标
                if (b == m)
                    return i;
            }
            return -1;
        }
    }

    class Solution {
        public int strStr(String haystack, String needle) {
            if (needle == null || needle.length() == 0)
                return 0;
            if (haystack == null || needle.length() > haystack.length())
                return -1;
            for (int i = 0; i < haystack.length(); i++) {
                if (haystack.charAt(i) == needle.charAt(0)) {
                    int j = 1;
                    while (j < needle.length() && i + j < haystack.length()
                            && haystack.charAt(i + j) == needle.charAt(j)) {
                        j++;
                    }
                    if (j == needle.length())
                        return i;
                }
            }
            return 0;
        }
    }

    /**
     * 这道题目有意思，需要仔细的想一遍
     */
    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length())
            return -1;

        int len = haystack.length(), i = 0, j = 0, pos = 0;
        while (i < len && j < needle.length()) {
            if (haystack.charAt(i++) == needle.charAt(j)) {
                j++;
            } else {
                i = i - j;
                j = 0;
                pos = i;
            }
        }
        return j == needle.length() ? pos : -1;
    }

    /**
     * 模拟JDK的风格
     */
    public static int strStr_2(String source, String target) {
        if (target == null)
            return -1;
        if (target.isEmpty())
            return 0;
        if (!target.isEmpty()) {
            char first = target.charAt(0);
            int max = source.length() - target.length();
            for (int i = 0; i <= max; i++) {
                /** Look for fisrt charscter. */
                if (source.charAt(i) != first) {
                    while (++i <= max && source.charAt(i) != first)
                        ;
                }

                /* Found first character,now look at the rest of v2 */
                if (i <= max) {
                    int j = i + 1;
                    int end = j + target.length() - 1;
                    for (int k = 1; j < end && source.charAt(j) == target.charAt(k); j++, k++)
                        ;
                    if (j == end) {
                        /* Found whole string */
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    static class Day20260417 {
        static void main() {
            System.out.println(strStr("sadbutsad", "sad"));
            System.out.println(strStr("leetcode", "leeto"));
            System.out.println(strStr("leetcode", "eet"));
        }

        public static int strStr(String haystack, String needle) {
            // empty needle appears everywhere, first appears at 0 index
            if (needle.length() == 0)
                return 0;
            if (haystack.length() == 0)
                return -1;

            for (int i = 0; i < haystack.length(); i++) {
                // no enough places for needle after i
                if (i + needle.length() > haystack.length())
                    break;

                for (int j = 0; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j))
                        break;
                    if (j == needle.length() - 1)
                        return i;
                }
            }

            return -1;
        }
    }
}
