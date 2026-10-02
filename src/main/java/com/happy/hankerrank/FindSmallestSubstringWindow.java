package com.happy.hankerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class FindSmallestSubstringWindow {

    /*
     * Complete the 'findSmallestSubstringWindow' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY patterns
     *  2. STRING S
     */

    static class Triple{
        int start;
        int end;
        String pat;

        public Triple(int start, int end, String pat){
            this.end=end;
            this.pat=pat;
            this.start=start;
        }
    }

    public static List<Integer> findSmallestSubstringWindow(List<String> patterns, String S) {
        List<Triple> lis= new ArrayList<>();
        HashMap<String,Integer> mp= new HashMap<>();
        for (int i=0;i<patterns.size();i++){
            lis.addAll(findOccurrences(S,patterns.get(i)));
            //HashSet to maintain distinct count
            mp.put(patterns.get(i),0);
        }

        Collections.sort(lis,(Triple a,Triple b)-> Integer.compare(a.start,b.start));
        int distinct=mp.size();
        int diff=Integer.MAX_VALUE;
        int start=-1;
        int end=-1;
        int i=0,j=0;
        while(j<lis.size()){

            String temp=lis.get(j).pat;
            mp.put(temp,mp.get(temp)+1);
            if (mp.get(temp)==1) distinct--;

            if (distinct==0){
                while(distinct==0){
                    if (diff > (lis.get(j).end- lis.get(i).start)){
                        diff= lis.get(j).end- lis.get(i).start;
                        start=lis.get(i).start;
                        end=lis.get(j).end;
                    }

                    mp.put(lis.get(i).pat,mp.get(lis.get(i).pat)-1);
                    if (mp.get(lis.get(i).pat)==0) distinct++;
                    i++;
                }
            }
            j++;

        }

        return new ArrayList<>( Arrays.asList(start,end));
    }

    private static List<Triple> findOccurrences(String text, String pattern) {
        List<Triple> result = new ArrayList<>();
        if (pattern.isEmpty()) {
            return result;
        }
        int start = text.indexOf(pattern);
        while (start != -1) {
            result.add(new Triple(
                    start,
                    start + pattern.length() - 1,
                    pattern
            ));
            start = text.indexOf(pattern, start + 1);
        }
        return result;
    }
}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int patternsCount = Integer.parseInt(bufferedReader.readLine().trim());
        List<String> patterns = IntStream.range(0, patternsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        String S = bufferedReader.readLine();

        List<Integer> result = FindSmallestSubstringWindow.findSmallestSubstringWindow(patterns, S);

        System.out.println(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
        );

        bufferedReader.close();
    }
}