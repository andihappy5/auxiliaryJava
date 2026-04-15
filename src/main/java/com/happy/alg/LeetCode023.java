package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode023 {

    // You are given an array of k linked-lists lists, each linked-list is sorted in
    // ascending order.
    //
    // Merge all the linked-lists into one sorted linked-list and return it.
    //
    //
    // Example 1:
    //
    //
    // Input: lists = [[1,4,5],[1,3,4],[2,6]]
    // Output: [1,1,2,3,4,4,5,6]
    // Explanation: The linked-lists are:
    // [
    // 1->4->5,
    // 1->3->4,
    // 2->6
    // ]
    // merging them into one sorted list:
    // 1->1->2->3->4->4->5->6
    //
    //
    // Example 2:
    //
    //
    // Input: lists = []
    // Output: []
    //
    //
    // Example 3:
    //
    //
    // Input: lists = [[]]
    // Output: []
    //
    //
    //
    // Constraints:
    //
    //
    // k == lists.length
    // 0 <= k <= 10^4
    // 0 <= lists[i].length <= 500
    // -10^4 <= lists[i][j] <= 10^4
    // lists[i] is sorted in ascending order.
    // The sum of lists[i].length won't exceed 10^4.
    //
    //
    static class Day202604162 {
        public static void main(String[] args) {
            System.out.println("Key Happy ,Boy!");
            System.out.println(mergeKLists(new ListNode[] { ListNode.build(new int[] { 1, 4, 5 }),
                    ListNode.build(new int[] { 1, 3, 4 }), ListNode.build(new int[] { 2, 6 }) }));
            System.out.println(mergeKLists(new ListNode[] { ListNode.build(new int[] {}) }));
            System.out.println(mergeKLists(new ListNode[] {}));
        }

        // this Question is about
        public static ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0)
                return null;
            if (lists.length == 1)
                return lists[0];
            return mergeKLists(lists, 0, lists.length - 1);
        }

        private static ListNode mergeKLists(ListNode[] lists, int from, int end) {
            if (from == end)
                return lists[from];
            int mid = from + (end - from) / 2;
            ListNode h1 = mergeKLists(lists, from, mid);
            ListNode h2 = mergeKLists(lists, mid + 1, end);
            return mergeLists(h1, h2);
        }

        private static ListNode mergeLists(ListNode h1, ListNode h2) {
            if (h1 == null)
                return h2;
            if (h2 == null)
                return h1;
            ListNode dummy = new ListNode(0);
            ListNode head = dummy;
            while (h1 != null || h2 != null) {
                if (h1 == null) {
                    head.next = h2;
                    return dummy.next;
                }
                if (h2 == null) {
                    head.next = h1;
                    return dummy.next;
                }
                if (h1.val <= h2.val) {
                    head.next = h1;
                    h1 = h1.next;
                } else {
                    head.next = h2;
                    h2 = h2.next;
                }
                head = head.next;
            }
            return dummy.next;
        }
    }
}
