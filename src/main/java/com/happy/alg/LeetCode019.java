package com.happy.alg;

import com.happy.util.ListNode;

/**
 * 
 * 19. Remove Nth Node From End of List
 * 
 * Given the head of a linked list, remove the nth node from the end of the list
 * and return its head.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * 
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * 
 * Input: head = [1,2], n = 1
 * Output: [1]
 * 
 * 
 * Constraints:
 * 
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 
 * 
 * Follow up: Could you do this in one pass?
 * 
 */

public class LeetCode019 {
    static class Day202604151 {
        public static void main() {
            System.out.println("Keep Happy,Boy!");
            System.out.println(removeNthFromEnd(ListNode.build(new int[] { 1, 2, 3, 4, 5 }), 2));
            System.out.println(removeNthFromEnd(ListNode.build(new int[] { 1, 2 }), 1));
            System.out.println(removeNthFromEnd(ListNode.build(new int[] { 1 }), 1));
            System.out.println(removeNthFromEnd(ListNode.build(new int[] { 1, 2 }), 2));

        }

        public static ListNode removeNthFromEnd(ListNode head, int n) {
            // that have a trick
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = head;
            ListNode cur = head;
            while (n > 0) {
                if (cur != null) {
                    cur = cur.next;
                }
                n--;
            }
            if (cur == null) {
                dummy.next = dummy.next.next;
                return dummy.next;
            }
            while (cur.next != null) {
                pre = pre.next;
                cur = cur.next;
            }

            if (pre.next != null) {
                pre.next = pre.next.next;
            }
            return dummy.next;
        }
    }
}
