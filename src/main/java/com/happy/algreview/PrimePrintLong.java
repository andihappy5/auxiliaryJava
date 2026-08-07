package com.happy.algreview;

import java.util.Scanner;

public class PrimePrintLong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        sc.close();

        boolean first = true;
        for (long i = 2; i <= num; i++) {
            if (isPrime(i)) {
                if (!first) System.out.print(" ");
                System.out.print(i);
                first = false;
            }
        }
        System.out.println();
    }

    private static boolean isPrime(long n) {
        if (n <= 1) return false;
        for (long j = 2; j * j <= n; j++) {
            if (n % j == 0) return false;
        }
        return true;
    }
}
