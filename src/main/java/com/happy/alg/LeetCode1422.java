package com.happy.algreview;

public class LeetCode1422 {
    /**
     * 1422. Maximum Score After Splitting a String
     * Given a string s consisting of only '0's and '1's, return the maximum score after splitting the string into two non-empty substrings.
     *
     * The score after splitting a string is the number of '0's in the left substring plus the number of '1's in the right substring.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "011101"
     * Output: 5
     * Explanation: We can split the string to get "0" and "11101".
     * The left substring has 1 zero and the right substring has 4 ones.
     * Example 2:
     *
     * Input: s = "00111"
     * Output: 5
     * Explanation: We can split the string to get "00" and "111".
     * The left substring has 2 zeros and the right substring has 3 ones.
     * Example 3:
     *
     * Input: s = "1111"
     * Output: 3
     * Explanation: We can split the string to get "111" and "1".
     * The left substring has 0 zeros and the right substring has 1 one.
     *
     *
     * Constraints:
     *
     * 2 <= s.length <= 500
     * The string s consists of characters '0' and '1' only.
     */
    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode1422 l = new LeetCode1422();
        LeetCode1422.Solution s = l.new Solution();
        System.out.println(s.maxScore("011101"));//5
        System.out.println(s.maxScore("00111"));//5
        System.out.println(s.maxScore("1111"));//3
    }

    class Solution {

        public int maxScore(String s) {

            int totalOnes = 0;//全部的1的数量
            for (char c : s.toCharArray()) {
                if (c == '1') {
                    totalOnes++;
                }
            }

            int maxScore = 0;

            int leftZeros = 0;//左边0的数量
            int rightOnes = totalOnes;

            // We iterate until length - 1 to ensure both substrings are non-empty
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == '0') {
                    leftZeros++;
                } else {
                    rightOnes--;
                }
                maxScore = Math.max(maxScore, leftZeros + rightOnes);
            }

            return maxScore;
        }
    }
}
