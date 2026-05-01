package com.happy.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day202604271 {
    public static void main() {
//        System.out.println("Keep Happy !");
//        System.out.println(permute(new int[]{1,2,3}));
//        System.out.println(permute(new int[]{1}));
//        System.out.println(permute(new int[]{1,2}));
//        System.out.println(subsets(new int[]{1,2,3}));
        System.out.println(permuteUnique(new int[]{1,1,3}));
    }
    //-----------------------
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] candidates = new int[]{1,2,3,4,5,6,7,8,9};
        combinationSum3(candidates,0,list,result,n);
        return result;
    }

    private void combinationSum3(int[] candidates, int start,
                                 List<Integer> list, List<List<Integer>> result, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || start >= candidates.length) return;
        for (int i = start; i < candidates.length; i++){
            if (candidates[i] > target){
                continue;
            }
            list.add(candidates[i]);
            combinationSum3(candidates,i+1,list,result,target-candidates[i]);
            list.remove(list.size()-1);
        }
    }


    //-----------------------
    public static  List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result  = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permuteUnique(nums,list,result,used);
        return result;
    }

    private static void permuteUnique(int[] nums, List<Integer> list,
                             List<List<Integer>> result, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if( (i>0 && nums[i]==nums[i-1]) && !used[i-1] || (used[i]) ) continue;
            list.add(nums[i]);
            used[i] = true;
            permuteUnique(nums,list,result,used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }


    //---------------------------------------------------------------
    public static  List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfsWithDup(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static void dfsWithDup(int[] nums, int start, ArrayList<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));           // 每个节点都是子集，先记录
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            if (i > start && i < nums.length-1 && nums[i] == nums[i + 1]) continue;
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    //----------------------------

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static  void dfs(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));           // 每个节点都是子集，先记录
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    //-------------------------------------------------------
    public static List<List<Integer>> subsetsMy(int[] nums) {
        List<List<Integer>> result = new  ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(list);
        for (int i = 1; i <=  nums.length; i++) {
            subsets(nums,result,list,0,i);
        }
        return  result;
    }

    private static void subsets(int[] nums,
                                List<List<Integer>> result, List<Integer> list,int from,int size) {
        if(from > nums.length){
            return;
        }
        if (list.size() == size) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = from; i < nums.length; i++) {
            list.add(nums[i]);
            subsets(nums,result,list,i+1,size);
            list.remove(list.size()-1);
        }
    }

    private static void subsets(int[] nums,
                                List<List<Integer>> result, List<Integer> list, int size) {
        if (list.size() == size){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < size; i++) {
            list.add(nums[i]);
            subsets(nums,result,list,size);

        }
    }

    //----------------------------------------------------------

    public static  List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result  = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        permute(nums,list,0,list,result);
        return result;
    }

    private static void permute(int[] nums, List<Integer> list, int start,
                                List<Integer> list1, List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list1.contains(nums[i])) continue;
            list.add(nums[i]);
            permute(nums,list,start+1,list1,result);
            list.remove(list.size()-1);
        }
    }
}
