package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode143ReorderList {
    /**
     * You are given the head of a singly linked-list. The list can be represented
     * as:
     * 
     * L0 → L1 → … → Ln - 1 → Ln
     * Reorder the list to be on the following form:
     * 
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * You may not modify the values in the list's nodes. Only nodes themselves may
     * be changed.
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,2,3,4]
     * Output: [1,4,2,3]
     * Example 2:
     * 
     * 
     * Input: head = [1,2,3,4,5]
     * Output: [1,5,2,4,3]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is in the range [1, 5 * 104].
     * 1 <= Node.val <= 1000
     */

    public void reorderList(ListNode head) {
        // Check if the list is empty or has only one node
        if (head == null || head.next == null) {
            return;
        }

        // 1. Find the middle of the linked list.
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2. Reverse the second half.
        ListNode secondHalf = slow.next;
        slow.next = null;
        secondHalf = reverseList(secondHalf);
        // 3. Merge the two halves alternately.
        ListNode firstHalf = head;
        while (secondHalf != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;
            firstHalf.next = secondHalf;
            secondHalf.next = temp1;
            firstHalf = temp1;
            secondHalf = temp2;
        }

    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
