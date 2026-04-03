package com.happy.alg;

import java.util.HashMap;
import java.util.Map;

public class LeetCode003 {

    public static void main(String[] args) {
        LeetCode003 leetCode003 = new LeetCode003();
        System.out.println(leetCode003.lengthOfLongestSubstring("abcbdeafg"));
        System.out.println(leetCode003.lengthOfLongestSubstring("bbbbb"));
        System.out.println(leetCode003.lengthOfLongestSubstring("pwwkew"));
    }

    // the solution is to use a sliding window to find the longest substring without
    // repeated charactter
    // the window is defined by two pointers, begin and end , just use a map to
    // store the char that have been visited and the index of the char
    // when we find a repeated char, we can update the begin index to the index of
    // the repeated char + 1,
    // and update the result if the current window is longer than the previous
    // result
    public int lengthOfLongestSubstring(String s) {
        // special case
        if (s.length() <= 1) {
            return s.length();
        }
        int begin = 0;
        int result = 1;
        // use a map to store the char that have been visited and the index of the char
        // so that we can update the begin index when we find a repeate char
        Map<Character, Integer> window = new HashMap<>(s.length());
        window.put(s.charAt(0), begin);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            // if the char is not in the window, just add it to the window and update
            // if the char is int the window, we need to update the begin index to the index
            // of the repeated char + 1
            // and update the window with the new index of the char
            // so update the result and put the i index char in window are done in both
            // cases:
            // i is the end index if the char is not in the window, and i - begin + 1 is the
            // length of the current window if the char is in the window
            if (window.containsKey(c)) {
                Integer repeatIndex = window.get(c);
                if (repeatIndex >= begin) {
                    // why only update begin when the repeat char is in the current window ?
                    // because if the repeat char is not in the current window,
                    // it means that the repeat char is before the begin index,
                    // so we don't need to update the begin index
                    begin = repeatIndex + 1;
                } else {
                    // give an example to explain this case:"abcbdeafg" when we find the second "a",
                    // the repeat index is 0,
                    // but the begin index is 2, so we don't need to update the begin index, and the
                    // current window is "cbde",
                    // the length of the current window is 4, so we need to update the result to 5
                    // System.out.println(s + "," + i + "," + begin + "," + repeatIndex + "," +
                    // result);
                }
            }
            window.put(c, i);
            result = result > (i - begin + 1) ? result : (i - begin + 1);
        }
        return result;
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) {
                return 0;
            }
            if (s.length() <= 1) {
                return s.length();
            }

            int begin = 0;
            int result = 1;
            Map<Character, Integer> window = new HashMap<>(s.length());
            window.put(s.charAt(0), begin);
            for (int i = 1; i < s.length(); i++) {
                Character c = s.charAt(i);
                // 如果不包含c
                if (!window.containsKey(c)) {
                    window.put(c, i);
                    if (result < (i - begin + 1)) {
                        result = i - begin + 1;
                    }
                } else {
                    // 包含c
                    Integer repeatIndex = window.get(c);
                    // 更新begin
                    if (repeatIndex >= begin) {
                        begin = repeatIndex + 1;
                    }
                    window.put(c, i);
                    if (result < (i - begin + 1)) {
                        result = i - begin + 1;
                    }
                }
            }
            return result;
        }
    }
}
