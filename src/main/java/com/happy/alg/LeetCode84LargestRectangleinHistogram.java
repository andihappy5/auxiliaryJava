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
        int v = largestRectangleArea1(values);
        System.out.println(v);
    }

    // The idea is to fix each bar as the height of the rectangle and expand towards
    // the left and right while the bars are at least as tall as the current bar.
    // For every valid step, we keep adding the current bar’s height to the area. By
    // doing this for all bars and keeping track of the maximum value.

    // O(n2) Time and O(1) Space >> Time limit exceeded
    public static int largestRectangleArea1(int[] arr) {
        int res = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            int curr = arr[i];

            // Traverse left while we have a greater height bar
            for (int j = i - 1; j >= 0 && arr[j] >= arr[i]; j--)
                curr += arr[i];

            // Traverse right while we have a greater height bar
            for (int j = i + 1; j < n && arr[j] >= arr[i]; j++)
                curr += arr[i];

            res = Math.max(res, curr);
        }
        return res;
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

    static class finalClass {
        static int getMaxArea(int[] arr) {
            int n = arr.length;
            Stack<Integer> st = new Stack<>();
            int res = 0; // result
            int top = 0; // statck stop value
            int curr = 0; // current postion
            for (int i = 0; i < arr.length; i++) {
                // according to the rule: Process the stack while current element is smaller
                // than
                // the element corresponding to the top of the stack
                while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                    // the popped item is to be considered as the smallest element of the histogrm
                    top = st.pop();
                    // for the popped item , previous smaller element is just below it in the stack(
                    // or current stack top) and next smaller element is i
                    int width = st.isEmpty() ? i : i - st.peek() - 1;
                    // Update the result if needed
                    res = Math.max(res, arr[top] * width);
                }
                st.push(i);
            }
            // For the remaining items in the stack, next smaller does
            // not exist. Previous smaller is the item just below in
            // the stack.
            while (!st.isEmpty()) {
                top = st.pop();
                curr = arr[top] * (st.isEmpty() ? n : n - st.peek() - 1);
                res = Math.max(res, curr);
            }
            return res;
        }
    }
}
