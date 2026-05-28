package com.happy.alg;

import com.happy.util.TreeNode;

public class LeetCode114FlattenBT {
    // 114. Flatten Binary Tree to Linked List
    /**
     * Given the root of a binary tree, flatten the tree into a "linked list":
     * 
     * The "linked list" should use the same TreeNode class where the right child
     * pointer points to the next node in the list and the left child pointer is
     * always null.
     * The "linked list" should be in the same order as a pre-order traversal of the
     * binary tree.
     * 
     * 
     * Example 1:
     * Input: root = [1,2,5,3,4,null,6]
     * Output: [1,null,2,null,3,null,4,null,5,null,6]
     * Example 2:
     * 
     * Input: root = []
     * Output: []
     * Example 3:
     * 
     * Input: root = [0]
     * Output: [0]
     * 
     * 
     * Constraints:
     * The number of nodes in the tree is in the range [0, 2000].
     * -100 <= Node.val <= 100
     */

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new Integer[] { 1, 2, 5, 3, 4, null, 6 });
        new LeetCode114FlattenBT().flatten(root);
        System.out.println(root);
    }

    public void flatten(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    static class sulution {
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }
    }
}
