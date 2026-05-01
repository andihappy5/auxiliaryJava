package com.happy.day;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day202604260 {
    public void main(String[] args) {
        System.out.println(isValidSudoku(new String[][]{
                  {"5", "3", ".", ".", "7", ".", ".", ".", "."}
                , {"6", ".", ".", "1", "9", "5", ".", ".", "."}
                , {".", "9", "8", ".", ".", ".", ".", "6", "."}
                , {"8", ".", ".", ".", "6", ".", ".", ".", "3"}
                , {"4", ".", ".", "8", ".", "3", ".", ".", "1"}
                , {"7", ".", ".", ".", "2", ".", ".", ".", "6"}
                , {".", "6", ".", ".", ".", ".", "2", "8", "."}
                , {".", ".", ".", "4", "1", "9", ".", ".", "5"}
                , {".", ".", ".", ".", "8", ".", ".", "7", "9"}}));

        System.out.println(isValidSudoku(new String[][]{
                {".",".","4",".",".",".","6","3","."},
                {".",".",".",".",".",".",".",".","."},
                {"5",".",".",".",".",".",".","9","."},

                {".",".",".","5","6",".",".",".","."},
                {"4",".","3",".",".",".",".",".","1"},
                {".",".",".","7",".",".",".",".","."},

                {".",".",".","5",".",".",".",".","."},
                {".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".","."}
        }));
    }

    class Solution {
        public void solveSudoku(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            solve(board);
        }

        private boolean solve(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        for (char c = '1'; c <= '9'; c++) { //trial
                            if (isValidSudoku(board, i, j, c)) {
                                board[i][j] = c;
                                if (solve(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return false;
        }

        private boolean isValidSudoku(char[][] board, int i, int j, char c) {
            for (int k = 0; k < 9; k++) {
                if (board[k][j] != '.' && board[k][j] == c)
                    return false;
                if (board[i][k] != '.' && board[i][k] == c)
                    return false;
                if (board[3 * (i / 3) + i / 3][3 * (j / 3) + i % 3] != '.' &&
                        board[3 * (i / 3) + i / 3][3 * (j / 3) + i % 3] == c)
                    return false; //check 3*3 block
            }
            return true;
        }
    }


    public static void solveSudokuError(String[][] board) {
        Map<String,Boolean> mapi = new HashMap<String,Boolean> (); //iv
        Map<String,Boolean> mapj = new HashMap<String,Boolean> (); //jv
        Map<String,Boolean> mapij = new HashMap<String,Boolean> (); //ijv
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != "."){
                    String iv = String.valueOf(i)+"i"+board[i][j];
                    String jv = String.valueOf(j)+"j"+board[i][j];
                    String ij =  String.valueOf(i/3) + String.valueOf(j/3) +"ij" +board[i][j];
                    mapi.put(iv,true);
                    mapj.put(jv,true);
                    mapij.put(ij,true);
                }
            }
        }
//        solveSudoku(mapi,mapj,mapij,board,0,0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == ".") {
                    for (char c = '1'; c <= '9'; c++) {
                        String ivc = String.valueOf(i)+"i"+c;
                        String jvc = String.valueOf(j)+"j"+c;
                        String ijc =  String.valueOf(i/3) + String.valueOf(j/3) +"ij" +board[i][j];
                        if(mapi.containsKey(ivc)){continue;}
                        if(mapj.containsKey(jvc)){continue;}
                        if(mapij.containsKey(ijc)){continue;}
                        board[i][j] = String.valueOf(c);
                    }
                }
            }
        }
    }


    public static boolean isValidSudoku(String[][] board) {
        Map<String,Boolean> mapi = new HashMap<String,Boolean> (); //iv
        Map<String,Boolean> mapj = new HashMap<String,Boolean> (); //jv
        Map<String,Boolean> mapij = new HashMap<String,Boolean> (); //ijv
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == ".") {
                    continue;
                }else{
                    String iv = String.valueOf(i)+"i"+board[i][j];
                    if (mapi.containsKey(iv)) {
                        return false;
                    }
                    mapi.put(iv,true);

                    String jv = String.valueOf(j)+"j"+board[i][j];
                    if (mapj.containsKey(jv)) {
                        return false;
                    }
                    mapj.put(jv,true);

                    String ij =  String.valueOf(i/3) + String.valueOf(j/3) +"ij" +board[i][j];
                    if (mapij.containsKey(ij)) {
                        return false;
                    }
                    mapij.put(ij,true);
                }
            }
        }
        return true;
    }
}
