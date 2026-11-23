package com.happy.alg.customUtil;

import java.util.LinkedList;

import com.happy.util.ListNode;

public class ReverseLinkedList {
    public static void main(String[] args) {
        System.out.println("Keep happy!");
        ListNode h = new ListNode(1,new ListNode(2,new ListNode(3)));
        System.out.println(h.toString());
        System.out.println(ReverseListNode(h).toString());
        
        h = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4))));
        System.out.println(h.toString());
        System.out.println(ReverseListNode(h).toString());
    }

    public static ListNode ReverseListNode(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode cur = null;
        while(head != null){
            ListNode tmp = head.next;
            head.next = cur;
            cur = head;
            head = tmp;
        }
        return cur;
    }
}
