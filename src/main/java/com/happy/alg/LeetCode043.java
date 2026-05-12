package com.happy.alg;

import java.util.ArrayList;

public class LeetCode043 {

    /**
     43. Multiply Strings

     Given two non-negative integers num1 and num2 represented as strings,
     return the product of num1 and num2, also represented as a string.

     Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.



     Example 1:

     Input: num1 = "2", num2 = "3"
     Output: "6"
     Example 2:

     Input: num1 = "123", num2 = "456"
     Output: "56088"


     Constraints:

     1 <= num1.length, num2.length <= 200
     num1 and num2 consist of digits only.
     Both num1 and num2 do not contain any leading zero, except the number 0 itself.

     * */


    //special method
    public static String multiply_NeverThinkOut(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }



    public static void main(String[] args) {
        System.out.println("keep Happy boy");
//        System.out.println(add("123213","544545"));
//        System.out.println(multiply(new char[]{'1','0','9'},new char[]{'4','5','9'}));
        System.out.println(multiply("123".toCharArray(),"456".toCharArray()));
    }

    public  String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        if(num1.equals("1") ) return num2;
        if(num2.equals("1") ) return num1;
        return multiply(num1.toCharArray(),num2.toCharArray());
    }

    private static String multiply(char[] n2, char[] n1) {
        String[] tmpAddString = new String[n2.length];
        for (int i = n2.length-1; i >=0 ; i--) {
            int n2value = n2[i]-'0';
            StringBuilder value = multiply(n2value,n1);
            int add = i;
            while(add < n2.length-1){
                value.append("0");
                add++;
            }
            tmpAddString[n2.length-1-i] = value.toString();
        }

        String result = tmpAddString[0];
        for (int i = 1; i < tmpAddString.length; i++) {
            result= add(result,tmpAddString[i]);
        }
        return result;
    }

    public static String add(String n1,String n2){
        if(n1.length() > n2.length())return add(n2,n1);
        int i = 0,carry=0;
        StringBuilder result = new StringBuilder();
        while(i <= n1.length()-1 || i <= n2.length()-1){
            int n1v = n1.length()-i > 0? n1.charAt(n1.length()-1-i)-'0':0;
            int n2v = n2.length()-i > 0? n2.charAt(n2.length()-1-i)-'0':0;
            int value = n1v+n2v+carry;
            int v = value%10;
            carry=value/10;
            result.insert(0,v);
            i++;
        }
        if(carry>0)result.insert(0,carry);
        return  result.toString();
    }

    private static StringBuilder multiply(int n2i, char[] n1) {
        StringBuilder tmpResult = new StringBuilder();
        int carry=0;
        for (int i = 0; i < n1.length; i++) {
            int n1i = n1[n1.length-1-i]-'0';
            int value = n2i*n1i+carry;
            int v = value%10;
            carry=value/10;
            tmpResult.insert(0,v);
        }
        if(carry > 0){
            tmpResult.insert(0,carry);
        }
        return tmpResult;
    }

    static class Solution {
        // Calculate the sum of all of the results from multiplyOneDigit.
        private StringBuilder sumResults(ArrayList<ArrayList<Integer>> results) {
            // Initialize answer as a number from results.
            ArrayList<Integer> answer = new ArrayList<>(
                    results.get(results.size() - 1)
            );
            ArrayList<Integer> newAnswer = new ArrayList<>();

            // Sum each digit from answer and result
            for (int j = 0; j < results.size() - 1; ++j) {
                ArrayList<Integer> result = new ArrayList<>(results.get(j));
                newAnswer = new ArrayList<>();

                int carry = 0;

                for (int i = 0; i < answer.size() || i < result.size(); ++i) {
                    // If answer is shorter than result or vice versa, use 0 as the current digit.
                    int digit1 = i < result.size() ? result.get(i) : 0;
                    int digit2 = i < answer.size() ? answer.get(i) : 0;
                    // Add current digits of both numbers.
                    int sum = digit1 + digit2 + carry;
                    // Set carry equal to the tens place digit of sum.
                    carry = sum / 10;
                    // Append the ones place digit of sum to answer.
                    newAnswer.add(sum % 10);
                }

                if (carry != 0) {
                    newAnswer.add(carry);
                }
                answer = newAnswer;
            }

            // Convert answer to a string.
            StringBuilder finalAnswer = new StringBuilder();
            for (int digit : answer) {
                finalAnswer.append(digit);
            }
            return finalAnswer;
        }

        // Multiply the current digit of secondNumber with firstNumber.
        ArrayList<Integer> multiplyOneDigit(
                StringBuilder firstNumber,
                char secondNumberDigit,
                int numZeros
        ) {
            // Insert zeros at the beginning based on the current digit's place.
            ArrayList<Integer> currentResult = new ArrayList<>();
            for (int i = 0; i < numZeros; ++i) {
                currentResult.add(0);
            }

            int carry = 0;

            // Multiply firstNumber with the current digit of secondNumber.
            for (int i = 0; i < firstNumber.length(); ++i) {
                char firstNumberDigit = firstNumber.charAt(i);
                int multiplication =
                        (secondNumberDigit - '0') * (firstNumberDigit - '0') + carry;
                // Set carry equal to the tens place digit of multiplication.
                carry = multiplication / 10;
                // Append last digit to the current result.
                currentResult.add(multiplication % 10);
            }

            if (carry != 0) {
                currentResult.add(carry);
            }
            return currentResult;
        }

        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }

            StringBuilder firstNumber = new StringBuilder(num1);
            StringBuilder secondNumber = new StringBuilder(num2);

            // Reverse both the numbers.
            firstNumber.reverse();
            secondNumber.reverse();

            // For each digit in secondNumber, multipy the digit by firstNumber and
            // store the multiplication result (reversed) in results.
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            for (int i = 0; i < secondNumber.length(); ++i) {
                results.add(
                        multiplyOneDigit(firstNumber, secondNumber.charAt(i), i)
                );
            }

            // Add all the results in the results array, and store the sum in the answer string.
            StringBuilder answer = sumResults(results);

            // answer is reversed, so reverse it to get the final answer.
            answer.reverse();
            return answer.toString();
        }
    }
}
