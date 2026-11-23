package com.happy.alg.leetcode;

public class LeetCode2413 {
    /**
     2413. Smallest Even Multiple
     Easy
     Given a positive integer n, return the smallest positive integer that is a multiple of both 2 and n.

     Example 1:

     Input: n = 5
     Output: 10
     Explanation: The smallest multiple of both 5 and 2 is 10.
     Example 2:

     Input: n = 6
     Output: 6
     Explanation: The smallest multiple of both 6 and 2 is 6. Note that a number is a multiple of itself.

     Constraints:

     1 <= n <= 150
      * **/
    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode2413 l = new LeetCode2413();
        LeetCode2413.Solution s = l.new Solution();
        System.out.println(s.smallestEvenMultiple(5));//10
        System.out.println(s.smallestEvenMultiple(6));//6
    }

    class Solution {
        public int smallestEvenMultiple(int n) {
            if(n % 2 == 0) return n;
            return n * 2;
        }
    }
}
