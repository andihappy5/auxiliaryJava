package com.happy.alg;

import com.happy.util.ListNode;

import java.util.List;

/**
 *
 * 24. Swap Nodes in Pairs
 * 
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 * 
 * 
 * 
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Explanation:
 * 
 * 
 * 
 * Example 2:
 * Input: head = []
 * Output: []
 * 
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 * 
 * Example 4:
 * Input: head = [1,2,3]
 * Output: [2,1,3]
 * 
 * 
 * 
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 * 
 **/
public class LeetCode024 {

    public static void main(String[] args) {
        ListNode h1 = ListNode.build(new int[] { 1, 2, 3, 4, 5 });
        System.out.println(h1);
        LeetCode024 l = new LeetCode024();
        LeetCode024.Solution s = l.new Solution();
        System.out.println(s.swapPairs(h1));
        // System.out.println(swapPairs(h1));
    }

    class Solution {
        public ListNode swapPairs(ListNode head) {
            // special case
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(0, head);
            ListNode prev = dummy;
            ListNode cur = head;
            ListNode next = head.next;
            // please paint a picture to understand
            while (cur != null && next != null) {
                // swap
                prev.next = next;
                cur.next = next.next;
                next.next = cur;

                // update
                prev = cur;
                cur = cur.next;
                if (cur != null) {
                    next = cur.next;
                }
            }
            return dummy.next;
        }
    }

    public static ListNode swapPairs_2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tmp = new ListNode(0);
        tmp.next = head;
        ListNode pre = tmp;
        ListNode after = head.next;
        while (head != null && head.next != null) {
            head.next = after.next;
            after.next = head;
            pre.next = after;
            pre = pre.next.next;
            if (pre != null && pre.next != null) {
                head = pre.next;
            }
            if (head != null && head.next != null) {
                after = head.next;
            }
        }

        return tmp.next;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return swapPairs(head, head.next);
    }

    private static ListNode swapPairs(ListNode head, ListNode next) {
        head.next = next.next;
        next.next = head;
        if (head.next != null && head.next.next != null) {
            head.next = swapPairs(head.next, head.next.next);
        }
        return next;
    }

    static class Day202604163 {
        static void main() {
            System.out.println("Keep Happy,Boy!");
            System.out.println(swapPairs(ListNode.build(new int[] { 1, 2, 3, 4 })));
            System.out.println(swapPairs(ListNode.build(new int[] { 1 })));
            System.out.println(swapPairs(ListNode.build(new int[] { 1, 2, 3 })));
        }

        // just loop the ListNode and change the linked position
        public static ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode prev = dummy;
            while (head != null && head.next != null) {
                prev.next = head.next;
                head.next = head.next.next;
                prev.next.next = head;
                prev = head;
                head = head.next;
            }
            return dummy.next;
        }
    }
}
