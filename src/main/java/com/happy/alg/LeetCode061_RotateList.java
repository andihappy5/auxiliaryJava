package com.happy.alg;

import com.happy.util.ListNode;

public class LeetCode061_RotateList {
    // Rotate List
    // Given the head of a linked list, rotate the list to the right by k places.
    //
    //Example 1:
    //Input: head = [1,2,3,4,5], k = 2
    //Output: [4,5,1,2,3]
    //Example 2:
    //Input: head = [0,1,2], k = 4
    //Output: [2,0,1]
    //Constraints:
    //
    //The number of nodes in the list is in the range [0, 500].
    //-100 <= Node.val <= 100
    //0 <= k <= 2 * 109

    public static void main() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(rotateRight(head,2));
    }

    //Do not use the length of ListNode
    public static  ListNode rotateRight(ListNode head, int k) {
        if(head == null || k== 0 || head.next == null) return head;
        ListNode dump =  new ListNode(-1);
        dump.next = head;
        ListNode cur = head; //1,2,3,4,5,6
        int len = k;
        while (len > 0) {
            if(cur == null){
                cur = head;
            }
            cur = cur.next;
            len--;
        }
        if (cur == null) return head;
        ListNode p = head;
        while (cur.next!= null) {
            p = p.next;
            cur = cur.next;
        }
        //p= ... n-2,n-1，n
        ListNode headP = dump;
        while (p.next != null) {
            ListNode tmp = p.next;
            p.next = tmp.next;
            tmp.next = head;
            headP.next = tmp;
            headP = tmp;
        }

        return  dump.next;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            // Step 1: find length and tail
            int n = 1;
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
                n++;
            }

            // Step 2: reduce k
            k = k % n;
            if (k == 0) {
                return head;
            }

            // Step 3: make circular [the Key Point]
            tail.next = head;

            // Step 4: find new tail
            int steps = n - k;
            ListNode newTail = head;
            for (int i = 1; i < steps; i++) {
                newTail = newTail.next;
            }

            // Step 5: break
            ListNode newHead = newTail.next;
            newTail.next = null;

            return newHead;
        }
    }
}
