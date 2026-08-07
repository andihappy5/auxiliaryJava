package com.happy.alg;

import java.util.Arrays;

public class LeetCode198 {
    //You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
    //Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
    //
    //Example 1:
    //Input: nums = [1,2,3,1]
    //Output: 4
    //Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    //Total amount you can rob = 1 + 3 = 4.
    //
    //Example 2:
    //Input: nums = [2,7,9,3,1]
    //Output: 12
    //Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    //Total amount you can rob = 2 + 9 + 1 = 12.
    //
    //
    //Constraints:
    //* 1 <= nums.length <= 100
    //* 0 <= nums[i] <= 400

    //超出时间限制
    class SolutionBackTracking {
        public int rob(int[] nums) {
            int n = nums.length;
            return dfs(nums,n-1);
        }
        public int dfs(int[] n,int index){
            if(index < 0){
                return 0;
            }else{
                return Math.max(dfs(n, index-1),dfs(n,index-2)+n[index]);
            }
        }
    }

    //增加缓存
    class SolutionBackTrackingWithCache {
        public int rob(int[] nums) {
            int n = nums.length;
            //coach
            int[] cache = new int[n];
            Arrays.fill(cache, -1);
            return dfs(nums,n-1,cache);
        }
        public int dfs(int[] n,int index,int[] cache){
            if(index < 0){
                return 0;
            }else{
                if (cache[index] != -1) {
                    return cache[index];
                }
                int v = Math.max(dfs(n, index-1,cache),dfs(n,index-2,cache)+n[index]);
                cache[index] = v;
                return v;
            }
        }
    }

    //动态规划1
    class SolutionDP {
        public int rob(int[] nums) {
            int n = nums.length;
            int[] d = new int[n+2];
            Arrays.fill(d, 0);
            for (int i = 0; i < n; i++) {
                d[i+2] = Math.max(d[i+1],d[i]+nums[i]);
            }
            return d[n+1];
        }
    }

    //动态规划 2
    class SolutionDP2 {
        public int rob(int[] nums) {
            int n = nums.length;
            int[] d = new int[n];
            d[0]=nums[0];
            if (n==1) return d[0];
            d[1]=Math.max(nums[0],nums[1]);
            if (n==2) return d[1];
            for (int i = 2; i < n; i++) {
                d[i] = Math.max(d[i - 1], d[i - 2] + nums[i]);
            }
            return d[n-1];
        }
    }

    //动态规划，固定的空间
    class SolutionDPWithFixCache {
        public int rob(int[] nums) {
            int n = nums.length;
            int pre=nums[0];
            if (n==1) return pre;
            int pre2=Math.max(nums[0],nums[1]);
            if (n==2) return pre2;
            int max = 0;
            for (int i = 2; i < n; i++) {
                max= Math.max(pre2, pre+ nums[i]);
                pre = pre2;
                pre2 = max;
            }
            return max;
        }
    }


}
