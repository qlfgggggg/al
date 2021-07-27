package qiaolf.zuoshen.class20;

/**
 * @Description: 马走日  马踏棋盘问题
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * Author:qiaolf
 * @Date:2021/7/22 16:13
 **/
public class code1_horseTaQiPan {

    public static void main(String[] args) {
        int a = 4,b=7,k=5;
        System.out.println(jump(a,b,k));
        System.out.println(jump2(a,b,k));
    }

    //马从(0,0)位置到(a,b)位置走k步共有多少种方法
    public static int jump(int a,int b,int k){
        return process(0,0,k,a,b);
    }

    /**
     * 从(x,y)位置经过rest步到达(a,b)
     * @param x
     * @param y
     * @param rest
     * @param a
     * @param b
     * @return
     */
    private static int process(int x, int y, int rest, int a, int b) {
        //如果已经跳出棋盘了，就直接这种情况就不存在能够到达(a,b)的情况了
        if (y<0 || x<0 || x>9 || y>8){
            return 0;
        }
        //如果还剩0步要判断当前位置是否是(a,b)
        if (rest==0){
            return (x==a && y==b) ? 1 : 0;
        }
        int ways = 0;
        ways+=process(x+1,y+2,rest-1,a,b);
        ways+=process(x+2,y+1,rest-1,a,b);
        ways+=process(x+2,y-1,rest-1,a,b);
        ways+=process(x+1,y-2,rest-1,a,b);
        ways+=process(x-1,y-2,rest-1,a,b);
        ways+=process(x-2,y-1,rest-1,a,b);
        ways+=process(x-2,y+1,rest-1,a,b);
        ways+=process(x-1,y+2,rest-1,a,b);
        return ways;
    }

    public static int jump2(int a,int b,int k){
        int[][][] dp = new int[10][9][k+1];//相当于x,y,z坐标
        //第z==0层只有在x==a,y==b时才会是1
        dp[a][b][0] = 1;
        for (int rest = 1; rest <=k ; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = 0;
                    ways+=pick(dp,x+1,y+2,rest-1);
                    ways+=pick(dp,x+2,y+1,rest-1);
                    ways+=pick(dp,x+2,y-1,rest-1);
                    ways+=pick(dp,x+1,y-2,rest-1);
                    ways+=pick(dp,x-1,y-2,rest-1);
                    ways+=pick(dp,x-2,y-1,rest-1);
                    ways+=pick(dp,x-2,y+1,rest-1);
                    ways+=pick(dp,x-1,y+2,rest-1);
                    dp[x][y][rest]=ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp,int x,int y,int rest){
        if (x < 0 || x>9 || y<0 || y>8){
            return 0;
        }
        return dp[x][y][rest];
    }

}
