package com.happy.day;

public class Day202604233 {

    public static void main() {
        System.out.println(isMatch("adceb","*a*b"));
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aa","*"));
        System.out.println(isMatch("aa","*b"));
        System.out.println(isMatch("cb","*a"));
    }

    public static boolean isMatch(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) return true;
        if (p.isEmpty() && !s.isEmpty()) return false;

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }else if (i == 0) {
                    if (p.charAt(j-1) == '*') {
                        dp[i][j] = dp[i][j-1];
                    }
                }else if (j == 0) {
                    dp[i][j] = false;
                }else{
                    if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    if (p.charAt(j-1) == '*') {
                        //  i=1 "a",j=1 "*" dp[i][j] =  dp[i-1][j-1] *匹配的是当前的字符
                        //  i=3 "adc",j=1 "a*" dp[3][1] = dp[3-1][1-1] || dp[3-2][1-1] || dp[3-3]][1-1]
                        for (int k = 1; k <= i; k++) {
                            dp[i][j] = dp[i][j] || dp[i-k][j-1];
                        }
                    }
                }
            }

        }

        return dp[s.length()][p.length()];
    }
}