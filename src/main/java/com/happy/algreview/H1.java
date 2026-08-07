package com.happy.algreview;

import java.util.HashMap;
import java.util.Map;

public class H1 {



    public static String[] NumberPattern(int n) {
        // Write your code here
        String[] result = new String[n];
        for(int i = 1; i <= n; i++){
            int pre = i;
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < i; j++){
                sb.append(pre);
                pre++;
            }
            result[i-1]=sb.toString();
        }
        return result;
    }


//    class Solution2 {
//        public static BinaryTreeNode<Integer> binaryTreeToBst(BinaryTreeNode<Integer> root) {
//            // Write your code here.
//            List<Integer> values = new ArrayList<>();
//            // add inorder of the tree
//            inorderCollection(root,values);
//            // sort
//            Collections.sort(values);
//            // range
//            int[] idx = {0};
//            inorderFill(root,values,idx);
//            return root;
//        }
//
//        // inorder of the tree
//        private static void inorderCollection(BinaryTreeNode<Integer> root,List<Integer> values){
//            if(root == null) return;
//            inorderCollection(root.left,values);
//            values.add(root.data);
//            inorderCollection(root.right,values);
//        }
//
//        private static void inorderFill(BinaryTreeNode<Integer> root,List<Integer> values,int[] idx){
//            if(root == null) return ;
//            inorderFill(root.left,values,idx);
//            root.data = values.get(idx[0]);
//            idx[0]++;
//            inorderFill(root.right,values,idx);
//        }
//    }


    public class Solution1 {
        public static long[] maxProductCount(int arr[], int n) {
            // key mul value,value: nums to the value
            HashMap<Long,Integer> productMap = new HashMap<Long,Integer>();
            for(int i =0 ; i < n;i++){
                for(int j = i+1;j < n;j++){
                    long prod = (long)arr[i]*arr[j];
                    productMap.put(prod,productMap.getOrDefault(prod,0)+1);
                }
            }

            long bestPro =0;
            long maxCount =0;
            for(Map.Entry<Long,Integer> entry : productMap.entrySet()){
                long p = entry.getKey();
                int cnt = entry.getValue();
                long c = (long)cnt*(cnt-1)/2;//num of quadruple
                if(c ==0) continue;
                if(c > maxCount || (c == maxCount && p < bestPro)){
                    maxCount = c;
                    bestPro = p;
                }
            }

            if(maxCount == 0){return new long[]{0};}
            else {return new long[]{bestPro,maxCount};}
        }
    }
}
