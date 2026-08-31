package com.happy.review;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        System.out.println("quickSort");
        int[] arr = new int[] { 8,7,9,3,5,0,2,3,5 };
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotI = partion(arr,low,high);
            System.out.println(Arrays.toString(arr));
            quickSort(arr,low,pivotI-1);
            quickSort(arr,pivotI+1,high);
        }
    }
    private static int partion(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low;
        while (low < high) {
            while (low < high && arr[high] >= pivot) { //>= 改成 > 就可能死循环，例如数组[3,3,3]
                high--;
            }
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            if (low < high) {
                swap(arr,low,high);
            }
        }
        swap(arr,left,low);
        return low;
    }

    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
