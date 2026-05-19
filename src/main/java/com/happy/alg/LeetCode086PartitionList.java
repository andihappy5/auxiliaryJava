package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode086PartitionList {
    // 86. Partition List
    /**
     * Given the head of a linked list and a value x, partition it such that all
     * nodes less than x come before nodes greater than or equal to x.
     * 
     * You should preserve the original relative order of the nodes in each of the
     * two partitions.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,4,3,2,5,2], x = 3
     * Output: [1,2,2,4,3,5]
     * Example 2:
     * 
     * Input: head = [2,1], x = 2
     * Output: [1,2]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is in the range [0, 200].
     * -100 <= Node.val <= 100
     * -200 <= x <= 200
     * 
     */

    public static void main(String[] args) {
        ListNode h = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5,
                new ListNode(2, new ListNode(6)))))));
        System.out.println(partition(h, 3));
        h = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5,
                new ListNode(2))))));
        System.out.println(partition(h, 3));
        h = new ListNode(2, new ListNode(1));
        System.out.println(partition(h, 2));
        h = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(0, new ListNode(2,
                new ListNode(5, new ListNode(2)))))));
        System.out.println(partition(h, 3));
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dum = new ListNode();
        dum.next = head;
        ListNode pre = dum;
        ListNode cp = dum;
        ListNode cur = head;
        while (cur.val < x) {
            cur = cur.next;
            pre = pre.next;
            cp = cp.next;
        }

        // 1,4,3,2,5,2
        while (cur != null) {
            if (cur.val < x) {
                ListNode tmp = pre.next;
                ListNode tmp2 = cur;
                cur = cur.next;
                cp.next = cur;
                pre.next = tmp2;
                tmp2.next = tmp;
                pre = pre.next;
            } else {
                cur = cur.next;
                cp = cp.next;
            }
        }
        return dum.next;
    }
}