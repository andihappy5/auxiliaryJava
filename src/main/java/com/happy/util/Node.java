package com.happy.util;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static Node build(Integer[] integers) {
        if (integers == null || integers.length == 0) {
            return null;
        }
        Node root = new Node(integers[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < integers.length) {
            Node node = queue.poll();
            if (integers[i] != null) {
                node.left = new Node(integers[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < integers.length && integers[i] != null) {
                node.right = new Node(integers[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
}
