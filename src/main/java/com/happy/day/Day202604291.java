package com.happy.day;

import java.util.ArrayList;
import java.util.List;

public class Day202604291 {
    public static void main(String[] args) {
        Day202604291 d = new Day202604291();
        System.out.println("Keep Moving!");
        System.out.println(d.combine(4,2));
    }



    //Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<>();
        if (n == 0 || k == 0) return result;
        if (k == n) {
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
            result.add(list);
            return result;
        }
        combine(1,n,k,result,list);
        return  result;
    }

    // 确定函数的意义
    private void combine(int cur, int n, int k, List<List<Integer>> result, List<Integer> list) {
        //确定终止条件
        if (list.size() == k){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = cur; i <= n; i++) {
            //剪枝
            if (list.contains(i)) continue;
            //主体逻辑
            list.add(i);
            //推导到下一步
            combine(i+1,n,k,result,list);
            //回
            list.remove(list.size()-1);
        }
    }
}
