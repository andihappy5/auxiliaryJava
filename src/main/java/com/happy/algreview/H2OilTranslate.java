package com.happy.algreview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H2OilTranslate {
    //邻接表,adj[u] 存 {v , cap}
    static List<List<int[]>> adj;

    public static void main(String[] args) {
        int num = 6;
        int baseR = 4;
        int[][] piples = new int[][]{{4,2,10},{4,6,20},{4,1,30},{1,3,50},{1,5,80}};
        System.out.println(oillTransport(num, baseR, piples));
    }

    public static int oillTransport(int num,int baseR,int[][] pipesCon){
        adj = new ArrayList<>();
        //节点 1 ~ num
        for (int i = 0; i <=num; i++) {
            adj.add(new ArrayList<>());
        }

        //构建无相边
        for(int[] pipe : pipesCon){
            int u = pipe[0];
            int v = pipe[1];
            int cap = pipe[2];
            adj.get(u).add(new int[]{v,cap});
            adj.get(v).add(new int[]{u,cap});
        }

        //dfs, 从 baseR 出发，父亲-1，返回该子树向上输出的最大流量
        int ans = dfs(baseR,-1);
        return ans;
    }

    private static int dfs(int baseR,int father){
        int totalCanSendToFather = 0;
        for(int[] edge : adj.get(baseR)){
            int childNode = edge[0];
            int pipeCapacity = edge[1];

            if(childNode == father){
                continue; // 不要回头往父亲走
            }
            // 递归：算出【childNode这颗子树】最多可以输送多少油给到当前cur节点
            int subTreeOutput = dfs(childNode, baseR);
            // 管道会限流：子树输出 和管道容量，取较小，就是这条分支实际送到cur的流量
            int realFlowThisBranch = Math.min(pipeCapacity, subTreeOutput);
            totalCanSendToFather += realFlowThisBranch;
        }
        return totalCanSendToFather;
    }
}
