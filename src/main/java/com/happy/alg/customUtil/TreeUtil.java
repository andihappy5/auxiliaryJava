package com.happy.alg.customUtil;

import com.happy.util.TreeNode;

public class TreeUtil {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // p、q 分别在左右两边，当前节点就是公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 哪边有结果就返回哪边
        return left != null ? left : right;
    }
}
