package qiaolf.zuoshen.class19;

/**
 * @Description: 最长公共子序列  ------样本对应模型 ，找样本模型的最后下标
 * Author:qiaolf
 * @Date:2021/7/21 23:01
 **/
public class code3_longestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length()==0 || text2.length()==0 || text1==null || text2==null){
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        return process(chars1,chars2,chars1.length-1,chars2.length-1);
    }

    /**
     * 样本对应模型：就是要用到最后下标索引
     * @param chars1
     * @param chars2
     * @param i
     * @param j
     * @return
     */
    private int process(char[] chars1, char[] chars2, int i, int j) {
        if (i==0 && j==0){
            return chars1[i]==chars2[j] ? 1:0;
        }else if (i==0){
            if (chars1[i]==chars2[j]){
                return 1;
            }else{
                return process(chars1,chars2,i,j-1);
            }
        }else if (j==0){
            if (chars1[i]==chars2[j]){
                return 1;
            }else{
                return process(chars1,chars2,i-1,j);
            }
        }else{//i！=0 j！=0
            //绝对不考虑i,可能考虑j
            int p1 = process(chars1,chars2,i-1,j);
            //绝对不考虑j,可能考虑i
            int p2 = process(chars1,chars2,i,j-1);
            //即考虑i也考虑j
//            int p3 = process(chars1,chars2,i,j);//这样会陷入递归的死循环
            int p3 = chars1[i]==chars2[j] ? (1+process(chars1,chars2,i-1,j-1)) : 0;//为什么是0呢？其实这个0不重要，主要是0的结果不影响，当可能考虑i，j时两者不相等，这第三种情况就不考虑了
            return Math.max(p1,Math.max(p2,p3));
        }
    }



    //动态规划来解题
    public int longestCommonSubsequence2(String text1, String text2) {
        if(text1.length()==0 || text2.length()==0 || text1==null || text2==null){
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int M = chars1.length;
        int N = chars2.length;
        int dp[][] = new int[M][N];
        dp[0][0]= chars1[0]==chars2[0] ? 1 : 0;
        for (int j = 1; j < N; j++) {
            if (chars1[0]==chars2[j]){
                dp[0][j]=1;
            }else{
                dp[0][j]=dp[0][j-1];
            }
        }
        for (int i = 1; i < M; i++) {
            if (chars2[0]==chars1[i]){
                dp[i][0]=1;
            }else{
                dp[i][0]=dp[i-1][0];
            }
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int p1 = dp[i-1][j];
                int p2 = dp[i][j-1];
                int p3 = chars1[i]==chars2[j] ? (1+dp[i-1][j-1]) : 0;
                dp[i][j]=Math.max(p1,Math.max(p2,p3));
            }
        }

        return dp[M-1][N-1];
    }
}
