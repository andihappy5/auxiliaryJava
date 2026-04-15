package com.happy.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
Given a string containing digits from 2-9 inclusive,

return all possible letter combinations that the number could represent.

Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons)
is given below. Note that 1 does not map to any letters.


{0 ==> "",
 1 ==> "",
 2 ==> "abc",
 3 ==> "def",
 4 ==> "ghi",
 5 ==> "lkj",
 6 ==> "mno",
 7 ==> "pqrs",
 8 ==> "tuv",
 9 ==> "wxyz"};


Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []


Example 3:
Input: digits = "2"
Output: ["a","b","c"]


Constraints:
        0 <= digits.length <= 4

digits[i] is a digit in the range ['2', '9'].
*/

public class LeetCode017 {

    class Solution2 {
        String[] a = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> res = new LinkedList<>();
        StringBuilder path = new StringBuilder();

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0)
                return res;
            rt(digits, 0);
            return res;
        }

        public void rt(String digits, int index) {
            if (path.length() == digits.length()) {
                res.add(path.toString());
                return;
            }

            String s = a[digits.charAt(index) - '0'];
            for (int i = 0; i < s.length(); i++) {
                path.append(s.charAt(i));
                rt(digits, index + 1);
                path.deleteCharAt(path.length() - 1);
            }

        }
    }

    static class Day20260415 {
        public static void main() {
            System.out.println("Keep Happy,Boy!");
            System.out.println(letterCombinations("2"));
            System.out.println(letterCombinations("23"));
        }

        public static Map<Character, String> alp = new HashMap<Character, String>();
        static {
            alp.put('2', "abc");
            alp.put('3', "def");
            alp.put('4', "ghi");
            alp.put('5', "jkl");
            alp.put('6', "mno");
            alp.put('7', "pqrs");
            alp.put('8', "tuv");
            alp.put('9', "wxyz");
        }

        public static List<String> letterCombinations(String digits) {
            return letterCombinations(digits, 0, new ArrayList<String>());
        }

        private static List<String> letterCombinations(String digits, int i, ArrayList<String> es) {
            if (i == digits.length()) {
                return es;
            }
            char c = digits.charAt(i);
            String letters = alp.get(c);
            if (es.isEmpty()) {
                for (int j = 0; j < letters.length(); j++) {
                    es.add(String.valueOf(letters.charAt(j)));
                }
                return letterCombinations(digits, i + 1, es);
            } else {
                ArrayList<String> esi = new ArrayList<>();
                for (int j = 0; j < letters.length(); j++) {
                    char l = letters.charAt(j);
                    for (String s : es) {
                        esi.add(s + l);
                    }
                }
                es.clear();
                return letterCombinations(digits, i + 1, esi);
            }
        }

        public List<String> letterCombinationsG(String digits) {
            List<String> res = new ArrayList<>();
            backtrack(digits, 0, new StringBuilder(), res);
            return res;
        }

        private void backtrack(String digits, int idx, StringBuilder comb, List<String> res) {
            if (idx == digits.length()) {
                res.add(comb.toString());
                return;
            }

            String letters = alp.get(digits.charAt(idx));
            for (char letter : letters.toCharArray()) {
                comb.append(letter);
                backtrack(digits, idx + 1, comb, res);
                comb.deleteCharAt(comb.length() - 1);
            }
        }
    }

}