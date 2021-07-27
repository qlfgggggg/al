package qiaolf.leetcode;


/**
 * @Description: 最长公共子序列
 * Author:qiaolf
 * @Date:2021/4/3 20:05
 **/
public class solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1==null || text2 == null || text2.length()==0 || text1.length()==0){
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        return process(chars1,chars2,chars1.length-1,chars2.length-1);
    }

    public int process(char[] str1,char[] str2,int i,int j){
        if (i==0 && j==0){
            return str1[i]==str2[j] ? 1:0;
        }else if (i==0){
            if (str1[i]==str2[j]){
                return 1;
            }else{
                return process(str1,str2,i,j-1);
            }
        }else if (j==0){
            if (str1[i]==str2[j]){
                return 1;
            }else{
                return process(str1,str2,i-1,j);
            }
        }else{
            //绝对不考虑i,可能考虑j
            int p1 = process(str1,str2,i-1,j);
            //绝对不考虑j,可能考虑i
            int p2 = process(str1,str2,i,j-1);
            //i和j都考虑
            int p3 = str1[i]==str2[j] ? (1+process(str1,str2,i-1,j-1)) : 0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }
}
