package com.happy.alg.leetcode;

import com.happy.util.ListNode;

public class LeetCode237 {

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        if(node.next != null){
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello,world!");
        System.out.println("Be Happy Boy");
    }
}
