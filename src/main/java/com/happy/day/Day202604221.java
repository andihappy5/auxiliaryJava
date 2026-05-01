package com.happy.day;

public class Day202604221 {
    public static void main() {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}},20));//t
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}},21));//f
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}},50));//t
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}},65));//f
        System.out.println(searchMatrix(new int[][]{{1,3,5,7}},3));//t
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},3));//t
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        if (matrix[0][0] > target || matrix[matrix.length-1][matrix[0].length-1] < target) return false;
        // confirm the row
        int left = 0,right  = matrix.length-1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if(matrix[mid][0] == target){
                return true;
            }else if(matrix[mid][0] < target){
                left = mid + 1;
            }else if(matrix[mid][0] > target){
                right = mid - 1;
            }
        }
        //confirm the clo
        int clo = left > 0?left-1:left;
        int start = 0; int end = matrix[clo].length-1;
        while (start <= end){
            int mid =  start + (end - start)/2;
            if(matrix[clo][mid] == target){
                return true;
            }else if(matrix[clo][mid] > target){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return false;
    }
}
