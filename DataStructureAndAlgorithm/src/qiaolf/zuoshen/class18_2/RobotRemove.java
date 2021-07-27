package qiaolf.zuoshen.class18_2;

/**
 * @Description: 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 * Author:qiaolf
 * @Date:2021/7/6 10:53
 **/
public class RobotRemove {
    public static void main(String[] args) {
        int N=5,M=4,K=4,P=2;
        System.out.println(way1(N,M,K,P));
        System.out.println(way2(N,M,K,P));
        System.out.println(way3(N,M,K,P));
    }


    public static int way1(int N,int cur,int step,int aim){
        //不符合情况
        if (N<=1 || cur<1 || cur > N || step<=0 || aim <1 || aim > N){
            return -1;
        }

        return process1(N,cur,step,aim);
    }

    /**
     *   返回有多少种到达目的地的方法
     * @param N
     * @param cur  当前位置
     * @param step   剩余步数
     * @param aim    目标位置
     * @return
     */
    public static int process1(int N,int cur,int step,int aim){
        //base  看看是否还有剩余步数
        if (step==0){
            return aim==cur ? 1 : 0;
        }
        //如果当前位置在1，只能向右右走
        if (cur==1){
            return process1(N,cur+1,step-1,aim);
        }else if (cur==N){//当前位置在N只能向左走
            return process1(N,cur-1,step-1,aim);
        }else{//如果当前位置在中间可以向两边走
            return process1(N,cur+1,step-1,aim) + process1(N,cur-1,step-1,aim);
        }
    }

    public static int way2(int N,int cur,int step,int aim){
        if (N<=1 || cur<1 || cur > N || step<=0 || aim <1 || aim > N){
            return -1;
        }
        /**
         * 动态规划：缓存：dp
         */
        int dp[][] = new int[N+1][step+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= step; j++) {
                dp[i][j]=-1;
            }
        }
        return process2(N,cur,step,aim,dp);
    }

    private static int process2(int n, int cur, int step, int aim, int[][] dp) {
        //之前算过
        if (dp[cur][step]!=-1){
            return dp[cur][step];
        }
        //之前没算过
        int ans = 0;
        if (step==0){
            ans = aim==cur ? 1:0;
        }else if (cur==1){
            ans = process2(n,cur+1,step-1,aim,dp);
        }else if (cur==n){
            ans = process2(n,cur-1,step-1,aim,dp);
        }else{
            ans = process2(n,cur+1,step-1,aim,dp)+process2(n,cur-1,step-1,aim,dp);
        }
        dp[cur][step]=ans;
        return ans;
    }


    public static int way3(int N,int cur,int step,int aim){
        if (N<=1 || cur<1 || cur > N || step<=0 || aim <1 || aim > N){
            return -1;
        }
        int[][] dp = new int[N+1][step+1];
        //当前位置是目标位置且还有0步可以走，这就是一个情况
        dp[aim][0]=1;
        //为什么不从step==0开始填，因为还有0步，且不是在目标位置，所以没有这种情况
        for (int i = 1; i <= step; i++) {
            //当当前位置为1的情况下
            dp[1][i]=dp[2][i-1];
            //当前位置为普通位置的情况下
            for (int j = 2; j < N ; j++) {
                dp[j][i]= dp[j-1][i-1]+dp[j+1][i-1];
            }
            dp[N][i]=dp[N-1][i-1];
        }
        return dp[cur][step];
    }
}
