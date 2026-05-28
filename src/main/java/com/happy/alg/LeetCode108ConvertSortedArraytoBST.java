package com.happy.alg;

import com.happy.util.TreeNode;

public class LeetCode108ConvertSortedArraytoBST {
    // 108. Convert Sorted Array to Binary Search Tree
    /**
     * Given an integer array nums where the elements are sorted in ascending order,
     * convert it to a height-balanced binary search tree.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: nums = [-10,-3,0,5,9]
     * Output: [0,-3,9,-10,null,5]
     * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
     * 
     * Example 2:
     * 
     * 
     * Input: nums = [1,3]
     * Output: [3,1]
     * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums is sorted in a strictly increasing order.
     * 
     */

    public static void main(String[] args) {
        int[] nums = new int[] { -10, -3, 0, 5 };
        TreeNode res = new LeetCode108ConvertSortedArraytoBST().sortedArrayToBST(nums);
        System.out.println(res);
    }

    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }
}
