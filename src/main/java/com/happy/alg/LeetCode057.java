package com.happy.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode057 {

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        LeetCode057 l = new LeetCode057();
        LeetCode057.Solution s = l.new Solution();

        int[][] intervals = new int[][] { { 1, 3 }, { 6, 9 } };
        int[] newInterval = new int[] { 2, 5 };
        System.out.println(Arrays.deepToString(s.insert(intervals, newInterval)));// [[1,5],[6,9]]

        intervals = new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        newInterval = new int[] { 4, 8 };
        System.out.println(Arrays.deepToString(s.insert(intervals, newInterval)));// [[1,2],[3,10],[12,16]]
    }

    class Solution {
        // please explain this code in detail
        // 1. add intervals that come completely before newInterval
        // 2. merge all overlapping intervals with newInterval
        // 3. add the rest intervals after merged interval
        // time complexity: O(n), space complexity: O(n)
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (newInterval == null || newInterval.length != 2)
                return intervals;

            if (intervals == null || intervals.length == 0) {
                return new int[][] { { newInterval[0], newInterval[1] } };
            }

            List<int[]> result = new ArrayList<>();
            int start = newInterval[0];
            int end = newInterval[1];
            int i = 0;
            int n = intervals.length;

            // add intervals that come completely before newInterval
            while (i < n && intervals[i][1] < start) {
                result.add(intervals[i++]);
            }

            // merge all overlapping intervals with newInterval
            while (i < n && intervals[i][0] <= end) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
                i++;
            }
            result.add(new int[] { start, end });

            // add the rest intervals after merged interval
            while (i < n) {
                result.add(intervals[i++]);
            }

            return result.toArray(new int[result.size()][]);
        }
    }

    /**
     * Given a set of non-overlapping intervals sorted by their start time,
     * insert a new interval into the intervals and merge if necessary.
     *
     * Example 1:
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     *
     * Example 2:
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     *
     * Example 3:
     * Input: intervals = [], newInterval = [5,7]
     * Output: [[5,7]]
     *
     * Constraints:
     * 0 <= intervals.length <= 10^4
     * intervals[i].length == 2
     * -10^6 <= intervals[i][0] <= intervals[i][1] <= 10^6
     */
}
