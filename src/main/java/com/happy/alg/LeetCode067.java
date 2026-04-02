
package com.happy.alg;

/**
 * LeetCode 67. Add Binary
 * https://leetcode.com/problems/add-binary/
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * Constraints:
 * 1 <= a.length, b.length <= 10^4
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 * 
 */

public class LeetCode067 {

    public static void main(String[] args) {
        LeetCode067.Solution T = new LeetCode067().new Solution();
        String a = "1010", b = "1011";
        String result = T.addBinary(a, b);
        System.out.println(result);

        LeetCode067 T1 = new LeetCode067();
        System.out.println(T1.addBinary(a, b));

    }

    // this method is good, because it is efficient, and it is easy to understand
    // just use a char array to store the result, and then convert it to string at
    // the end
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0)
            return b;
        if (b == null || b.length() == 0)
            return a;
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }
        // make sure a is the longer one, so we can avoid the case when i < 0 and j >= 0
        char[] result = new char[a.length() + 1];
        int carry = 0;
        for (int i = 0; i < a.length(); i++) {
            int aValue = a.length() - 1 - i >= 0 ? a.charAt(a.length() - 1 - i) - '0' : 0;
            int bValue = b.length() - 1 - i >= 0 ? b.charAt(b.length() - 1 - i) - '0' : 0;
            int sum = aValue + bValue + carry;
            result[result.length - 1 - i] = (char) (sum % 2 + '0');
            carry = sum / 2;
        }
        if (carry > 0) {
            result[0] = '1';
        } else {
            result[0] = '0';
        }

        if (result[0] == '1') {
            return new String(result);
        } else {
            return new String(result, 1, result.length - 1);
        }
    }

    class Solution {
        // this method is not good, because of the insert(0, value) method of
        // StringBuilder:
        // it is not efficient, because it needs to shift the existing characters to the
        // right every time we insert a new character at the beginning of the string.
        public String addBinary(String a, String b) {
            if (a == null || a.length() == 0)
                return b;
            if (b == null || b.length() == 0)
                return a;
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            int i = a.length() - 1, j = b.length() - 1;
            while (i >= 0 || j >= 0) {
                int aValue = i >= 0 ? a.charAt(i) - '0' : 0;
                int bValue = j >= 0 ? b.charAt(j) - '0' : 0;
                int sum = aValue + bValue + carry;
                sb.insert(0, sum % 2);
                carry = sum / 2;
                i--;
                j--;
            }
            if (carry > 0) {
                sb.insert(0, carry);
            }
            return sb.toString();
        }
    }
}