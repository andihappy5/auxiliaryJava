package com.happy.alg;

import com.happy.util.TreeNode;

public class LeetCode110BalancedBinaryTree {
    // 110. Balanced Binary Tree
    /**
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [3,9,20,null,null,15,7]
     * Output: true
     * Example 2:
     * 
     * 
     * Input: root = [1,2,2,3,3,null,null,4,4]
     * Output: false
     * Example 3:
     * 
     * Input: root = []
     * Output: true
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [0, 5000].
     * -104 <= Node.val <= 104
     * 
     */

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new Integer[] { 1, 2, 2, 3, null, null, 3, 4, null, null, 4 });
        boolean res = new LeetCode110BalancedBinaryTree().isBalanced(root);
        System.out.println(res);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left - right) <= 1;
    }

    private int depth(TreeNode n) {
        if (n == null) {
            return 0;
        }
        if (n.left == null && n.right == null) {
            return 1;
        } else {
            int left = n.left != null ? depth(n.left) + 1 : 0;
            int right = n.right != null ? depth(n.right) + 1 : 0;
            return Math.max(left, right);
        }
    }

}
