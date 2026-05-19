package com.happy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PSE {
    // Previous Smaller Element
    /*
     * Given an array arr[], find the Previous Smaller Element (PSE) for every
     * element in the array.
     * 
     * The Previous Smaller Element of an element x is defined as the first element
     * to its left in the array that is smaller than x.
     * If no such element exists for a particular position, the PSE should be
     * considered as -1.
     * Examples:
     * 
     * Input: arr[] = [1, 6, 2]
     * Output: [-1, 1, 1]
     * Explanation: For the first element 1, there is no element to its left, so the
     * result is -1. For 6, the previous smaller element is 1. For 2, the previous
     * smaller element is also 1, since it is the closest smaller number when
     * looking left.
     * 
     * Input: arr[] = [1, 5, 0, 3, 4, 5]
     * Output: [-1, 1, -1, 0, 3, 4]
     * Explanation:
     * For 1, no element on the left → -1
     * For 5, the previous smaller element is 1
     * For 0, no smaller element on the left → -1
     * For 3, the previous smaller element is 0
     * For 4, the previous smaller element is 3
     * For the last 5, the previous smaller element is 4
     * 
     * 
     */

    // Bruce loop O(n2) Time and O(1) Space
    static ArrayList<Integer> prevSmaller2(int arr[]) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();

        // initialize all as -1
        for (int i = 0; i < n; i++)
            result.add(-1);

        // for each element, check all elements
        // on the left
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    result.set(i, arr[j]);
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 5, 0, 3, 4, 5 };
        ArrayList<Integer> ans = prevSmallerElement(arr);
        for (int x : ans)
            System.out.print(x + " ");
        System.out.println();
        System.out.println(Arrays.toString(prevSmallerindex(arr)));
    }

    //
    static ArrayList<Integer> prevSmallerElement(int arr[]) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();
        // initialize all as -1
        for (int i = 0; i < n; i++)
            result.add(-1);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            // pop elements from stack until a smaller
            // element is found or stack becomes empty
            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }
            // if stack is not empty, top is nearest smaller
            if (!st.isEmpty()) {
                result.set(i, st.peek());
            }
            // push current element to stack
            st.push(arr[i]);
        }
        return result;
    }

    // return pse index: { 1, 5, 0, 3, 4, 5 }
    static int[] prevSmallerindex(int[] array) {
        int[] res = new int[array.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] >= array[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek();
            }
            stack.add(i);
        }
        return res;
    }

}
