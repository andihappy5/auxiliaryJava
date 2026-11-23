package com.happy.alg.leetcode;

/**
 29. Divide Two Integers
 中等
 Given two integers dividend and divisor,
 divide two integers without using multiplication, division, and mod operator.

 The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

 Return the quotient after dividing dividend by divisor.

 Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.



 Example 1:

 Input: dividend = 10, divisor = 3
 Output: 3
 Explanation: 10/3 = 3.33333.. which is truncated to 3.
 Example 2:

 Input: dividend = 7, divisor = -3
 Output: -2
 Explanation: 7/-3 = -2.33333.. which is truncated to -2.


 Constraints:

 -231 <= dividend, divisor <= 231 - 1
 divisor != 0

 * */
public class LeetCode029 {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(0-Integer.MIN_VALUE);
        System.out.println(divide_3(Integer.MIN_VALUE,-2));
        System.out.println(divide_3(Integer.MAX_VALUE,-2));
        System.out.println(divide(10,5));
        System.out.println(divide(10,10));

        LeetCode029 l = new LeetCode029();
        LeetCode029.Solution s = l.new Solution();
        System.out.println(s.divide(10,3));
        System.out.println(s.divide(7,-3));
        System.out.println(s.divide(1,1));
        System.out.println(s.divide(-1,1));
        System.out.println(s.divide(-1,-1));
        System.out.println(s.divide(Integer.MIN_VALUE,-1));
        System.out.println(s.divide(Integer.MIN_VALUE,1));
        System.out.println(s.divide(Integer.MIN_VALUE,-2));
        System.out.println(s.divide(Integer.MAX_VALUE,2));

        LeetCode029.Solution3 s3 = l.new Solution3();
        System.out.println(s.divide(0,1));

    }

    class Solution3 {
        public int divide(int dividend, int divisor) {
            if(dividend==Integer.MIN_VALUE &&divisor==-1) return Integer.MAX_VALUE;
            if(dividend==Integer.MIN_VALUE &&divisor==1) return Integer.MIN_VALUE;
            if (dividend== 0 || divisor == 0) return 0;
            if(dividend>0 && divisor>0) return divide(-dividend, -divisor);
            if(dividend>0 && divisor<0) return -divide(-dividend, divisor);
            if(dividend<0 && divisor>0) return -divide(dividend, -divisor);
            if(dividend>divisor) return 0;
            int res = 1, tmp = divisor;
            while((dividend-divisor)<=divisor)
            {
                res+=res;
                divisor+=divisor;
            }
            return res+divide(dividend-divisor, tmp);
        }
    }

    class Solution2 {
        public int divide(int dividend, int divisor) {
            if(dividend==Integer.MIN_VALUE &&divisor==-1) return Integer.MAX_VALUE;
            if(dividend==Integer.MIN_VALUE &&divisor==1) return Integer.MIN_VALUE;
            if (divisor == 0) return 0;
            if(dividend>0) return -divide(-dividend, divisor);
            if(divisor>0) return -divide(dividend, -divisor);
            if(dividend>divisor) return 0;
            int res = 1, tmp = divisor;
            while((dividend-divisor)<=divisor)
            {
                res+=res;
                divisor+=divisor;
            }
            return res+divide(dividend-divisor, tmp);
        }
    };


    class Solution {

        int MIN = Integer.MIN_VALUE;
        int MAX = Integer.MAX_VALUE;
        int LIMIT = -1073741824; // MIN 的一半

        public int divide(int dividend, int divisor) {
            if (dividend == MIN && divisor == -1) return MAX;
            boolean flag = false;
            if ((dividend > 0 && divisor <0) || (dividend < 0 && divisor > 0)) flag = true;
            if (dividend > 0) dividend = -dividend;
            if (divisor > 0) divisor = -divisor;
            int ans = 0;
            while (dividend <= divisor){
                int c = divisor;int d = -1;
                while(c >= LIMIT && d >= LIMIT && c >= (dividend -c)){
                    c += c;
                    d += d;
                }
                dividend -= c;
                ans+=d;
            }
            return flag? ans:-ans;
        }
    }

    /***
     Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

     Return the quotient after dividing dividend by divisor.

     The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

     Note:

     Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.


     Example 1:

     Input: dividend = 10, divisor = 3
     Output: 3
     Explanation: 10/3 = truncate(3.33333..) = 3.
     Example 2:

     Input: dividend = 7, divisor = -3
     Output: -2
     Explanation: 7/-3 = truncate(-2.33333..) = -2.
     Example 3:

     Input: dividend = 0, divisor = 1
     Output: 0
     Example 4:

     Input: dividend = 1, divisor = 1
     Output: 1


     Constraints:

     -231 <= dividend, divisor <= 231 - 1
     divisor != 0

     *
     * */

    /**
     * 最主要的是关于 特殊情况的处理
     * 一些需要注意的点：
     * Integer.MIN_VALUE: -2147483648
     * Math.abs(Integer.MIN_VALUE) = -2147483648
     * */

    public static int divide_2(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }

    public static int divide(int A, int B) {
        if (A == Integer.MIN_VALUE && B == -1) return Integer.MAX_VALUE;
        int a = Math.abs(A);
        int b = Math.abs(B);
        int res = 0;
        int x = 0;

        // if a > b >= 0, a=Integer.MIN_VALUE b = 1 a-b out of Integer ，is Positive，wonderful
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }

        return (A > 0) == (B > 0) ? res : -res;
    }

    public static int divide_3(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        if(dividend > 0 && divisor > 0) return divideHelper(-dividend, -divisor);
        else if(dividend > 0) return -divideHelper(-dividend,divisor);
        else if(divisor > 0) return -divideHelper(dividend,-divisor);
        else return divideHelper(dividend, divisor);
    }

    private static int divideHelper(int dividend, int divisor) {
        int result = 0;
        int currentDivisor = divisor;

        while(true) {
            if(dividend > divisor) {
                break;
            }

            int temp = 1;
            while(dividend <= currentDivisor << 1 && currentDivisor << 1 < 0) {
                temp = temp << 1;
                currentDivisor = currentDivisor << 1;
            }

            dividend -= currentDivisor;
            result += temp;
            currentDivisor = divisor;
        }

        return result;
    }



    static final public int divide_error (int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(dividend == Integer.MIN_VALUE && divisor== -1) return Integer.MAX_VALUE;
        if(dividend == Integer.MIN_VALUE && divisor== 1) return Integer.MIN_VALUE;

        int flag = ( (dividend >0 && divisor > 0)  || (dividend < 0 && divisor < 0) ) ?1:-1;

        if(dividend == Integer.MIN_VALUE) dividend= Integer.MAX_VALUE;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int result = -1;
        while (dividend >= 0){
            dividend  =  dividend - divisor;
            result++;
        }
        return flag*result;
    }
}
