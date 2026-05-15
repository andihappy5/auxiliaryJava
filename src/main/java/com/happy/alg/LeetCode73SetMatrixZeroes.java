package com.happy.alg;

public class LeetCode73SetMatrixZeroes {
    // 73. Set Matrix Zeroes
    /**
     * 
     * Given an m x n integer matrix matrix, if an element is 0, set its entire row
     * and column to 0's.
     * 
     * You must do it in place.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: [[1,0,1],[0,0,0],[1,0,1]]
     * Example 2:
     * 
     * 
     * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
     * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
     * 
     * Constraints:
     * 
     * m == matrix.length
     * n == matrix[0].length
     * 1 <= m, n <= 200
     * -231 <= matrix[i][j] <= 231 - 1
     */

    // you must do it in place
    class Solution {
        public void setZeroes(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            boolean firstRowHasZero = false;
            boolean firstColHasZero = false;

            // Check if the first row contains zero
            for (int c = 0; c < cols; c++) {
                if (matrix[0][c] == 0) {
                    firstRowHasZero = true;
                    break;
                }
            }

            // Check if the first column contains zero
            for (int r = 0; r < rows; r++) {
                if (matrix[r][0] == 0) {
                    firstColHasZero = true;
                    break;
                }
            }

            // Use the first row and column as markers
            for (int r = 1; r < rows; r++) {
                for (int c = 1; c < cols; c++) {
                    if (matrix[r][c] == 0) {
                        matrix[r][0] = 0;
                        matrix[0][c] = 0;
                    }
                }
            }

            // Set the marked rows to zero
            for (int r = 1; r < rows; r++) {
                if (matrix[r][0] == 0) {
                    for (int c = 1; c < cols; c++) {
                        matrix[r][c] = 0;
                    }
                }
            }

            // Set the marked columns to zero
            for (int c = 1; c < cols; c++) {
                if (matrix[0][c] == 0) {
                    for (int r = 1; r < rows; r++) {
                        matrix[r][c] = 0;
                    }
                }
            }

            // Set the first row to zero if needed
            if (firstRowHasZero) {
                for (int c = 0; c < cols; c++) {
                    matrix[0][c] = 0;
                }
            }

            // Set the first column to zero if needed
            if (firstColHasZero) {
                for (int r = 0; r < rows; r++) {
                    matrix[r][0] = 0;
                }
            }
        }
    }
}
