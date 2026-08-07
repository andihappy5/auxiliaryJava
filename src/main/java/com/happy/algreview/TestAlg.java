package com.happy.algreview;

public class TestAlg {
    public static void main(String[] args) {
        System.out.println("Hello, TestAlg!");

        productExceptSelf(new int[] { 1, 2, 3, 4 });
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int pre[] = new int[n];
        int suff[] = new int[n];
        pre[0] = 1;
        suff[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }

        System.out.println("pre: " + java.util.Arrays.toString(pre));

        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1];
        }

        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = pre[i] * suff[i];
        }
        return ans;
    }
}
