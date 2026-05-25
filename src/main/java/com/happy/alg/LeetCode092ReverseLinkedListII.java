package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode092ReverseLinkedListII {
    /**
     * Given the head of a singly linked list and two integers left and right where
     * left <= right, reverse the nodes of the list from position left to position
     * right, and return the reversed list.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     * Example 2:
     * 
     * Input: head = [5], left = 1, right = 1
     * Output: [5]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is n.
     * 1 <= n <= 500
     * -500 <= Node.val <= 500
     * 1 <= left <= right <= n
     * 
     * 
     * Follow up: Could you do it in one pass?
     */

    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[] { 1, 2, 3, 4, 5 });
        ListNode result = reverseBetween(head, 2, 4);
        System.out.println(result);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preLeft = dummy;
        ListNode rightNode = dummy;
        for (int i = 0; i < left - 1; i++) {
            preLeft = preLeft.next;
            rightNode = rightNode.next;
        }
        for (int i = 0; i < (right - left + 1); i++) {
            rightNode = rightNode.next;
        }
        ListNode leftNode = preLeft.next;
        ListNode postRight = rightNode.next;

        // 1,2,3,4,5 left=2 right=4 ==> preLeft=1 rightNode=4 postRight=5
        while (preLeft.next != rightNode) {
            preLeft.next = leftNode.next;//
            leftNode.next = postRight;
            rightNode.next = leftNode;//
            leftNode = preLeft.next;// 1 -> 3 -> 4->2-> 5
            postRight = rightNode.next;// 1 -> 3 -> 4->2-> 5
        }
        return dummy.next;
    }
}
