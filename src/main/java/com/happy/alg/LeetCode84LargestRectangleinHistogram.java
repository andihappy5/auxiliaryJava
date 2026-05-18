package com.happy.alg;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode84LargestRectangleinHistogram {
    // 84. Largest Rectangle in Histogram
    /*
     * Given an array of integers heights representing the histogram's bar height
     * where the width of each bar is 1, return the area of the largest rectangle in
     * the histogram.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: heights = [2,1,5,6,2,3]
     * Output: 10
     * Explanation: The above is a histogram where width of each bar is 1.
     * The largest rectangle is shown in the red area, which has an area = 10 units.
     * 
     * 
     * Example 2:
     * Input: heights = [2,4]
     * Output: 4
     * 
     * 
     * Constraints:
     * 
     * 1 <= heights.length <= 105
     * 0 <= heights[i] <= 104
     */
    public static void main(String[] args) {
        int[] values = new int[] { 2, 1, 5, 6, 2, 3 };
        int v = largestRectangleArea(values);
        System.out.println(v);
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Nearest Smaller to Left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        System.out.println(Arrays.toString(left));

        stack.clear(); // Reuse stack

        // Nearest Smaller to Right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(right));
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }
}
