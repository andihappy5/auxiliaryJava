package com.happy.alg;

public class LeetCode69Sqrt {

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395600));
        System.out.println(289399 * 289399);
        System.out.println(289398 * 289398);
    }

    public static int mySqrt(int x) {
        if (x == 0)
            return 0;
        if (x == 1 || x == 2 || x == 3)
            return 1;
        int res = 2;
        while ((long) res * (long) res <= x) {
            res++;
        }
        return res-1;
    }

    static class Solution {

        public static void main(String[] args) {
            System.out.println(mySqrt(2147395600));
        }

        public static int mySqrt(int x) {
            // For special cases when x is 0 or 1, return x.
            if (x == 0 || x == 1)
                return x;

            // Initialize the search range for the square root.
            int start = 1;
            int end = x;
            int mid = -1;

            // Perform binary search to find the square root of x.
            while (start <= end) {
                // Calculate the middle point using "start + (end - start) / 2" to avoid integer
                // overflow.
                mid = start + (end - start) / 2;

                // If the square of the middle value is greater than x, move the "end" to the
                // left (mid - 1).
                if ((long) mid * mid > (long) x)
                    end = mid - 1;
                else if (mid * mid == x)
                    // If the square of the middle value is equal to x, we found the square root.
                    return mid;
                else
                    // If the square of the middle value is less than x, move the "start" to the
                    // right (mid + 1).
                    start = mid + 1;
            }

            // The loop ends when "start" becomes greater than "end", and "end" is the
            // integer value of the square root.
            // However, since we might have been using integer division in the calculations,
            // we round down the value of "end" to the nearest integer to get the correct
            // square root.
            return Math.round(end);
        }
    }
}
