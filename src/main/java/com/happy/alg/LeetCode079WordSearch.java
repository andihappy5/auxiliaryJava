package com.happy.alg;

import java.util.Arrays;

public class LeetCode079WordSearch {
    /*
     * Given an m x n grid of characters board and a string word, return true if
     * word exists in the grid.
     * 
     * The word can be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring. The same
     * letter cell may not be used more than once.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
     * = "ABCCED"
     * Output: true
     * Example 2:
     * 
     * 
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
     * = "SEE"
     * Output: true
     * Example 3:
     * 
     * 
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
     * = "ABCB"
     * Output: false
     * 
     * 
     * Constraints:
     * 
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board and word consists of only lowercase and uppercase English letters.
     * 
     * 
     * Follow up: Could you use search pruning to make your solution faster with a
     * larger board?
     */

    static class Solution {

        public static void main(String[] args) {
            System.out.println(new Solution().exist(new char[][] {
                    { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' }
            }, "SEE"));
        }

        public boolean exist(char[][] board, String word) {
            boolean[][] used = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                Arrays.fill(used[i], false);
            }
            // begine from every position in board
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (exist(board, i, j, 0, word, used)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean exist(char[][] board, int i, int j, int index, String word, boolean[][] used) {
            if (index == word.length()) {
                return true;
            }
            if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || index > word.length()
                    || board[i][j] != word.charAt(index)) {
                return false;
            }

            if (board[i][j] == word.charAt(index)) {
                index = index + 1;
                used[i][j] = true;
                if (index == word.length()) {
                    return true;
                }

                // 不能后退，The same letter cell may not be used more than once.
                if ((i + 1 < used.length && !used[i + 1][j] && exist(board, i + 1, j, index, word, used)) ||
                        (i - 1 >= 0 && !used[i - 1][j] && exist(board, i - 1, j, index, word, used)) ||
                        (j + 1 < used[0].length && !used[i][j + 1] && exist(board, i, j + 1, index, word, used)) ||
                        (j - 1 >= 0 && !used[i][j - 1] && exist(board, i, j - 1, index, word, used))) {
                    return true;
                }
                used[i][j] = false;
            }
            return false;
        }
    }

}
