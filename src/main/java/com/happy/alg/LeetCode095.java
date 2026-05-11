package com.happy.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.happy.util.TreeNode;

public class LeetCode095 {
    public static void main(String[] args) {
        System.out.println("Andihappy!");
    }

    /*
     * 
     * Given an integer n, return all the structurally unique BST's (binary search
     * trees), which has exactly n nodes of unique values from 1 to n. Return the
     * answer in any order.
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: n = 3
     * Output:
     * [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     * Example 2:
     * 
     * Input: n = 1
     * Output: [[1]]
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 8
     */

    static class Solution {
        public static void main(String[] args) {
            System.out.println(Solution.generateTrees(2));
        }

        public static List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new ArrayList<>();
            }
            return generateTrees(1, n);
        }

        // generate the trees
        private static List<TreeNode> generateTrees(int from, int to) {
            List<TreeNode> res = new ArrayList<>();
            if (from > to) {
                res.add(null);
                return res;
            }
            for (int i = from; i <= to; i++) {
                List<TreeNode> left = generateTrees(from, i - 1);
                List<TreeNode> right = generateTrees(i + 1, to);
                for (TreeNode leftNode : left) {
                    for (TreeNode rightNode : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode;
                        root.right = rightNode;
                        res.add(root);
                    }
                }
            }
            return res;
        }
    }
}
