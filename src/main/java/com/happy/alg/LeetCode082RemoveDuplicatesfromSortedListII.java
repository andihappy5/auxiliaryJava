package com.happy.alg;

import java.util.List;

import com.happy.util.ListNode;

public class LeetCode082RemoveDuplicatesfromSortedListII {
    // 82. Remove Duplicates from Sorted List II
    /**
     * 
     * Given the head of a sorted linked list, delete all nodes that have duplicate
     * numbers, leaving only distinct numbers from the original list. Return the
     * linked list sorted as well.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,2,3,3,4,4,5]
     * Output: [1,2,5]
     * Example 2:
     * 
     * 
     * Input: head = [1,1,1,2,3]
     * Output: [2,3]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is in the range [0, 300].
     * -100 <= Node.val <= 100
     * The list is guaranteed to be sorted in ascending order.
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
        if (head == null || head.next == null)
            return head;
        ListNode dummp = new ListNode(Integer.MIN_VALUE);
        dummp.next = head;
        ListNode cur = head;
        ListNode pre = new ListNode();
        pre.next = cur;
        while (cur != null && cur.next != null) {
            if (cur.next.val == cur.val) {
                ListNode repeate = cur.next;
                while (repeate != null && repeate.val == cur.val) {
                    repeate = repeate.next;
                }
                if (cur == head) {
                    dummp = pre;
                }
                cur = repeate;
                pre.next = cur;
            } else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return dummp.next;
    }
}
