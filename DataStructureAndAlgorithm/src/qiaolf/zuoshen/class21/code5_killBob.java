package qiaolf.zuoshen.class21;

/**
 * @Description: 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 * Author:qiaolf
 * @Date:2021/7/27 11:25
 **/
public class code5_killBob {

    public static double kill(int N,int M,int row,int col,int k){
        //总的概率   也就是一共有多少走法
        double pow = Math.pow(4, k);
        //能狗存活的走法
        int lives = process(N,M,row,col,k);
        return (double)lives/pow;
    }

    private static int process(int N, int M, int x, int y, int rest) {
        //越界的话直接死亡,边界是0-(N-1) ,0-(M-1)
        if (x<0 || x>N-1 || y<0 || y>M-1){
            return 0;
        }
        //没有越界
        if (rest==0){
           return 1;
        }
        int ways = 0;
        ways+=process(N,M,x+1,y,rest-1);
        ways+=process(N,M,x-1,y,rest-1);
        ways+=process(N,M,x,y+1,rest-1);
        ways+=process(N,M,x,y-1,rest-1);
        return ways;
    }

    public static double kill1(int N,int M,int row,int col,int k){
        if (row<0 || row>=N || col<0 || col>=M){
            return 0;
        }
        int dp[][][] = new int[N][M][k+1];
        for (int i = 0; i <N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0]=1;
            }
        }
        for (int i = 1; i <=k; i++) {
            int ways = 0;
            for (int j = 0; j < N; j++) {
                for (int l = 0; l < M; l++) {
                    ways = dp[j+1][l][i-1];
                    ways = dp[j-1][l][i-1];
                    ways = dp[j][l+1][i-1];
                    ways = dp[j][l-1][i-1];
                    dp[j][l][i]=ways;
                }
            }
        }
        return (double) dp[row][col][k]/Math.pow(4,k);
    }
}
