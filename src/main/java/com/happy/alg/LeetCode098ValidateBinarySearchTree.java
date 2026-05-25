package com.happy.alg;

import com.happy.util.TreeNode;

public class LeetCode098ValidateBinarySearchTree {
    // 98. Validate Binary Search Tree
    /**
     * Given the root of a binary tree, determine if it is a valid binary search
     * tree (BST).
     * 
     * A valid BST is defined as follows:
     * 
     * The left subtree of a node contains only nodes with keys strictly less than
     * the node's key.
     * The right subtree of a node contains only nodes with keys strictly greater
     * than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [2,1,3]
     * Output: true
     * Example 2:
     * 
     * 
     * Input: root = [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [1, 104].
     * -231 <= Node.val <= 231 - 1
     * 
     */

    public static void main(String[] args) {
        System.out.println(isValidBST(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
        System.out.println(
                isValidBST(new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)))));
    }

    public static boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    static boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;

        if ((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;

        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }

    // Error case: [5,1,4,null,null,3,6] 5 is the root node, 4 is the right child of
    // 5, but 4 < 5
    public static boolean isValidBSTError(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isValidBST2(root.left) && isValidBST2(root.right);
    }
}
