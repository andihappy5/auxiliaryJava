package com.happy.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.happy.util.TreeNode;

public class LeetCode107BTLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
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
            res.add(0, new ArrayList<>(level));
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
