package com.happy.day;

import java.util.Arrays;

public class Day202604292 {

    public static void main(String[] args) {
        Day202604292 d = new Day202604292();
        System.out.println(d.exist(new char[][]{
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        },"AAB"));

//        System.out.println(d.exist(new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                 {'A','D','E','E'}
//        },"ABCCE"));
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(used[i], false);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(exist(board,i,j,0,word,used)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean exist(char[][] board, int i, int j, int index, String word, boolean[][] used){
        if(index == word.length()){
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || index > word.length() ||  board[i][j] != word.charAt(index)){
            return false;
        }

        if( board[i][j] == word.charAt(index)){
            index = index + 1;
            used[i][j] = true;
            if(index == word.length()){
                return true;
            }

            //不能后退，The same letter cell may not be used more than once.
            if( (i+1 < used.length && !used[i+1][j] && exist(board,i+1,j,index,word,used)) ||
             (i-1 >= 0 &&!used[i-1][j] && exist(board,i-1,j,index,word,used)) ||
              (j+1 < used[0].length && !used[i][j+1] && exist(board,i,j+1,index,word,used)) ||
             (j-1 >= 0 && !used[i][j-1] && exist(board,i,j-1,index,word,used))){
                return  true;
            }
            used[i][j] = false;
        }
        return false;
    }
}
