package com.happy.alg;

public class LeetCode058 {
    static void main() {
        System.out.println(lengthOfLastWord("a "));
    }
    // 58. Length of Last Word
    // Given a string s consisting of words and spaces, return the length of the last word in the string.
    //
    //A word is a maximal substring consisting of non-space characters only.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "Hello World"
    //Output: 5
    //Explanation: The last word is "World" with length 5.
    //Example 2:
    //
    //Input: s = "   fly me   to   the moon  "
    //Output: 4
    //Explanation: The last word is "moon" with length 4.
    //Example 3:
    //
    //Input: s = "luffy is still joyboy"
    //Output: 6
    //Explanation: The last word is "joyboy" with length 6.
    //
    //
    //Constraints:
    //
    //1 <= s.length <= 104
    //s consists of only English letters and spaces ' '.
    //There will be at least one word in s.

    public static int lengthOfLastWord(String s) {
        boolean isL = false;
        int from = s.length()-1;
        for(int i = s.length()-1;i >=0;i--){
            if(!isL && s.charAt(i) != ' '){
                isL = true;
                from = i;
            }
            if(isL && s.charAt(i) == ' '){
                return from-i;
            }
        }
        return s.substring(0,from+1).length();
    }
}
