package com.happy.hankerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringChange {

    public static void main(String[] args) {
        String s = "abcba";
        int result = sherlockAndAnagrams(s);
        System.out.println("Number of anagrammatic pairs: " + result);
    }

    public static int sherlockAndAnagrams(String s) {
        Map<String, Integer> seen = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] counts = new int[26];
            for (int j = i; j < s.length(); j++) {
                counts[s.charAt(j) - 'a']++;
                String key = Arrays.toString(counts);
                int previous = seen.getOrDefault(key, 0);

                ans = ans + previous;
                seen.put(key, previous + 1);
            }
        }
        return ans;
    }

    public static int sherlockAndAnagrams2(String s) {
        Map<String, Integer> seen = new HashMap<>();
        int answer = 0;

        for (int start = 0; start < s.length(); start++) {
            for (int end = start + 1; end <= s.length(); end++) {
                char[] chars = s.substring(start, end).toCharArray();
                Arrays.sort(chars);

                String key = new String(chars);
                int count = seen.getOrDefault(key, 0);

                answer += count;
                seen.put(key, count + 1);
            }
        }

        return answer;
    }
}
