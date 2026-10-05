package com.happy.hankerrank;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Recursions {
    public static void main(String[] args) {
//        List<String> list = generateAngleBracketSequences(1);
//        System.out.println(list);
//
//        list = generateAngleBracketSequences(2);
//        System.out.println(list);
//        list = generateAngleBracketSequences(3);
//        System.out.println(list);

        List<Integer> list = List.of(2, 3, 5);
        List tmp = findCombinationsByWeightIndices(list, 8);
        System.out.println(tmp);
    }

    public static long getAutoSaveInterval(int n) {
        BigInteger previous = BigInteger.ONE;
        BigInteger current = BigInteger.valueOf(2);
        if (n == 0) {
            return previous.longValue();
        }
        for (int i = 2; i <= n; i++) {
            BigInteger next = previous.add(current);
            previous = current;
            current = next;
        }
        return current.longValue();
    }

    public static List<List<Integer>> findCombinationsByWeightIndices2(
            List<Integer> weights, int capacity) {
        return explore(weights.size() - 1, capacity, weights);
    }

    private static List<List<Integer>> explore(int index, int remaining, List<Integer> weights) {
        List<List<Integer>> result = new ArrayList<>();
        if (remaining < 0 || index < 0) {
            return result;
        }
        if (remaining == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        // First: do not use the current index.
        result.addAll(explore(index - 1, remaining, weights));
        // Second: use the current index; allow repeated use.
        List<List<Integer>> subResult = explore(index, remaining - weights.get(index), weights);
        for (List<Integer> combination : subResult) {
            List<Integer> copy = new ArrayList<>(combination);
            copy.add(index);
            result.add(copy);
        }
        return result;
    }



    public static List<List<Integer>> findCombinationsByWeightIndices(List<Integer> weights, int capacity) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinationsByWeightIndices(weights,0,0,capacity,new ArrayList<>(),result);
        return result;
    }

    public static void findCombinationsByWeightIndices(List<Integer> weights,int from,int sum,int capacity,List<Integer> tmp,List<List<Integer>> result){
        if(sum == capacity){
            result.add(new ArrayList<>(tmp));
            return;
        }
        if(from > weights.size()-1 || sum > capacity ){
            return;
        }
        tmp.add(from);
        findCombinationsByWeightIndices(weights,from,sum+weights.get(from),capacity,tmp,result);
        tmp.remove(tmp.size()-1);
        findCombinationsByWeightIndices(weights,from+1,sum,capacity,tmp,result);
    }


    public static List<String> generateAngleBracketSequences(int n) {
        List<String> result = new ArrayList<>();
        generateAngleBracketSequences(n,0,0,new StringBuilder(),result);
        return result;
    }

    public static void generateAngleBracketSequences(int n,int left,int right,StringBuilder tmp,List<String> result){
        if(left > n || right > n || left < right){
            return;
        }
        if(left == right && n == left){
            result.add(tmp.toString());
            return;
        }
        tmp.append("<");
        generateAngleBracketSequences(n,left+1,right,tmp,result);
        tmp.deleteCharAt(tmp.length()-1);
        tmp.append(">");
        generateAngleBracketSequences(n,left,right+1,tmp,result);
        tmp.deleteCharAt(tmp.length()-1);
    }
}
