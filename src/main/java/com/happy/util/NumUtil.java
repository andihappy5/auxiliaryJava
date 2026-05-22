package com.happy.util;

import java.util.Arrays;
import java.util.List;

public class NumUtil {

    static void main() {
        System.out.println(decimalConv(Arrays.asList(1,0,0,1)));
        System.out.println(decimalConv(new int[]{1,0,0,1}));
    }


    public static int decimalConv(List<Integer> bit) {
        int pow = 0;
        int sum = 0;
        for (int ele : bit) {
            sum += (ele * (int) Math.pow(2, pow));
            pow++;
        }
        return sum;
    }
    public static int decimalConv(int[] bit) {
        int pow = 0;
        int sum = 0;
        for (int ele : bit) {
            sum += (ele * (int) Math.pow(2, pow));
            pow++;
        }
        return sum;
    }
}
