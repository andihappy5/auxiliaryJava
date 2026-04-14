package com.happy.alg;//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number o

//f rows like this: (you may want to display this pattern in a fixed font for bett
//er legibility) 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R
// 
//
// And then read line by line: "PAHNAPLSIIGYIR" 
//
// Write the code that will take a string and make this conversion given a numbe
//r of rows: 
//
// 
//string convert(string s, int numRows);
// 
//
// 
// Example 1: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
// 
//
// Example 2: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// Example 3: 
//
// 
//Input: s = "A", numRows = 1
//Output: "A"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists of English letters (lower-case and upper-case), ',' and '.'. 
// 1 <= numRows <= 1000 
//

public class LeetCode006 {

    class Solution {
        // the Question is not about algorimthm，but about code and understand the
        // problem meaning，and then find the regular pattern
        public String convert(String s, int numRows) {
            // corner case
            if (s == null || s.length() <= numRows || numRows == 1)
                return s;

            // use StringBuilder to store the result of each row, and then combine them
            // together . but not forgot initialize the StringBuilder for each row
            StringBuilder[] res = new StringBuilder[numRows];
            for (int i = 0; i < res.length; i++) {
                res[i] = new StringBuilder();
            }

            for (int i = 0; i < s.length();) {
                for (int j = 0; j < numRows && i < s.length(); j++) {
                    res[j].append(s.charAt(i));
                    i++;
                }

                for (int j = numRows - 2; j > 0 && i < s.length(); j--) {
                    res[j].append(s.charAt(i));
                    i++;
                }
            }

            StringBuilder result = new StringBuilder();
            for (StringBuilder sb : res) {
                result.append(sb.toString());
            }
            return result.toString();
        }

        //
        public String convert2(String s, int numRows) {
            if (numRows == 1 || numRows >= s.length())
                return s;

            int n = s.length();
            int cycle = 2 * (numRows - 1);
            char[] result = new char[n];
            int idx = 0;

            for (int row = 0; row < numRows; row++) {
                for (int j = row; j < n; j += cycle) {
                    result[idx++] = s.charAt(j);
                    int diag = j + cycle - 2 * row;
                    if (row != 0 && row != numRows - 1 && diag < n) {
                        result[idx++] = s.charAt(diag);
                    }
                }
            }
            return new String(result);
        }
    }

    public String convert(String s, int numRows) {
        if (s == null || s.length() <= numRows)
            return s;
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        int i = 0;
        while (i < s.length()) {
            for (int j = 0; j < numRows && i < s.length(); j++) {
                builders[j].append(s.charAt(i));
                i++;
            }
            for (int j = numRows - 2; j > 0 && i < s.length(); j--) {
                builders[j].append(s.charAt(i));
                i++;
            }
        }
        String res = "";
        for (int jj = 0; jj < numRows; jj++) {
            res += builders[jj].toString();
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode006 T = new LeetCode006();
        System.out.println(T.convert("PAYPALISHIRING", 4));
        LeetCode006.Solution s = T.new Solution();
        System.out.println(s.convert("PAYPALISHIRING", 4));
    }

}
