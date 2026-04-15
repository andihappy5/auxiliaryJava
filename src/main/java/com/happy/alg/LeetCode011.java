package com.happy.alg;//Given n non-negative integers a1, a2, ..., an , where each represents a point

//at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
// the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x
//-axis forms a container, such that the container contains the most water. 
//
// Notice that you may not slant the container. 
//
// 
// Example 1: 
//
// 
//Input: height = [1,8,6,2,5,4,8,3,7]
//Output: 49
//Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,
//3,7]. In this case, the max area of water (blue section) the container can conta
//in is 49.
// 
//
// Example 2: 
//
// 
//Input: height = [1,1]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: height = [4,3,2,1,4]
//Output: 16
// 
//
// Example 4: 
//
// 
//Input: height = [1,2,1]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics Array Two Pointers 
// 👍 7901 👎 646

public class LeetCode011 {

    class Solution2 {
        public int maxArea(int[] height) {
            int i = 0, j = height.length - 1;
            int capacity = 0;
            while (i < j) {
                int c = Math.min(height[i], height[j]) * (j - i);
                capacity = Math.max(capacity, c);
                if (height[i] <= height[j]) {
                    int a = height[i];
                    while (i < j && height[i] <= a) {
                        i++;
                    }
                } else {
                    int b = height[j];
                    while (i < j && height[j] <= b) {
                        j--;
                    }
                }
            }
            return capacity;
        }
    }

    class Solution {
        public int maxArea(int[] height) {
            if (height == null || height.length <= 1)
                return 0;
            int left = 0, right = height.length - 1;
            int maxArea = 0;
            while (left < right) {
                maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return maxArea;
        }

        public int maxArea2(int[] height) {
            if (height == null || height.length <= 1)
                return 0;
            int left = 0, right = height.length - 1;
            int res = 0;
            while (left < right) {
                int min = Math.min(height[left], height[right]);
                res = Math.max(res, min * (right - left));
                while (left < right && height[left] <= min)
                    left++;
                while (left < right && height[right] <= min)
                    right--;
            }
            return res;
        }
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int max = 0;
        int from = 0;
        int to = height.length - 1;
        while (from < to) {
            max = Math.max(max, (to - from) * Math.min(height[from], height[to]));
            if (height[from] > height[to]) {
                to--;
            } else {
                from++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("hello holia");
        System.out.println(maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
        System.out.println(maxArea(new int[] { 1, 1 }));

        System.out.println(maxArea(new int[] { 4, 3, 2, 1, 4 }));

        System.out.println(maxArea(new int[] { 1, 2, 1 }));

        System.out.println(maxArea(new int[] { 1, 1, 3, 4, 5, 1, 1, 1, 1 }));

    }
}
