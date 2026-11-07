package com.happy.hankerrank;

public class Staircaseproblem {

    public static void main(String[] args) {
        // int n = 5; // Example number of steps
        // int ways = climbStairs(n);
        // System.out.println("Number of ways to climb " + n + " steps: " + ways);
        drawStair(4);
    }
    
    public static void drawStair(int size) {
        int level = size - 1;
        for (int i = 0; i < size; i++) {
            StringBuilder s = new StringBuilder();
            for (int k = 0; k < level; k++) {
                s.append(" ");
            }
            for (int k = 0; k < size - level; k++) {
                s.append("#");
            }
            level -= 1;
            System.out.println(s.toString());
        }
    }
}
