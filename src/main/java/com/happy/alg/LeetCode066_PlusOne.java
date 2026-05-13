package com.happy.alg;

import java.util.Arrays;

public class LeetCode066_PlusOne {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[] { 9, 9 })));
    }

    public static int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        int carry = 1;
        for (; i >= 0; i--) {
            int v = digits[i] + carry;
            if (v <= 9) {
                digits[i] = v;
                return digits;
            } else {
                digits[i] = v % 10;
                carry = v / 10;
            }
        }
        if (carry == 1) {
            int[] dl = new int[digits.length + 1];
            dl[0] = 1;
            System.arraycopy(digits, 0, dl, 1, digits.length);
            return dl;
        }
        return null;
    }
}
