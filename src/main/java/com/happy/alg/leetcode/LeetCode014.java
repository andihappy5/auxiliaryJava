package com.happy.alg.leetcode;//Write a function to find the longest common prefix string amongst an array of
//strings. 
//
// If there is no common prefix, return an empty string "". 
//
// 
// Example 1: 
//
// 
//Input: strs = ["flower","flow","flight"]
//Output: "fl"
// 
//
// Example 2: 
//
// 
//Input: strs = ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.
// 
//
// 
// Constraints: 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] consists of only lower-case English letters. 
// 
// Related Topics String


public class LeetCode014 {

    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            if (strs.length == 1) return strs[0];

            StringBuilder result = new StringBuilder();
            char[] stand = strs[0].toCharArray();
            for (int i = 0; i < stand.length; i++) {
                char tmp = stand[i];
                for (int j = 0; j < strs.length; j++) {
                    if (i < strs[j].length() && strs[j].charAt(i) == tmp){
                        continue;
                    }else{
                        return result.toString();
                    }
                }
                result.append(tmp);
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        LeetCode014 l = new LeetCode014();
        LeetCode014.Solution s = l.new Solution();
        System.out.println(s.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(s.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }








    public static String longestCommonPrefix(String[] strs) {
        if(strs==null || strs.length==0) return "";
        if (strs.length == 1)
            return strs[0];
        StringBuilder result = new StringBuilder();
        char[] stand = strs[0].toCharArray();

        for (int i = 0; i < stand.length; i++) {
            Character indexchar = stand[i];
            boolean equal = true;
            for (int j = 1; j < strs.length; j++) {
                equal = equal && i < strs[j].length() && strs[j].charAt(i) == indexchar;
                if (!equal) break;
            }
            if (equal) {
                result.append(indexchar);
            } else {
                break;
            }
        }

        return result.toString();

    }


}
