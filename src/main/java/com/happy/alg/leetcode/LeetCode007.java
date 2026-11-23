package com.happy.alg.leetcode;//Given a 32-bit signed integer, reverse digits of an integer.
//
// Note: 
//Assume we are dealing with an environment that could only store integers withi
//n the 32-bit signed integer range: [−231, 231 − 1]. For the purpose of this prob
//lem, assume that your function returns 0 when the reversed integer overflows. 
//
// 
// Example 1: 
// Input: x = 123
//Output: 321
// Example 2: 
// Input: x = -123
//Output: -321
// Example 3: 
// Input: x = 120
//Output: 21
// Example 4: 
// Input: x = 0
//Output: 0
// 
// 
// Constraints: 
//
// 
// -231 <= x <= 231 - 1 
// 

public class LeetCode007 {

    class Solution {
        public int reverse(int x) {
            //负数转化为整数，最后再变回负数
            boolean flag = x > 0 ? true : false;
            if (x < 0)
                x = Math.abs(x);
            int result = 0;
            while (x > 0) {
                int tmp = x % 10;
                //判断溢出的公式
                if (result > (Integer.MAX_VALUE - tmp) / 10) {
                    return 0;
                }
                result = result * 10 + tmp;
                x = x / 10;
            }
            return flag? result : -result;
        }
    }
    
    public static int change(int a) {
        int flag = a > 0 ? 1 : -1;
        if (a < 0)
            a = Math.abs(a);
        int result = 0;
        while (a > 0) {
            int tmp = a % 10;
            if (result > (Integer.MAX_VALUE - tmp) / 10) {
                return 0;
            }
            result = result * 10 + tmp;
            a = a / 10;
        }
        return result * flag;
    }

    public static void main(String[] args) {
        LeetCode007 leetCode007 = new LeetCode007();
        LeetCode007.Solution s = leetCode007.new Solution();
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(change(2147483647));// 7463847421
        System.out.println(s.reverse(2147483647));// 7463847421

        System.out.println(change(-2147483648));// 0
        System.out.println(s.reverse(-2147483648));// 0

        System.out.println(change(12345678));// 87654321
        System.out.println(s.reverse(12345678));// 87654321

        System.out.println(change(-1234567899));// -9987654321
        System.out.println(s.reverse(-1234567899));// -9987654321


    }
}
