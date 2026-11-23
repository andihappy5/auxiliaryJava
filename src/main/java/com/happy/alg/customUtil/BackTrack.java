package com.happy.alg.customUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrack {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        permute(nums,0,list,result);
        return result;
    }

    private static void permute(int[] nums, int i, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int j = 0; j < nums.length ; j++) {
            if (!list.contains(nums[j])) {
                list.add(nums[j]);
                permute(nums,j+1,list,result);
                list.remove(list.size()-1);
            }
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, target, candidates, ans, path);
        return ans;
    }

    private void dfs(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if (left == 0) {
            // 找到一个合法组合
            ans.add(new ArrayList<>(path));
            return;
        }
        // 枚举选哪个
        for (int j = i; j < candidates.length && candidates[j] <= left; j++) {
            path.add(candidates[j]);
            dfs(j, left - candidates[j], candidates, ans, path);
            path.remove(path.size() - 1); // 恢复现场
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

}
