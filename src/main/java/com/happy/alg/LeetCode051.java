package com.happy.alg;

import java.util.*;

public class LeetCode051 {

    public static void main(String[] rags) {
//        getCarNum("/Users/didi/xiamen");
        List<List<String>> result = queue(5);
        for (List<String> value :
                result) {
            for (String value1 :
                    value) {
                System.out.println();
                System.out.println(value1);
            }
            System.out.println();
        }
    }

    /**
     * backTracking
     * */
    public static List<List<String>> queue(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queues = new int[n];
        Arrays.fill(queues, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> slid1 = new HashSet<Integer>();
        Set<Integer> slid2 = new HashSet<Integer>();
        backtracking(result, queues, 0, n, columns, slid1, slid2);
        return result;
    }

    private static void backtracking(List<List<String>> result, int[] queues, int row, int n, Set<Integer> columns, Set<Integer> slid1, Set<Integer> slid2) {
        if (row == n) {
            List<String> tmp = generate(queues, n);
            result.add(tmp);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!columns.contains(i) && !slid1.contains(row + i) && !slid2.contains(row - i)) {
                columns.add(i);
                slid1.add(row + i);
                slid2.add(row - i);
                // put the queue
                queues[row] = i;
                backtracking(result, queues, row + 1, n, columns, slid1, slid2);
                queues[row] = -1;
                columns.remove(i);
                slid1.remove(row + i);
                slid2.remove(row - i);
            }
        }
    }

    private static List<String> generate(int[] queues, int n) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] queueindex = new char[n];
            Arrays.fill(queueindex, '.');
            queueindex[queues[i]] = 'Q';
            result.add(new String(queueindex));
        }
        return result;
    }

    static class Solution {
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

        //construct return data
        private List<String> construct(char[][] queen) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < queen.length; i++) {
                list.add(new String(queen[i]));
            }
            return list;
        }
    }
}
