package com.happy.alg;

/**
 *
 */
public class LeetCode050 {

    static void main() {
        System.out.println(LeetCode050.myPow(2.0000,10));
    }

    /**
     * Typical recursion problems
     * */
    public static double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;
        if(n > 0){
            if (n % 2 == 0){
                return myPow(x*x, n / 2);
            }else {
                return x*myPow(x*x, (n-1) / 2);
            }
        }else {
            if(Integer.MIN_VALUE == n){
                return 1/x*myPow(1/x,Integer.MAX_VALUE);
            }else{
                return myPow(1/x,-n);
            }
        }
    }
}
