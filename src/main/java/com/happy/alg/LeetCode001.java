package com.happy.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//
//## HashMap
//## Cache
/**
 * how to explain the code to interviewer?
 * 1. the problem is to find two numbers in an array that add up to a target
 * value, and return their indices .
 * 2. the solution uses a HashMap to store the complement of each number (target
 * - current number) and its index as we iterate through the array.
 * 3. for each number, we check if its complement is already in the HashMap
 * 4. if it is, we have found the two numbers that add up to the target, and we
 * return their indices.
 * 5. if it is not, we add the current number and its index to the HashMap for
 * future reference.
 * 6. this approach allows us to find the solution in a single pass through the
 * array, resulting in a time complexity of O(n) and a space complexity of O(n)
 * due to the HashMap storing the complements.
 * 7. the second and third methods are variations of the first method, where we
 * check for the complement from both ends of the array simultaneously, which
 * can potentially reduce the number of iterations needed to find the solution.
 * 8. the key point is that we are using the HashMap to efficiently check for
 * the existence of the complement, which allows us to find the solution in
 * linear time.
 * 8. the reason we put target - nums[i] in the HashMap instead of nums[i] is
 * because we want to store the complement of the current number, which is what
 * we need to find in order to reach the target. If we stored nums[i], we would
 * be looking for the same number instead of its complement, which would not
 * help us find the solution.
 * 9. the second and third methods are essentially the same in terms of logic,
 * but they differ in how they check for the complement. The second method
 * checks for the complement from both ends of the array simultaneously, while
 * the third method checks for the complement in a single pass from the
 * beginning of the array. Both methods achieve the same result, but the second
 * method may be more efficient in certain cases where the solution is located
 * towards the end of the array.
 * 10. in summary, the key to solving this problem is to use a HashMap to store
 * the complements of the numbers we have seen so far, which allows us to
 * efficiently check for the existence of the complement and find the solution
 * in linear time.
 * 11. the time complexity of the first method is O(n) because we iterate
 * through the array once, and each lookup in the HashMap is O(1). The space
 * complexity is also O(n) because in the worst case, we may store all the
 * numbers in the HashMap if there are no two numbers that add up to the target.
 * 12. the second and third methods also have a time complexity of O(n) because
 * we still iterate through the array once, but they may have a slightly better
 * performance in practice due to
 * the way they check for the complement from both ends of the array, which can
 * potentially reduce the number of iterations needed to find the solution.
 * However, the worst-case time complexity remains O(n) for all three methods.
 * 13. in conclusion, the key to solving the two-sum problem efficiently is to
 * use a HashMap to store the complements of the numbers we have seen so far,
 * which allows us to find the solution in linear time. The variations of the
 * method can provide some performance benefits in certain cases, but the
 * overall approach remains the same.
 * 14. the second and third methods are essentially the same in terms of logic,
 * but they differ in how they check for the complement. The second method
 * checks for the complement from both ends of the array simultaneously, while
 * the third method checks for the complement in a single pass from the
 * beginning of the array. Both methods achieve the same result, but the second
 * method may be more efficient in certain cases where the solution is located
 * towards the end of the array.
 * 15. in summary, the key to solving this problem is to use a HashMap to store
 * the complements of the numbers we have seen so far, which allows us to
 * efficiently check for the existence of the complement and find the solution
 * in linear time.
 * 16. the time complexity of the first method is O(n) because we iterate
 * through the array once, and each lookup in the HashMap is O(1). The space
 * complexity is also O(n) because in the worst case, we may store all the
 * numbers in the HashMap if there are no two numbers that add up to the target.
 * 17. the second and third methods also have a time complexity of O(n) because
 * we still iterate through the array once, but they may have a slightly better
 * performance in practice due to the way they check for the complement from
 * both ends of the array, which can potentially reduce the number of iterations
 * needed to find the solution. However, the worst-case time complexity remains
 * O(n) for all three methods.
 * 
 */
public class LeetCode001 {
    public static void main(String[] args) {
        Solution T = new Solution();
        int[] result = T.twoSum2(new int[] { 1, 2, 3, 4, 5 }, 6);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(T.twoSum3(new int[] { 1, 2, 3, 4, 5 }, 6)));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[2];
            }
            // data structure hashmap used to cache the value and index of the array
            HashMap<Integer, Integer> cache = new HashMap<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                if (cache.containsKey(target - nums[i])) {
                    return new int[] { cache.get(target - nums[i]), i };
                }
                cache.put(nums[i], i);
            }
            return new int[2];
        }

        // from begin and end two directions to loop/circulation
        public int[] twoSum2(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int j = nums.length - 1 - i;
                if (i > j) {
                    return null;
                }
                if (map.containsKey(nums[i])) {
                    return new int[] { map.get(nums[i]), i };
                } else {
                    // why put target - nums[i] instead of nums[i]?
                    // Because we want to find the complement of the current number in the map,
                    // which is target - nums[i]. If we put nums[i] in the map,
                    // we would be looking for the same number instead of its complement.
                    map.put(target - nums[i], i);
                }
                if (map.containsKey(nums[j])) {
                    return new int[] { map.get(nums[j]), j };
                } else {
                    map.put(target - nums[j], j);
                }
            }
            return null;
        }

        public int[] twoSum3(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int j = nums.length - 1 - i;
                if (i > j) {
                    return null;
                }
                if (map.containsKey(target - nums[i])) {
                    return new int[] { map.get(target - nums[i]), i };
                } else {
                    // why put target - nums[i] instead of nums[i]?
                    // Because we want to find the complement of the current number in the map,
                    // which is target - nums[i]. If we put nums[i] in the map,
                    // we would be looking for the same number instead of its complement.
                    map.put(nums[i], i);
                }
                if (map.containsKey(target - nums[j])) {
                    return new int[] { map.get(target - nums[j]), j };
                } else {
                    map.put(nums[j], j);
                }
            }
            return null;
        }
    }
}
