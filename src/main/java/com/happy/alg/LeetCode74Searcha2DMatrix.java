package com.happy.alg;

public class LeetCode74Searcha2DMatrix {
    // 74. Search a 2D Matrix
    /**
     * You are given an m x n integer matrix matrix with the following two
     * properties:
     * 
     * Each row is sorted in non-decreasing order.
     * The first integer of each row is greater than the last integer of the
     * previous row.
     * Given an integer target, return true if target is in matrix or false
     * otherwise.
     * 
     * You must write a solution in O(log(m * n)) time complexity.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * Output: true
     * Example 2:
     * 
     * 
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * Output: false
     * 
     * 
     * Constraints:
     * 
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     */

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][] {
                { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 }
        }, 11));
        System.out.println(searchMatrix(new int[][] {
                { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 }
        }, 16));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        // first judge in which row
        int start = 0, end = m - 1, mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        mid = end;
        int from = 0, to = n - 1;
        while (from <= to) {
            int m2 = from + (to - from) / 2;
            if (matrix[mid][m2] == target) {
                return true;
            } else if (matrix[mid][m2] > target) {
                to = m2 - 1;
            } else {
                from = m2 + 1;
            }
        }
        return false;
    }
}
