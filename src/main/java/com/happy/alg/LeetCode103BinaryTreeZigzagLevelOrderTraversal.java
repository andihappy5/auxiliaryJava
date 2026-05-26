package com.happy.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.happy.util.TreeNode;

public class LeetCode103BinaryTreeZigzagLevelOrderTraversal {
    // 103. Binary Tree Zigzag Level Order Traversal
    /**
     * Given the root of a binary tree, return the zigzag level order traversal of
     * its nodes' values. (i.e., from left to right, then right to left for the
     * next level and alternate between).
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[20,9],[15,7]]
     * Example 2:
     * 
     * Input: root = [1]
     * Output: [[1]]
     * Example 3:
     * 
     * Input: root = []
     * Output: []
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [0, 2000].
     * -100 <= Node.val <= 100
     */

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        List<List<Integer>> res = new LeetCode103BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
        System.out.println(res);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        List<TreeNode> stack = new ArrayList<>();
        if (root == null) {
            return res;
        } else {
            stack.add(root);
            level.add(root.val);
        }
        dfs(stack, level, res, true);
        return res;
    }

    private void dfs(List<TreeNode> stack, List<Integer> level, List<List<Integer>> res,
            boolean leftToRight) {
        if (stack.isEmpty() && level.size() == 0) {
            return;
        }
        if (stack.size() == level.size()) {
            if (!leftToRight) {
                Collections.reverse(level);
            }
            res.add(level);
        }
        List<TreeNode> nextLevelStack = new ArrayList<>();
        List<Integer> nextLevel = new ArrayList<>();
        for (int i = 0; i < stack.size(); i++) {
            TreeNode node = stack.get(i);
            if (node.left != null) {
                nextLevelStack.add(node.left);
                nextLevel.add(node.left.val);
            }
            if (node.right != null) {
                nextLevelStack.add(node.right);
                nextLevel.add(node.right.val);
            }
        }
        dfs(nextLevelStack, nextLevel, res, !leftToRight);
    }
}
