package com.happy.algreview;

public class LeetCode867 {

    public static void main(String[] args) {
        System.out.println("keep happy");
        LeetCode867 l = new LeetCode867();
        LeetCode867.Solution s = l.new Solution();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        s.transpose(matrix1);
        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        s.transpose(matrix2);
        // Expected output:
//        int[][] expected1 = {
//                {1, 4, 3},
//                {2, 5, 6}
//        };

    }

    class Solution {
        public int[][] transpose(int[][] matrix) {
            System.out.println("input matrix:");
            print(matrix);
            int row = matrix.length;
            int col = matrix[0].length;
            int[][] result = new int[col][row];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    result[j][i] = matrix[i][j];
                }
            }
            System.out.println("after transpose:");
            print(result);
            return result;
        }

        private void print(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
