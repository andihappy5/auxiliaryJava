package com.happy.alg;

import com.happy.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode257 {
    /**
     Given the root of a binary tree, return all root-to-leaf paths in any order.

     A leaf is a node with no children.



     Example 1:


     Input: root = [1,2,3,null,5]
     Output: ["1->2->5","1->3"]
     Example 2:

     Input: root = [1]
     Output: ["1"]


     Constraints:

     The number of nodes in the tree is in the range [1, 100].
     -100 <= Node.val <= 100
     * */

    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(new LeetCode257().binaryTreePaths(root));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(result,new ArrayList<Integer>(),root);
        return result;
    }

    private void dfs(List<String> result,  List<Integer> builder, TreeNode root) {
        if (root == null) return;
        builder.add(root.val);
        if (root.left == null && root.right == null) {
            result.add(constrcut(builder));
        }else{
            dfs(result, builder, root.left);
            dfs(result, builder, root.right);
        }
        builder.remove(builder.size()-1);
    }

    private String constrcut(List<Integer> builder) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < builder.size(); i++) {
            sb.append(builder.get(i));
            if (i < builder.size() - 1) {
                sb.append("->");
            }
        }
        return sb.toString();
    }
}
