package com.happy.day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day202604293 {

    public static void main(String[] args) {
        Day202604293 d = new Day202604293();
        System.out.println(d.grayCode(4));
        System.out.println(d.restoreIpAddresses("25525511135"));
    }

    public static  List<String> restoreIpAddresses(String s) {
        List<String> result  = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        restoreIpAddresses(s,0,sb,result);
        return result;
    }

    private static boolean isIp(String ip){
        if(ip.length() > 3 || ip.length() == 0) return false;
        if(ip.length() > 1 && ip.charAt(0) == '0') return false;
        if(ip.length() > 0 && Integer.parseInt(ip) > 255) return false;
        return true;
    }

    // s input
    // i cur index
    // sb temp result
    // result
    private static void restoreIpAddresses(String s, int i, StringBuilder sb, List<String> result) {
        if (i >= s.length()) {
            result.add(sb.toString());
        }
        for (int j = i; j < sb.length(); j++) { //每一个位置1，,2，,3
            for (int k = 1; k <= 3; k++) {
                if(j+k <= s.length()){
                    String str = sb.substring(j, j+k);
                    if (isIp(str)) {
                        sb.append(str).append('.');
                        restoreIpAddresses(s,j+k,sb,result);
                    }
                }
            }
        }
    }


    //----------------------------------------------------------------------

    int limit; //maximum number
    List<Integer> ans ; //return answer
    boolean flg;

    public List<Integer> grayCode(int n) {
        limit = (int) Math.pow(2, n);
        ans = new ArrayList<>(); //return answer
        flg = false;
        ArrayList<Integer> bit = new ArrayList<>(limit);
        for (int i = 0; i < n; i++) {
            bit.add(0);
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);
        maker(res, set, bit);

        return ans;
    }

    //ord: current path
    //visited values
    public void maker(ArrayList<Integer> ord, Set<Integer> set, ArrayList<Integer> bit) {
        //meet condition,
        if (ord.size() == limit) {
            ans.addAll(ord);
            flg=true;
            return;
        }

        //bit size init 00000000000... (limit)
        for (int i = 0; i < bit.size(); i++) {
            int bt = bit.get(i);
            int ans = bt == 1 ? 0 : 1;//最近的0，变为 1
            bit.set(i, ans);//设定完毕，bit 的第 i 位变为 1
            int dec = decimalConv(bit); //二进制变为整数，去重
            if (!set.contains(dec)) {
                set.add(dec);
                ord.add(dec);
                maker(ord, set, bit);
                ord.remove(ord.size() - 1);
                set.remove(dec);
            }
            if (flg)
                return;
            bit.set(i, bt);
        }
    }

    public int decimalConv(List<Integer> bit) {
        int pow = 0;
        int sum = 0;
        for (int ele : bit) {
            sum += (ele * (int) Math.pow(2, pow++));
        }
        return sum;
    }
}
