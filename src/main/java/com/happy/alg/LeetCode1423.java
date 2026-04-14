package com.happy.alg;

public class LeetCode1423 {
    /**
     * There are several cards arranged in a row, and each card has an associated
     * number of points.
     * The points are given in the integer array cardPoints.
     * 
     * In one step, you can take one card from the beginning or from the end of the
     * row.
     * You have to take exactly k cards.
     ** 
     * 这里比较的难理解，take one card from the beginning or from the end of the
     * row，从前面拿或者从后面拿，
     * 但是连续这里怎么体现的？我以为这里是只拿一张 card，from beginning or from the end of the row **
     * 
     * Your score is the sum of the points of the cards you have taken.
     * 
     * Given the integer array cardPoints and the integer k, return the maximum
     * score you can obtain.
     * 
     * Example 1:
     * 
     * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
     * Output: 12
     * Explanation: After the first step, your score will always be 1. However,
     * choosing the rightmost card first will maximize your total score. The optimal
     * strategy is to take the three cards on the right, giving a final score of 1 +
     * 6 + 5 = 12.
     * Example 2:
     * 
     * Input: cardPoints = [2,2,2], k = 2
     * Output: 4
     * Explanation: Regardless of which two cards you take, your score will always
     * be 4.
     * Example 3:
     * 
     * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
     * Output: 55
     * Explanation: You have to take all the cards. Your score is the sum of points
     * of all cards.
     * 
     * 
     * Constraints:
     * 
     * 1 <= cardPoints.length <= 105
     * 1 <= cardPoints[i] <= 104
     * 1 <= k <= cardPoints.length
     */

    class Solution {
        public int maxScore(int[] cardPoints, int k) {
            // deal the corner case
            if (cardPoints.length == k) {
                int sum = 0;
                for (int i : cardPoints) {
                    sum += i;
                }
                return sum;
            }

            // 1. get the sum of the first k cards
            int sumk = 0;
            for (int i = 0; i < k; i++) {
                sumk += cardPoints[i];
            }
            // 2. use sliding window method , get the max sum of the last k cards
            // example: [1,2,3,4,5,6,1], k = 3
            // sumk = 1 + 2 + 3 = 6
            // currentSum = 6 - 3 + 1 = 4
            // maxSum = max(6, 4) = 6
            // currentSum = 4 - 2 + 6 = 8
            // maxSum = max(6, 8) = 8
            // currentSum = 8 - 1 + 5 = 12
            // maxSum = max(8, 12) = 12
            int maxSum = sumk;
            int currentSum = sumk;
            for (int i = 0; i < k; i++) {
                currentSum = currentSum - cardPoints[k - 1 - i] + cardPoints[cardPoints.length - 1 - i];
                maxSum = Math.max(maxSum, currentSum);
            }
            return maxSum;
        }
    }

    class Solution2 {
        public int maxScore(int[] cardPoints, int k) {
            // corner case
            if (cardPoints.length == k) {
                int sum = 0;
                for (int e : cardPoints) {
                    sum += e;
                }
                return sum;
            }

            // 1. get the sum of the first k elements
            int sumk = 0;
            for (int i = 0; i < k; i++) {
                sumk = sumk + cardPoints[i];
            }
            int max = sumk;
            int cursum = sumk;
            for (int i = k; i > 0; i--) {
                cursum = cursum - cardPoints[i - 1] + cardPoints[cardPoints.length - 1 - k + i];
                max = Math.max(max, cursum);
            }
            return max;
        }
    }

    public static void main(String[] args) {
        LeetCode1423.Solution2 solution = new LeetCode1423().new Solution2();
        System.out.println(solution.maxScore(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 3));
    }
}
