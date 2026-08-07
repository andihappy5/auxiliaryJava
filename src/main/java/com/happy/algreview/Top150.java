package com.happy.algreview;

import java.util.*;

import org.hibernate.Remove;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Update;

import com.happy.util.ListNode;
import com.happy.util.Node;
import com.happy.util.TreeNode;

// just for code 
public class Top150 {

    // ----------------------------第一组------------------------------------------ //
    // 1
    // 88. Merge Sorted Array
    // Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    // Output: [1,2,2,3,5,6]
    class Solution88 {
        // remember to use the last index of nums1 to fill the array **from the back**
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1;
            int j = n - 1;
            int k = m + n - 1;
            while (j >= 0) {
                if (i >= 0 && nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }
        }
    }

    // 2
    // 27. Remove Element
    // Input: nums = [3,2,2,3], val = 3
    // Output: 2, nums = [2,2]
    class Solution27 {
        // two pointers, one for the current index, one for the next index to fill
        public int removeElement(int[] nums, int val) {
            int k = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val) {
                    nums[k++] = nums[i];
                }
            }
            return k;
        }
    }

    // 3
    // 26. Remove Duplicates from Sorted Array
    // Input: nums = [0,0,1,1,1,2,2,3,3,4]
    // Output: 5, nums = [0,1,2,3,4]
    class Solution26 {
        // two pointers, one for the current index, one for the next index to fill
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0)
                return 0;
            int k = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[k++] = nums[i];
                }
            }
            return k;
        }
    }

    // 4
    // 80. Remove Duplicates from Sorted Array II
    // Input: nums = [0,0,1,1,1,1,2,3,3]
    // Output: 7, nums = [0,0,1,1,2,3,3]
    class Solution80 {
        // two pointers, one for the current index, one for the next index to fill
        public int removeDuplicates(int[] nums) {
            if (nums.length <= 2)
                return nums.length;
            int k = 2;
            for (int i = 2; i < nums.length; i++) {
                if (nums[i] != nums[k - 2]) {
                    nums[k++] = nums[i];
                }
            }
            return k;
        }
    }

    // 5
    // 169. Majority Element
    // Input: nums = [3,2,3]
    // Output: 3
    class Solution169 {
        // Boyer-Moore Voting Algorithm
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }
    }

    // 6
    // 189. Rotate Array
    // Input: nums = [1,2,3,4,5,6,7], k = 3
    // Output: [5,6,7,1,2,3,4]
    class Solution189 {
        // reverse the whole array, then reverse the first k elements, then reverse the
        // rest
        public void rotate(int[] nums, int k) {
            k = k % nums.length;// 1,2,3,4,5,6,7
            reverse(nums, 0, nums.length - 1); // 7,6,5,4,3,2,1
            reverse(nums, 0, k - 1); // 5,6,7,4,3,2,1
            reverse(nums, k, nums.length - 1); // 5,6,7,1,2,3,4
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

    // 7
    // 121. Best Time to Buy and Sell Stock
    // Input: prices = [7,1,5,3,6,4]
    // Output: 5
    class Solution121 {
        // keep track of the minimum price and the maximum profit
        public int maxProfit(int[] prices) {
            int minPrice = Integer.MAX_VALUE;
            int maxProfit = 0;
            for (int price : prices) {
                if (price < minPrice) {
                    minPrice = price;
                } else if (price - minPrice > maxProfit) {
                    maxProfit = price - minPrice;
                }
            }
            return maxProfit;
        }
    }

    // 8
    // 122. Best Time to Buy and Sell Stock II
    // Input: prices = [7,1,5,3,6,4]
    // Output: 7
    class Solution122 {
        // keep track of the profit by adding the difference between the current price
        // and the previous price if the current price is greater than the previous
        // price
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
    }

    // 9
    // 55. Jump Game
    // Input: nums = [2,3,1,1,4]
    // Output: true
    class Solution55 {
        // keep track of the maximum index that can be reached
        public boolean canJump(int[] nums) {
            int maxReach = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > maxReach) {
                    return false;
                }
                maxReach = Math.max(maxReach, i + nums[i]);
            }
            return true;
        }
    }

    // 10
    // 45. Jump Game II
    // Input: nums = [2,3,1,1,4]
    // Output: 2
    class Solution45 {
        // keep track of the maximum index that can be reached and the number of jumps
        public int jump(int[] nums) {
            int jumps = 0;
            int currentEnd = 0;
            int maxReach = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                maxReach = Math.max(maxReach, i + nums[i]);
                if (i == currentEnd) {
                    jumps++;
                    currentEnd = maxReach;
                }
            }
            return jumps;
        }
    }

    // 11
    // 274. H-Index
    // Input: citations = [3,0,6,1,5]
    // Output: 3
    class Solution274 {
        // sort the citations array and find the maximum h-index
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int n = citations.length;
            for (int i = 0; i < n; i++) {
                if (citations[i] >= n - i) {
                    return n - i;
                }
            }
            return 0;
        }
    }

    // 12
    // 380. Insert Delete GetRandom O(1)
    class RandomizedSet {
        List<Integer> arr;// A simple list allows random access but makes removals costly.
        Map<Integer, Integer> numToIndex;// A hash map allows fast lookups but doesn’t support random access.
        Random rand;

        public RandomizedSet() {
            this.arr = new ArrayList<>();
            this.numToIndex = new HashMap<>();
            this.rand = new Random();
        }

        public boolean insert(int val) {
            if (numToIndex.containsKey(val))
                return false;
            arr.add(val);
            numToIndex.put(val, arr.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            Integer index = numToIndex.get(val);
            if (index == null)
                return false;
            int size = arr.size();

            if (index == size - 1) {
                arr.remove(size - 1);
            } else {
                int last = arr.get(size - 1);
                arr.remove(size - 1);
                arr.set(index, last);
                numToIndex.put(last, index);
            }
            numToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            return arr.get(rand.nextInt(arr.size()));
        }

    }

    // 13
    // 238. Product of Array Except Self
    // Input: nums = [1,2,3,4]
    // Output: [24,12,8,6]
    class Solution238 {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int pre[] = new int[n];
            int suff[] = new int[n];
            pre[0] = 1;
            suff[n - 1] = 1;

            for (int i = 1; i < n; i++) {
                pre[i] = pre[i - 1] * nums[i - 1];
            }
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

    // 14
    // 134. Gas Station
    // Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
    // Output: 3
    class Solution134 {
        // keep track of the total surplus and the current surplus, if the current
        // surplus is negative, reset the start index to the next station
        // if the total surplus is negative, return -1, otherwise return the start index
        // Time complexity: O(n), Space complexity: O(1)
        // https://leetcode.com/problems/gas-station/solution
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int total_surplus = 0;
            int surplus = 0;
            int start = 0;

            for (int i = 0; i < n; i++) {
                total_surplus += gas[i] - cost[i];
                surplus += gas[i] - cost[i];
                if (surplus < 0) {
                    surplus = 0;
                    start = i + 1;
                }
            }
            return (total_surplus < 0) ? -1 : start;
        }

        // 135. Candy
        class Solution135 {
            public int candy(int[] ratings) {
                int n = ratings.length;
                int cnt = 0;
                int[] candies = new int[n];
                Arrays.fill(candies, 1);
                for (int i = 1; i < n; i++)
                    if (ratings[i] > ratings[i - 1])
                        candies[i] = candies[i - 1] + 1;

                for (int i = n - 1; i > 0; i--) {
                    if (ratings[i - 1] > ratings[i])
                        candies[i - 1] = Math.max(candies[i] + 1, candies[i - 1]);
                    cnt += candies[i - 1];
                }
                return cnt + candies[n - 1];
            }
        }

        // 42. Trapping Rain Water
        class Solution42 {
            public int trap(int[] height) {
                int left = 0, right = height.length - 1;
                int res = 0;
                int maxleft = 0, maxright = 0;
                while (left < right) { // height[left] <= height[right]
                                       // 这个比较巧妙地保证了：只有在一侧低于另一侧时，才尝试计算该侧的水量，并且此时另一侧存在一个不低于该侧当前高度的柱子，从而避免了在单调序列中错误加水。
                    if (height[left] <= height[right]) {
                        if (height[left] >= maxleft) {
                            maxleft = height[left];
                        } else {
                            res = res + maxleft - height[left];
                        }
                        left++;
                    } else {
                        if (height[right] >= maxright) {
                            maxright = height[right];
                        } else {
                            res = res + maxright - height[right];
                        }
                        right--;
                    }
                }
                return res;
            }
            // 用一次比较 height[left] <= height[right]，同时完成了三件事：
            // 决定移动哪一侧指针（移动较低的那边，保证另一侧有“托底”）。
            // 保证当前计算水量的那一侧，其另一侧一定存在不低于当前高度的屏障（即使尚未扫描中间区域）。
            // 避免了对每个位置预先计算左右最大值的 O(n) 额外空间，做到了 O(1) 空间、O(n) 时间。
        }

        // 13. Roman to Integer
        class Solution13 {
            public int romanToInt(String s) {
                Map<Character, Integer> map = new HashMap<>();
                map.put('I', 1);
                map.put('V', 5);
                map.put('X', 10);
                map.put('L', 50);
                map.put('C', 100);
                map.put('D', 500);
                map.put('M', 1000);

                int sum = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                        sum += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
                    } else {
                        sum += map.get(s.charAt(i));
                    }
                }
                return sum;
            }
        }

        // 12. Integer to Roman
        class Solution12 {
            public String intToRoman(int num) {
                String[] thousands = { "", "M", "MM", "MMM" };
                String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
                String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
                String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

                return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10]
                        + ones[num % 10];
            }
        }

        // 58. Length of Last Word
        class Solution58 {
            public int lengthOfLastWord(String s) {
                String[] words = s.trim().split(" ");
                return words[words.length - 1].length();
            }
        }

        class Solution58_2 {
            public int lengthOfLastWord(String s) {
                boolean isL = false;
                int from = s.length() - 1;
                for (int i = s.length() - 1; i >= 0; i--) {
                    if (!isL && s.charAt(i) != ' ') {
                        isL = true;
                        from = i;
                    }
                    if (isL && s.charAt(i) == ' ') {
                        return from - i;
                    }
                }
                return s.substring(0, from + 1).length();
            }
        }

        // 14. Longest Common Prefix
        class Solution14 {
            public String longestCommonPrefix(String[] strs) {
                if (strs == null || strs.length == 0)
                    return "";
                String prefix = strs[0];
                for (int i = 1; i < strs.length; i++) {
                    while (strs[i].indexOf(prefix) != 0) {
                        prefix = prefix.substring(0, prefix.length() - 1);
                        if (prefix.isEmpty())
                            return "";
                    }
                }
                return prefix;
            }
        }

        // 151. Reverse Words in a String
        class Solution151 {
            public String reverseWords(String s) {
                String[] words = s.trim().split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (int i = words.length - 1; i >= 0; i--) {
                    sb.append(words[i]);
                    if (i != 0) {
                        sb.append(" ");
                    }
                }
                return sb.toString();
            }
        }

        // 6. Zigzag Conversion
        // Input: s = "PAYPALISHIRING", numRows = 3
        // Output: "PAHNAPLSIIGYIR"
        class Solution6 {
            public String convert(String s, int numRows) {
                if (numRows == 1)
                    return s;
                StringBuilder[] sb = new StringBuilder[numRows];
                for (int i = 0; i < numRows; i++) {
                    sb[i] = new StringBuilder();
                }
                int row = 0;
                boolean down = true;
                for (char c : s.toCharArray()) {
                    sb[row].append(c);
                    if (down) {
                        row++;
                        if (row == numRows) {
                            row = numRows - 2;
                            down = false;
                        }
                    } else {
                        row--;
                        if (row == -1) {
                            row = 1;
                            down = true;
                        }
                    }
                }
                StringBuilder result = new StringBuilder();
                for (StringBuilder b : sb) {
                    result.append(b);
                }
                return result.toString();
            }
        }

        // 28. Find the Index of the First Occurrence in a String
        class Solution28 {
            public int strStr(String haystack, String needle) {
                if (needle.isEmpty())
                    return 0;
                int m = haystack.length();
                int n = needle.length();
                for (int i = 0; i <= m - n; i++) {
                    if (haystack.substring(i, i + n).equals(needle)) {
                        return i;
                    }
                }
                return -1;
            }
        }

        class Solution28_2 {
            public int strStr(String haystack, String needle) {
                if (needle.isEmpty())
                    return 0;
                int m = haystack.length();
                int n = needle.length();
                for (int i = 0; i <= m - n; i++) {
                    int j = 0;
                    while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                        j++;
                    }
                    if (j == n) {
                        return i;
                    }
                }
                return -1;
            }
        }

        // * 68. Text Justification
        // Input: words = ["This", "is", "an", "example", "of", "text",
        // "justification."], maxWidth = 16
        // Output: ["This is an","example of text","justification. "]
        class Solution68 {
            public static List<String> fullJustify(String[] words, int maxWidth) {
                int left = 0; // We start with left being the first word.
                List<String> result = new ArrayList<>(); // return result
                while (left < words.length) {
                    // findRight: Then we greedily try to go as far right as possible until we fill
                    // our current line. Then we justify one line at a time.
                    int right = findRight(left, words, maxWidth);
                    // justify: In all cases we pad the right side with spaces until we reach max
                    // width for the line;
                    result.add(justify(left, right, words, maxWidth));
                    left = right + 1;
                }

                return result;
            }

            private static int findRight(int left, String[] words, int maxWidth) {
                int right = left;
                int sum = words[right].length();
                right++;
                while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
                    sum += 1 + words[right].length();
                    right++;
                }

                return right - 1;
            }

            /**
             * If it's one word then it is easy, the result is just that word.
             * If it's the last line then the result is all words separated by a single
             * space.
             * Otherwise we calculate the size of each space evenly and if there is a
             * remainder we distribute an extra space until it is gone.
             * 
             */
            private static String justify(int left, int right, String[] words, int maxWidth) {
                if (right - left == 0)
                    // 如果 right == left only one word
                    return padResult(words[left], maxWidth);

                boolean isLastLine = right == words.length - 1;
                int numSpaces = right - left;
                int totalSpace = maxWidth - wordsLength(left, right, words);

                String space = isLastLine ? " " : blank(totalSpace / numSpaces);
                int remainder = isLastLine ? 0 : totalSpace % numSpaces;

                StringBuilder result = new StringBuilder();
                for (int i = left; i <= right; i++)
                    result.append(words[i])
                            .append(space)
                            .append(remainder-- > 0 ? " " : "");

                return padResult(result.toString().trim(), maxWidth);
            }

            private static int wordsLength(int left, int right, String[] words) {
                int wordsLength = 0;
                for (int i = left; i <= right; i++)
                    wordsLength += words[i].length();
                return wordsLength;
            }

            private static String padResult(String result, int maxWidth) {
                return result + blank(maxWidth - result.length());
            }

            private static String blank(int length) {
                return new String(new char[length]).replace('\0', ' ');
            }

        }

        // 125. Valid Palindrome
        class Solution125 {
            public boolean isPalindrome(String s) {
                if (s == null || s.length() == 0)
                    return true;
                int left = 0, right = s.length() - 1;
                while (left < right) {
                    while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                        left++;
                    }
                    while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                        right--;
                    }
                    if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                        return false;
                    }
                    left++;
                    right--;
                }
                return true;
            }
        }

    }

    // 15
    // 392. Is Subsequence
    class Solution392 {
        public boolean isSubsequence(String s, String t) {
            int i = 0, j = 0;
            for (; i < s.length() && j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
            }
            return i == s.length();
        }
    }

    // ----------------------------第二组------------------------------------------ //
    // 1
    // 167. Two Sum II - Input Array Is Sorted
    class Solution167 {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    return new int[] { left + 1, right + 1 };
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            return new int[] { -1, -1 };
        }
    }

    // 2
    // % 11. Container With Most Water
    class Solution11 {
        public int maxArea(int[] height) {
            int left = 0, right = height.length - 1;
            int maxArea = 0;
            while (left < right) {
                int area = Math.min(height[left], height[right]) * (right - left);
                maxArea = Math.max(maxArea, area);
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return maxArea;
        }
    }

    // 3
    // 15. 3Sum
    class Solution15 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1])
                    continue;
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;
                        left++;
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }
    }

    // 4
    // 209. Minimum Size Subarray Sum
    class Solution209 {
        public int minSubArrayLen(int target, int[] nums) {
            int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
            for (int right = 0; right < nums.length; right++) {
                sum += nums[right];
                while (sum >= target) {
                    minLen = Math.min(minLen, right - left + 1);
                    sum -= nums[left++];
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
    }

    // 5
    // 3. Longest Substring Without Repeating Characters
    class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            int left = 0, right = 0, maxLen = 0;
            Map<Character, Integer> map = new HashMap<>();
            while (right < s.length()) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    left = Math.max(left, map.get(c) + 1);
                }
                map.put(c, right);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }
            return maxLen;
        }
    }

    // 6
    // 30. Substring with Concatenation of All Words
    class Solution30 {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || words == null || words.length == 0)
                return res;

            int wordLen = words[0].length();
            int wordCount = words.length;
            int totalLen = wordLen * wordCount;

            Map<String, Integer> wordMap = new HashMap<>();
            for (String word : words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }

            for (int i = 0; i <= s.length() - totalLen; i++) {
                String subStr = s.substring(i, i + totalLen);
                if (isValid(subStr, wordLen, wordCount, new HashMap<>(wordMap))) {
                    res.add(i);
                }
            }
            return res;
        }

        private boolean isValid(String subStr, int wordLen, int wordCount, Map<String, Integer> wordMap) {
            for (int i = 0; i < subStr.length(); i += wordLen) {
                String word = subStr.substring(i, i + wordLen);
                if (!wordMap.containsKey(word)) {
                    return false;
                }
                wordMap.put(word, wordMap.get(word) - 1);
                if (wordMap.get(word) < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 7
    // 36. Valid Sudoku
    class Solution36 {
        public boolean isValidSudoku(char[][] board) {
            Set<String> seen = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char number = board[i][j];
                    if (number != '.') {
                        if (!seen.add(number + " in row " + i) ||
                                !seen.add(number + " in column " + j) ||
                                !seen.add(number + " in block " + i / 3 + "-" + j / 3)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    // 8
    // 54. Spiral Matrix
    // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    // Output: [1,2,3,6,9,8,7,4,5]
    class Solution54 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            if (matrix == null || matrix.length == 0)
                return res;
            int top = 0, bottom = matrix.length - 1;
            int left = 0, right = matrix[0].length - 1;
            while (top <= bottom && left <= right) {
                for (int i = left; i <= right; i++) {
                    res.add(matrix[top][i]);
                }
                top++;
                for (int i = top; i <= bottom; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
                if (top <= bottom) {
                    for (int i = right; i >= left; i--) {
                        res.add(matrix[bottom][i]);
                    }
                    bottom--;
                }
                if (left <= right) {
                    for (int i = bottom; i >= top; i--) {
                        res.add(matrix[i][left]);
                    }
                    left++;
                }
            }
            return res;
        }
    }

    // 9
    // 48. Rotate Image
    class Solution {
        public static void rotate(int[][] matrix) {
            // for (int i = 0; i < matrix.length; i++) {
            // for (int j = 0; j < matrix[0].length; j++) {
            // System.out.print(" " + matrix[i][j] + " ");
            // }
            // System.out.println();
            // }
            // ------------change 180 ---------------
            int ii = 0, jj = matrix.length - 1;
            while (ii < jj) {
                for (int k = 0; k < matrix[0].length; k++) {
                    int temp = matrix[ii][k];
                    matrix[ii][k] = matrix[jj][k];
                    matrix[jj][k] = temp;
                }
                ii++;
                jj--;
            }

            // for (int i = 0; i < matrix.length; i++) {
            // for (int j = 0; j < matrix[0].length; j++) {
            // System.out.print(" " + matrix[i][j] + " ");
            // }
            // System.out.println();
            // }

            // -------------------90---------------------
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i >= j)
                        continue;
                    int value = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = value;
                }
            }

            // for (int i = 0; i < matrix.length; i++) {
            // for (int j = 0; j < matrix[0].length; j++) {
            // System.out.print(" " + matrix[i][j] + " ");
            // }
            // System.out.println();
            // }

        }
    }

    // 10
    // 73. Set Matrix Zeroes
    // Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
    // Output: [[1,0,1],[0,0,0],[1,0,1]]
    class Solution73 {
        // 主要的思想既是，把第一行和第一列作为标记，来标记哪些行和列需要被置零。然后再根据这些标记来更新矩阵。
        // 在标记的时候，把第一行第一列 当做一部分，其余的当做一部分，更新的时候各自更新
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            boolean firstRowZero = false;
            boolean firstColZero = false;

            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == 0) {
                    firstColZero = true;
                    break;
                }
            }

            for (int j = 0; j < n; j++) {
                if (matrix[0][j] == 0) {
                    firstRowZero = true;
                    break;
                }
            }

            // Use the first row and first column as markers.
            // If matrix[i][j] is zero, we mark the corresponding row and column.
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            if (firstRowZero) {
                for (int j = 0; j < n; j++) {
                    matrix[0][j] = 0;
                }
            }
            if (firstColZero) {
                for (int i = 0; i < m; i++) {
                    matrix[i][0] = 0;
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    // 11
    // 289. Game of Life
    // Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
    // Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
    // 这道题主要就是模拟，遍历每一个格子，然后统计其周围八个格子的活细胞个数，来看这个格子的状态是否改变。
    // 但难点在于：如果这个格子的状态改变，不能直接改变。这样会影响后面格子的统计。
    // 即题目中说的：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
    // 因此我们需要使用特殊值去标记发生改变的格子，
    // 从而根据特殊值可以知道这个格子原状态是什么，要更新的状态是什么。
    // 我们使用：2表示活细胞变成死细胞，3表示死细胞变成活细胞。
    // 【这样的好处是最终是死细胞的都是偶数，活细胞的都是奇数，模2即结果；】
    class Solution289 {
        public void gameOfLife(int[][] board) {
            int m = board.length; // 行数
            int n = board[0].length; // 列数
            int count = 0; // 统计每个格子周围八个位置的活细胞数
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    count = 0; // 每个格子计数重置为0
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            // 枚举周围八个位置，其中去掉本身（x = y = 0）和越界的情况
                            if ((x == 0 && y == 0) || i + x < 0 || i + x >= m || j + y < 0 || j + y >= n)
                                continue;
                            // 如果周围格子是活细胞（1）或者是活细胞变死细胞（2）的，都算一个活细胞
                            if (board[i + x][j + y] == 1 || board[i + x][j + y] == 2)
                                count++;
                        }
                    }
                    if (board[i][j] == 1 && (count < 2 || count > 3))
                        board[i][j] = 2; // 格子本身是活细胞，周围满足变成死细胞的条件，标记为2
                    if (board[i][j] == 0 && count == 3)
                        board[i][j] = 3; // 格子本身是死细胞，周围满足复活条件，标记为3
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 死细胞为0，活细胞变成死细胞为2，都为偶数，模2为0，刚好是死细胞
                    // 活细胞为1，死细胞变成活细胞为3，都为奇数，模2为1，刚好是活细胞
                    board[i][j] %= 2;
                }
            }
        }
    }

    // 12
    // 383. Ransom Note
    // Input: ransomNote = "a", magazine = "b"
    // Output: false
    class Solution383 {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] count = new int[26];
            for (char c : magazine.toCharArray()) {
                count[c - 'a']++;
            }
            for (char c : ransomNote.toCharArray()) {
                if (--count[c - 'a'] < 0) {
                    return false;
                }
            }
            return true;
        }

    }

    // 13
    // 205. Isomorphic Strings
    class Solution205 {
        public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length())
                return false;
            Map<Character, Character> map = new HashMap<>();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (map.containsKey(c1)) {
                    if (map.get(c1) != c2)
                        return false;
                } else {
                    if (set.contains(c2))// 这个是为了预防不同的字符映射到同一个字符的情况，类似："badc"，"baba"
                        return false;
                    map.put(c1, c2);
                    set.add(c2);
                }
            }
            return true;
        }
    }

    // 14
    // 290. Word Pattern
    class Solution290 {
        public boolean wordPattern(String pattern, String s) {
            String[] words = s.split(" ");
            if (pattern.length() != words.length)
                return false;
            Map<Character, String> map = new HashMap<>();
            Set<String> set = new HashSet<>();
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                String word = words[i];
                if (map.containsKey(c)) {
                    if (!map.get(c).equals(word))
                        return false;
                } else {
                    if (set.contains(word))
                        return false;
                    map.put(c, word);
                    set.add(word);
                }
            }
            return true;
        }
    }

    // 15
    // 242. Valid Anagram
    class Solution242 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length())
                return false;
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            for (char c : t.toCharArray()) {
                count[c - 'a']--;
                if (count[c - 'a'] < 0)
                    return false;
            }
            return true;
        }
    }

    // ----------------------------第三组------------------------------------------ //
    // 1
    // 1. Two Sum

    // 2
    // 49. Group Anagrams
    class Solution49 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }

    // 3
    // 202. Happy Number
    class Solution202 {
        public boolean isHappy(int n) {
            Set<Integer> seen = new HashSet<>();
            while (n != 1 && !seen.contains(n)) {
                seen.add(n);
                n = getNext(n);
            }
            return n == 1;
        }

        private int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n /= 10;
                totalSum += d * d;
            }
            return totalSum;
        }
    }

    // 4
    // 219. Contains Duplicate II
    class Solution219 {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    return true;
                }
                set.add(nums[i]);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }
    }

    // 5
    // 128. Longest Consecutive Sequence
    class Solution128 {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            int longestStreak = 0;
            for (int num : set) {
                if (!set.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;
                    while (set.contains(currentNum + 1)) {
                        currentNum++;
                        currentStreak++;
                    }
                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }
            return longestStreak;
        }

        public int longestConsecutive2(int[] nums) {
            Arrays.sort(nums);
            int curr = 0;
            int max = 0;
            int i = 0;
            if (nums.length <= 0) {
                return 0;
            }
            while (i < nums.length - 1) {
                int x = nums[i] - nums[i + 1];
                if (x == -1) {
                    curr++;
                    i++;
                } else if (x == 0) {
                    i++;
                } else {
                    max = Math.max(max, curr);
                    curr = 0;
                    i++;
                }
            }
            max = Math.max(max, curr);
            return max + 1;
        }
    }

    // 6
    // 228. Summary Ranges
    class Solution228 {
        public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<>();
            if (nums == null || nums.length == 0)
                return res;

            int start = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1] + 1) {
                    if (start == nums[i - 1]) {
                        res.add(String.valueOf(start));
                    } else {
                        res.add(start + "->" + nums[i - 1]);
                    }
                    start = nums[i];
                }
            }

            if (start == nums[nums.length - 1]) {
                res.add(String.valueOf(start));
            } else {
                res.add(start + "->" + nums[nums.length - 1]);
            }
            return res;
        }
    }

    // 7
    // 56. Merge Intervals
    class Solution56 {
        public int[][] merge(int[][] intervals) {
            if (intervals.length <= 1)
                return intervals;

            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

            List<int[]> result = new ArrayList<>();
            int[] newInterval = intervals[0];
            result.add(newInterval);

            for (int[] interval : intervals) {
                if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                else { // Disjoint intervals, add the new interval to the list
                    newInterval = interval;
                    result.add(newInterval);
                }
            }

            return result.toArray(new int[result.size()][]);
        }
    }

    // 8
    // 57. Insert Interval
    class Solution57 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();
            int i = 0;
            // Add all intervals ending before newInterval starts
            while (i < intervals.length && intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
                i++;
            }
            // Merge all overlapping intervals to one considering newInterval
            while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }
            result.add(newInterval); // Add the union of intervals we got

            // Add all the rest
            while (i < intervals.length) {
                result.add(intervals[i]);
                i++;
            }

            return result.toArray(new int[result.size()][]);
        }
    }

    // 9
    // 452. Minimum Number of Arrows to Burst Balloons
    class Solution452 {
        public int findMinArrowShots(int[][] points) {
            if (points.length == 0)
                return 0;
            Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
            System.out.println("points: " + java.util.Arrays.deepToString(points));
            int arrows = 1;
            int end = points[0][1];
            for (int i = 1; i < points.length; i++) {
                if (points[i][0] > end) {
                    arrows++;
                    end = points[i][1];
                }
            }

            return arrows;
        }
    }

    // 10
    // 20. Valid Parentheses

    // 11
    // 71. Simplify Path
    class Solution71 {
        public static String simplifyPath(String path) {
            if (path == null || path.length() == 0 || path.equals("/")) {
                return path;
            }
            // divided into array by "/"
            String[] p = path.split("/");
            ArrayList<String> pp = new ArrayList<>();
            for (int i = 0; i < p.length; i++) {
                if (p[i] == "" || p[i].equals(".")) {
                    continue;
                } else if (p[i].equals("..")) {
                    if (pp.size() > 0) {
                        pp.remove(pp.size() - 1);
                    } else {
                        continue;
                    }

                } else {
                    pp.add(p[i]);
                }
            }
            if (pp.isEmpty()) {
                return "/";
            }
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < pp.size(); i++) {
                s.append("/").append(pp.get(i));
            }
            return s.toString();
        }
    }

    // 12
    // 155. Min Stack
    class Solution155 {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public Solution155() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // 13
    // 2. Add Two Numbers

    // 14
    // 141. Linked List Cycle
    class Solution141 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null)
                return false;
            ListNode slow = head;
            ListNode fast = head.next;
            while (slow != fast) {
                if (fast == null || fast.next == null)
                    return false;
                slow = slow.next;
                fast = fast.next.next;
            }
            return true;
        }
    }

    // 15
    // 150. Evaluate Reverse Polish Notation
    // Input: tokens = ["2","1","+","3","*"]
    // Output: 9
    class Solution150 {
        // use stack to evaluate the expression in reverse polish notation
        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();
            for (String token : tokens) {
                if (isOperator(token)) {
                    int b = stack.pop();
                    int a = stack.pop();
                    int result = applyOperator(a, b, token);
                    stack.push(result);
                } else {
                    stack.push(Integer.parseInt(token));
                }
            }
            return stack.pop();
        }

        private boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
        }

        private int applyOperator(int a, int b, String operator) {
            switch (operator) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                case "/":
                    return a / b;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }

    // ----------------------------第四组------------------------------------------ //
    // 1
    // 224. Basic Calculator
    static class Solution224 {
        public static void main(String[] args) {
            System.out.println("Basic Calculator Test");
            String s = "10+1+43-21";
            System.out.println("Result: " + calculate(s));
            s = "(1+(4+5+2)-3)+(6+8)";
            System.out.println("Result: " + calculate(s));
        }

        // s consists of digits, '+', '-', '(', ')', and ' '.
        // s represents a valid expression.
        // '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
        // '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
        // There will be no two consecutive operators in the input.
        // Every number and running calculation will fit in a signed 32-bit integer.
        static public int calculate(String s) {
            Stack<Integer> stack = new Stack<>();
            int result = 0;
            int number = 0;
            int sign = 1; // 1 means positive, -1 means negative

            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    number = number * 10 + (c - '0');
                } else if (c == '+') {
                    result += sign * number;
                    number = 0;
                    sign = 1;
                } else if (c == '-') {
                    result += sign * number;
                    number = 0;
                    sign = -1;
                } else if (c == '(') {
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                } else if (c == ')') {
                    result += sign * number;
                    number = 0;
                    // this is the sign before the parenthesis
                    result *= stack.pop(); // pop 的是 sign，为了计算值的正负而设定的
                    result += stack.pop(); // this is the result calculated before the parenthesis
                }
            }
            result += sign * number; // add the last number
            return result;
        }
    }

    // 2
    // Add two Number

    // 3
    // Merge Two Sorted Lists

    // 4
    // 138. Copy List with Random Pointer
    static class Solution138 {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            // Step 1: Create a new node for each original node and insert it right after
            // the original node.
            Node current = head;
            while (current != null) {
                Node newNode = new Node(current.val);
                newNode.next = current.next;
                current.next = newNode;
                current = newNode.next;
            }

            // Step 2: Assign random pointers for the new nodes.
            current = head;
            while (current != null) {
                if (current.random != null) {
                    current.next.random = current.random.next;
                }
                current = current.next.next; // Move to the next original node
            }

            // Step 3: Separate the original list and the copied list.
            Node pseudoHead = new Node(0);
            Node copyCurrent = pseudoHead;
            current = head;

            while (current != null) {
                copyCurrent.next = current.next; // Link the copied node
                copyCurrent = copyCurrent.next; // Move to the next copied node

                current.next = copyCurrent.next; // Restore the original list
                current = current.next; // Move to the next original node
            }

            return pseudoHead.next; // Return the head of the copied list
        }

        public Node copyRandomList_2(Node head) {
            if (head == null)
                return null;

            HashMap<Node, Node> oldToNew = new HashMap<>();

            Node curr = head;
            while (curr != null) {
                oldToNew.put(curr, new Node(curr.val));
                curr = curr.next;
            }

            curr = head;
            while (curr != null) {
                oldToNew.get(curr).next = oldToNew.get(curr.next);
                oldToNew.get(curr).random = oldToNew.get(curr.random);
                curr = curr.next;
            }

            return oldToNew.get(head);
        }
    }

    // 5
    // 92 Reverse Linked List II
    // input head = [1,2,3,4,5], left = 2, right = 4
    // output [1,4,3,2,5]
    static class Solution92 {
        public static ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || left == right) {
                return head;
            }
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode preLeft = dummy;
            ListNode rightNode = dummy;
            for (int i = 0; i < left - 1; i++) {
                preLeft = preLeft.next;
                rightNode = rightNode.next;
            }
            for (int i = 0; i < (right - left + 1); i++) {
                rightNode = rightNode.next;
            }
            ListNode leftNode = preLeft.next;
            ListNode postRight = rightNode.next;

            // 1,2,3,4,5 left=2 right=4 ==> preLeft=1 leftNode=2 rightNode=4 postRight=5
            while (preLeft.next != rightNode) {
                preLeft.next = leftNode.next;// 1->3->4->5,2->3->4->5
                leftNode.next = postRight; // 1->3->4->5 2->5
                rightNode.next = leftNode;// 1->3->4->2->5
                leftNode = preLeft.next;//
                postRight = rightNode.next;//
            }
            return dummy.next;
        }
    }

    // 6
    // 25. Reverse Nodes in k-Group
    static class Solution25 {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k <= 1) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy;

            while (true) {
                ListNode end = pre;
                for (int i = 0; i < k && end != null; i++) {
                    end = end.next;
                }
                if (end == null) {
                    break;
                }

                ListNode start = pre.next;
                ListNode nextGroup = end.next;

                // Reverse the k nodes
                end.next = null;
                pre.next = reverseList(start);
                start.next = nextGroup;

                pre = start;
            }

            return dummy.next;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode nextTemp = head.next;
                head.next = prev;
                prev = head;
                head = nextTemp;
            }
            return prev;
        }

        public ListNode reverseKGroup2(ListNode head, int k) {
            // 1. test weather we have more then k node left, if less then k node left we
            // just return head
            ListNode node = head;
            int count = 0;
            while (count < k) {
                if (node == null)
                    return head;
                node = node.next;
                count++;
            }
            // 2.reverse k node at current level
            ListNode pre = reverseKGroup2(node, k); // pre node point to the the answer of sub-problem
            while (count > 0) {
                ListNode next = head.next;
                head.next = pre;
                pre = head;
                head = next;
                count = count - 1;
            }
            return pre;
        }
    }

    // 7
    // 19. Remove Nth Node From End of List
    static class Solution19 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode first = dummy;
            ListNode second = dummy;

            // Move first n+1 steps ahead
            for (int i = 0; i <= n; i++) {
                first = first.next;
            }

            // Move first to the end, maintaining the gap
            while (first != null) {
                first = first.next;
                second = second.next;
            }

            // Remove the nth node from end
            second.next = second.next.next;

            return dummy.next;
        }
    }

    // 8
    // 82. Remove Duplicates from Sorted List II
    static class Solution82 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode prev = dummy; // The last node before the sublist of duplicates
            ListNode current = head;

            while (current != null) {
                // Move current until the end of duplicates sublist
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }

                // If prev's next is not current, we found duplicates
                if (prev.next != current) {
                    prev.next = current.next; // Skip all duplicates
                } else {
                    prev = prev.next; // No duplicates, move prev
                }

                current = current.next; // Move current forward
            }

            return dummy.next;
        }
    }

    // 9
    // 61. Rotate List
    static class Solution61 {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            // Compute the length of the list and get the tail node
            ListNode oldTail = head;
            int length = 1;
            while (oldTail.next != null) {
                oldTail = oldTail.next;
                length++;
            }

            // Make the list circular
            oldTail.next = head;

            // Find the new tail: (length - k % length - 1)th node
            // and the new head: (length - k % length)th node
            int newTailIndex = length - k % length - 1;
            ListNode newTail = head;
            for (int i = 0; i < newTailIndex; i++) {
                newTail = newTail.next;
            }
            ListNode newHead = newTail.next;

            // Break the circle
            newTail.next = null;

            return newHead;
        }
    }

    // 10
    // 86. Partition List
    static class Solution86 {
        public ListNode partition(ListNode head, int x) {
            ListNode beforeHead = new ListNode(0);
            ListNode before = beforeHead;
            ListNode afterHead = new ListNode(0);
            ListNode after = afterHead;

            while (head != null) {
                if (head.val < x) {
                    before.next = head;
                    before = before.next;
                } else {
                    after.next = head;
                    after = after.next;
                }
                head = head.next;
            }

            after.next = null; // Important! Avoid potential cycle in linked list.
            before.next = afterHead.next; // Connect the two partitions

            return beforeHead.next;
        }

        public static ListNode partition2(ListNode head, int x) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dum = new ListNode();
            dum.next = head;
            ListNode pre = dum;
            ListNode cp = dum;
            ListNode cur = head;
            while (cur != null && cur.val < x) {
                cur = cur.next;
                pre = pre.next;
                cp = cp.next;
            }

            // 1,4,3,2,5,2
            while (cur != null) {
                if (cur.val < x) {
                    ListNode tmp = pre.next;
                    ListNode tmp2 = cur;
                    cur = cur.next;
                    cp.next = cur;
                    pre.next = tmp2;
                    tmp2.next = tmp;
                    pre = pre.next;
                } else {
                    cur = cur.next;
                    cp = cp.next;
                }
            }
            return dum.next;
        }
    }

    // 11
    // 146. LRU Cache
    static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> cache;
        // Each node in the list stores both the key and value.
        // The key is necessary so that when we evict from the list, we know which entry
        // to remove from the HashMap.
        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            // head and tail are dummy nodes to avoid null checks
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // If the key doesn't exist in the HashMap, return -1.
        // If it exists, the item is now "recently used." Remove it from its current
        // position in the list and Insert it at the front (right after the dummy head).
        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            remove(node);
            insertToHead(node);
            return node.value;
        }

        // If key exists: Update the value, remove the node, and move it to the front.
        // If key is new: * Check if the cache is at capacity. If so, delete the node
        // right before the tail (the Least Recently Used item) and remove it from the
        // HashMap.
        // Create a new node and insert it at the front.
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                remove(node);
                insertToHead(node);
            } else {
                if (cache.size() == capacity) {
                    Node lru = tail.prev;
                    remove(lru);
                    cache.remove(lru.key);
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                insertToHead(newNode);
            }
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // keep the head is always the head of the list, and the tail is always the tail
        // of the list.
        private void insertToHead(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        private static class Node {
            int key, value;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    // 12
    // 104. Maximum Depth of Binary Tree
    static class Solution104 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    // 13
    // 100. Same Tree
    static class Solution100 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    // 14
    // 226. Invert Binary Tree
    static class Solution226 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }
    }

    // 15
    // 101. Symmetric Tree
    static class Solution101 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isMirror(root.left, root.right);
        }

        private boolean isMirror(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return isMirror(left.left, right.right) && isMirror(left.right, right.left);
        }
    }

    // ----------------------------第五组------------------------------------------ //
    // 1
    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    static class Solution105 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null || preorder.length != inorder.length) {
                return null;
            }
            Map<Integer, Integer> inorderIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderIndexMap.put(inorder[i], i);
            }
            return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1,
                    inorderIndexMap);
        }

        private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
                int inEnd, Map<Integer, Integer> inorderIndexMap) {
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }
            int rootValue = preorder[preStart];
            TreeNode root = new TreeNode(rootValue);
            int rootIndexInInorder = inorderIndexMap.get(rootValue);
            int leftSubtreeSize = rootIndexInInorder - inStart;

            root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSubtreeSize, inorder, inStart,
                    rootIndexInInorder - 1, inorderIndexMap);
            root.right = buildTreeHelper(preorder, preStart + leftSubtreeSize + 1, preEnd, inorder,
                    rootIndexInInorder + 1, inEnd, inorderIndexMap);

            return root;
        }
    }

    // 2
    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    static class Solution106 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return builder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }

        private TreeNode builder(int[] inorder, int inS, int inE, int[] postorder, int postS, int postE) {
            // bad case,return error
            if (inS > inE || postS > postE) {
                return null;
            }

            // Find the root node from the last element of the postOrder traversal
            int rootval = postorder[postE];
            TreeNode root = new TreeNode(rootval);

            // Find the index of the root node in inorder traversal
            int rootindex = 0;
            for (int i = inS; i <= inE; i++) {
                if (inorder[i] == rootval) {
                    rootindex = i;
                    break;
                }
            }

            // Recursively build the left and right subTrees
            int leftSize = rootindex - inS;
            int rightSize = inE - rootindex;
            // in postOrder,【左节点，右节点，根节点】
            root.left = builder(inorder, inS, rootindex - 1, postorder, postS, postS + leftSize - 1);
            root.right = builder(inorder, rootindex + 1, inE, postorder, postE - rightSize, postE - 1);
            return root;
        }
    }

    // 3
    // 117. Populating Next Right Pointers in Each Node II
    static class Solution117 {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }

            Node current = root; // Start with the root node
            Node nextLevelHead = null; // Head of the next level
            Node prev = null; // The previous node on the next level

            while (current != null) {
                // Iterate through the current level
                while (current != null) {
                    if (current.left != null) {
                        if (prev != null) {
                            prev.next = current.left;
                        } else {
                            nextLevelHead = current.left;
                        }
                        prev = current.left;
                    }
                    if (current.right != null) {
                        if (prev != null) {
                            prev.next = current.right;
                        } else {
                            nextLevelHead = current.right;
                        }
                        prev = current.right;
                    }
                    current = current.next; // Move to the next node in the current level
                }
                // Move to the next level
                current = nextLevelHead;
                nextLevelHead = null;
                prev = null;
            }

            return root;
        }
    }

    // 4
    // 114. Flatten Binary Tree to Linked List
    static class Solution114 {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            flatten(root.left);
            flatten(root.right);

            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null;
            root.right = left;
            TreeNode current = root;
            while (current.right != null) {
                current = current.right;
            }
            current.right = right;
        }
    }

    // 5
    // 112. Path Sum
    static class Solution112 {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            if (root.left == null && root.right == null) {
                return targetSum == root.val;
            }
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
    }

    // 6
    // 129. Sum Root to Leaf Numbers
    static class Solution129 {
        public int sumNumbers(TreeNode root) {
            return sumNumbersHelper(root, 0);
        }

        private int sumNumbersHelper(TreeNode node, int currentSum) {
            if (node == null) {
                return 0;
            }
            currentSum = currentSum * 10 + node.val;
            if (node.left == null && node.right == null) {
                return currentSum; // Leaf node
            }
            return sumNumbersHelper(node.left, currentSum) + sumNumbersHelper(node.right, currentSum);
        }
    }

    // 7 *
    // 124. Binary Tree Maximum Path Sum
    static class Solution124 {
        // Global variable to keep track of the maximum path sum found so far
        private int maxSum;

        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE;
            maxGain(root);
            return maxSum;
        }

        private int maxGain(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // Recursively get the maximum gain from left and right subtrees
            int leftGain = Math.max(maxGain(node.left), 0); // Ignore paths with negative sums
            int rightGain = Math.max(maxGain(node.right), 0);

            // Price of the current path is the value of the current node plus the maximum
            // gains from left and right
            int priceNewPath = node.val + leftGain + rightGain;

            // Update the global maximum sum if the new path is better
            maxSum = Math.max(maxSum, priceNewPath);

            // Return the maximum gain if we continue the same path
            return node.val + Math.max(leftGain, rightGain);
        }
    }

    // 8
    // 173. Binary Search Tree Iterator
    static class Solution173 {
        private Stack<TreeNode> stack;

        public Solution173(TreeNode root) {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeft(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    // 9
    // 222. Count Complete Tree Nodes
    static class Solution222 {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = getDepth(root.left);
            int rightDepth = getDepth(root.right);

            if (leftDepth == rightDepth) {
                return (1 << leftDepth) + countNodes(root.right);
            } else {
                return (1 << rightDepth) + countNodes(root.left);
            }
        }

        private int getDepth(TreeNode node) {
            int depth = 0;
            while (node != null) {
                depth++;
                node = node.left;
            }
            return depth;
        }
    }

    // 10
    // 236. Lowest Common Ancestor of a Binary Tree
    static class Solution236 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null && right != null) {
                return root;
            }
            return left != null ? left : right;
        }
    }

    // 11
    // 199. Binary Tree Right Side View
    static class Solution199 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (i == size - 1) { // The last node in the current level
                        result.add(node.val);
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            return result;
        }
    }

}