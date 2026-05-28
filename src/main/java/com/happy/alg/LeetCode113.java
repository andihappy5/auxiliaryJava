package com.happy.alg;

import com.happy.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode113 {
    /**
     * Given the root of a binary tree and an integer targetSum,
     * return all root-to-leaf paths where the sum of the node values in the path
     * equals targetSum.
     * Each path should be returned as a list of the node values, not node
     * references.
     *
     * A root-to-leaf path is a path starting from the root and ending at any leaf
     * node.
     * A leaf is a node with no children.
     *
     *
     *
     * Example 1:
     * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * Output: [[5,4,11,2],[5,8,4,5]]
     * Explanation: There are two paths whose sum equals targetSum:
     * 5 + 4 + 11 + 2 = 22
     * 5 + 8 + 4 + 5 = 22
     *
     * Example 2:
     * Input: root = [1,2,3], targetSum = 5
     * Output: []
     * Example 3:
     *
     * Input: root = [1,2], targetSum = 0
     * Output: []
     */

    public static void main() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(7);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(new LeetCode113().pathSum(root, 22));
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        List<Integer> path = new ArrayList<>();
        path(path, result, root, targetSum);
        return result;
    }

    private void path(List<Integer> path, List<List<Integer>> result, TreeNode root, int targetSum) {
        if (root == null)
            return;
        path.add(root.val);
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (targetSum != 0 && root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            path(path, result, root.left, targetSum);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path(path, result, root.right, targetSum);
            path.remove(path.size() - 1);
        }
    }

    static class SolutionStandard {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null)
                return result;
            path(new ArrayList<>(), result, root, targetSum);
            return result;
        }

        private void path(List<Integer> path, List<List<Integer>> result, TreeNode root, int targetSum) {
            if (root == null)
                return;
            path.add(root.val);
            targetSum = targetSum - root.val;
            if (targetSum == 0 && root.left == null && root.right == null) {
                result.add(new ArrayList<>(path));
            }
            path(path, result, root.left, targetSum);
            path(path, result, root.right, targetSum);
            path.remove(path.size() - 1);
        }
    }

    static class SolutionCompare {
        static void main() {
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(4);
            root.right = new TreeNode(8);
            root.left.left = new TreeNode(11);
            root.left.left.right = new TreeNode(2);
            root.left.left.left = new TreeNode(7);

            root.right.left = new TreeNode(13);
            root.right.right = new TreeNode(4);
            root.right.right.left = new TreeNode(5);
            root.right.right.right = new TreeNode(1);

            System.out.println(new SolutionCompare().pathSum(root, 22));
        }

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null)
                return result;
            List<Integer> path = new ArrayList<>();
            path(path, result, root, targetSum);
            return result;
        }

        private void path(List<Integer> path, List<List<Integer>> result, TreeNode root, int targetSum) {
            if (root == null)
                return;
            path.add(root.val);
            targetSum = targetSum - root.val;
            if (targetSum == 0 && root.left == null && root.right == null) {
                result.add(new ArrayList<>(path));
            }
            if (targetSum != 0 && root.left == null && root.right == null) {
                path.remove(path.size() - 1);
                return;
            }
            path(path, result, root.left, targetSum);
            path(path, result, root.right, targetSum);
            path.remove(path.size() - 1);
        }
    }
}
