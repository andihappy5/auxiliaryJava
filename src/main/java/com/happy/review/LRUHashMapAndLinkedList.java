package com.happy.review;

import java.util.HashMap;
import java.util.Map;

public class LRUHashMapAndLinkedList {
    private class Node {
        private int key;
        private Node next;
        private Node prev;
    }
    private final int capacity;
    private Node head;
    private Node tail;
    private int size;
    private Map<Integer, Node> map;

    private LRUHashMapAndLinkedList(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, Node>(capacity);
        this.capacity = capacity;
    }

    //put and get 操作
    private void put (int key){
        if (!map.containsKey(key)){
            if (size == capacity ){
                Node node = tail.prev;
                removeNode(node);
                map.remove(node.key);
            }
            Node node = new Node();
            node.key = key;
            map.put(key, node);
            insertH(node);
        }else{
            Node node = map.get(key);
            removeNode(node);
            insertH(node);
        }
    }

    private Integer getKey(int key){
        if (!map.containsKey(key)){
            return null;
        }else{
            Node node = map.get(key);
            removeNode(node);
            insertH(node);
            return node.key;
        }
    }

    private void insertH(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.prev = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
}
