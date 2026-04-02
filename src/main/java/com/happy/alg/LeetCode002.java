package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode002 {

    public static void main(String[] args) {
        LeetCode002 T = new LeetCode002();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = T.addTwoNumbers(l1, l2);
        System.out.println(result);
    }

    int carry = 0;

    // this method is not good, because of the global variable carry：
    // it is not thread safe, and it is not easy to understand
    // but the code is very concise(adj. 简明的，简洁的), and it is easy to write
    // by the iterative method, it is more concise and elegant
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null && carry == 0)
            return null;
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
        carry = sum / 10;
        return new ListNode(sum % 10, addTwoNumbers2(l1 != null ? l1.next : null, l2 != null ? l2.next : null));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // head 循环的过程中保持不变
        ListNode head = new ListNode(-1);
        // 变动的部分，另外的声明
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1value = l1 == null ? 0 : l1.val;
            int l2value = l2 == null ? 0 : l2.val;
            int sum = l1value + l2value + carry;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return head.next;
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            ListNode dummy = new ListNode(0);
            ListNode p = l1, q = l2, curr = dummy;
            int carry = 0;
            while (p != null || q != null) {
                if (p == null && carry == 0) {
                    curr.next = q;
                    break;
                }
                if (q == null && carry == 0) {
                    curr.next = p;
                    break;
                }
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                curr.next = new ListNode(sum % 10);
                carry = sum / 10;
                p = (p != null) ? p.next : null;
                q = (q != null) ? q.next : null;
                curr = curr.next;
            }
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return dummy.next;
        }
    }
}
