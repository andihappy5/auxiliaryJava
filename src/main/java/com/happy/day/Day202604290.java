package com.happy.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day202604290 {
    public static void main(String[] args){
        System.out.println("Keep,Happy!");
        Day202604290 obj = new Day202604290();
        System.out.println(obj.solveNQueens(4));
    }

    class Solution {
        public volatile int count= 0;
        public int totalNQueens(int n) {
            char[][] queen  = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(queen[i], '.');
            }
            //backtracking solve the problem
            solveNQueens(queen,0,n);
            return count;
        }

        private void solveNQueens(char[][] queen,int curRow, int n) {
            //finish condition
            if (curRow == n){
                count++;
                return;
            }
            // main logic
            for (int i = 0; i < n; i++){
                // check  (curRow，i) 是否合适，如果不合适则跳过，如果合适则放置，进行下一行的调用
                queen[curRow][i] = 'Q';
                if (isValidQueen(queen,curRow,i)){
                    solveNQueens(queen,curRow+1,n); //进行下一步
                }
                queen[curRow][i] = '.';//下一步返回后，需要回退到初始的状态
            }
        }

        private boolean isValidQueen(char[][] queen, int curRow, int column) {
            //首先确定(curRow，i) 已经被设置为了 Q,只需要判断当前节点即可
            if (curRow == 0){ //如果是第一行，第一个 queen 直接返回 true
                return true;
            }
            //当前列 column
            for (int j = 0; j < queen.length; j++){
                if (curRow!= j && queen[j][column] =='Q'){
                    return false;
                }
            }
            //当前行 row,已经不需要判断了，因为已经判断过了，根据 Row 进行的放置
            //对角线进行判断
            for (int j = 1; j < queen.length; j++){
                if (column+j < queen.length && curRow+j < queen[curRow].length){
                    if (queen[curRow+j][column+j]=='Q'){
                        return false;
                    }
                }
                if (column-j>= 0 && curRow-j >=0){

                    if(queen[curRow-j][column-j]=='Q'){
                        return false;
                    }
                }
                if (column+j < queen.length && curRow-j >=0 ){

                    if (queen[curRow-j][column+j]=='Q'){
                        return false;
                    }

                }
                if (column-j >=0 && curRow+j < queen.length){

                    if (queen[curRow+j][column-j]=='Q'){
                        return false;
                    }
                }
            }

            return true;
        }
    }

    //-------------------------------------
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] queen  = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(queen[i], '.');
        }
        //backtracking solve the problem
        solveNQueens(queen,result,0,n);
        return  result;
    }

    private void solveNQueens(char[][] queen, List<List<String>> result, int curRow, int n) {
        //finish condition
        if (curRow == n){
            result.add(construct(queen));
            return;
        }
        // main logic
        for (int i = 0; i < n; i++){
            // check  (curRow，i) 是否合适，如果不合适则跳过，如果合适则放置，进行下一行的调用
            queen[curRow][i] = 'Q';
            if (isValidQueen(queen,curRow,i)){
                solveNQueens(queen,result,curRow+1,n); //进行下一步
            }
            queen[curRow][i] = '.';//下一步返回后，需要回退到初始的状态
        }
    }

    private boolean isValidQueen(char[][] queen, int curRow, int column) {
        //首先确定(curRow，i) 已经被设置为了 Q,只需要判断当前节点即可
        if (curRow == 0){ //如果是第一行，第一个 queen 直接返回 true
            return true;
        }
        //当前列 column
        for (int j = 0; j < queen.length; j++){
            if (curRow!= j && queen[j][column] =='Q'){
                return false;
            }
        }
        //当前行 row,已经不需要判断了，因为已经判断过了，根据 Row 进行的放置
        //对角线进行判断
        for (int j = 1; j < queen.length; j++){
            if (column+j < queen.length && curRow+j < queen[curRow].length){
                System.out.println((curRow+j)+" "+(column+j));
                if (queen[curRow+j][column+j]=='Q'){
                    return false;
                }
            }
            if (column-j>= 0 && curRow-j >=0){
                System.out.println((curRow-j)+" "+(column-j));
                if(queen[curRow-j][column-j]=='Q'){
                    return false;
                }
            }
            if (column+j < queen.length && curRow-j >=0 ){
                System.out.println((curRow-j)+" "+(column+j));
                if (queen[curRow-j][column+j]=='Q'){
                    return false;
                }

            }
            if (column-j >=0 && curRow+j < queen.length){
                System.out.println((curRow+j)+" "+(column-j));
                if (queen[curRow+j][column-j]=='Q'){
                    return false;
                }
            }
        }

        return true;
    }

    //construct return data
    private List<String> construct(char[][] queen) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < queen.length; i++) {
            list.add(new String(queen[i]));
        }
        return list;
    }

    private boolean meetCondition(String[][] queens, int m, int n) {
        for (int i = 0; i < queens[m].length; i++) {
            if (i!= n&& queens[m][i].equals("Q")) {
                return false;
            }
        }
        for (int i = 0; i < queens.length; i++) {
            if (i!= m && queens[i][n].equals("Q")) {
                return false;
            }
        }
        for (int i = m,j=n; i >= 0 && j >=0 ; i--,j--) {
            if ((i!=m || j !=n) && queens[i][j].equals("Q")) {
                return false;
            }
        }
        for (int i = m,j=n; i < queens[m].length && j < queens.length ; i++,j++) {
            if ((i!=m || j !=n)&& queens[i][j].equals("Q")) {
                return false;
            }
        }
        return true;
    }
}
