package com.happy.alg.leetcode;//Determine whether an integer is a palindrome. An integer is a palindrome when
//it reads the same backward as forward. 
//
// Follow up: Could you solve it without converting the integer to a string? 
//
// 
// Example 1: 
//
// 
//Input: x = 121
//Output: true
// 
//
// Example 2: 
//
// 
//Input: x = -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes
// 121-. Therefore it is not a palindrome.
// 
//
// Example 3: 
//
// 
//Input: x = 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
// 
//
// Example 4: 
//
// 
//Input: x = -101
//Output: false
// 
//
// 
// Constraints: 
//
// 
// -231 <= x <= 231 - 1 
// 


public class LeetCode009 {

    class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0)
                return false;
            if (x == 0)
                return true;
            if (x%10 == 0)
                return false;

            int result = 0;
            int cur = x;
            while(cur > 0) {
                int tmp = cur%10;
                result = 10*result+tmp;
                cur = cur/10;
            }
            return result == x;
        }
    }



    public static boolean isPalindrome(int x) {
        // From left to right, it reads -121. From right to left, it becomes 121-.
        // Therefore it is not a palindrome.
        if (x < 0 || x != 0 && x % 10 == 0)
            return false;
        int tmp = 0;
        int origin = x;

        // -231 <= x <= 231 - 1
        while (x > 0) {
            tmp = tmp * 10 + x % 10;
            if (tmp < 0)
                return false;
            x = x / 10;
        }
        return origin == tmp;

    }

    public static void main(String[] args) {
        LeetCode009 leetCode009 = new LeetCode009();
        LeetCode009.Solution s = leetCode009.new Solution();
        System.out.println(s.isPalindrome(121));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(12321));
    }
}
