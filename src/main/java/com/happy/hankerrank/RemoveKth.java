package com.happy.hankerrank;

public class RemoveKth {
    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(5);
        head.next = new SinglyLinkedListNode(6, new SinglyLinkedListNode(7,
                new SinglyLinkedListNode(8)));
        SinglyLinkedListNode head2 = removeKthNodeFromEnd(head, 3);
        System.out.println(head2);

        head = new SinglyLinkedListNode(5);
        head.next = new SinglyLinkedListNode(6);
        head2 = removeKthNodeFromEnd(head, 0);
        System.out.println(head2);

        head = new SinglyLinkedListNode(5);
        head2 = removeKthNodeFromEnd(head, 1);
        System.out.println(head2);

        head = new SinglyLinkedListNode(10);
        head.next = new SinglyLinkedListNode(20, new SinglyLinkedListNode(30, new SinglyLinkedListNode(40, new SinglyLinkedListNode(50, new SinglyLinkedListNode(60)))));
        head2 = extractAndAppendSponsoredNodes(head);
        System.out.println(head2);
    }

    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }

        public SinglyLinkedListNode(int data, SinglyLinkedListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static SinglyLinkedListNode extractAndAppendSponsoredNodes(SinglyLinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        SinglyLinkedListNode dump = new SinglyLinkedListNode(0);
        SinglyLinkedListNode dump2 = new SinglyLinkedListNode(0);

        SinglyLinkedListNode first = head;
        SinglyLinkedListNode second = head.next;
        dump.next = first;
        first.next = null;
        dump2.next = second;

        SinglyLinkedListNode cur = second.next;
        while (cur != null) {
            SinglyLinkedListNode tmp = cur.next;
            cur.next = dump.next;
            dump.next = cur;
            cur = tmp;
            if (cur != null) {
                second.next = cur;
                second = second.next;
                cur = cur.next;
            }
        }
        if (first != null && first.next != null) {
            first.next = null;
        }

        second.next = dump.next;
        return dump2.next;
    }


    public static SinglyLinkedListNode removeKthNodeFromEnd(SinglyLinkedListNode head, int k) {
        if (head == null) {
            return null;
        }

        // Write your code here
        int kth = k + 1;
        int count = 0;
        SinglyLinkedListNode dump = head;
        while (dump != null) {
            count++;
            dump = dump.next;
        }

        int th = 0;
        if (count >= kth) {
            th = count - kth;
        } else {
            return head;
        }

        if (th == 0) {
            return head.next;
        } else {
            SinglyLinkedListNode d = head;
            SinglyLinkedListNode pre = head;
            SinglyLinkedListNode cur = head;
            th = th - 1;
            while (cur != null && th > 0) {
                pre = pre.next;
                cur = cur.next;
                th--;
            }
            if (pre != null) {
                pre.next = pre.next.next;
            }
            return d;
        }

    }
}
