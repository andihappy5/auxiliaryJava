package com.happy.day;

import java.util.HashMap;
import java.util.Map;

public class Day202604230 {
     // two-pointer
     public int[] twoSumTP(int[] numbers, int target) {
         int left =0,right = numbers.length-1;
         while(left < right){
             int s = numbers[left]+numbers[right];
             if(s == target ){
                 return new int[]{left+1,right+1};
             } else if(s < target){
                 left++;
             }else{
                 right--;
             }
         }
         return new int[]{-1,-1};
     }

     // Dictionary
     public int[] twoSumD(int[] numbers, int target) {
         Map<Integer, Integer> map = new HashMap<>();
         for (int i = 0; i < numbers.length; i++) {
             if (!map.containsKey(target - numbers[i])) {
                 map.put(numbers[i], i);
             } else {
                 return new int[] { map.get(target - numbers[i]) + 1, i + 1 };
             }
         }
         return new int[] { -1, -1 };
     }

     //binary search
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int v =  numbers[i];
            int left = i+1,right = numbers.length-1;
            while (left <= right){
                int mid = left + (right-left)/2;
                if (v == target - numbers[mid]){
                    return new int[]{left+1,right+1};
                }else if (v < target - numbers[mid]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
        return new int[] { -1, -1 };
    }

}
