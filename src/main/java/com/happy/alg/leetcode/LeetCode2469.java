package com.happy.alg.leetcode;

import java.util.Arrays;

public class LeetCode2469 {
    /**
     2469. Convert the Temperature
     Easy
     You are given a non-negative floating point number rounded to two decimal places celsius, that denotes the temperature in Celsius.

     You should convert Celsius into Kelvin and Fahrenheit and return it as an array ans = [kelvin, fahrenheit].

     Return the array ans. Answers within 10-5 of the actual answer will be accepted.

     Note that:

     Kelvin = Celsius + 273.15
     Fahrenheit = Celsius * 1.80 + 32.00


     Example 1:

     Input: celsius = 36.50
     Output: [309.65000,97.70000]
     Explanation: Temperature at 36.50 Celsius converted in Kelvin is 36.50 + 273.15 = 309.65000
     and converted in Fahrenheit is 36.50 * 1.80 + 32.00 = 97.70000
     Example 2:

     Input: celsius = 122.11
     Output: [395.26000,251.79800]
     Explanation: Temperature at 122.11 Celsius converted in Kelvin is 122.11 + 273.15 = 395.26000
     and converted in Fahrenheit is 122.11 * 1.80 + 32.00 = 251.79800

     Constraints:

     0 <= celsius <= 1000

     **/

    public static void main(String[] args) {
        System.out.println("keep Happy");
        LeetCode2469 leetcode = new LeetCode2469();
        LeetCode2469.Solution solution = leetcode.new Solution();
        System.out.println(Arrays.toString(solution.convertTemperature(36.50)));
        System.out.println(Arrays.toString(solution.convertTemperature(122.11)));
    }

    class Solution {
        public double[] convertTemperature(double celsius) {
            return new double[]{celsius+273.15, celsius*1.8+32};
        }
    }
}
