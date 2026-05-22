package com.happy.alg;

public class LeetCode91DecodeWays {
    // 91. Decode Ways
    /**
     *ou have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
     *
     * "1" -> 'A'
     *
     * "2" -> 'B'
     *
     * ...
     *
     * "25" -> 'Y'
     *
     * "26" -> 'Z'
     *
     * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
     *
     * For example, "11106" can be decoded into:
     *
     * "AAJF" with the grouping (1, 1, 10, 6)
     * "KJF" with the grouping (11, 10, 6)
     * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
     * Note: there may be strings that are impossible to decode.
     *
     * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
     *
     * The test cases are generated so that the answer fits in a 32-bit integer.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "12"
     *
     * Output: 2
     *
     * Explanation:
     *
     * "12" could be decoded as "AB" (1 2) or "L" (12).
     *
     * Example 2:
     *
     * Input: s = "226"
     *
     * Output: 3
     *
     * Explanation:
     *
     * "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     *
     * Example 3:
     *
     * Input: s = "06"
     *
     * Output: 0
     *
     * Explanation:
     *
     * "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.
     *
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s contains only digits and may contain leading zero(s).
     * */

    //dp dp[i] represent  times to decode the string
    // dp[i] = 0 if string.chatAt(i) = 0
    // dp[i] = dp[i-1]{'chatAt(i)'=[0--9]} + dp[i-2]{'chatAt(i)'=[0--6] && 'chatAt(i-1)'=[1-2]}
    static void main() {
        System.out.println(numDecodings("2101"));
        System.out.println(numDecodings("10"));
        System.out.println(numDecodings("1123"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("06"));
        System.out.println(numDecodings("12"));
    }

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        /**
         * dp [0] = 1 是为了让动态规划「递推公式」能正确计算，是人为定义的「基准条件」，不是真实意义的解码方式
         * dp[i]：字符串前 i 个字符的解码方法总数
         * dp[1]：第 1 个字符的解码数
         * dp[2]：前 2 个字符的解码数
         * dp[0]：前 0 个字符（空字符串）的解码数
         * 举个最关键的例子：s = "12"
         * 我们要算 dp[2]（前 2 个字符的解码数）：
         * 最后 1 位 "2" 合法 → dp[2] += dp[1]
         * 最后 2 位 "12" 合法 → dp[2] += dp[0]
         * 正确答案："12" 有 2 种解码（1+2 / 12）
         * 如果 dp[0] = 1：
         * dp[2] = dp[1] + dp[0] = 1 + 1 = 2 ✅ 正确
         * 如果 dp[0] = 0：
         * dp[2] = 1 + 0 = 1 ❌ 错误
         * 3. 通俗理解：dp [0] = 1 是「空路径计数」
         * 动态规划里，dp[0] = 1 是数学上的占位符，意思是：
         * 当前面 0 个字符都处理完时，有一种「空的方法」来完成这一步
         * 它不是真的解码方式，只是为了让：
         * 两位数字组合解码时
         * 公式能自洽、正确累加
         * 就像数学里：0! = 1 一样，是定义出来让公式成立的。
         * 总结一句话:
         * 现实意义：空字符串 dp[0] = 0
         * 动态规划公式：必须定义 dp[0] = 1，否则无法正确计算两位组合的情况
         * 这是 DP 里非常经典的边界初始化技巧，不是逻辑错误，是为了递推成立。
         * */
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    public static int numDecodings2(String s) {
        int n=s.length();
        int[] dp=new int[n+1];
        dp[n]=1;
        for(int i=n-1;i>=0;i--)
            if(s.charAt(i)!='0') {
                dp[i]=dp[i+1];
                if(i<n-1&&(s.charAt(i)=='1'||s.charAt(i)=='2'&&s.charAt(i+1)<'7'))
                    dp[i]+=dp[i+2];
            }
        return dp[0];
    }

    public static  int numDecodings_error(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0' ) return 0;
        if (s.length() == 1) return 1;
        if (s.length() > 1 ){
            int len = s.length();
            int[] dp = new int[len+1];
            dp[0] = 0;
            for (int i = 1; i <= len; i++) {
                if (i < 2) {
                    dp[1] = s.charAt(i-1)!='0'?1:0;
                }else{
                    if (s.charAt(i-1) ==  '0'){
                        //601,1101,2208
                        if (s.charAt(i-2) <= '2' && s.charAt(i-2) >= '1'){
                            if (i-2 == 0) {
                                dp[2] = 1;
                            }else{
                                dp[i] = dp[i-2];
                            }
                        }else{
                            dp[i] = 0;
                        }
                    }else if (s.charAt(i-1) >=  '1'  && s.charAt(i-1) <= '6'){
                        if (s.charAt(i-2) <= '2' && s.charAt(i-2) >= '1'){
                            dp[i] = dp[i-1]+1;
                        }else{
                            dp[i] = dp[i-1];
                        }
                    }else if (s.charAt(i-1) >=  '7'  && s.charAt(i-1) <= '9'){
                        if (s.charAt(i-2) == '1'){
                            dp[i] = dp[i-1]+1;
                        }else{
                            dp[i] = dp[i-1];
                        }
                    }
                }
            }
            return dp[len];
        }
        return 0;
    }
}
//具体的范式：
//Solution{
//    /* initial conditions */
//    dp[0] = ??
//       :
//
//    /* bottom up method */
//    foreach( i ){
//        dp[i] = COMBINE dp[i-1] and dp[i-2] ;
//    }
//
//    /* Return */
//    return dp[last];
//}
