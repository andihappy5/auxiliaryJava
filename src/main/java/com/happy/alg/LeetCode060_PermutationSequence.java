package com.happy.alg;

import java.util.LinkedList;
import java.util.List;

public class LeetCode060_PermutationSequence {
//    example:
//    n = 4, k = 14:
//    We have {1, 2, 3, 4}, find the 14th permutation.
//    List out all the permutations:
//            1 + (permutations of 2, 3, 4)
//            2 + (permutations of 1, 3, 4)
//            3 + (permutations of 1, 2, 4)
//            4 + (permutations of 1, 2, 3)

//    To find the 14th, we can see it falling to range 3 + (permutations of 1, 2, 4),
//    since 1 + (permutations of 2, 3, 4) and 2 + (permutations of 1, 3, 4)
//    could take the first 2 * (3!) = 12 permutations.
//    So we can know the first number of result is 3.
//    Then the question turn to be a smaller problem.
//    {1, 2, 4}, find the 2nd permutation.
//    List out all the permutations:
//            1 + (permutations of 2, 4)
//            2 + (permutations of 1, 4)
//            4 + (permutations of 1, 2)
//    There are 2! + 2! + 2!, 6 permutation.
//    The 2nd must be in range 1 + (permutations of 2, 4).
//    So we can know the second number of result is 1.

//    So the question turn be a smaller problem.
//    {2, 4}, find the 2nd permutation. The answer is (4, 2).
//    So the final result is (3, 1, 4, 2)

    public   static void main() {
        System.out.println(getPermutation(6,334));
    }

    public static String getPermutation(int n, int k) {
        List<Integer> nums = new LinkedList();

        StringBuilder res = new StringBuilder();
        int[] f = new int[n];
        f[0] = 1; // 0's factorial is 1
        for (int i = 1; i < n; i++) {
            nums.add(i);
            f[i] = f[i - 1] * i;
        }
        nums.add(n);
        //        1, 1, 2,  6,   24,  120
        //        0, 1!, 2!, 3!,  4!,   5!
        k--; // 14th count from 1, turn to be 13th count from 0.
        for (int i = n; i > 0; i--) {
            int idx = k / f[i - 1]; //从大到小的，先判断能出来几个【1，，，，n-1】
            k = k % f[i - 1];
            res.append(nums.get(idx)); //如果包含了 2 个f[i - 1]，第一个元素就是 3,nums.get(idx)
            nums.remove(idx); // nums 然后把第 2 个（从 0 开始）给删除了
        }

        return res.toString();
    }

}
