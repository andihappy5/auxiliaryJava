package com.happy.hot100;

public class L242ValidAnagram {

    public static void main(String[] args) {
        System.out.println("Hello,World");
    }

    public boolean isAnagram(String s, String t) {
        if(s == null && t == null) return true;
        if(s.length() != t.length()) return false;
        int[] count = new int[26];
        for(int i =0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for (int c : count) {
            if(c != 0) return false;
        }
        return true;
    }
}
