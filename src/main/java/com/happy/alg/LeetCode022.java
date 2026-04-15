package com.happy.alg;

import java.util.*;

/**
 * 22. Generate Parentheses
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 * Constraints:
 * 1 <= n <= 8
 **/

public class LeetCode022 {

    public static void main(String[] args) {
        System.out.println("keep happy");
        System.out.println(generateParenthesis(3));
        LeetCode022 l = new LeetCode022();
        LeetCode022.Solution s = l.new Solution();
        System.out.println(s.generateParenthesis(3));

    }

    class Solution2 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            char[] path = new char[n * 2];
            build(n, 0, 0, path, res);
            return res;
        }

        private void build(int n, int l, int r, char[] path, List<String> res) {
            if (r == n) {
                res.add(new String(path));
                return;
            }
            // 如果左括号没用完，可以填左括号
            if (l < n) {
                path[l + r] = '(';
                build(n, l + 1, r, path, res);
            }
            // 如果右括号比较少，可以填右括号
            if (r < l) {
                path[l + r] = ')';
                build(n, l, r + 1, path, res);
            }
        }
    }

    class Solution {
        public List<String> generateParenthesis(int n) {
            if (n < 1)
                return new ArrayList<>();
            return g(n, n, new ArrayList<String>(), new ArrayList<String>());
        }

        private List<String> g(int left, int right, List<String> tmp, List<String> res) {
            if (left == 0 && right == 0) {
                res.addAll(tmp);
                return res;
            }
            if (left == right) {
                ArrayList<String> temp = new ArrayList<>();
                if (tmp.isEmpty()) {
                    temp.add("(");
                } else {
                    for (int i = 0; i < tmp.size(); i++) {
                        temp.add(i, tmp.get(i) + "(");
                    }
                }
                return g(left - 1, right, temp, res);
            } else if (left < right && left > 0) {
                ArrayList<String> temp = new ArrayList<>();
                for (int i = 0; i < tmp.size(); i++) {
                    String s = tmp.get(i);
                    if (left > 0) {
                        temp.add(s + "(");
                        g(left - 1, right, temp, res);
                        temp.remove(temp.size() - 1);
                    }
                    temp.add(s + ")");
                    g(left, right - 1, temp, res);
                }
            } else {
                ArrayList<String> temp = new ArrayList<>();
                for (String s : tmp) {
                    temp.add(s + ")");
                }
                return g(left, right - 1, temp, res);
            }
            return res;
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        Set<String> judge = new HashSet<>();
        // 第一种解法
        // generateParenthesis(result,"",0,0,n);
        return generateParenthesis(1, result, judge, n);
    }

    /**
     * 回溯算法中，如何返回最后的结果值
     * 是如何递进推荐的
     */
    private static List<String> generateParenthesis(int i, List<String> result, Set<String> judge, int n) {
        if (i == 1) {
            result.add("()");
            judge.add("()");
            return generateParenthesis(i + 1, result, judge, n);
        }
        if (i > n)
            return result;

        List<String> resChange = new ArrayList<>();
        if (!result.isEmpty()) {
            for (int j = 0; j < result.size(); j++) {
                String tmp = result.get(j);
                for (int k = 0; k < tmp.length(); k++) {
                    String tmpBuilder = tmp.substring(0, k) + "()" + tmp.substring(k, tmp.length());
                    if (!judge.contains(tmpBuilder)) {
                        judge.add(tmpBuilder);
                        resChange.add(tmpBuilder);
                    }
                }
            }
        }
        resChange = generateParenthesis(i + 1, resChange, judge, n);
        return resChange;
    }

    static class Day202604161 {
        static void main() {
            System.out.println(generateParenthesis(1));
            System.out.println(generateParenthesis(2));
        }

        // this Question is about generating all combinations of well-formed
        // parentheses.
        // 1 "()"
        // 2 "()()","(())"
        // 3 "((()))","(()())","(())()","()(())","()()()"
        // it is about backtract and meet condition break！
        public static List<String> generateParenthesis(int n) {
            if (n == 1)
                return Arrays.asList("()");
            StringBuilder sbulder = new StringBuilder();
            List<String> result = new ArrayList<>();
            return generateParenthesis(n, n, sbulder, result);
        }

        private static List<String> generateParenthesis(int left, int right,
                StringBuilder sbulder, List<String> result) {
            if (left == 0 && right == 0) {
                result.add(sbulder.toString());
                return result;
            }
            if (left > 0) {
                sbulder.append("(");
                generateParenthesis(left - 1, right, sbulder, result);
                sbulder.deleteCharAt(sbulder.length() - 1);
            }

            if (right > left) {
                sbulder.append(")");
                generateParenthesis(left, right - 1, sbulder, result);
                sbulder.deleteCharAt(sbulder.length() - 1);
            }
            return result;
        }
    }

}
