package com.happy.alg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode093 {
    public static void main(String[] args) {
        System.out.println("Andihappy!");
    }

    static class Solution {
        // result store in list of string
        List<String> ans = new LinkedList<>();
        String str;

        public List<String> restoreIpAddresses(String s) {
            str = s;
            dfs("", 0, 0);
            return ans;
        }

        // 0000 ==> 0.0.0.0
        // 没有选在 index 在 String 上面进行控制，而是单独用了一个参数:""来增加substring
        void dfs(String path, int index, int dots) {
            if (dots > 4)
                return;
            if (dots == 4 && index >= str.length()) {
                ans.add(path.substring(0, path.length() - 1));
                return;
            }
            // according to the length of the number, to decide the length of the number
            // index is the start index of the number , the length of the number is 1 to 3
            for (int length = 1; length <= 3 && index + length <= str.length(); length++) {
                String num = str.substring(index, index + length);
                if (num.charAt(0) == '0' && length != 1)
                    break;
                else if (Integer.parseInt(num) <= 255) {
                    dfs(path + str.substring(index, index + length) + ".", index + length, dots + 1);
                }
            }
        }
    }

    static class Solution2 {
        List<String> ans = new LinkedList<>();

        public static void main(String[] args) {
            Solution2 s = new Solution2();
            s.restoreIpAddresses("25525511135");
            System.out.println(Arrays.toString(s.ans.toArray()));
            s = new Solution2();
            s.restoreIpAddresses("0000");
            System.out.println(Arrays.toString(s.ans.toArray()));
            s = new Solution2();
            s.restoreIpAddresses("1111");
            System.out.println(Arrays.toString(s.ans.toArray()));
        }

        public List<String> restoreIpAddresses(String s) {
            int n = s.length();
            // special case if the string is empty, return the empty list
            if (n == 0)
                return ans;
            dfs(new StringBuilder(), s, 4);
            return ans;
        }

        // str is the current ip address
        // s is the original string
        // sections is the number of sections left
        private void dfs(StringBuilder str, String s, int sections) {
            if (s.length() == 0 && sections == 0) {
                ans.add(str.substring(0, str.length() - 1));
                return;
            }
            if (s.length() > 0 && sections == 0) {
                return;
            }
            if (s.length() == 0 && sections != 0) {
                return;
            }

            StringBuffer sb = new StringBuffer();
            int min = Math.min(3, s.length());
            for (int i = 0; i < min; i++) {
                sb.append(s.charAt(i));
                int val = Integer.parseInt(sb.toString());
                if (sb.length() == 2 && val < 10)
                    continue;
                if (sb.length() == 3 && val < 100)
                    continue;
                if (sb.length() == 3 && val > 255)
                    continue;
                StringBuilder strTmp = new StringBuilder(str);
                str.append(sb.toString()).append(".");
                dfs(str, s.substring(i + 1), sections - 1);
                str = strTmp;
            }
        }
    }
}
