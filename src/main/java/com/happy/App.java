package com.happy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        App.Solution solution = new App().new Solution();
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations(""));
    }

    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            return result;
        }
    }
}
