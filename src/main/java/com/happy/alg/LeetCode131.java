package com.happy.alg;

import java.util.ArrayList;
import java.util.List;

public class LeetCode131 {
    /**
     *
     131. Palindrome Partitioning
     Medium
     Topics
     premium lock icon
     Companies
     Given a string s, partition s such that every substring of the partition is a palindrome.
     Return all possible palindrome partitioning of s.



     Example 1:

     Input: s = "aab"
     Output: [["a","a","b"],["aa","b"]]
     Example 2:

     Input: s = "a"
     Output: [["a"]]


     Constraints:

     1 <= s.length <= 16
     s contains only lowercase English letters.

     * */

    /**
     * all backtracking problems are composed by these three steps : choose ,explore , unchoose
     * so for this problem, you need to know:
     *
     * choose what? For this problem, we choose each substring.
     * how to explore? For this problem, we do the same thing to the remained string.
     * unchoose Do the opposite operation of choose.
     * Let's take this problem as an example:
     * 1.Define helper(): Usually we need a helper funcition in backtracking problem,
     * to accept more parameters.
     * 2.Parameters: Usually we need the following parameters
     *
     *     1. The object you are working on:  For this problem is String s.
     *     2. A start index or an end index which indicate which part you are working on:
     *     For this problem, we use substring to indicate the start index.
     *     3. A step result, to remember current choose and then do unchoose :
     *     For this problem, we use List<String> step.
     *     4. A final result, to remember the final result. Usually when we add,
     *     we use 'result.add(new ArrayList<>(step))' instead of 'result.add(step)',
     *     since step is reference passed.
     *     We will modify step later, so we need to copy it and add the copy to the result;
     *
     * 3.Base case: The base case defines when to add step into result, and when to return.
     * 4.Use for-loop : Usually we need a for loop to iterate though the input String s,
     * so that we can choose all the options.
     * 5.Choose : In this problem, if the substring of s is palindrome,
     * we add it into the step, which means we choose this substring.
     * 6.Explore : In this problem, we want to do the same thing to the remaining substring.
     * So we recursively call our function.
     * 7.Un-Choose : We draw back, remove the chosen substring, in order to try other options.
     * */

    static void main() {
        System.out.println(new LeetCode131().partition("aab"));
        System.out.println(new LeetCode131().partition("a"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        dfs(result,path,0,s);
        return result;
    }

    /***/
    private void dfs(List<List<String>> result, List<String> path, int i, String s) {
        // special case to return
        if (i == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        //main logic
        for (int j = i+1; j <= s.length(); j++) {
            //choose different subString
            String sub =  s.substring(i,j);
            //valid sub is Palindrome
            if (valid(sub)) {
                path.add(sub);
                dfs(result,path,j,s);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean valid(String sub) {
        int i = 0,j = sub.length()-1;
        if(i==j) return true;
        while (i < j) {
            if (sub.charAt(i++) != sub.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
