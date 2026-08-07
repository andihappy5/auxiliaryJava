package com.happy.alg;

public class LeetCode200 {
    static class Solution200 {
        public int numIslands(char[][] grid) {
            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);//遍历的时候，同一座岛屿全部都涂黑了，如果再有grid[i][j] == 1，那就是另外的一座岛娱了
                        res++;
                    }
                }
            }
            return res;
        }

        public void dfs(char[][] grid,int r,int c){
            //判断 base 坐标
            // 如果坐标(r,c)超出了网格的范围，直接返回
            if(!inArea(grid,r,c)){
                return ;
            }

            //如果这个格子不是岛屿
            if(grid[r][c] != '1'){
                return ;
            }
            grid[r][c]='2';//标记为已经已经便利过了
            //访问上，下，左，右
            dfs(grid,r-1,c);
            dfs(grid,r+1,c);
            dfs(grid,r,c-1);
            dfs(grid,r,c+1);
        }

        //判断是否在网格中
        boolean inArea(char[][] grid,int r,int c){
            return 0<=r && r < grid.length && 0 <=c &&
                    c < grid[0].length;
        }
    }
}
