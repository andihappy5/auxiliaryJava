package com.happy.alg.leetcode;

public class LeetCode231 {
    /**
     * Given a positive integer n, return true if it is a power of two. Otherwise, return false.
     *
     * An integer n is a power of two, if there exists an integer x such that n == 2x.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 1
     * Output: true
     * Explanation: 20 = 1
     * Example 2:
     *
     * Input: n = 16
     * Output: true
     * Explanation: 24 = 16
     * Example 3:
     *
     * Input: n = 3
     * Output: false
     *
     *
     * Constraints:
     *
     * -231 <= n <= 231 - 1
     *
     *
     * Follow up: Could you solve it without loops/recursion?
     */
    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode231 l = new LeetCode231();
        LeetCode231.Solution2 s = l.new Solution2();
        System.out.println(s.isPowerOfTwo(1));//true
        System.out.println(s.isPowerOfTwo(16));//true
        System.out.println(s.isPowerOfTwo(3));//false
    }

    class Solution2 {
        public boolean isPowerOfTwo(int n){
            if (n == 0) return true;
            if (n < 0) return false;
            while (n % 2 == 0) {
                n = n / 2;
            }
            return n == 1;
        }
    }


    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            return (n & (n - 1)) == 0;
        }
    }
}
