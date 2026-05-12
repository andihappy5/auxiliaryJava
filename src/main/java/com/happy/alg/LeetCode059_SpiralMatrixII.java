package com.happy.alg;

public class LeetCode059_SpiralMatrixII {
    //59. Spiral Matrix II
    //Given a positive integer n,
    // generate an n x n matrix filled with elements from 1 to n2 in spiral order.
    //
    //
    //
    //Example 1:
    //Input: n = 3
    //Output: [[1,2,3],[8,9,4],[7,6,5]]
    //Example 2:
    //Input: n = 1
    //Output: [[1]]
    //
    //Constraints:
    //1 <= n <= 20

    static  class Solution {
        public int[][] generateMatrix(int x) {
            int[][] matrix = new int[x][x];
            int m = matrix.length;
            int n = matrix[0].length;
            int left = 0, right = n-1;
            int top = 0, bottom = m-1;

            int ii = 1;
            while(ii <= m*n){
                for(int j=left; j<=right && ii <= m*n ; j++){
                    matrix[top][j] = ii;
                    ii++;
                }
                top++;
                for(int i = top; i<=bottom && ii <= m*n; i++){
                    matrix[i][right]=ii;
                    ii++;
                }
                right--;
                for(int j=right; j>=left && ii <= m*n; j--){
                    matrix[bottom][j] = ii;
                    ii++;
                }
                bottom--;
                for(int i=bottom; i>=top && ii <= m*n; i--){
                    matrix[i][left] = ii;
                    ii++;
                }
                left++;
            }
            return matrix;
        }
    }
}
