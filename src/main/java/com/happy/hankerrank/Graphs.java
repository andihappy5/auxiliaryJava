package com.happy.hankerrank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graphs {
    public static void main(String[] args) {
        // int[][] graph = {
        //         {1, 2},
        //         {0, 3},
        //         {0, 3},
        //         {1, 2}
        // };
        // boolean isBipartite = isBipartite(graph);
        // System.out.println("Is the graph bipartite? " + isBipartite);

        List<List<Integer>> graph = List.of(
                List.of(0, 1),
                List.of(1, 2),
                List.of(3, 4)
        );
        int n = 5; // Number of nodes in the graph
        int isolatedGroups = countIsolatedCommunicationGroups(graph, n);
        System.out.println("Number of isolated communication groups: " + isolatedGroups);
    }

    public static int countIsolatedCommunicationGroups(List<List<Integer>> graph,int n) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for(List<Integer> neighbors : graph) {
           int elementA = neighbors.get(0);
           int elementB = neighbors.get(1);
           link(parents, elementA, elementB);
        }

        Set<Integer> uniqueRoots = new HashSet<>();
        for (int i = 0; i < parents.length; i++) {
            uniqueRoots.add(parents[i]);
        }
        return uniqueRoots.size();
    }

    private static int find(int[] parents, int element) {
        if (parents[element] != element) {
            return find(parents, parents[element]); // Path compression
        }
        return parents[element];
    }

    private static void link(int[] parents, int elementA, int elementB) {
        int rootA = find(parents, elementA);
        int rootB = find(parents, elementB);
        if (rootA != rootB) {
            parents[rootB] = rootA;
        }
    }

    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // 0: uncolored, 1: color A, -1: color B

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == 0) {
                if (!dfs(graph, colors, neighbor, -color)) {
                    return false;
                }
            } else if (colors[neighbor] == color) {
                return false; // Same color as current node
            }
        }
        return true;
    }
}
