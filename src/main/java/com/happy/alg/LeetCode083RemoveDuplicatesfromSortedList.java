package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode083RemoveDuplicatesfromSortedList {
    // 83. Remove Duplicates from Sorted List
    /**
     * Given the head of a sorted linked list, delete all duplicates such that each
     * element appears only once. Return the linked list sorted as well.
     * 
     * 
     * 
     * Example 1:
     * Input: head = [1,1,2]
     * Output: [1,2]
     * 
     * Example 2:
     * Input: head = [1,1,2,3,3]
     * Output: [1,2,3]
     * 
     * 
     * Constraints:
     * The number of nodes in the list is in the range [0, 300].
     * -100 <= Node.val <= 100
     * The list is guaranteed to be sorted in ascending order.
     * 
     */

    public static void main(String[] args) {
        ListNode h = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(3, new ListNode(4, new ListNode(5))))));
        System.out.println(deleteDuplicates(h));

        h = new ListNode(1, new ListNode(1, new ListNode(3,
                new ListNode(6, new ListNode(4, new ListNode(5))))));
        System.out.println(deleteDuplicates(h));

        h = new ListNode(1, new ListNode(1, new ListNode(3,
                new ListNode(3, new ListNode(4, new ListNode(5))))));
        System.out.println(deleteDuplicates(h));

        h = new ListNode(1, new ListNode(1));
        System.out.println(deleteDuplicates(h));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummp = new ListNode();
        dummp.next = head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val == cur.val) {
                ListNode repeate = cur.next;
                while (repeate != null && repeate.val == cur.val) {
                    repeate = repeate.next;
                }
                cur.next = repeate;
            }
            cur = cur.next;
        }
        return dummp.next;
    }

    static /**
            * Definition for singly-linked list.
            * public class ListNode {
            * int val;
            * ListNode next;
            * ListNode() {}
            * ListNode(int val) { this.val = val; }
            * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
            * }
            */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode current = head;

            if (head == null) {
                return null;
            }
            while (current != null && current.next != null) {
                if (current.val == current.next.val) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
            return head;
        }
    }
}
