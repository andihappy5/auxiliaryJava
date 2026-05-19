package com.happy.util;

import java.util.Arrays;

public class Array {

    public static void main(String[] args) {
        int[][] v = new int[][] {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 0, 1, 9 }
        };
        int[] v2 = useRow(v);
        System.out.println(Arrays.toString(v2));
    }

    public static int[] useRow(int[][] arr) {
        for (int[] is : arr) {
            System.out.println(Arrays.toString(is));
            if (is.length > 0) {
                if (is[is.length - 1] == 9) {
                    return is;
                }
            }
        }
        return new int[] {};
    }
}
