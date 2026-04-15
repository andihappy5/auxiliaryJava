package com.happy.alg;
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
// Given an integer, convert it to a roman numeral. 
//
// 
// Example 1: 
//
// 
//Input: num = 3
//Output: "III"
// 
//
// Example 2: 
//
// 
//Input: num = 4
//Output: "IV"
// 
//
// Example 3: 
//
// 
//Input: num = 9
//Output: "IX"
// 
//
// Example 4: 
//
// 
//Input: num = 58
//Output: "LVIII"
//Explanation: L = 50, V = 5, III = 3.
// 
//
// Example 5: 
//
// 
//Input: num = 1994
//Output: "MCMXCIV"
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
// 
//
// 
// Constraints: 
//
// 
// 1 <= num <= 3999 
//

import java.util.HashMap;
import java.util.Map;

public class LeetCode012 {

    static class App {
        public static void main(String[] args) {
            App.Solution solution = new App().new Solution();
            System.out.println(solution.intToRoman(3749));
            System.out.println(solution.intToRoman(58));
        }

        public static Map<Integer, String> map = new HashMap<>(10);
        static {
            map.put(0, "");
            map.put(1, "I");
            map.put(2, "II");
            map.put(3, "III");
            map.put(4, "IV");
            map.put(5, "V");
            map.put(6, "VI");
            map.put(7, "VII");
            map.put(8, "VIII");
            map.put(9, "IX");
            map.put(10, "X");
            map.put(20, "XX");
            map.put(30, "XXX");
            map.put(40, "XL");
            map.put(50, "L");
            map.put(60, "LX");
            map.put(70, "LXX");
            map.put(80, "LXXX");
            map.put(90, "XC");
            map.put(100, "C");
            map.put(200, "CC");
            map.put(300, "CCC");
            map.put(400, "CD");
            map.put(500, "D");
            map.put(600, "DC");
            map.put(700, "DCC");
            map.put(800, "DCCC");
            map.put(900, "CM");
            map.put(1000, "M");
            map.put(2000, "MM");
            map.put(3000, "MMM");

        }

        class Solution {
            public String intToRoman(int num) {
                return map.get(num / 1000 * 1000) + map.get(num % 1000 / 100 * 100) + map.get(num % 100 / 10 * 10)
                        + map.get(num % 10);
            }
        }

    }

    public static String intToRoman(int num) {
        // 0,1000,2000,3000
        String M[] = { "", "M", "MM", "MMM" };
        // 0,100,200,300,400,500,600,700,800,900
        String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        // 0,10,20,30,40,50,60,70,80,90
        String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        // 0,1,2,3,4,5,6,7,8,9
        String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    public static void main(String[] args) {
        System.out.println("hello holiday");

        System.out.println(intToRoman(2345));
    }

}
