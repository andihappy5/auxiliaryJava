package com.happy.alg;

import java.util.List;

public class LeetCode120Triangle {
    // 120. Triangle
    /**
     * Given a triangle array, return the minimum path sum from top to bottom.
     * 
     * For each step, you may move to an adjacent number of the row below. More
     * formally, if you are on index i on the current row, you may move to either
     * index i or index i + 1 on the next row.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * Output: 11
     * Explanation: The triangle looks like:
     * 2
     * 3 4
     * 6 5 7
     * 4 1 8 3
     * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined
     * above).
     * Example 2:
     * 
     * Input: triangle = [[-10]]
     * Output: -10
     * 
     * 
     * Constraints:
     * 
     * 1 <= triangle.length <= 200
     * triangle[0].length == 1
     * triangle[i].length == triangle[i - 1].length + 1
     * -104 <= triangle[i][j] <= 104
     * Follow up: Could you do this using only O(n) extra space, where n is the
     * total number of rows in the triangle?
     * 
     */
    /**
     * This problem is quite well-formed in my opinion.
     * The triangle has a tree-like structure, which would lead people to think
     * about traversal algorithms such
     * as DFS. However, if you look closely, you would notice that the adjacent
     * nodes always share a 'branch'. In other word, there are overlapping
     * subproblems.
     * Also, suppose x and y are 'children' of k. Once minimum paths
     * from x and y to the bottom are known, the minimum path starting from k can be
     * decided in O(1), that is optimal substructure.
     * Therefore, dynamic programming would be the best solution to this problem
     * in terms of time complexity.
     * 
     * What I like about this problem even more is that the difference between
     * 'top-down' and 'bottom-up' DP can be 'literally' pictured in the input
     * triangle. For 'top-down' DP, starting from the node on the very top, we
     * recursively find the minimum path sum of each node. When a path sum is
     * calculated, we store it in an array (memoization); the next time we need to
     * calculate the path sum of the same node, just retrieve it from the array.
     * However, you will need a cache that is at least the same size as the input
     * triangle itself to store the pathsum, which takes O(N^2) space. With some
     * clever thinking, it might be possible to release some of the memory that will
     * never be used after a particular point, but the order of the nodes being
     * processed is not straightforwardly seen in a recursive solution, so deciding
     * which part of the cache to discard can be a hard job.
     * 
     * 'Bottom-up' DP, on the other hand, is very straightforward: we start from the
     * nodes on the bottom row; the min pathsums for these nodes are the values of
     * the nodes themselves. From there, the min pathsum at the ith node on the kth
     * row would be the lesser of the pathsums of its two children plus the value of
     * itself, i.e.:
     * 
     * 
     */

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] minlen = triangle.get(n - 1).stream().mapToInt(Integer::intValue).toArray();
        for (int layer = n - 2; layer >= 0; layer--) // For each layer
        {
            for (int i = 0; i <= layer; i++) // Check its every 'node'
            {
                // Find the lesser of its two children, and sum the current value in the
                // triangle with it.
                minlen[i] = Math.min(minlen[i], minlen[i + 1]) + triangle.get(layer).get(i);
            }
        }
        return minlen[0];
    }
}
