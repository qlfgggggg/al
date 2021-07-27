package qiaolf.zuoshen.class18;

/**
 * @Description: 从暴力递归到动态规划
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 * Author:qiaolf
 * @Date:2021/4/2 9:44
 **/
public class RobotMove {
    public static void main(String[] args) {
        int N=4,M=2,K=4,P=2;
        System.out.println(way1(N,M,K,P));
        System.out.println(way2(N,M,K,P));
        System.out.println(way3(N,M,K,P));
    }
    public static int way1(int N,int M,int K,int P){
        //输入参数不规范
        if (N<2 || M<1 || M>N || P>N || P<1 || K<1){
            return -1;
        }
        return process1(N,M,K,P);
    }

    /**
     *
     * @param N
     * @param cur
     * @param step
     * @param aim
     * @return
     */
    public static int process1(int N,int cur,int step,int aim){
        //没有步数需要走了,判断当前位置是否是目标位置
        if (step==0){
            return cur==aim ? 1 : 0;
        }
        //step>0   还有步数要走
        //在位置1自己能往2位置走
        if (cur==1){
            return process1(N,cur+1,step-1,aim);
        }
        //在N位置只能往N-1位置走
        else if (cur==N){
            return process1(N,cur-1,step-1,aim);
        }
        //在中间位置
        else{
            return process1(N,cur+1,step-1,aim)+process1(N,cur-1,step-1,aim);
        }
    }

    public static int way2(int N,int M,int K,int P){
        //输入参数不规范
        if (N<2 || M<1 || M>N || P>N || P<1 || K<1){
            return -1;
        }
        //我们根据有多少位置和多少步来做个动态规划数组
        int dp[][]=new int[N+1][K+1];
        for (int i = 0; i <=N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j]=-1;
            }
        }
        //dp就是缓存表
        //dp[cur][step]==-1   ->   process2()之前没有算过该位置
        //dp[cur][step]!=-1   ->   process2()之前算过该位置
        //(N+1)*(K+1)
        return process2(N,M,K,P,dp);
    }
    //cur 范围1-N
    //step  范围0-K
    //求机器人在当前cur位置，剩余step步，要走到目标位置aim的情况下要走多少步

    private static int process2(int N, int cur, int step, int aim,int dp[][]) {
        //如果之前算过了，就直接将该位置走到目标位置有多少种走法直接返回
        if (dp[cur][step]!=-1){
            return dp[cur][step];
        }
        //之前没算过
        int ans = 0;
        if (step==0){
            ans = aim==cur ? 1:0;
        }else if (cur==1){
            ans = process2(N,cur+1,step-1,aim,dp);
        }else if (cur==N){
            ans = process2(N,cur-1,step-1,aim,dp);
        }else{
            ans = process2(N,cur+1,step-1,aim,dp)+process2(N,cur-1,step-1,aim,dp);
        }
        //记录在 机器人在当前cur位置，剩余step步，要走到目标位置aim的情况下要走多少步
        dp[cur][step]= ans;
        return ans;
    }

    public static int way3(int N,int M,int K,int P){
        //输入参数不规范
        if (N<2 || M<1 || M>N || P>N || P<1 || K<1){
            return -1;
        }
        int[][] dp = new int[N+1][K+1];
        //在目标位置，已经走完了方案是1,第0列已经填好了，剩下的位置都是0
        dp[P][0]=1;
        //因为第0列都已经填好了，从第1列开始填
        //为什么列在外层for循环？自己画图
        for (int step = 1; step <= K; step++) {
            //第一行
            dp[1][step]=dp[2][step-1];
            //普通行
            for (int cur = 2; cur <N ; cur++) {
                dp[cur][step]=dp[cur-1][step-1]+dp[cur+1][step-1];
            }
            //第N行
            dp[N][step]=dp[N-1][step-1];
        }
        return dp[M][K];
    }
}
