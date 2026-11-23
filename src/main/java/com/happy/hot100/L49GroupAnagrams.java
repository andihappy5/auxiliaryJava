package com.happy.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L49GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> result = new ArrayList<List<String>>();
        if(null == strs || strs.length == 0) return result;
        Map<String,List<String>> keyToArray = new HashMap<>();
        for(int i=0;i< strs.length;i++){
            char[] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            List<String> v = keyToArray.getOrDefault(new String(tmp),new ArrayList<String>());
            v.add(strs[i]);
            keyToArray.put(new String(tmp),v);
        }
        result.addAll(keyToArray.values());
        return result;
    }
}
