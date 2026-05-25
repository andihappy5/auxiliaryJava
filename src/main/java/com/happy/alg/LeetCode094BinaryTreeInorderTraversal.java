package com.happy.alg;

import java.util.LinkedList;
import java.util.List;

import com.happy.util.TreeNode;

public class LeetCode094BinaryTreeInorderTraversal {
    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes'
     * values.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [1,null,2,3]
     * Output: [1,3,2]
     * Example 2:
     * 
     * Input: root = []
     * Output: []
     * Example 3:
     * 
     * Input: root = [1]
     * Output: [1]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     */

    public static void main(String[] args) {
        System.out.println("Andihappy!");
        System.out.println(inorderTraversal(new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<Integer>();
        dfs(root, ans);
        return ans;
    }

    // inorder traversal： left -> root -> right
    private static void dfs(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        dfs(root.left, ans);
        ans.add(root.val);
        dfs(root.right, ans);
    }
}
