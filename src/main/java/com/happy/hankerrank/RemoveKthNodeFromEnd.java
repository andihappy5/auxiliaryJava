package com.happy.hankerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

public class RemoveKthNodeFromEnd {


    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    class SinglyLinkedListPrintHelper {
        public static void printList(SinglyLinkedListNode node, String sep) {
            while (node != null) {
                System.out.print(node.data);

                node = node.next;

                if (node != null) {
                    System.out.print(sep);
                }
            }
        }
    }



    class Result {

        /*
         * Complete the 'removeKthNodeFromEnd' function below.
         *
         * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
         * The function accepts following parameters:
         *  1. INTEGER_SINGLY_LINKED_LIST head
         *  2. INTEGER k
         */

        /*
         * For your reference:
         *
         * SinglyLinkedListNode {
         *     int data;
         *     SinglyLinkedListNode next;
         * }
         *
         */

        public static SinglyLinkedListNode removeKthNodeFromEnd(SinglyLinkedListNode head, int k) {
            if (head == null || k < 0) {
                return null;
            }

            SinglyLinkedListNode dump = new SinglyLinkedListNode(0);
            dump.next = head;
            SinglyLinkedListNode pre = dump;
            SinglyLinkedListNode after = dump;
            for (int i = 0; i <=k; i++) {
                pre = pre.next;
                if(pre == null){
                    return head;
                }
            }

            while (pre.next != null) {
                after = after.next;
                pre = pre.next;
            }

            after.next = after.next.next;
            return dump.next;
        }

    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            SinglyLinkedList head = new SinglyLinkedList();

            int headCount = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, headCount).forEach(i -> {
                try {
                    int headItem = Integer.parseInt(bufferedReader.readLine().trim());

                    head.insertNode(headItem);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            int k = Integer.parseInt(bufferedReader.readLine().trim());

            SinglyLinkedListNode result = Result.removeKthNodeFromEnd(head.head, k);

            SinglyLinkedListPrintHelper.printList(result, "\n");
            System.out.println();

            bufferedReader.close();
        }
    }

}
