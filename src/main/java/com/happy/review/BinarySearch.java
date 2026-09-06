package com.happy.review;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[] { 8,7,9,3,5,0,2,3,5};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.binarySearch(arr,8));
        System.out.println(binarySearch(arr,8));
//        System.out.println(Arrays.binarySearch(arr,10));
        System.out.println(binarySearch(arr,10));
    }

    public static int binarySearch(int[] arr,int key){
        if (arr==null||arr.length==0){
            return -1;
        }
        int left =0;
        int right = arr.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (arr[mid] < key){
                left=mid+1;
            }else if (arr[mid] > key){
                right=mid-1;
            }else  {
                return mid;
            }
        }
        return -1;
    }

    public int binarySearch(int[] arr, int low, int high, int key) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == key) {
            return mid;
        }else if (arr[mid] > key) {
            return binarySearch(arr, low, mid - 1, key);
        }else{
            return binarySearch(arr, mid + 1, high, key);
        }
    }
}
