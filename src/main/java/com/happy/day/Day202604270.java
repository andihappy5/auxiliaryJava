package com.happy.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day202604270 {
    public static void main(String[] args){
        System.out.println("Keep Happy!");
//        System.out.println(combinationSum(new int[]{8,7,4,3},11));
//        System.out.println(combinationSum(new int[]{4,2,8},8));
//        System.out.println(combinationSum(new int[]{2,3,6,7},7));
//        System.out.println(combinationSum(new int[]{2,3,5},8));

        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates,0,list,result,target);
        return result;
    }

    private static void combinationSum2(int[] candidates, int start, List<Integer> list,
                                 List<List<Integer>> result, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0){
            return;
        }
        for (int i = start; i < candidates.length; i++){
            if (candidates[i] > target){
                continue;
            }
            if (i > start && candidates[i] == candidates[i - 1]){
                continue;
            }
            list.add(candidates[i]);
            combinationSum2(candidates,i+1,list,result,target-candidates[i]);
            list.remove(list.size()-1);
        }
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum(candidates,0,list,result,target);
        return result;
    }

    private static void combinationSum(int[] candidates, int i,
                                        List<Integer> list, List<List<Integer>> result, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }else if (target < 0){
            return;
        }else{
            for (int j = i; j < candidates.length; j++) {
                list.add(candidates[j]);
                combinationSum(candidates,j,list,result,target-candidates[j]);
                list.remove(list.size()-1);
            }
        }
    }



    private static void combinationSum2Error(int[] candidates, int i,
                                List<Integer> list, List<List<Integer>> result, int target) {
        for (int j = i; j < candidates.length; j++) {
            if (candidates[j] == target) {
                list.add(candidates[j]);
                result.add(new ArrayList<>(list));
                list.remove(list.size()-1);
                return;
            }else if (candidates[j] < target){
                list.add(candidates[j]);
                combinationSum(candidates,j,list,result,target-candidates[j]);
                list.remove(list.size()-1);
            }else  {
                combinationSum(candidates,j+1,list,result,target-candidates[j]);
            }
        }
    }
}
