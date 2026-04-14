package com.happy.alg;//Given a 32-bit signed integer, reverse digits of an integer.

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

public class LeetCode007a {

    public int reverse(int x) {
        // deal corner case
        boolean flag = x > 0 ? true : false;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            int tmp = x % 10;
            if ((Integer.MAX_VALUE - tmp) / 10 < res) {
                return 0;
            }
            res = 10 * res + tmp;
            x = x / 10;
        }
        return flag ? res : -res;
    }

    public static void main(String[] args) {
        LeetCode007a s = new LeetCode007a();
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(s.reverse(2147483647));// 7463847421
        System.out.println(s.reverse(-2147483412));// 0
        System.out.println(s.reverse(12345678));// 87654321
        System.out.println(s.reverse(-1234567899));// -9987654321

    }
}
