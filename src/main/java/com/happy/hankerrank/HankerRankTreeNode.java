package com.happy.hankerrank;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HankerRankTreeNode {
    
    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        public TreeNode(Integer val) {
            this.val = val;
        }
     }

    public static void main(String[] args) {
        List<Integer> values = List.of(4, 2, 6, 1, 3, 5, 7);
        List<Integer> leftChild = List.of(1, 3, 5, -1, -1, -1, -1);
        List<Integer> rightChild = List.of(2, 4, 6, -1, -1, -1, -1);
        int height = getBinarySearchTreeHeight(values, leftChild, rightChild);
        System.out.println("Height of the binary search tree: " + height);
    }

    private static final int NULL_VALUE = 100001;

private static class Node {
    Node left;
    Node right;
}

public static boolean verifySameMultisetDifferentStructure(
        List<Integer> root1, List<Integer> root2) {

    List<Integer> values1 = collectValues(root1);
    List<Integer> values2 = collectValues(root2);

    Collections.sort(values1);
    Collections.sort(values2);

    // List comparison preserves duplicate counts.
    if (!values1.equals(values2)) {
        return false;
    }

    Node tree1 = buildTree(root1);
    Node tree2 = buildTree(root2);

    return !sameStructure(tree1, tree2);
}

private static List<Integer> collectValues(List<Integer> values) {
    List<Integer> result = new ArrayList<>();

    for (int value : values) {
        if (value != NULL_VALUE) {
            result.add(value);
        }
    }

    return result;
}

// Standard level-order format:
// each non-null node consumes its left and right child entries.
private static Node buildTree(List<Integer> values) {
    if (values.isEmpty() || values.get(0) == NULL_VALUE) {
        return null;
    }

    Node root = new Node();
    ArrayDeque<Node> queue = new ArrayDeque<>();
    queue.addLast(root);

    int index = 1;

    while (!queue.isEmpty() && index < values.size()) {
        Node current = queue.removeFirst();

        if (values.get(index++) != NULL_VALUE) {
            current.left = new Node();
            queue.addLast(current.left);
        }

        if (index < values.size()
                && values.get(index++) != NULL_VALUE) {
            current.right = new Node();
            queue.addLast(current.right);
        }
    }

    return root;
}

private static boolean sameStructure(Node a, Node b) {
    if (a == null || b == null) {
        return a == null && b == null;
    }

    return sameStructure(a.left, b.left)
            && sameStructure(a.right, b.right);
}

    public static int getBinarySearchTreeHeight(List<Integer> values, List<Integer> leftChild, List<Integer> rightChild) {
        if(values == null || values.size() == 0) {
            return 0;
        }
        
        HashMap<Integer, TreeNode> hm = new HashMap<>();
        for(int i=0; i<values.size(); i++) {
            hm.put(i, new TreeNode(values.get(i)));
        }
        // Add Left child
        for(int i=0; i<leftChild.size(); i++) {
            if(leftChild.get(i) != -1) {
                TreeNode node = hm.get(i);
                node.left = hm.get(leftChild.get(i));
                System.out.println("Adding left child: " + node.left.val + " to parent: " + node.val);
            }
        }
        // Add Right child
        for(int i=0; i<rightChild.size(); i++) {
            if(rightChild.get(i) != -1) {
                TreeNode node = hm.get(i);
                node.right = hm.get(rightChild.get(i));
            }
        }
        // Set root of tree
        TreeNode root = hm.get(0);
        return height(root);
    }
    
    private static int height(TreeNode node) {
        if(node == null) {
            return 0;
        } else {
            return 1+Math.max(height(node.left), height(node.right));
        }
    }
}
