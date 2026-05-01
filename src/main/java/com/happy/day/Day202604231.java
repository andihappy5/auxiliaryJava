package com.happy.day;

public class Day202604231 {
    public static void main() {

        System.out.println(searchMatrix(new int[][]{
                {1},{3},{5}
        },2));

        System.out.println(searchMatrix(new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}},5));//t
    }

    public static  boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        if (matrix[0][0] > target || matrix[matrix.length - 1][matrix[0].length - 1] < target)
            return false;
        // confirm the row
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                left = mid + 1;
            } else if (matrix[mid][0] > target) {
                right = mid - 1;
            }
        }
        //confirm the clo
        int clo = left > 0 ? left - 1 : left;
        for (int i = 0; i <= clo; i++) {
            if (matrix[i].length-1 >= 1) {
                if (searchMatrixLine(matrix[i],1,matrix[i].length-1,target)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean searchMatrixLine(int[] matrix, int left, int right, int target) {
        if (matrix == null || matrix.length == 0) return false;
        if (matrix[left] > target || matrix[right] < target) return false;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid] == target) {
                return true;
            }else if (matrix[mid] < target) {
                left = mid + 1;
            }else  {
                right = mid - 1;
            }
        }
        return false;
    }

    //右上角出发，比 target 大就左移（砍列），比 target 小就下移（砍行），直到找到或越界。
    public boolean searchMatrixOptimized(int[][] matrix, int target) {
        int rowStart=0;
        int rowEnd=matrix.length-1;
        int colStart=0;
        int colEnd=matrix[0].length-1;

        while(colEnd>=0 && rowStart<=rowEnd){
            if(matrix[rowStart][colEnd]==target){
                return true;
            }
            else if(matrix[rowStart][colEnd]>target){
                colEnd--;
            }
            else if (matrix[rowStart][colEnd]<=target){
                rowStart++;
            }
        }
        return false;
    }
}
