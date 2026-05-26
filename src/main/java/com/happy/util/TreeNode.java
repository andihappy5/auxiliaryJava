package com.happy.util;

public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;

      public TreeNode() {
      }

      public TreeNode(int val) {
            this.val = val;
      }

      public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
      }

      @Override
      public String toString() {
            return " " + this.val + " " + (this.left == null ? "null" : this.left.val) + " "
                        + (this.right == null ? "null" : this.right.val) + "";
      }

      public static TreeNode build(Integer[] integers) {
            if (integers == null || integers.length == 0) {
                  return null;
            }
            TreeNode root = new TreeNode(integers[0]);
            TreeNode[] nodes = new TreeNode[integers.length];
            nodes[0] = root;
            for (int i = 1; i < integers.length; i++) {
                  if (integers[i] != null) {
                        TreeNode node = new TreeNode(integers[i]);
                        nodes[i] = node;
                        if (i % 2 == 1) {
                              nodes[(i - 1) / 2].left = node;
                        } else {
                              nodes[(i - 1) / 2].right = node;
                        }
                  }
            }
            return root;
      }
}
