package com.happy.alg;

public class LeetCode306 {
    /**
     An additive number is a string whose digits can form an additive sequence.

     A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

     Given a string containing only digits, return true if it is an additive number or false otherwise.

     Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.



     Example 1:

     Input: "112358"
     Output: true
     Explanation:
     The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
     1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     Example 2:

     Input: "199100199"
     Output: true
     Explanation:
     The additive sequence is: 1, 99, 100, 199.
     1 + 99 = 100, 99 + 100 = 199


     Constraints:

     1 <= num.length <= 35
     num consists only of digits.


     Follow up: How would you handle overflow for very large input integers?
     * */
    static void main() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

    public boolean isAdditiveNumber(String num) {
        return backtrack(num, 0, 0, 0, 0);
    }

    public boolean backtrack(String num, int idx, long sum, long prev, int length){
        if(idx == num.length()){
            return length >= 3;
        }

        long currLong = 0;
        for(int i = idx; i < num.length(); i++){
            //make sure it won't start with 0
            if(i > idx && num.charAt(idx) == '0') break;
            currLong = currLong * 10 + num.charAt(i) - '0';

            if(length >= 2){
                if(sum < currLong){
                    //currLong is greater than sum of previous 2 numbers
                    break;
                }else if(sum > currLong){
                    //currLong is smaller than sum of previous 2 numbers
                    continue;
                }
            }
            //currLong == sum of previous 2 numbers
            if(backtrack(num, i + 1, currLong + prev, currLong, length + 1) == true){
                return true;
            }
        }
        return false;
    }

    static class Solution {
        static void main() {
            System.out.println(new Solution().isAdditiveNumber("999999999999999999999999"));
        }
        public boolean isAdditiveNumber(String num) {
            int n = num.length();
            // if there are less than 3 characters, we cannot divide them to n1, n2, n3.
            if(n < 3){
                return false;
            }
            // iterate from 1 to n/2+1. Because if n is 10, then n1 can max be of 10/2 = 5 length.
            for(int i=1; i<n/2+1; i++){
                long n1 = Long.parseLong(num.substring(0, i));
                //judge not from the 0
                if(!String.valueOf(n1).equals(num.substring(0, i))){
                    break;
                }
                // j will run till n. Because, n2 might have more digits than n1.
                for(int j=i+1; j<n; j++){
                    if (j-i > n/2){
                        break;
                    }
                    long n2 = Long.parseLong(num.substring(i, j));
                    //judge not from the 0
                    if(!String.valueOf(n2).equals(num.substring(i, j))){
                        break;
                    }
                    // recursively match the preceding characters.
                    if(backtrack(n1, n2, num.substring(j))){
                        return true;
                    }
                }
            }
            return false;
        }

        boolean backtrack(long n1, long n2, String num){
            if(num.length() == 0){
                return true;
            }
            String n3 = String.valueOf(n1+n2);
            // n3 might have more length than num. So, get the minimum length.
            int idx = Math.min(num.length(), n3.length());

            // if the substring is equal to n3, then we can further proceed.
            if(num.substring(0, idx).equals(n3)){
                return backtrack(n2, Long.parseLong(n3), num.substring(idx));
            }
            return false;
        }
    }
}
