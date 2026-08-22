package com.happy.partSolution;

public class BitOperation {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(-1));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.numberOfLeadingZeros(5));
        // >>> : 无符号右移，忽略符号位，空位都以0补齐
        System.out.println(Integer.toBinaryString(-1 >>> Integer.numberOfLeadingZeros(5)));
    }
}
