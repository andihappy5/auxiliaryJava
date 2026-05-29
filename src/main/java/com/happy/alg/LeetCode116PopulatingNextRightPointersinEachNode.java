package com.happy.alg;

import com.happy.util.Node;

public class LeetCode116PopulatingNextRightPointersinEachNode {
    // 117. Populating Next Right Pointers in Each Node II
    /**
     * Given a binary tree
     * 
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no
     * next right node, the next pointer should be set to NULL.
     * 
     * Initially, all next pointers are set to NULL.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: root = [1,2,3,4,5,null,7]
     * Output: [1,#,2,3,#,4,5,7,#]
     * Explanation: Given the above binary tree (Figure A), your function should
     * populate each next pointer to point to its next right node, just like in
     * Figure B. The serialized output is in level order as connected by the next
     * pointers, with '#' signifying the end of each level.
     * Example 2:
     * 
     * Input: root = []
     * Output: []
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the tree is in the range [0, 6000].
     * -100 <= Node.val <= 100
     * 
     * 
     * Follow-up:
     * 
     * You may only use constant extra space.
     * The recursive approach is fine. You may assume implicit stack space does not
     * count as extra space for this problem.
     * 
     */

    public static void main(String[] args) {
        LeetCode116PopulatingNextRightPointersinEachNode solution = new LeetCode116PopulatingNextRightPointersinEachNode();
        Node root = Node.build(new Integer[] { 1, 2, 3, 4, 5, null, 7 });
        // Test cases can be added here
        solution.connect(root);
    }

    // 图形层序遍历，使用dummy节点串联每一层的节点
    public Node connect(Node root) {
        Node curr = root;
        
        while (curr != null) {
            Node dummy = new Node(0);
            Node tail = dummy;
            while (curr != null) {
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }
                curr = curr.next;
            }

            curr = dummy.next;
        }

        return root;
    }
}
