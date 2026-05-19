package com.happy.alg;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class LeetCode85MaximalRectangle {
    // 85. Maximal Rectangle
    /**
     * Given a rows x cols binary matrix filled with 0's and 1's, find the largest
     * rectangle containing only 1's and return its area.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: matrix =
     * [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
     * Output: 6
     * Explanation: The maximal rectangle is shown in the above picture.
     * Example 2:
     * 
     * Input: matrix = [['0']]
     * Output: 0
     * Example 3:
     * 
     * Input: matrix = [['1']]
     * Output: 1
     * 
     * 
     * Constraints:
     * 
     * rows == matrix.length
     * cols == matrix[i].length
     * 1 <= rows, cols <= 200
     * matrix[i][j] is '0' or '1'.
     * 
     */

    // Instead of directly searching the whole matrix, think row by row. For every
    // row, treat it as the base of a rectangle and see how far rectangles of 1’s
    // can extend upward.

    /**
     * Let's break down the solution process into two distinct sub-processes:
     * Processing Height Array and Max Area Calculation.
     * 
     * 1️⃣Processing Height Array✅:
     * Initialization:
     * 
     * Create an array height initialized with zeros, with length equal to the
     * number of columns in the matrix. This array will represent the heights of
     * bars in a histogram.
     * Processing Each Row:
     * 
     * For each row currRow in the 2D matrix:
     * Traverse through each element of currRow.
     * If the element is 1, increment the corresponding index in the height array.
     * If the element is 0, reset the corresponding index in the height array to 0.
     * 
     * 2️⃣Max Area Calculation (Naive Approach):
     * Now that we got height array assume that you are solving Leetcode Problem 84.
     * Largest Rectangle in Histogram where we are given an array of integers
     * heights representing the histogram's bar height where the width of each bar
     * is 1.
     * We want to find and return the area of the largest rectangle in the
     * histogram.
     * Approach:
     * Iterate over all possible pairs of bars (i, j) where i < j.
     * For each pair (i, j), determine the minimum height h between the bars from
     * index i to j.
     * Calculate the area of the rectangle formed by this pair of bars, which is
     * area = h * (j - i + 1).
     * Keep track of the maximum area found during these iterations.
     */

    // The naive approach examines all possible rectangles by iterating through each
    // pair of bars in the histogram, resulting in a time complexity of O(n^3),
    // where n is the number of bars.
    static class Solution2 {
        public static void main(String[] args) {
            System.out.println(maximalRectangle(new char[][] {
                    { '1', '0', '1', '0', '0' },
                    { '1', '0', '1', '1', '1' },
                    { '1', '1', '1', '1', '1' },
                    { '1', '0', '0', '1', '0' }
            }));
        }

        /**
         * 
         * { '1', '0', '1', '0', '0' }, h[0] = 1,0,1,0,0,0
         * { '1', '0', '1', '1', '1' }, h[1] = 2,0,2,1,2,0
         * { '1', '1', '1', '1', '1' }, h[2] = 3,1,3,2,2,0
         * { '1', '0', '0', '1', '0' } h[3] = 4,0,0,3,0,0
         */
        public static int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int cols = matrix[0].length;
            int[] heights = new int[cols + 1]; // Include an extra element for easier calculation
            int[] areas = new int[cols + 1];
            int maxArea = 0;

            for (char[] row : matrix) {
                for (int i = 0; i < cols; i++) {
                    heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
                }

                // Calculate max area using histogram method
                int n = heights.length; // Number of bars in the histogram
                for (int i = 0; i < n; i++) {
                    for (int j = i, minHeight = Integer.MAX_VALUE; j < n; j++) {
                        minHeight = Math.min(minHeight, heights[j]);
                        int area = minHeight * (j - i + 1);
                        areas[i] = area;
                        maxArea = Math.max(maxArea, area);
                    }
                    System.out.println(Arrays.toString(areas));
                }
            }

            return maxArea;
        }
    }

    static class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int cols = matrix[0].length;
            int[] heights = new int[cols + 1]; // Include an extra element for easier calculation
            int maxArea = 0;
            for (char[] row : matrix) {
                for (int i = 0; i < cols; i++) {
                    heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
                }
                // Calculate max area using stack-based method
                Stack<Integer> stack = new Stack<>();
                for (int i = 0; i < heights.length; i++) {
                    while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                        int h = heights[stack.pop()];
                        int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                        maxArea = Math.max(maxArea, h * w);
                    }
                    stack.push(i);
                }
            }
            return maxArea;
        }
    }
}
