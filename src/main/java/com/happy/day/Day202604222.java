package com.happy.day;

public class Day202604222 {
    public static void main() {
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(findMin(new int[]{3,1,2}));
        System.out.println(findMin(new int[]{2,1}));
        System.out.println(findMin(new int[]{3,4,5,1,2}));
    }


    //nums may contain duplicates
    public int findMin2(int[] nums) {
        if (nums.length <= 1) return nums[0];
        int from = 0, to = nums.length - 1;
        while (from < to) {
            if (nums[from] < nums[to]) {
                return nums[from];
            }

            if (from == to) {
                return nums[from];
            }
            if (from+1 == to){
                return nums[from] < nums[to]? nums[from]:nums[to];
            }
            int mid = from + (to - from) / 2;
            if (nums[mid] == nums[from]) {
                from++;
                continue;
            }
            if (nums[mid] == nums[to]) {
                to--;
                continue;
            }
            //[3,4,5,1,2]
            //[3,4,1,2]
            //[2,1]
            if (nums[mid] >= nums[from]) {
                from = mid+1;
            } else if (nums[mid] < nums[to]) {
                // [5,0,1,2]
                to = mid;
            }
        }
        return nums[from];
    }



    public static int findMin(int[] nums) {
        if (nums.length <= 1) return nums[0];
        int from = 0, to = nums.length - 1;
        while (from < to) {
            if (nums[from] < nums[to]) {
                return nums[from];
            }

            if (from == to) {
                return nums[from];
            }
            if (from+1 == to){
                return nums[from] < nums[to]? nums[from]:nums[to];
            }
            int mid = from + (to - from) / 2;
            //[3,4,5,1,2]
            //[3,4,1,2]
            //[2,1]
            if (nums[mid] >= nums[from]) {
                from = mid+1;
            } else if (nums[mid] < nums[to]) {
                // [5,0,1,2]
                to = mid;
            }
        }
        return nums[from];
    }
}
