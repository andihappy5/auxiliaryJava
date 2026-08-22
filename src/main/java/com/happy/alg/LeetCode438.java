package com.happy.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode438 {

    /**
     Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

     Example 1:

     Input: s = "cbaebabacd", p = "abc"
     Output: [0,6]
     Explanation:
     The substring with start index = 0 is "cba", which is an anagram of "abc".
     The substring with start index = 6 is "bac", which is an anagram of "abc".
     Example 2:

     Input: s = "abab", p = "ab"
     Output: [0,1,2]
     Explanation:
     The substring with start index = 0 is "ab", which is an anagram of "ab".
     The substring with start index = 1 is "ba", which is an anagram of "ab".
     The substring with start index = 2 is "ab", which is an anagram of "ab".


     Constraints:

     1 <= s.length, p.length <= 3 * 104
     s and p consist of lowercase English letters.
     * */

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;
        char[] t =  p.toCharArray();
        Arrays.sort(t);
        String line = new String(t);
        //window length = p.size()
        for (int i = 0; i <= s.length() - p.length(); i++) {
            String subStr = s.substring(i, i + p.length());
            char[] t1 =  subStr.toCharArray();
            Arrays.sort(t1);
            if(new String(t1).equals(line)){
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList<Integer>();
        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
