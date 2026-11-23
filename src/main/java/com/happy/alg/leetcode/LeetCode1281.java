package com.happy.alg.leetcode;

public class LeetCode1281 {
    /**

     Given an integer number n,
     return the difference between the product of its digits and the sum of its digits.


     Example 1:

     Input: n = 234
     Output: 15
     Explanation:
     Product of digits = 2 * 3 * 4 = 24
     Sum of digits = 2 + 3 + 4 = 9
     Result = 24 - 9 = 15
     Example 2:

     Input: n = 4421
     Output: 21
     Explanation:
     Product of digits = 4 * 4 * 2 * 1 = 32
     Sum of digits = 4 + 4 + 2 + 1 = 11
     Result = 32 - 11 = 21


     Constraints:

     1 <= n <= 10^5
     * */

    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode1281 l = new LeetCode1281();
        LeetCode1281.Solution s = l.new Solution();
        System.out.println(s.subtractProductAndSum(234));//15
        System.out.println(s.subtractProductAndSum(4421));//21
    }
    class Solution {
        public int subtractProductAndSum(int n) {
            int pd = 1;
            int sd = 0;
            while (n > 0) {
                int tmp = n % 10;
                pd *= tmp;
                sd += tmp;
                n = n / 10;
            }
            return pd - sd;
        }
    }
}
