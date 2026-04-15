package com.happy.alg;

import com.happy.util.ListNode;

/***
 * 21. Merge Two Sorted Lists
 * 
 * You are given the heads of two sorted linked lists list1 and list2.
 * 
 * Merge the two lists into one sorted list. The list should be made by splicing
 * together the nodes of the first two lists.
 * 
 * Return the head of the merged linked list.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 * 
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 * 
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * 
 * 
 * Constraints:
 * 
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */

public class LeetCode021 {

    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            ListNode curL1 = l1;
            ListNode curL2 = l2;
            ListNode cur = new ListNode(0);
            ListNode head = cur;
            while (curL1 != null || curL2 != null) {
                if (curL1 == null) {
                    cur.next = curL2;
                    break;
                } else if (curL2 == null) {
                    cur.next = curL1;
                    break;
                } else {
                    if (curL1.val < curL2.val) {
                        cur.next = curL1;
                        curL1 = curL1.next;
                        cur = cur.next;
                    } else {
                        cur.next = curL2;
                        curL2 = curL2.next;
                        cur = cur.next;
                    }
                }
            }
            return head.next;
        }
    }

    static class Day202604160 {
        static void main() {
            System.out.println("Keep Happy , Boy！");
            System.out.println(
                    mergeTwoLists(ListNode.build(new int[] { 1, 2, 4 }), ListNode.build(new int[] { 1, 3, 4 })));
            System.out.println(mergeTwoLists(ListNode.build(new int[] {}), ListNode.build(new int[] { 1, 3, 4 })));
        }

        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null)
                return list2;
            if (list2 == null)
                return list1;
            ListNode dummy = new ListNode();
            ListNode cur = dummy;
            while (list1 != null || list2 != null) {
                if (list1 == null) {
                    cur.next = list2;
                    return dummy.next;
                }
                if (list2 == null) {
                    cur.next = list1;
                    return dummy.next;
                }
                if (list1.val > list2.val) {
                    cur.next = list2;
                    list2 = list2.next;
                } else {
                    cur.next = list1;
                    list1 = list1.next;
                }
                cur = cur.next;
            }
            return dummy.next;
        }
    }

}
