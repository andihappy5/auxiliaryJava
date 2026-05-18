package com.happy.alg;

import java.util.ArrayList;
import java.util.List;

public class LeetCode77Combines {
    /**
     * 
     * 77. Combinations
     * Given two integers n and k, return all possible combinations of k numbers
     * chosen from the range [1, n].
     * 
     * You may return the answer in any order.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: n = 4, k = 2
     * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
     * Explanation: There are 4 choose 2 = 6 total combinations.
     * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to
     * be the same combination.
     * Example 2:
     * 
     * Input: n = 1, k = 1
     * Output: [[1]]
     * Explanation: There is 1 choose 1 = 1 total combination.
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 20
     * 1 <= k <= n
     */

    static class Solution {

        public static void main(String[] args) {
            System.out.println(new Solution().combine(4, 2));
        }

        // Given two integers n and k, return all possible combinations of k numbers
        // chosen from the range [1, n].
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            List<Integer> list = new ArrayList<>();
            if (n == 0 || k == 0)
                return result;
            if (k == n) {
                for (int i = 1; i <= n; i++) {
                    list.add(i);
                }
                result.add(list);
                return result;
            }
            combine(1, n, k, result, list);
            return result;
        }

        // 确定函数的意义
        // cur from index: begine from 1
        // n index end
        // k numbers of integer
        private void combine(int cur, int n, int k, List<List<Integer>> result, List<Integer> list) {
            // 确定终止条件
            if (list.size() == k) {
                result.add(new ArrayList<>(list));
                return;
            }
            for (int i = cur; i <= n; i++) {
                // 剪枝
                if (list.contains(i))
                    continue;
                // 主体逻辑
                list.add(i);
                // 推导到下一步
                combine(i + 1, n, k, result, list);
                // 回
                list.remove(list.size() - 1);
            }
        }
    }
}
