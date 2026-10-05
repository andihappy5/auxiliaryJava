package com.happy.hankerrank;

import graphql.com.google.common.collect.Lists;

import java.util.*;

public class TreeMapSort {
    public static void main(String[] args) {
        TreeMap<Integer,Integer> treeMapSort = new TreeMap<>();
        List<Integer> v = Lists.asList(4, new Integer[]{4, 1, 2, 2, 3, 1, 3, 2});
        for (int i = 0; i < v.size(); i++) {
            treeMapSort.put(v.get(i),treeMapSort.getOrDefault(v.get(i),0)+1);
        }
        System.out.println(treeMapSort);
    }

    public static List<Integer> getTopKFrequentEvents(List<Integer> events, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> arr = new ArrayList<>();
        for(int curr : events){
            int freq = map.getOrDefault(curr, 0) + 1;
            if(freq == 1)
                arr.add(curr);
            map.put(curr, freq);
        }
        arr.sort(Comparator.comparingInt(value -> map.get(value)).reversed());
        return arr.subList(0, k);

    }

    public static int maximizeNonOverlappingMeetings(List<List<Integer>> meetings) {
        if (meetings == null || meetings.isEmpty()) return 0;
        if (meetings.size() == 1) return 1;
        int nonOverlappingCount = 1;
        meetings.sort(Comparator.comparingInt(m -> m.get(1)));
        List<Integer> meetingEnd = meetings.get(0);
        for (int index = 1; index < meetings.size(); index++) {
            if ( meetings.get(index).get(0) >= meetingEnd.get(1)) {
                meetingEnd = meetings.get(index);
                nonOverlappingCount++;
            }
        }
        return nonOverlappingCount;
    }

}
