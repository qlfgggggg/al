package qiaolf.leetcode;


/**
 * @Description: 最长公共子序列
 * Author:qiaolf
 * @Date:2021/4/3 20:05
 **/
public class solution1143_2 {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1==null || text2 == null || text2.length()==0 || text1.length()==0){
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int dp[][]=new int[str1.length][str2.length];
        dp[0][0] = str1[0]==str2[0]? 1:0;
        for (int i = 0; i < str1.length; i++) {
            dp[i][0]= str1[i]==str2[0]? 1 : dp[i-1][0];
        }
        for (int i = 0; i < str2.length; i++) {
            dp[0][i]=str1[0]==str2[i] ? 1 : dp[0][i-1];
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                //绝对不考虑i,可能考虑j
                int p1 = dp[i-1][j];
                //绝对不考虑j,可能考虑i
                int p2 = dp[i][j-1];
                //i和j都考虑
                int p3 = str1[i]==str2[j] ? (1+dp[i-1][j-1]) : 0;
                dp[i][j]=Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[str1.length-1][str2.length-1];
    }
}
