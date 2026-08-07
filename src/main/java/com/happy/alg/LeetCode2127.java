package com.happy.alg;

import java.util.*;

public class LeetCode2127 {
    class Solution {
        public int maximumInvitations(int[] favorite) {
            int n = favorite.length;
            // i 向 favorite[i] 连边，可以得到一张有向图
            int[] deg = new int[n];

            for (int f : favorite) {
                deg[f]++;//统计技术每一个节点的入度，说明被喜欢的次数
            }

            List<Integer>[] rg = new List[n]; //反图
            Arrays.setAll(rg, e -> new ArrayList<>());

            //如果节点没有入度，说明什么？说明没有人喜欢他，没有入度
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (deg[i] == 0) {
                    q.add(i);
                }
            }
            while (!q.isEmpty()) { // 拓扑排序，剪掉图上所有树枝
                int x = q.poll();
                int y = favorite[x]; // x 只有一条出边，解释为x 喜欢 y
                rg[y].add(x);
                if (--deg[y] == 0) {
                    q.add(y);
                }
            }

            int maxRingSize = 0, sumChainSize = 0;
            for (int i = 0; i < n; i++) {
                if (deg[i] == 0)
                    continue;
                // 遍历基环上的点
                deg[i] = 0; // 将基环上的点的入度标记为 0，避免重复访问
                int ringSize = 1; // 基环长度
                for (int x = favorite[i]; x != i; x = favorite[x]) {
                    deg[x] = 0; // 将基环上的点的入度标记为 0，避免重复访问
                    ringSize++;
                }

                if (ringSize == 2) { // 基环长度为 2
                    sumChainSize += rdfs(i, rg) + rdfs(favorite[i], rg); // 累加两条最长链的长度
                } else {
                    maxRingSize = Math.max(maxRingSize, ringSize); // 取所有基环长度的最大值
                }
            }
            return Math.max(maxRingSize, sumChainSize);
        }

        // 通过反图 rg 寻找树枝上最深的链
        private int rdfs(int x, List<Integer>[] rg) {
            int maxDepth = 1;
            for (int son : rg[x]) {
                maxDepth = Math.max(maxDepth, rdfs(son, rg) + 1);
            }
            return maxDepth;
        }
    }
}
