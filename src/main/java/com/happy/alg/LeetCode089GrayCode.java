package com.happy.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode89GrayCode {

    /**
     * An n-bit gray code sequence is a sequence of 2n integers where:
     * 
     * Every integer is in the inclusive range [0, 2n - 1],
     * The first integer is 0,
     * An integer appears no more than once in the sequence,
     * The binary representation of every pair of adjacent integers differs by
     * exactly one bit, and
     * The binary representation of the first and last integers differs by exactly
     * one bit.
     * Given an integer n, return any valid n-bit gray code sequence.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: n = 2
     * Output: [0,1,3,2]
     * Explanation:
     * The binary representation of [0,1,3,2] is [00,01,11,10].
     * - 00 and 01 differ by one bit
     * - 01 and 11 differ by one bit
     * - 11 and 10 differ by one bit
     * - 10 and 00 differ by one bit
     * [0,2,3,1] is also a valid gray code sequence, whose binary representation is
     * [00,10,11,01].
     * - 00 and 10 differ by one bit
     * - 10 and 11 differ by one bit
     * - 11 and 01 differ by one bit
     * - 01 and 00 differ by one bit
     * Example 2:
     * 
     * Input: n = 1
     * Output: [0,1]
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 16
     * 
     * 
     */

   static class Solution {
        static void main() {
            Solution s = new Solution();
            System.out.println(s.grayCode(3));
        }
        int limit; // maximum number
        List<Integer> ans; // return answer
        boolean flg;

        public List<Integer> grayCode(int n) {
            limit = (int) Math.pow(2, n); // n=3,limit=8
            ans = new ArrayList<>(); // return answer
            flg = false;
            ArrayList<Integer> bit = new ArrayList<>(n);//limit=8,bit.size()=8
            for (int i = 0; i < n; i++) {
                bit.add(0);
            }// init bit = 00000000

            HashSet<Integer> set = new HashSet<>();
            set.add(0);//init set={0}
            ArrayList<Integer> res = new ArrayList<>();
            res.add(0);//init res={0}
            maker(res, set, bit);
            return ans;
        }

        // ord: path value
        // set: visited values
        // bit: operate current result
        public void maker(ArrayList<Integer> ord, Set<Integer> set, ArrayList<Integer> bit) {
            // meet condition,
            if (ord.size() == limit) {
                ans.addAll(ord);
                flg = true;
                return;
            }

            // bit size init 00000000000... (limit)
            for (int i = 0; i < bit.size(); i++) {
                int bt = bit.get(i);
                int ans = bt == 1 ? 0 : 1;// 最近的0，变为 1
                bit.set(i, ans);// 设定完毕，bit 的第 i 位变为 1
                int dec = decimalConv(bit); // 二进制变为整数，去重
                if (!set.contains(dec)) {
                    set.add(dec);
                    ord.add(dec);
                    maker(ord, set, bit);
                    ord.remove(ord.size() - 1);
                    set.remove(dec);
                }
                if (flg)
                    return;
                bit.set(i, bt);
            }
        }

        public int decimalConv(List<Integer> bit) {
            int pow = 0;
            int sum = 0;
            for (int ele : bit) {
                sum += (ele * (int) Math.pow(2, pow));
                pow++;
            }
            return sum;
        }
    }
}
