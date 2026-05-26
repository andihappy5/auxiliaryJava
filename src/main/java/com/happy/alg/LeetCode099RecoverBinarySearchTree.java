package com.happy.alg;

import com.happy.util.TreeNode;

public class LeetCode099RecoverBinarySearchTree {
    /**
     * You are given the root of a binary search tree (BST), where the values of
     * exactly two nodes of the tree were swapped by mistake. Recover the tree
     * without changing its structure.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [1,3,null,null,2]
     * Output: [3,1,null,null,2]
     * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3
     * makes the BST valid.
     * Example 2:
     * 
     * 
     * Input: root = [3,1,4,null,null,2]
     * Output: [2,1,4,null,null,3]
     * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2
     * and 3 makes the BST valid.
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [2, 1000].
     * -231 <= Node.val <= 231 - 1
     * 
     * 
     * Follow up: A solution using O(n) space is pretty straight-forward. Could you
     * devise a constant O(1) space solution?
     * 
     */

    // This question appeared difficult to me but it is really just a simple
    // in-order traversal!

    // template for in-order traversal
    private void traverse(TreeNode root) {
        if (root == null)
            return;
        traverse(root.left);
        // Do some business
        traverse(root.right);
    }

    /**
     * So when we need to print the node values in order, we insert
     * System.out.println(root.val) in the place of "Do some business".
     * 
     * What is the business we are doing here?
     * We need to find the first and second elements that are not in order right?
     * 
     * How do we find these two elements? For example, we have the following tree
     * that is printed as in order traversal:
     * 
     * 6, 3, 4, 5, 2
     * We compare each node with its next one and we can find out that 6 is the
     * first element to swap because 6 > 3 and 2 is the second element to swap
     * because 2 < 5.
     * 
     * Really, what we are comparing is the current node and its previous node in
     * the "in order traversal".
     * 
     * Let us define three variables, firstElement, secondElement, and prevElement.
     * Now we just need to build the "do some business" logic as finding the two
     * elements. See the code below:
     */

    static class Solution {

        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.recoverTree(new TreeNode(3, new TreeNode(1), new TreeNode(4,
                    new TreeNode(2), new TreeNode(6))));
        }

        TreeNode firstElement = null;
        TreeNode secondElement = null;
        // The reason for this initialization is to avoid null pointer exception
        // in the first comparison when prevElement has not been initialized
        TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {

            // In order traversal to find the two elements
            traverse(root);
            // Swap the values of the two nodes
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }

        private void traverse(TreeNode root) {

            if (root == null)
                return;

            traverse(root.left);

            // Start of "do some business",
            // If first element has not been found, assign it to prevElement (refer to 6 in
            // the example above)
            if (firstElement == null && prevElement.val >= root.val) {
                firstElement = prevElement;
            }

            // If first element is found, assign the second element to the root (refer to 2
            // in the example above)
            if (firstElement != null && prevElement.val >= root.val) {
                secondElement = root;
            }
            prevElement = root;

            // End of "do some business"

            traverse(root.right);
        }
    }
}
