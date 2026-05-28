package com.happy.alg;

import com.happy.util.TreeNode;

public class LeetCode106ConstructBTfroInorderanPostorderTraversal {
    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    /**
     * Given two integer arrays inorder and postorder where inorder is the inorder
     * traversal of a binary tree and postorder is the postorder traversal of the
     * same tree, construct and return the binary tree.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: [3,9,20,null,null,15,7]
     * Example 2:
     * 
     * Input: inorder = [-1], postorder = [-1]
     * Output: [-1]
     * 
     * 
     * Constraints:
     * 
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder and postorder consist of unique values.
     * Each value of postorder also appears in inorder.
     * inorder is guaranteed to be the inorder traversal of the tree.
     * postorder is guaranteed to be the postorder traversal of the tree.
     * 
     */

    // Approach:
    // Start with the last element of the postorder array as the root node.
    // Find the index of the root node in the inorder array.
    // Divide the inorder array into left and right subtrees based on the index of
    // the root node.
    // Divide the postorder array into left and right subtrees based on the number
    // of elements
    // in the left and right subtrees of the inorder array.
    // Recursively construct the left and right subtrees.

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Call the recursive function with full arrays and return the result
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        // Base case
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // Find the root node from the last element of postorder traversal
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // Find the index of the root node in inorder traversal
        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        // Recursively build the left and right subtrees
        int leftSize = rootIndex - inStart;
        int rightSize = inEnd - rootIndex;
        root.left = buildTree(inorder, inStart, rootIndex - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = buildTree(inorder, rootIndex + 1, inEnd, postorder, postEnd - rightSize, postEnd - 1);

        return root;
    }
}
