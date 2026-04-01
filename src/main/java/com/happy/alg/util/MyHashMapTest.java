package com.happy.alg.util;

import java.util.HashMap;

public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, String> t = new MyHashMap<>();
        t.put("1", "2");
        t.put("2", "2");
        t.put("1a", "1");
        t.put("2a", "2");
        t.put("3a", "3");
        t.put("4a", "4");
        t.put("5a", "5");
        t.put("6a", "6");
        t.PrintHashContainTable();

        HashMap<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("1", "22");
        System.out.println(map.get("1"));
    }
}
