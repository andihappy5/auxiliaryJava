# 1 链表的循环   
~~~java 
// head 循环的过程中保持不变
ListNode head = new ListNode(-1);
// 变动的部分，另外的声明
ListNode cur = head;  
// 循环的过程中，cur 发生变动，head 不变
while (cur != null) {
    // do something
    cur = cur.next;
}
return head.next;
~~~

# 2. 链表的翻转
~~~java
// 反转链表的核心逻辑
ListNode pre = null;
while (h != null) {
    ListNode next = h.next; // 保存下一个节点
    h.next = pre;          // 反转当前节点的指针
    pre = h;               // 移动 pre 到当前节点
    h = next;               // 移动 h 到下一个节点
}
return pre; // pre 最终指向新的头节点
//       A-->B-->C-->D-->E
//pre    h 
//       A<--B-->C-->D-->E
//       pre h
//       A<--B<--C-->D-->E
//          pre  h
//       A<--B<--C<--D-->E
//               pre h
//       A<--B<--C<--D<--E
//                       pre  h    
// 最终 prev 指向 E，head 指向 null
~~~   


