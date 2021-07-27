package qiaolf.zuoshen.class19;

/**
 * @Description: 最长回文子序列   ----可以转换为最长公共子序列   将该子序列反转   得到两个子序列    ----样本对应模型
 * 范围尝试模型
 *
 *
 * Author:qiaolf
 * @Date:2021/7/22 16:07
 **/
public class code4_longestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        if(s==null || s.length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        return process(0,chars.length-1,chars);
    }

    private int process(int l, int r, char[] chars) {
        if (l==r){
            return 1;
        }else if (l==r-1){
            return chars[l]==chars[r] ? 2 :1;
        }else{
            int p1 = process(l+1,r-1,chars);
            int p2 = process(l,r-1,chars);
            int p3 = process(l+1,r,chars);
            int p4 = chars[l]==chars[r] ? (2+process(l+1,r-1,chars)) : 0;
            return Math.max(Math.max(p1,p2),Math.max(p3,p4));
        }
    }


    public int longestPalindromeSubseq2(String s) {
        if(s==null || s.length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int dp[][] = new int[N][N];
        //l<=r时可以的，l>r不可以
        //先找对角线
        dp[N-1][N-1] = 1;
        for (int i = 0; i < N-1; i++) {
            dp[i][i]=1;
        }
        for (int i = 0; i < N-1; i++) {
            dp[i][i+1] = chars[i]==chars[i+1] ? 2 : 1;
        }
        for (int i = N-3; i>=0 ; i--) {
            for (int j = i+2; j <=N-1; j++) {
                int p1 = dp[i][j-1];
                int p2 = dp[i+1][j];
                //不考虑这个情况了p4 = dp[i+1][j-1]   因为这种情况(左下)肯定比左 和 下 小
                int p3 = 0;
                if (chars[i]==chars[j]){
                    p3 = 2+dp[i+1][j-1];
                }
                dp[i][j]=Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[0][N-1];
    }
}
