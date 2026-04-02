package com.happy.alg;

import java.util.Stack;

import com.happy.util.ListNode;

/*
2816. Double a Number Represented as a Linked List
https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/
You are given the head of a linked list that represents a non-negative integer. 
The digits are stored in reverse order, and each of their nodes contains a single digit. 
Double the integer and return the head of the resulting linked list.
Example 1:
Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. 
Hence, the returned linked list represents the number 189 * 2 = 378.

Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. 
Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
Constraints:
The number of nodes in the linked list is in the range [1, 10^4]
0 <= Node.val <= 9
The number represented by the linked list does not contain leading zeros except for the zero itself.

*/
public class LeetCode2816 {
    public static void main(String[] args) {
        LeetCode2816 T = new LeetCode2816();
        ListNode head = new ListNode(1, new ListNode(8, new ListNode(9, new ListNode(9))));
        ListNode result = T.doubleIt(head);
        System.out.println(result);
    }

    // revert the linked list, then double it, and then revert it back to the
    // original order?
    public ListNode doubleIt(ListNode head) {
        if (head == null) {
            return null;
        }
        head = reverse(head);
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (head != null) {
            int sum = head.val * 2 + carry;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
            head = head.next;

        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return reverse(dummy.next);
    }

    // Helper method to reverse a singly linked list
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    class Solution {
        public ListNode doubleIt(ListNode head) {
            ListNode curr = head;
            if (head.val >= 5) {
                ListNode newHead = new ListNode(1);
                newHead.next = head;
                head = newHead;
                curr = head.next;
            }
            // double the value of each node, and if the next node's value is greater than
            // or equal to 5,
            // then we need to add 1 to the current node's value
            // so clever !
            while (curr != null) {
                curr.val = (curr.val * 2) % 10;
                if (curr.next != null && curr.next.val >= 5) {
                    curr.val += 1;
                }
                curr = curr.next;
            }
            return head;
        }
    }

}
