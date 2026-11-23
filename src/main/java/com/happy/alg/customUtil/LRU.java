package com.happy.alg.customUtil;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    // 146. LRU Cache
    static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> cache;
        // Each node in the list stores both the key and value.
        // The key is necessary so that when we evict from the list, we know which entry
        // to remove from the HashMap.
        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            // head and tail are dummy nodes to avoid null checks
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // If the key doesn't exist in the HashMap, return -1.
        // If it exists, the item is now "recently used." Remove it from its current
        // position in the list and Insert it at the front (right after the dummy head).
        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            remove(node);
            insertToHead(node);
            return node.value;
        }

        // If key exists: Update the value, remove the node, and move it to the front.
        // If key is new: * Check if the cache is at capacity. If so, delete the node
        // right before the tail (the Least Recently Used item) and remove it from the
        // HashMap.
        // Create a new node and insert it at the front.
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                remove(node);
                insertToHead(node);
            } else {
                if (cache.size() == capacity) {
                    Node lru = tail.prev;
                    remove(lru);
                    cache.remove(lru.key);
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                insertToHead(newNode);
            }
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // keep the head is always the head of the list, and the tail is always the tail
        // of the list.
        private void insertToHead(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        private static class Node {
            int key, value;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
