package com.happy.alg.customUtil;

import com.happy.util.ListNode;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println("Keep Happy!");
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    class Solution {
        private ListNode left;
        public boolean isPalindrome(ListNode head) {
            left = head;
            return isPal(head);
        }
        private boolean isPal(ListNode right){
            // move the right to the tail of the list
            if(right.next != null && !isPal(right.next)){
                return false;
            }
            // compare value
            if(left.val != right.val){
                return false;
            }
            left = left.next;
            return true;
        }
    }

    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = reverseList(mid);
        while (head2 != null) {
            if (head.val != head2.val) { // 不是回文链表
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    // 876. 链表的中间结点
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 206. 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }



}
