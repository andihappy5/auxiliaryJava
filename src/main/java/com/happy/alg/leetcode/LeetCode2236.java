package com.happy.alg.leetcode;

import com.happy.util.TreeNode;

public class LeetCode2236 {
    /**
     2236. Root Equals Sum of Children
     Easy
     You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.

     Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.

     Example 1:

     Input: root = [10,4,6]
     Output: true
     Explanation: The value of the root is 10, and the sum of the values of its two children is 4 + 6 = 10.
     Example 2:

     Input: root = [5,3,1]
     Output: false
     Explanation: The value of the root is 5, and the sum of the values of its two children is 3 + 1 = 4.
     Constraints:

     The tree consists only of the root, its left child, and its right child.
     -100 <= Node.val <= 100
      * **/
    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode2236 l = new LeetCode2236();
        LeetCode2236.Solution s = l.new Solution();
        System.out.println(s.checkTree(new TreeNode(10,new TreeNode(4),new TreeNode(6))));//true
        System.out.println(s.checkTree(new TreeNode(5,new TreeNode(3),new TreeNode(1))));//false
    }

    class Solution {
        public boolean checkTree(TreeNode root) {
            if (root == null) return false;
            if (root.left == null || root.right == null) return false;
            return root.val == (root.left.val + root.right.val);
        }
    }
}
