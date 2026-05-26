package com.happy.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.happy.util.TreeNode;

public class LeetCode102BinaryTreeLevelOrderTraversal {
    // 102. Binary Tree Level Order Traversal
    /**
     * Given the root of a binary tree, return the level order traversal of its
     * nodes' values. (i.e., from left to right, level by level).
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[9,20],[15,7]]
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
     * -1000 <= Node.val <= 1000
     */

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new Integer[] { 1, 2, 3, 4, null, null, 5 });
        List<List<Integer>> res = new LeetCode102BinaryTreeLevelOrderTraversal().levelOrder(root);
        System.out.println(res);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        List<TreeNode> stack = new ArrayList<>();
        if (root == null) {
            return res;
        } else {
            stack.add(root);
            level.add(root.val);
        }
        dfs(stack, level, res);
        return res;
    }

    private void dfs(List<TreeNode> stack, List<Integer> level, List<List<Integer>> res) {
        if (stack.isEmpty() && level.size() == 0) {
            return;
        }
        if (stack.size() == level.size()) {
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
        dfs(nextLevelStack, nextLevel, res);
    }
}
