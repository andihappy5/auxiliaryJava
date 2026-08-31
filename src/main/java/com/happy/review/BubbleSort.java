package com.happy.review;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        System.out.println("Bubble Sort");
        int[] arr = new int[] { 8,7,9,3,5,0,2,3,5};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean  swap = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) { // j< len-1 确定了j可以使用j+1
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }
            System.out.println(Arrays.toString(arr));
            if (!swap) {
                return;
            }
        }
    }
}
