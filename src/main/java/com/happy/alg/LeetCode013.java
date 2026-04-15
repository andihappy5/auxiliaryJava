package com.happy.alg;

import java.util.HashMap;
import java.util.Map;

//Roman numerals are represented by seven different symbols: I, V, X, L, C, D an
//d M. 
//
// 
//Symbol       Value
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// For example, 2 is written as II in Roman numeral, just two one's added togeth
//er. 12 is written as XII, which is simply X + II. The number 27 is written as XX
//VII, which is XX + V + II. 
//
// Roman numerals are usually written largest to smallest from left to right. Ho
//wever, the numeral for four is not IIII. Instead, the number four is written as 
//IV. Because the one is before the five we subtract it making four. The same prin
//ciple applies to the number nine, which is written as IX. There are six instance
//s where subtraction is used: 
//
// 
// I can be placed before V (5) and X (10) to make 4 and 9. 
// X can be placed before L (50) and C (100) to make 40 and 90. 
// C can be placed before D (500) and M (1000) to make 400 and 900. 
// 
//
// Given a roman numeral, convert it to an integer. 
//
// 
// Example 1: 
//
// 
//Input: s = "III"
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: s = "IV"
//Output: 4
// 
//
// Example 3: 
//
// 
//Input: s = "IX"
//Output: 9
// 
//
// Example 4: 
//
// 
//Input: s = "LVIII"
//Output: 58
//Explanation: L = 50, V= 5, III = 3.
// 
//
// Example 5: 
//
// 
//Input: s = "MCMXCIV"
//Output: 1994
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 15 
// s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M'). 
// It is guaranteed that s is a valid roman numeral in the range [1, 3999]. 
// 
// Related Topics Math String 
// 👍 2800 👎 3846

public class LeetCode013 {
    // s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    static Map<Character, Integer> comvertMap = new HashMap<Character, Integer>();
    static {
        comvertMap.put('a', 0);
        comvertMap.put('I', 1);
        comvertMap.put('V', 5);
        comvertMap.put('X', 10);
        comvertMap.put('L', 50);
        comvertMap.put('C', 100);
        comvertMap.put('D', 500);
        comvertMap.put('M', 1000);
    }

    class Solution {
        public int romanToInt(String s) {
            // special case
            if (s == null || s.trim().length() == 0)
                return 0;
            char[] tmp = s.toCharArray();
            int result = 0;
            int cur = 0;
            while (cur < tmp.length) {
                char tmpchar = tmp[cur];
                char tmpafter = cur + 1 >= tmp.length ? 'a' : tmp[cur + 1];
                // 根据后一个字符的大小，决定是加还是减
                result = (comvertMap.get(tmpchar) >= comvertMap.get(tmpafter)) ? (result + comvertMap.get(tmpchar))
                        : (result - comvertMap.get(tmpchar));
                cur++;
            }
            return result;
        }
    }

    public static int romanToInt(String s) {
        char[] tmp = s.toCharArray();
        int result = 0;
        for (int i = 0; i < tmp.length; i++) {
            char tmpchar = tmp[i];
            char tmpafter = i + 1 >= tmp.length ? 'a' : tmp[i + 1];
            result = (comvertMap.get(tmpchar) >= comvertMap.get(tmpafter)) ? (result + comvertMap.get(tmpchar))
                    : (result - comvertMap.get(tmpchar));
        }

        return result;

    }

    static public void main(String[] args) {

        System.out.println("hello holiday");

        System.out.println(romanToInt("MLC"));
    }

    static class App {
        public static void main(String[] args) {
            App.Solution solution = new App().new Solution();
            System.out.println(solution.romanToInt("III"));
            System.out.println(solution.romanToInt("LVIII"));
            System.out.println(solution.romanToInt("MCMXCIV"));
        }

        class Solution {
            public static Map<Character, Integer> map = new HashMap<>();
            static {
                map.put('I', 1);
                map.put('V', 5);
                map.put('X', 10);
                map.put('L', 50);
                map.put('C', 100);
                map.put('D', 500);
                map.put('M', 1000);
            }

            public int romanToInt(String s) {
                if (s == null || s.length() < 1) {
                    return 0;
                }
                int result = 0;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    int cv = map.get(c);
                    int ncv = 0;
                    if (i + 1 < s.length()) {
                        char nc = s.charAt(i + 1);
                        ncv = map.get(nc);
                    }
                    if (cv < ncv) {
                        result = result - cv;
                    } else {
                        result = result + cv;

                    }
                }
                return result;
            }

            public int romanToInt2(String s) {
                int prev = Integer.MAX_VALUE;
                int res = 0;

                for (int i = 0; i < s.length(); i++) {
                    int val = map.get(s.charAt(i));

                    if (prev < val) {
                        res -= prev;
                        res += (val - prev);
                    } else {
                        res += val;
                    }
                    prev = val;
                }

                return res;

            }
        }

    }

}
