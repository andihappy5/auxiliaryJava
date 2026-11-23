package com.happy.alg.leetcode;

public class LeetCode2235_Add_Two_Integers {

    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode2235_Add_Two_Integers l = new LeetCode2235_Add_Two_Integers();
        LeetCode2235_Add_Two_Integers.Solution s = l.new Solution();
        System.out.println(s.sum(12,5));//17
        System.out.println(s.sum(-10,4));//-6
        System.out.println(s.sum(-2147483648,-1));//-2147483648
        System.out.println(s.sum(2147483647,1));//2147483647
    }

    class Solution {
        public int sum(int num1, int num2) {
            if (num1 > 0 && num2 > 0) {
                if (Integer.MAX_VALUE - num1 < num2) {
                    return Integer.MAX_VALUE;
                }else{
                    return num1+num2;
                }
            }
            if (num1 < 0 && num2 < 0) {
                if (num1 + num2 > 0 ) {
                    return Integer.MIN_VALUE;
                }else{
                    return num1+num2;
                }
            }
            return num1+num2;
        }
    }
}
